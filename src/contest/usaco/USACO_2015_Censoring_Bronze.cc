#include <bits/stdc++.h>

using namespace std;

int main() {
  string s, t;
  cin >> s >> t;
  string res = "";

  for (int i = 0; i < s.size(); i++) {
    res += s[i];
    if (res.size() >= t.size() && res.substr(res.size() - t.size()) == t) {
      res.resize(res.size() - t.size());
    }
  }
  cout << res << endl;
  return 0;
}
