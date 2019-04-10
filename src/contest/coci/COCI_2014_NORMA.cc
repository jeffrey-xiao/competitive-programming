#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000000
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 500000
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

struct node {
  int l, m, M, lm, lM, mM, lmM;
  int lazyM, lazym, lazyl;
};

int add(int a, int b) {
  return a + b >= MOD ? a + b - MOD : a + b;
}

int sub(int a, int b) {
  return a >= b ? a - b : a - b + MOD;
}

int mul(int a, int b) {
  return (ll(a) * b) % MOD;
}

int n;
node tree[3 * SIZE];

void pushUp(int n) {
  tree[n].l = add(tree[n << 1].l, tree[n << 1 | 1].l);
  tree[n].m = add(tree[n << 1].m, tree[n << 1 | 1].m);
  tree[n].M = add(tree[n << 1].M, tree[n << 1 | 1].M);
  tree[n].mM = add(tree[n << 1].mM, tree[n << 1 | 1].mM);
  tree[n].lM = add(tree[n << 1].lM, tree[n << 1 | 1].lM);
  tree[n].lm = add(tree[n << 1].lm, tree[n << 1 | 1].lm);
  tree[n].lmM = add(tree[n << 1].lmM, tree[n << 1 | 1].lmM);
}

void pushDownLen(int n, int l, int r, int mid) {
  if (tree[n].lazyl == 0)
    return;

  tree[n << 1].l = add(tree[n << 1].l, mul(tree[n].lazyl, (mid - l + 1)));
  tree[n << 1 | 1].l = add(tree[n << 1 | 1].l, mul(tree[n].lazyl, (r - (mid + 1) + 1)));

  tree[n << 1].lM = add(tree[n << 1].lM, mul(tree[n].lazyl, tree[n << 1].M));
  tree[n << 1 | 1].lM = add(tree[n << 1 | 1].lM, mul(tree[n].lazyl, tree[n << 1 | 1].M));

  tree[n << 1].lm = add(tree[n << 1].lm, mul(tree[n].lazyl, tree[n << 1].m));
  tree[n << 1 | 1].lm = add(tree[n << 1 | 1].lm, mul(tree[n].lazyl, tree[n << 1 | 1].m));

  tree[n << 1].lmM = add(tree[n << 1].lmM, mul(tree[n].lazyl, tree[n << 1].mM));
  tree[n << 1 | 1].lmM = add(tree[n << 1 | 1].lmM, mul(tree[n].lazyl, tree[n << 1 | 1].mM));

  tree[n << 1].lazyl = add(tree[n << 1].lazyl, tree[n].lazyl);
  tree[n << 1 | 1].lazyl = add(tree[n << 1 | 1].lazyl, tree[n].lazyl);
  tree[n].lazyl = 0;
}

void pushDownMin(int n, int l, int r, int mid) {
  if (tree[n].lazym == 0)
    return;

  tree[n << 1].m = mul(tree[n].lazym, (mid - l + 1));
  tree[n << 1 | 1].m = mul(tree[n].lazym, (r - (mid + 1) + 1));

  tree[n << 1].mM = mul(tree[n].lazym, tree[n << 1].M);
  tree[n << 1 | 1].mM = mul(tree[n].lazym, tree[n << 1 | 1].M);

  tree[n << 1].lm = mul(tree[n].lazym, tree[n << 1].l);
  tree[n << 1 | 1].lm = mul(tree[n].lazym, tree[n << 1 | 1].l);

  tree[n << 1].lmM = mul(tree[n].lazym, tree[n << 1].lM);
  tree[n << 1 | 1].lmM = mul(tree[n].lazym, tree[n << 1 | 1].lM);

  tree[n << 1].lazym = tree[n << 1 | 1].lazym = tree[n].lazym;
  tree[n].lazym = 0;
}

void pushDownMax(int n, int l, int r, int mid) {
  if (tree[n].lazyM == 0)
    return;

  tree[n << 1].M = mul(tree[n].lazyM, (mid - l + 1));
  tree[n << 1 | 1].M = mul(tree[n].lazyM, (r - (mid + 1) + 1));

  tree[n << 1].mM = mul(tree[n].lazyM, tree[n << 1].m);
  tree[n << 1 | 1].mM = mul(tree[n].lazyM, tree[n << 1 | 1].m);

  tree[n << 1].lM = mul(tree[n].lazyM, tree[n << 1].l);
  tree[n << 1 | 1].lM = mul(tree[n].lazyM, tree[n << 1 | 1].l);

  tree[n << 1].lmM = mul(tree[n].lazyM, tree[n << 1].lm);
  tree[n << 1 | 1].lmM = mul(tree[n].lazyM, tree[n << 1 | 1].lm);

  tree[n << 1].lazyM = tree[n << 1 | 1].lazyM = tree[n].lazyM;
  tree[n].lazyM = 0;
}

