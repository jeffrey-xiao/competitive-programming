#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define MAX_N 100000
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

static ll N, K;
static ll height[MAX_N];

double f(int i, double y) {
  return (sqrt(1 + 4 * height[i] / y) - 1) / 2;
}

int main() {
  freopen("tallbarn.in", "r", stdin);
  freopen("tallbarn.out", "w", stdout);

  scanf("%lld%lld", &N, &K);

  K -= N;

  for (int i = 0; i < N; i++)
    scanf("%lld", height + i);

  double lo = 0;
  double hi = 1e18;

  while ((hi - lo) > 1e-15) {
    double mid = (lo + hi) / 2;
    ll x = 0;
    for (int i = 0; i < N; i++)
      x += (ll)f(i, mid);
    if (x >= K)
      lo = mid;
    else
      hi = mid;
  }

  double ans = 0;
  ll x = 0;
  for (int i = 0; i < N; i++) {
    ans += 1.0 * height[i] / (1 + (ll)f(i, lo));
    x += (ll)f(i, lo);
  }

  printf("%lld", (ll)round(ans - (K - x) * lo));
  return 0;
}
