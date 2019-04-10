#include <bits/stdc++.h>

#define SIZE 4000
using namespace std;

const double EPS = 1e-6;

int N, W[SIZE], ans;
pair<int, int> P[SIZE];
vector<pair<double, int>> sweep, compressed;

int main() {
  scanf("%d", &N);

  for (int i = 0; i < N; i++)
    scanf("%d%d%d", &P[i].first, &P[i].second, &W[i]);

  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
      if (i == j)
        continue;
      double angle = atan2(P[j].second - P[i].second, P[j].first - P[i].first);
      sweep.push_back({angle, W[j]});
      sweep.push_back({angle + 2 * M_PI, W[j]});
    }
    sort(sweep.begin(), sweep.end());

    // you consider every suffix and prefix by changing the center (P[i])
    for (int j = 0; j < (int)sweep.size(); j++) {
      int value = sweep[j].second;
      while (j + 1 < (int)sweep.size() && fabs(sweep[j].first - sweep[j + 1].first) <= EPS)
        value += sweep[++j].second;
      compressed.push_back({sweep[j].first, value});
    }

    int value = max(0, W[i]);

    for (int n = 0, m = 0; compressed[n].first < M_PI; n++) {
      while (compressed[m].first - compressed[n].first <= M_PI)
        value += compressed[m++].second;
      ans = max(ans, value);
      value -= compressed[n].second;
    }
    sweep.clear();
    compressed.clear();
  }

  printf("%d\n", ans);
  return 0;
}
