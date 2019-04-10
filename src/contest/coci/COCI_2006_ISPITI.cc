#include <bits/stdc++.h>

using namespace std;

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rchar(x) scanf(" %c", &(x))
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 200001
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

struct person {
  int a, b, index, sorted;
};

struct event {
  bool type;
  int i;
};

int tree[SIZE * 3];
event e[SIZE];
person p[SIZE];

int N, i;

bool pair_cmp(const person &X, const person &Y) {
  if (X.b == Y.b)
    return X.a < Y.a;
  return X.b < Y.b;
}

bool index_cmp(const person &X, const person &Y) {
  return X.index < Y.index;
}

void update(int n, int l, int r, int i) {
  if (l > p[i].sorted || p[i].sorted > r)
    return;
  if (l == r) {
    tree[n] = i;
    return;
  }
  int mid = m(l, r);
  update(l(n), l, mid, i);
  update(r(n), mid + 1, r, i);
  if (tree[l(n)] == -1) {
    tree[n] = tree[r(n)];
  } else if (tree[r(n)] == -1) {
    tree[n] = tree[l(n)];
  } else if (p[tree[l(n)]].a < p[tree[r(n)]].a) {
    tree[n] = tree[r(n)];
  } else {
    tree[n] = tree[l(n)];
  }
}

int query(int n, int l, int r, int i) {
  if (r <= p[i].sorted)
    return -1;
  if (tree[n] == -1)
    return -1;
  if (p[tree[n]].a < p[i].a)
    return -1;
  if (l == r)
    return tree[n];
  int mid = m(l, r);
  int res = query(l(n), l, mid, i);
  if (res != -1)
    return res;
  return query(r(n), mid + 1, r, i);
}

int main() {
  rint(N);
  for (int x = 0; x < N; x++) {
    char c;
    rchar(c);
    if (c == 'D') {
      int a, b;
      rint(a), rint(b);
      p[i].a = a;
      p[i].b = b;
      p[i].index = i;
      e[x].type = true;
      e[x].i = i++;
    } else {
      int a;
      rint(a);
      e[x].type = false;
      e[x].i = a - 1;
    }
  }
  sort(p, p + i, pair_cmp);
  for (int j = 0; j < i; j++) {
    p[j].sorted = j;
  }
  sort(p, p + i, index_cmp);
  memset(tree, -1, sizeof tree);
  for (int j = 0; j < N; j++) {
    if (e[j].type) {
      update(1, 0, i - 1, e[j].i);
    } else {
      int res = query(1, 0, i - 1, e[j].i);
      if (res == -1)
        printf("NE\n");
      else
        printf("%d\n", res + 1);
    }
  }
}
