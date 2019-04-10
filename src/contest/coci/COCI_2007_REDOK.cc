#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 250000
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int tree[4 * SIZE][10];
int lazy[4 * SIZE];

int n, m;
string in;

void shift(int (&a)[10], int num) {
  int res[10];
  for (int i = 0; i < 10; i++)
    res[(i + num) % 10] = a[i];
  for (int i = 0; i < 10; i++)
    a[i] = res[i];
}

void build(int n, int l, int r) {
  if (l == r) {
    tree[n][in[l] - '0']++;
    return;
  }
  int mid = (l + r) >> 1;
  build(n << 1, l, mid);
  build(n << 1 | 1, mid + 1, r);
  for (int i = 0; i < 10; i++)
    tree[n][i] = tree[n << 1][i] + tree[n << 1 | 1][i];
}
int query(int n, int l, int r, int ql, int qr) {
  if (l == ql && r == qr) {
    int res = 0;
    for (int i = 0; i < 10; i++)
      res += i * tree[n][i];
    return res;
  }
  if (lazy[n] != 0) {
    lazy[n << 1] += lazy[n];
    lazy[n << 1 | 1] += lazy[n];

    shift(tree[n << 1], lazy[n]);
    shift(tree[n << 1 | 1], lazy[n]);

    lazy[n] = 0;
  }
  int mid = (l + r) >> 1;
  if (qr <= mid)
    return query(n << 1, l, mid, ql, qr);
  else if (ql > mid)
    return query(n << 1 | 1, mid + 1, r, ql, qr);
  else
    return query(n << 1, l, mid, ql, mid) + query(n << 1 | 1, mid + 1, r, mid + 1, qr);
}

void update(int n, int l, int r, int ql, int qr, int val) {
  if (l == ql && r == qr) {
    lazy[n] += val;
    shift(tree[n], val);
    return;
  }
  if (lazy[n] != 0) {
    lazy[n << 1] += lazy[n];
    lazy[n << 1 | 1] += lazy[n];

    shift(tree[n << 1], lazy[n]);
    shift(tree[n << 1 | 1], lazy[n]);

    lazy[n] = 0;
  }
  int mid = (l + r) >> 1;
  if (qr <= mid)
    update(n << 1, l, mid, ql, qr, val);
  else if (ql > mid)
    update(n << 1 | 1, mid + 1, r, ql, qr, val);
  else {
    update(n << 1, l, mid, ql, mid, val);
    update(n << 1 | 1, mid + 1, r, mid + 1, qr, val);
  }
  for (int i = 0; i < 10; i++)
    tree[n][i] = tree[n << 1][i] + tree[n << 1 | 1][i];
}

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  rint(n);
  rint(m);
  cin >> in;
  in = " " + in;
  build(1, 1, n);

  for (int i = 0; i < m; i++) {
    int l, r;
    rint(l), rint(r);
    printf("%d\n", query(1, 1, n, l, r));
    update(1, 1, n, l, r, 1);
  }

  return 0;
}
