#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 100100
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

struct node {
  vector<int> sorted;
  vector<int> prefix;
  int l, r;
};

node tree[3 * SIZE];
int seq[SIZE];
int n, q;

void build(int n, int l, int r) {
  tree[n].l = l;
  tree[n].r = r;
  if (l == r) {
    tree[n].sorted.pb(seq[l]);
    tree[n].prefix.pb(seq[l]);
    return;
  }
  int mid = m(l, r);
  build(l(n), l, mid);
  build(r(n), mid + 1, r);
  unsigned int i1 = 0, i2 = 0;
  vector<int> v1 = tree[l(n)].sorted;
  vector<int> v2 = tree[r(n)].sorted;
  while (i1 < v1.size() || i2 < v2.size()) {
    if (i1 == v1.size()) {
      tree[n].sorted.pb(v2[i2++]);
    } else if (i2 == v2.size()) {
      tree[n].sorted.pb(v1[i1++]);
    } else if (v1[i1] > v2[i2]) {
      tree[n].sorted.pb(v1[i1++]);
    } else {
      tree[n].sorted.pb(v2[i2++]);
    }
  }
  tree[n].prefix.pb(tree[n].sorted[0]);
  for (unsigned int x = 1; x < tree[n].sorted.size(); x++) {
    tree[n].prefix.pb(tree[n].sorted[x] + tree[n].prefix[x - 1]);
  }
}
int query(int n, int a, int b, int c) {
  if (tree[n].l == a && tree[n].r == b) {
    int lo = 0, hi = tree[n].sorted.size() - 1;
    while (lo <= hi) {
      int mi = lo + (hi - lo) / 2;
      if (tree[n].sorted[mi] >= c)
        lo = mi + 1;
      else
        hi = mi - 1;
    }
    return tree[n].prefix[hi];
  }
  int mid = m(tree[n].l, tree[n].r);
  if (b <= mid)
    return query(l(n), a, b, c);
  else if (a > mid)
    return query(r(n), a, b, c);
  return query(l(n), a, mid, c) + query(r(n), mid + 1, b, c);
}
int main() {
  scanf("%d", &n);
  for (int x = 1; x <= n; x++)
    scanf("%d", &seq[x]);
  build(1, 1, n);

  scanf("%d", &q);
  for (int x = 0; x < q; x++) {
    int a, b, c;
    scanf("%d%d%d", &a, &b, &c);
    printf("%d\n", query(1, a + 1, b + 1, c));
  }

  return 0;
}
