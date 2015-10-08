#include <bits/stdc++.h>

using namespace std;
int dp[18][1 << 12];
int N;
vector<int> combo;
int MOD = 1000000001;

int solve(int start) {
      memset(dp, 0, sizeof dp);
      int szlast = 0, szcurr = 0, ret = 0, row, bd;
      dp[0][0] = 1;
      for (row = 1; start * (1<<(row-1)) <= N; row++) {
            //cout << row << ": " << start * (1<<(row-1)) << endl;
            for (bd = 1, szcurr = 0; start * (1<<(row-1)) * bd <= N; szcurr ++, bd *= 3);
            //cout << "SIZE OF ROW: " << szcurr << endl << "BOUND: " << start * (1<<(row-1)) * bd/3 << endl;
            for(int last : combo) {
                  if (last >= (1<<szlast)) break;
                  for(int mask : combo) {
                        if(mask >= (1<<szcurr)) break;
                        if (last & mask) continue;     // last row + this row adjacent
                        dp[row][mask] += dp[row-1][last];
                        if (dp[row][mask] > MOD) dp[row][mask] -= MOD;
                  }
            }
            szlast = szcurr;
          /*
            For(i,0,1<<szcurr) {
                  cout << dp[row][i] << " " ;
            }
            cout << endl;
           */
      }
      for(int i = 0; i < 1<<szlast; i++) {
            ret += dp[row-1][i];
            if (ret > MOD) ret -= MOD;
      }
      return ret;
}
int main() {
      cin >> N;
      //cout << N << endl;
      for(int i = 0; i < 1<<11; i++) {  // logN/log3
            if ((i & i<<1) || (i & i>>1)) continue;
            combo.push_back(i);
      }
      long long ans = 1;
      for(int i = 59000; i <= 60000; i++) {
            if (i % 2 == 0 || i % 3 == 0) continue;

            cout << solve(i) << " " << i <<endl;
            ans *= solve(i);
            ans %= MOD;
      }
      cout << ans << endl;
}
