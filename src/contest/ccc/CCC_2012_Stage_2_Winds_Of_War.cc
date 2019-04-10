#include <bits/stdc++.h>

#define SIZE 1000

using namespace std;

int P, T;

struct point {
  int x, y, type;

  point() {}

  point(int x, int y, int type) {
    this->x = x;
    this->y = y;
    this->type = type;
  }

  bool operator<(const point &p) const {
    double a = atan2(p.y, p.x);
    double b = atan2(y, x);
    return a > b;
  }
};

int dp[SIZE][SIZE];
point p[SIZE];
point o = point(0, 0, 0);

long long cross(long long x1, long long y1, long long x2, long long y2) {
  return 1L * x1 * y2 - 1L * x2 * y1;
}

long long ccw(point p1, point p2, point p3) {
  return cross(p2.x - p1.x, p2.y - p1.y, p3.x - p1.x, p3.y - p1.y);
}

int main() {
  scanf("%d%d", &P, &T);

  for (int i = 0; i < P + T; i++) {
    int x, y;
    scanf("%d%d", &x, &y);
    p[i] = point(x, y, i < P ? 1 : -1);
  }

  sort(p, p + P + T);

  for (int i = 0; i < P + T; i++) {
    for (int j = i + 1; j < P + T; j++) {
      dp[i][j] = -1 << 30;
    }
  }

  for (int j = 0; j < P + T; j++) {
    for (int k = j + 1; k < P + T; k++) {
      int score = 0;

      for (int i = j + 1; i < k; i++)
        if (ccw(p[i], p[j], p[k]) > 0)
          score += p[i].type;

      dp[j][k] = max(dp[j][k], p[j].type + p[k].type + score);
      for (int i = 0; i < j; i++) {
        if (ccw(p[i], p[j], p[k]) > 0) {
          dp[j][k] = max(dp[j][k], dp[i][j] + p[k].type + score);
        }
      }
    }
  }

  int ans = 0;
  for (int i = 0; i < P + T; i++) {
    for (int j = i + 1; j < P + T; j++) {
      ans = max(ans, dp[i][j]);
    }
  }

  printf("%d\n", ans);
  return 0;
}
