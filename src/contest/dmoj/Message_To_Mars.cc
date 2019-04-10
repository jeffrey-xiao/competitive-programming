#include <bits/stdc++.h>

using namespace std;

string text;
int n;
bitset<2001> dp[2001];
void search(string pattern) {
  int LCP[pattern.length()];
  LCP[0] = 0;
  for (int i = 1; i < pattern.length(); i++) {
    int j = LCP[i - 1];
    while (j > 0 && pattern[i] != pattern[j])
      j = LCP[j - 1];
    if (pattern[i] == pattern[j])
      j++;
    LCP[i] = j;
  }
  int j = 0;
  for (int i = 0; i < text.length(); i++) {
    while (j > 0 && text[i] != pattern[j])
      j = LCP[j - 1];
    if (text[i] == pattern[j])
      j++;
    if (j == pattern.length()) {
      dp[i - j + 1][i + 1] = true;
      j = LCP[j - 1];
    }
  }
}
int main() {
  cin >> text;
  scanf("%d", &n);

  for (int i = 0; i < n; i++) {
    string str;
    cin >> str;
    search(str);
  }
  for (int i = 0; i <= text.length(); i++) {
    for (int j = i + 1; j <= text.length(); j++) {
      if (dp[i][j])
        dp[i] |= dp[j];
    }
  }
  int ans = 0;
  for (int i = 0; i <= text.length(); i++) {
    for (int j = 0; j <= text.length(); j++) {
      if (dp[i][j])
        ans += 1;
    }
  }
  printf("%d\n", ans);
  return 0;
}