void updateLen(int n, int l, int r, int ql, int qr, ll val) {
  if (l == ql && r == qr) {
    tree[n].l = add(tree[n].l, mul(val, (r - l + 1)));
    tree[n].lm = add(tree[n].lm, mul(val, tree[n].m));
    tree[n].lM = add(tree[n].lM, mul(val, tree[n].M));
    tree[n].lmM = add(tree[n].lmM, mul(val, tree[n].mM));
    tree[n].lazyl = add(tree[n].lazyl, val);
    return;
  }
  int mid = (l + r) >> 1;
  pushDownMax(n, l, r, mid);
  pushDownMin(n, l, r, mid);
  pushDownLen(n, l, r, mid);

  if (qr <= mid)
    updateLen(n << 1, l, mid, ql, qr, val);
  else if (ql > mid)
    updateLen(n << 1 | 1, mid + 1, r, ql, qr, val);
  else {
    updateLen(n << 1, l, mid, ql, mid, val);
    updateLen(n << 1 | 1, mid + 1, r, mid + 1, qr, val);
  }
  pushUp(n);
}

void updateMin(int n, int l, int r, int ql, int qr, ll val) {
  if (l == ql && r == qr) {
    tree[n].m = mul(val, (r - l + 1));
    tree[n].mM = mul(val, tree[n].M);
    tree[n].lm = mul(val, tree[n].l);
    tree[n].lmM = mul(val, tree[n].lM);
    tree[n].lazym = val;
    return;
  }
  int mid = (l + r) >> 1;
  pushDownMax(n, l, r, mid);
  pushDownMin(n, l, r, mid);
  pushDownLen(n, l, r, mid);

  if (qr <= mid)
    updateMin(n << 1, l, mid, ql, qr, val);
  else if (ql > mid)
    updateMin(n << 1 | 1, mid + 1, r, ql, qr, val);
  else {
    updateMin(n << 1, l, mid, ql, mid, val);
    updateMin(n << 1 | 1, mid + 1, r, mid + 1, qr, val);
  }
  pushUp(n);
}

void updateMax(int n, int l, int r, int ql, int qr, ll val) {
  if (l == ql && r == qr) {
    tree[n].M = mul(val, (r - l + 1));
    tree[n].mM = mul(val, tree[n].m);
    tree[n].lM = mul(val, tree[n].l);
    tree[n].lmM = mul(val, tree[n].lm);
    tree[n].lazyM = val;
    return;
  }
  int mid = (l + r) >> 1;
  pushDownMax(n, l, r, mid);
  pushDownMin(n, l, r, mid);
  pushDownLen(n, l, r, mid);

  if (qr <= mid)
    updateMax(n << 1, l, mid, ql, qr, val);
  else if (ql > mid)
    updateMax(n << 1 | 1, mid + 1, r, ql, qr, val);
  else {
    updateMax(n << 1, l, mid, ql, mid, val);
    updateMax(n << 1 | 1, mid + 1, r, mid + 1, qr, val);
  }
  pushUp(n);
}

int main() {
  // freopen("../../../in.txt", "r", stdin);
  // freopen("../../../out.txt", "w", stdout);

  rint(n);
  int ans = 0;
  stack<pi> lo;
  stack<pi> hi;
  for (int i = 1; i <= n; i++) {
    int val;
    rint(val);

    while (!lo.empty() && lo.top().first >= val)
      lo.pop();
    if (lo.empty())
      updateMin(1, 1, n, 1, i, val);

    else
      updateMin(1, 1, n, lo.top().second + 1, i, val);
    lo.push(mp(val, i));

    while (!hi.empty() && hi.top().first <= val)
      hi.pop();
    if (hi.empty())
      updateMax(1, 1, n, 1, i, val);
    else
      updateMax(1, 1, n, hi.top().second + 1, i, val);
    hi.push(mp(val, i));

    updateLen(1, 1, n, 1, i, 1);

    ans = add(ans, tree[1].lmM);
  }
  printf("%d\n", ans);

  return 0;
}
