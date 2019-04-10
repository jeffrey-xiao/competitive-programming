#include <bits/stdc++.h>

using namespace std;

int T;
long long N, K;
map<long long, long long> occ;

int main() {
  cin >> T;

  for (int t = 1; t <= T; t++) {
    cin >> N >> K;
    K--;
    occ.clear();

    occ[N] = 1;

    while (K) {
      long long key = (occ.rbegin())->first;
      if (occ[key] > K) {
        break;
      } else {
        if (occ.find((key - 1) / 2) != occ.end())
          occ[(key - 1) / 2] += occ[key];
        else
          occ[(key - 1) / 2] = occ[key];
        if (occ.find(key / 2) != occ.end())
          occ[key / 2] += occ[key];
        else
          occ[key / 2] = occ[key];
        K -= occ[key];
        occ.erase(key);
      }
    }
    long long gap = occ.rbegin()->first;
    cout << "Case #" << t << ": " << gap / 2 << " " << (gap - 1) / 2 << endl;
  }
}
