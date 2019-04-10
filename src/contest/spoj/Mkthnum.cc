#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 100001
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int n, q;
vector<vector<int>> tree(4 * SIZE);
int a[SIZE];

vector<int> combine(vector<int> x, vector<int> y) {
  vector<int> res;
  int j = 0;
  int k = 0;
  for (int i = 0; i < x.size() + y.size(); i++) {
    if (k == y.size() || (j != x.size() && x[j] < y[k]))
      res.pb(x[j++]);
    else
      res.pb(y[k++]);
  }
  return res;
}

void build(int n, int l, int r) {
  if (l == r) {
    tree[n].pb(a[l - 1]);
    return;
  }
  int mid = (l + r) >> 1;
  build(n << 1, l, mid);
  build(n << 1 | 1, mid + 1, r);
  tree[n] = combine(tree[n << 1], tree[n << 1 | 1]);
}
int query(int n, int l, int r, int ql, int qr, int val) {
  if (ql == l && qr == r) {
    int lo = 0, hi = r - l;
    assert(tree[n].size() == r - l + 1);
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if (tree[n][mid] <= val)
        lo = mid + 1;
      else
        hi = mid - 1;
    }
    return lo;
  }
  int mid = (l + r) >> 1;
  if (qr <= mid)
    return query(n << 1, l, mid, ql, qr, val);
  else if (ql > mid)
    return query(n << 1 | 1, mid + 1, r, ql, qr, val);
  else
    return query(n << 1, l, mid, ql, mid, val) + query(n << 1 | 1, mid + 1, r, mid + 1, qr, val);
}
int query(int l, int r, int k) {
  int lo = -1000000000, hi = 1000000000;
  while (lo <= hi) {
    int mid = lo + (hi - lo) / 2;
    if (query(1, 1, n, l, r, mid) < k)
      lo = mid + 1;
    else
      hi = mid - 1;
  }
  return lo;
}

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  rint(n), rint(q);
  for (int i = 0; i < n; i++)
    rint(a[i]);
  build(1, 1, n);

  for (int i = 0; i < q; i++) {
    int x, y, z;
    scanf("%d%d%d", &x, &y, &z);
    printf("%d\n", query(x, y, z));
  }

  return 0;
}
