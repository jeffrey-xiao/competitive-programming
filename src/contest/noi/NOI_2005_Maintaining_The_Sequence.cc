#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 500100
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

int readInt() {
  bool minus = false;
  int result = 0;
  char ch;
  ch = getchar();
  while (true) {
    if (ch == '-')
      break;
    if (ch >= '0' && ch <= '9')
      break;
    ch = getchar();
  }
  if (ch == '-')
    minus = true;
  else
    result = ch - '0';
  while (true) {
    ch = getchar();
    if (ch < '0' || ch > '9')
      break;
    result = result * 10 + (ch - '0');
  }
  if (minus)
    return -result;
  else
    return result;
}

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

struct Node {
  Node *left, *right;
  int p, sz, value, same;
  int maxPrefix, maxSuffix, maxSubarray, sum;
  bool rev;

  Node(int value) {
    this->value = value;
    this->p = (rand() << 16) + rand();
    this->sz = 1;
    this->same = 1 << 30;
    this->rev = false;
    this->left = nullptr;
    this->right = nullptr;
  }
};

int getSize(Node *n) {
  return n == nullptr ? 0 : n->sz;
}

int getSum(Node *n) {
  return n == nullptr ? 0 : n->sum;
}

Node *reverse(Node *n) {
  if (n == nullptr)
    return n;
  n->rev = !n->rev;
  int maxPrefix = n->maxPrefix;
  int maxSuffix = n->maxSuffix;
  Node *left = n->left;
  Node *right = n->right;
  n->maxPrefix = maxSuffix;
  n->maxSuffix = maxPrefix;
  n->left = right;
  n->right = left;
  return n;
}

Node *makeSame(Node *n, int c) {
  if (n == nullptr)
    return n;
  n->value = c;
  n->same = c;
  n->sum = getSize(n) * c;
  n->maxPrefix = max(n->sum, c);
  n->maxSuffix = max(n->sum, c);
  n->maxSubarray = max(n->sum, c);

  return n;
}

Node *push(Node *n) {
  if (n == nullptr)
    return n;

  if (n->same != 1 << 30) {
    n->left = makeSame(n->left, n->same);
    n->right = makeSame(n->right, n->same);
    n->same = 1 << 30;
  }

  if (n->rev) {
    n->left = reverse(n->left);
    n->right = reverse(n->right);
    n->rev = false;
  }

  return n;
}

Node *update(Node *n) {
  if (n == nullptr)
    return n;

  n->sz = getSize(n->left) + getSize(n->right) + 1;

  n->sum = n->value + getSum(n->left) + getSum(n->right);
  n->maxSubarray = n->value;
  n->maxPrefix = n->sum;
  n->maxSuffix = n->sum;

  n->maxPrefix = max(n->maxPrefix, getSum(n->left) + n->value);
  n->maxSuffix = max(n->maxSuffix, getSum(n->right) + n->value);

  if (n->left != nullptr) {
    n->maxPrefix = max(n->maxPrefix, n->left->maxPrefix);
    n->maxSuffix = max(n->maxSuffix, getSum(n->right) + n->value + max(0, n->left->maxSuffix));
    n->maxSubarray = max(n->maxSubarray, n->left->maxSubarray);
    n->maxSubarray = max(n->maxSubarray, n->value + n->left->maxSuffix);
  }

  if (n->right != nullptr) {
    n->maxSuffix = max(n->maxSuffix, n->right->maxSuffix);
    n->maxPrefix = max(n->maxPrefix, getSum(n->left) + n->value + max(0, n->right->maxPrefix));
    n->maxSubarray = max(n->maxSubarray, n->right->maxSubarray);
    n->maxSubarray = max(n->maxSubarray, n->value + n->right->maxPrefix);
  }

  if (n->left != nullptr && n->right != nullptr)
    n->maxSubarray =
        max(n->maxSubarray, n->value + max(max(0, n->left->maxSuffix) + n->right->maxPrefix,
                                           n->left->maxSuffix + max(0, n->right->maxPrefix)));
  else if (n->left == nullptr && n->right == nullptr)
    n->maxPrefix = n->maxSuffix = n->value;

  return n;
}

pair<Node *, Node *> split(Node *n, int k, int lower) {
  if (n == nullptr)
    return {nullptr, nullptr};

  int key = lower + getSize(n->left) + 1;

  n = push(n);
  // n = update(n);

  if (k >= key) {
    auto res = split(n->right, k, lower + getSize(n->left) + 1);
    n->right = res.first;
    res.first = n;
    res.first = update(res.first);
    res.second = update(res.second);
    return res;
  } else {
    auto res = split(n->left, k, lower);
    n->left = res.second;
    res.second = n;
    res.first = update(res.first);
    res.second = update(res.second);
    return res;
  }
}

Node *merge(Node *a, Node *b) {
  if (a == nullptr)
    return b;
  if (b == nullptr)
    return a;

  a = push(a);
  b = push(b);

  if (a->p > b->p) {
    a->right = merge(a->right, b);
    a->right = update(a->right);
    a = update(a);
    return a;
  } else {
    b->left = merge(a, b->left);
    b->left = update(b->left);
    b = update(b);
    return b;
  }
}

Node *root = nullptr;

int N, M;

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  N = readInt();
  M = readInt();

  for (int i = 0; i < N; i++) {
    int x = readInt();
    root = merge(root, new Node(x));
  }

  for (int i = 0; i < M; i++) {
    char in[16];
    scanf("%s", in);

    if (in[0] == 'I') {
      Node *add = nullptr;
      int pos = readInt(), tot = readInt();

      for (int j = 0; j < tot; j++) {
        int x = readInt();
        add = merge(add, new Node(x));
      }

      auto res = split(root, pos, 0);
      root = merge(res.first, merge(add, res.second));
    } else if (in[0] == 'D') {
      int pos = readInt(), tot = readInt();
      pos--;
      auto res2 = split(root, pos + tot, 0);
      auto res1 = split(res2.first, pos, 0);
      root = merge(res1.first, res2.second);
    } else if (in[0] == 'M' && in[2] == 'K') {
      int pos = readInt(), tot = readInt(), c = readInt();
      pos--;
      auto res2 = split(root, pos + tot, 0);
      auto res1 = split(res2.first, pos, 0);
      root = merge(res1.first, merge(makeSame(res1.second, c), res2.second));
    } else if (in[0] == 'R') {
      int pos = readInt(), tot = readInt();
      pos--;
      auto res2 = split(root, pos + tot, 0);
      auto res1 = split(res2.first, pos, 0);
      root = merge(res1.first, merge(reverse(res1.second), res2.second));
    } else if (in[0] == 'G') {
      int pos = readInt(), tot = readInt();
      pos--;
      if (tot == 0) {
        printf("0\n");
      } else {
        auto res2 = split(root, pos + tot, 0);
        auto res1 = split(res2.first, pos, 0);
        printf("%d\n", res1.second->sum);
        root = merge(res1.first, merge(res1.second, res2.second));
      }
    } else if (in[0] == 'M' && in[2] == 'X') {
      printf("%d\n", root->maxSubarray);
    }
  }

  return 0;
}
