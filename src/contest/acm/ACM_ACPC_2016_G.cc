#include <bits/stdc++.h>
#define SIZE 2010
#define MOD (int)(1e9 + 7)
using namespace std;
typedef long long ll;
string s;
ll dp[SIZE][SIZE];

ll compute(int l, int r) {
  if (l > r)
    return 1;
  if (dp[l][r] != -1)
    return dp[l][r];
  ll &ret = dp[l][r];
  ret = 0;

  if (s[l] == s[r])
    ret = (ret + compute(l + 1, r - 1)) % MOD;

  ret = (ret + compute(l + 1, r)) % MOD;
  ret = (ret + compute(l, r - 1)) % MOD;
  ret = (ret - compute(l + 1, r - 1) + MOD) % MOD;

  return ret;
}

int main() {
  int T;
  cin >> T;
  for (int t = 0; t < T; t++) {
    cin >> s;
    for (int i = 0; i < SIZE; i++)
      for (int j = 0; j < SIZE; j++)
        dp[i][j] = -1L;
    cout << (compute(0, (int)s.size() - 1) - 1 + MOD) % MOD << endl;
  }
}
