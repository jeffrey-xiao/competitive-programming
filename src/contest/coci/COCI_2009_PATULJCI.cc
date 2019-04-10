#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 300100
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

struct node {
  int l, r;
  int value, count;
};

struct state {
  int value, count;
  state(int value, int count) {
    this->value = value;
    this->count = count;
  }
};

node tree[4 * SIZE];
int A[SIZE];
int N, C, Q;

void build(int n, int l, int r) {
  tree[n].l = l;
  tree[n].r = r;
  if (l == r) {
    tree[n].value = A[l];
    tree[n].count = 1;
    return;
  }
  int mid = m(l, r);
  build(l(n), l, mid);
  build(r(n), mid + 1, r);
  if (tree[l(n)].value == tree[r(n)].value) {
    tree[n].count = tree[l(n)].count + tree[r(n)].count;
    tree[n].value = tree[l(n)].value;
  } else {
    if (tree[l(n)].count >= tree[r(n)].count) {
      tree[n].value = tree[l(n)].value;
      tree[n].count = tree[l(n)].count - tree[r(n)].count;
    } else {
      tree[n].value = tree[r(n)].value;
      tree[n].count = tree[r(n)].count - tree[l(n)].count;
    }
  }
}

state query(int n, int l, int r) {
  if (l == tree[n].l && r == tree[n].r) {
    return state(tree[n].value, tree[n].count);
  }
  int mid = m(tree[n].l, tree[n].r);
  if (r <= mid)
    return query(l(n), l, r);
  else if (l > mid)
    return query(r(n), l, r);
  state s1 = query(l(n), l, mid);
  state s2 = query(r(n), mid + 1, r);
  if (s1.value == s2.value)
    return state(s1.value, s1.count + s2.count);
  if (s1.count >= s2.count)
    return state(s1.value, s1.count - s2.count);
  return state(s2.value, s2.count - s1.count);
}

struct cmp {
  bool operator()(const state &s1, const state &s2) const {
    if (s1.value == s2.value)
      return s1.count < s2.count;
    return s1.value < s2.value;
  }
};
vector<state> s;
int bsearch(int value, int count, bool lower) {
  int lo = 0;
  int hi = N - 1;
  while (lo <= hi) {
    int mid = lo + (hi - lo) / 2;
    if (lower) {
      if (value < s[mid].value || (value == s[mid].value && count <= s[mid].count)) {
        hi = mid - 1;
      } else {
        lo = mid + 1;
      }
    } else {
      if (value < s[mid].value || (value == s[mid].value && count < s[mid].count)) {
        hi = mid - 1;
      } else {
        lo = mid + 1;
      }
    }
  }
  return hi;
}
int main() {
  rint(N);
  rint(C);

  for (int i = 1; i <= N; i++) {
    rint(A[i]);
    s.pb(state(A[i], i));
  }
  sort(s.begin(), s.end(), cmp());
  build(1, 1, N);
  rint(Q);
  for (int i = 1; i <= Q; i++) {
    int a, b;
    rint(a), rint(b);
    state ans = query(1, a, b);
    int num = bsearch(ans.value, b, false) - bsearch(ans.value, a, true);
    if (num > (b - a + 1) / 2) {
      printf("yes %d\n", ans.value);
    } else {
      printf("no\n");
    }
  }
}
