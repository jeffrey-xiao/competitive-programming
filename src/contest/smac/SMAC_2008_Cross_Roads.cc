#include <bits/stdc++.h>

using namespace std;
int c, r, b;
int total = 0;
int grid[502][502];
int tl[502][502];
int tr[502][502];
int bl[502][502];
int br[502][502];

bool poss(int row, int col) {
  for (int x = 1; x + row - 1 <= r; x++) {
    for (int y = 1; y + col - 1 <= c; y++) {
      int cost =
          total - tl[x - 1][y - 1] - tr[x - 1][y + col] - bl[x + row][y - 1] - br[x + row][y + col];
      if (cost <= b)
        return true;
    }
  }
  return false;
}

int main() {
  scanf("%d%d%d", &c, &r, &b);
  for (int x = 1; x <= r; x++) {
    for (int y = 1; y <= c; y++) {
      scanf("%d", &grid[x][y]);
      total += grid[x][y];
    }
  }
  // calculating top values
  for (int x = 1; x <= r; x++) {
    // calculating left values
    for (int y = 1; y <= c; y++)
      tl[x][y] = grid[x][y] + tl[x - 1][y] + tl[x][y - 1] - tl[x - 1][y - 1];
    // calculating right values
    for (int y = c; y >= 1; y--)
      tr[x][y] = grid[x][y] + tr[x - 1][y] + tr[x][y + 1] - tr[x - 1][y + 1];
  }
  // calculating bottom values
  for (int x = r; x >= 1; x--) {
    // calculating left values
    for (int y = 1; y <= c; y++)
      bl[x][y] = grid[x][y] + bl[x + 1][y] + bl[x][y - 1] - bl[x + 1][y - 1];
    // calculating right values
    for (int y = c; y >= 1; y--)
      br[x][y] = grid[x][y] + br[x + 1][y] + br[x][y + 1] - br[x + 1][y + 1];
  }
  int maxV = 0;
  for (int x = 1; x <= r; x++) {
    int y;
    for (y = 1; y <= c; y++) {
      if (!poss(x, y)) {
        y--;
        break;
      }
    }
    if (y == c + 1)
      y--;
    if (y == 0)
      break;
    maxV = max(x * c + y * r - x * y, maxV);
  }
  printf("%d", maxV);
}
