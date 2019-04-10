#include <bits/stdc++.h>

using namespace std;

int main() {
  int T;
  scanf("%d", &T);
  for (int t = 0; t < T; t++) {
    vector<pair<int, int>> v;
    int K, P, Q;
    scanf("%d %d/%d", &K, &P, &Q);
    while (P != 1 || Q != 1) {
      // move right to get there
      if (P > Q) {
        if (Q == 1) {
          v.push_back({1, P / Q - 1});
          P = 1;
        } else {
          v.push_back({1, P / Q});
          P %= Q;
        }
      }
      // move left to get there
      else {
        if (P == 1) {
          v.push_back({-1, Q / P - 1});
          Q = 1;
        } else {
          v.push_back({-1, Q / P});
          Q %= P;
        }
      }
    }
    reverse(v.begin(), v.end());
    int ans = 1;
    for (int i = 0; i < (int)v.size(); i++) {
      if (v[i].first == 1)
        for (int j = 0; j < v[i].second; j++)
          ans = ans * 2 + 1;
      else
        for (int j = 0; j < v[i].second; j++)
          ans = ans * 2;
    }
    printf("%d %d\n", K, ans);
  }
  return 0;
}
