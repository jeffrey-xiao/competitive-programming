#include <bits/stdc++.h>

using namespace std;

int r, c;
int minD[501][501][2];

inline int sqr(int x) {
  return x * x;
}

int main() {
  scanf("%d%d", &r, &c);
  for (int i = 0; i < r; i++)
    for (int j = 0; j < c; j++)
      minD[i][j][0] = minD[i][j][1] = 1 << 30;

  for (int i = 0; i < r; i++) {
    for (int j = 0; j < c; j++) {
      char ch;
      scanf(" %c", &ch);
      if (ch != 'x')
        continue;
      for (int k = 0; k < c; k++) {
        if (k == j)
          minD[i][k][0] = minD[i][k][1] = 0;
        else if (k < j)
          minD[i][k][0] = min(minD[i][k][0], sqr(k - j));
        else
          minD[i][k][1] = min(minD[i][k][1], sqr(k - j));
      }
    }
  }
  int q;
  scanf("%d", &q);
  for (int k = 0; k < q; k++) {
    int x, y;
    scanf("%d%d", &x, &y);
    x--, y--;
    int minDist = 1 << 30;
    for (int i = 0; i < r; i++)
      minDist = min(minDist, sqr(x - i) + min(minD[i][y][0], minD[i][y][1]));

    for (int i = 0; i < c; i++) {
      if (i == y)
        minD[x][i][0] = minD[x][i][1] = 0;
      else if (i < y)
        minD[x][i][0] = min(minD[x][i][0], sqr(y - i));
      else
        minD[x][i][1] = min(minD[x][i][1], sqr(y - i));
    }
    printf("%d\n", minDist);
  }
}