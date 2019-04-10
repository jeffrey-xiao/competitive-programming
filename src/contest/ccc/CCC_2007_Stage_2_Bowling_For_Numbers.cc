#include <bits/stdc++.h>

using namespace std;

int dp[501][10101];
int sum[10101];
int pins[10101];
int n, w, K, t;

int compute(int k, int x) {
  if (k <= 0 || x < 0)
    return 0;
  int &ret = dp[k][x];
  if (ret != -1)
    return ret;
  int usePos = max(0, x - w);
  int useScore = sum[x] - sum[usePos];
  ret = max(compute(k, x - 1), compute(k - 1, usePos) + useScore);
  if (k >= 2) {
    int scoreSum = 0;
    for (int i = usePos; i > usePos - w + 1 && i > 0; i--) {
      scoreSum += pins[i];
      ret = max(ret, compute(k - 2, i - 1) + useScore + scoreSum);
    }
  }
  return ret;
}

int main() {
  scanf("%d", &t);
  for (int qq = 0; qq < t; qq++) {
    memset(dp, -1, sizeof dp);
    memset(pins, 0, sizeof pins);
    memset(sum, 0, sizeof sum);
    scanf("%d%d%d", &n, &K, &w);
    for (int i = 1; i <= n; i++)
      scanf("%d", &pins[i]);
    for (int i = 1; i <= n + w; i++)
      sum[i] = sum[i - 1] + pins[i];
    printf("%d\n", compute(K, n + w));
  }
}
