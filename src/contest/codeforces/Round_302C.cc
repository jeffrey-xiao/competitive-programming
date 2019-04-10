#include <bits/stdc++.h>

using namespace std;
typedef long long ll;
int n, m, b;
ll mod;
ll dp[501][501];
int num[501];
int main() {
  scanf("%d%d%d%lld", &n, &m, &b, &mod);
  for (int i = 0; i < n; i++)
    scanf("%d", &num[i]);
  for (int i = 0; i < n; i++) {
    for (int j = 0; j <= m; j++) {
      if (i == 0 && j * num[i] <= b)
        dp[j][j * num[i]] = 1ll;
      else
        for (int k = 0; k <= b; k++) {
          if (j && k - num[i] >= 0)
            dp[j][k] = (dp[j][k] + dp[j - 1][k - num[i]]) % mod;
        }
    }
  }
  ll res = 0;
  for (int i = 0; i <= b; i++)
    res = (res + dp[m][i]) % mod;
  printf("%lld\n", res);
}
