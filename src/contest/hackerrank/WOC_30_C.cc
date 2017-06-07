#include <bits/stdc++.h>

using namespace std;

int N;
const string vowels = "aeiou";
const string consonants = "bcdfghjklmnpqrstvwxz";

void solve (int n, bool isVowel, string curr) {
  if (n == N) {
    puts(curr.c_str());
    return;
  }
  if (isVowel)
    for (int i = 0; i < (int)vowels.size(); i++)
      solve(n + 1, !isVowel, curr + vowels[i]);
  else
    for (int i = 0; i < (int)consonants.size(); i++)
      solve(n + 1, !isVowel, curr + consonants[i]);
}

int main () {
  ios_base::sync_with_stdio(false); cin.tie(NULL);
  cin >> N;
  solve(0, 0, "");
  solve(0, 1, "");
}
