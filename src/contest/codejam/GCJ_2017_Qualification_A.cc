#include <bits/stdc++.h>

using namespace std;

int T, K;
string S;

int main () {
  cin >> T;
  for (int t = 1; t <= T; t++) {
    cin >> S >> K;
    int ans = 0;
    for (int i = 0; i <= (int)S.size() - K; i++) {
      if (S[i] == '-') {
        for (int j = 0; j < K; j++)
          S[i + j] = S[i + j] == '-' ? '+' : '-';
        ans++;
      }
    }
    bool valid = true;
    for (int i = 0; i < (int)S.size(); i++)
      if (S[i] == '-')
        valid = false;
    if (valid)
      cout << "Case #" << t << ": " << ans << endl;
    else
      cout << "Case #" << t << ": IMPOSSIBLE" << endl;
  }
  return 0;
}
