#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define EPS 1e-7
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 100000
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef double ld;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int N;
int a[SIZE], b[SIZE], va[SIZE], vb[SIZE];

ld getDist(ld t) {
  ld ret = 0;
  for (int i = 0; i < N; i++) {
    ld diff = (b[i] - a[i]) + t * (vb[i] - va[i]);
    ret += diff * diff;
  }
  return ret;
}

ld ternarySearch(ld lo, ld hi) {
  while (abs(hi - lo) >= EPS) {
    ld mid1 = (lo * 2 + hi) / 3.0;
    ld mid2 = (lo + hi * 2) / 3.0;

    if (getDist(mid1) >= getDist(mid2))
      lo = mid1;
    else
      hi = mid2;
  }
  return (lo + hi) / 2;
}

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  rint(N);

  for (int i = 0; i < N; i++)
    rint(a[i]);
  for (int i = 0; i < N; i++)
    rint(b[i]);
  for (int i = 0; i < N; i++)
    rint(va[i]);
  for (int i = 0; i < N; i++)
    rint(vb[i]);

  if (getDist(0.0) <= getDist(EPS))
    printf("0\n");
  else
    printf("%lf", ternarySearch(0.0, 1e10));

  return 0;
}
