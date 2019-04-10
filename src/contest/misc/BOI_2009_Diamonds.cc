#include <bits/stdc++.h>

using namespace std;
int grid[101][101][101];
int main() {
  int L, M, N;
  scanf("%d%d%d", &L, &M, &N);

  for (int x = 1; x <= N; x++) {
    for (int y = 1; y <= M; y++) {
      for (int z = 1; z <= L; z++) {
        int d;
        scanf("%d", &d);
        grid[x][y][z] = d + grid[x - 1][y][z] + grid[x][y - 1][z] + grid[x][y][z - 1] -
                        grid[x - 1][y - 1][z] - grid[x - 1][y][z - 1] - grid[x][y - 1][z - 1] +
                        grid[x - 1][y - 1][z - 1];
      }
    }
  }
  for (int x = 0;; x++) {
    int z1 = -1, y1, x1, z2, y2, x2;
    scanf("%d%d%d%d%d%d", &z1, &y1, &x1, &z2, &y2, &x2);
    if (z1 < 0)
      break;
    int sum = grid[x2][y2][z2] - grid[x1][y2][z2] - grid[x2][y1][z2] - grid[x2][y2][z1] +
              grid[x1][y1][z2] + grid[x2][y1][z1] + grid[x1][y2][z1] - grid[x1][y1][z1];
    printf("%d\n", sum);
  }
  return 0;
}