#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define MAX_N 51
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int dp[MAX_N][MAX_N][MAX_N][MAX_N];
int val[MAX_N];

int N;

int solve(int l, int r, int a, int b) {
  if (l == r)
    return a <= val[l] && val[l] <= b;
  if (l > r)
    return 0;
  if (dp[l][r][a][b] != -1)
    return dp[l][r][a][b];
  int &ret = dp[l][r][a][b];
  ret = 0;
  if (val[r] <= val[l] && a <= val[r] && val[l] <= b)
    ret = max(ret, solve(l + 1, r - 1, val[r], val[l]) + 2);
  if (a <= val[l] && val[l] <= b) {
    ret = max(ret, solve(l + 1, r, val[l], b) + 1);
    ret = max(ret, solve(l + 1, r - 1, a, val[l]) + 1);
  }
  if (a <= val[r] && val[r] <= b) {
    ret = max(ret, solve(l, r - 1, a, val[r]) + 1);
    ret = max(ret, solve(l + 1, r - 1, val[r], b) + 1);
  }
  ret = max(ret, solve(l + 1, r, a, b));
  ret = max(ret, solve(l, r - 1, a, b));
  return ret;
}

int main() {
  freopen("subrev.in", "r", stdin);
  freopen("subrev.out", "w", stdout);

  scanf("%d", &N);

  for (int i = 0; i < N; i++)
    scanf("%d", val + i);

  for (int i = 0; i < MAX_N; i++)
    for (int j = 0; j < MAX_N; j++)
      for (int n = 0; n < MAX_N; n++)
        for (int m = 0; m < MAX_N; m++)
          dp[i][j][n][m] = -1;

  printf("%d\n", solve(0, N - 1, 1, 50));
  return 0;
}
