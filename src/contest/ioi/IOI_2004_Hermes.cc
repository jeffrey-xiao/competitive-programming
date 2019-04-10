#include <bits/stdc++.h>

using namespace std;

#define SHIFT 1000
#define SIZE 20005

struct point {
  int x, y;
  point() {}
  point(int x, int y) {
    this->x = x;
    this->y = y;
  }
};

int n;
point p[SIZE];
int dp1[2][2001];
int dp2[2][2001];
int main() {
  scanf("%d", &n);
  p[0] = point(0, 0);
  for (int x = 1; x <= n; x++) {
    int a, b;
    scanf("%d%d", &a, &b);
    p[x] = point(a, b);
  }
  for (int x = 0; x <= 2000; x++) {
    dp1[0][x] = abs(x - 1000);
    dp2[0][x] = abs(x - 1000);
  }
  for (int z = 1; z <= n; z++) {
    for (int i = 0; i <= 2000; i++) {
      dp1[z % 2][i] = min(abs(p[z - 1].x - p[z].x) + dp1[(z - 1) % 2][i],
          dp2[(z - 1) % 2][p[z].x + 1000] + abs(i - 1000 - p[z - 1].y));
      dp2[z % 2][i] = min(abs(p[z - 1].y - p[z].y) + dp2[(z - 1) % 2][i],
          dp1[(z - 1) % 2][p[z].y + 1000] + abs(i - 1000 - p[z - 1].x));
    }
  }
  int minV = 1 << 30;
  for (int i = 0; i <= 2000; i++)
    minV = min(minV, min(dp1[n % 2][i], dp2[n % 2][i]));
  printf("%d\n", minV);
  return 0;
}
