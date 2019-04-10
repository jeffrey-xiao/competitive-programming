#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define MAX_N 1000005
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int K, N;
ll val[MAX_N], dp[MAX_N];

ll compute(int n) {
  if (dp[n] != -1)
    return dp[n];
  if (n == 0)
    return val[0];
  if (n == 1)
    return max(val[0], val[1]);
  dp[n] = max(val[n], compute(n - 2) + val[n - 1]);
  return dp[n];
}

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  scanf("%d%d", &K, &N);

  if (K > N + 1) {
    printf("-1\n");
    return 0;
  }

  memset(val, 0, sizeof(val));
  for (int i = 0; i < N; i++)
    scanf("%lld", &val[i]);
  memset(dp, -1, sizeof(dp));
  if (K <= 2)
    printf("%lld\n", max(val[0], val[1]));
  else
    printf("%lld\n", compute(K - 1));
  return 0;
}
