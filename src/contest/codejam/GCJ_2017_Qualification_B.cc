#include <bits/stdc++.h>

using namespace std;

int T;
long long ans;
string N;

long long parseInt (string s) {
  long long ret = 0;
  for (int i = 0; i < (int)s.size(); i++)
    ret = ret * 10 + s[i] - '0';
  return ret;
}

string adjust (string s) {
  string ret = s;
  for (int i = 0; i < (int)s.size() - 1; i++)
    if (ret[i] > s[s.size() - 1])
      ret[i] = s[s.size() - 1];
  return ret;
}

bool isValid (string s) {
  for (int i = 1; i < (int)s.size(); i++)
    if (s[i] < s[i - 1])
      return false;
  return true;
}

int main () {
  cin >> T;
  for (int t = 1; t <= T; t++) {
    cin >> N;
    ans = 0;

    if (isValid(N))
      cout << "Case #" << t << ": " << parseInt(N) << endl;
    else {
      for (int i = 1; i <= (int)N.size(); i++) {
        bool decreased = 0;
        for (int j = 0; j < i - 1; j++)
          if (N[j] > N[i - 1]) {
            decreased = true;
            N[j] = N[i - 1];
          }
        if (N[i - 1] == '0')
          break;
        long long curr = parseInt(adjust(N.substr(0, i - 1) + (char)(N[i - 1] - !decreased)));
        for (int j = 0; j < (int)N.size() - i; j++)
          curr = curr * 10 + 9;
        ans = max(ans, curr);
      }
      cout << "Case #" << t << ": " << ans << endl;
    }
 }
  return 0;
}
