#include <bits/stdc++.h>

using namespace std;

struct Box {
  int minx, maxx, miny, maxy;
  Box() {}
  Box(int x1, int y1, int x2, int y2) {
    minx = min(x1, x2);
    maxx = max(x1, x2);
    miny = min(y1, y2);
    maxy = max(y1, y2);
  }
  double distTo(Box b) {
    if (minx <= b.maxx && maxx >= b.minx && miny <= b.maxy && maxy >= b.miny) {
      return 0;
    } else if (b.minx >= maxx && b.miny >= maxy) {
      return dist(b.minx, b.miny, maxx, maxy);
    } else if (b.maxx <= minx && b.miny >= maxy) {
      return dist(b.maxx, b.miny, minx, maxy);
    } else if (b.maxx <= minx && b.maxy <= miny) {
      return dist(b.maxx, b.maxy, minx, miny);
    } else if (b.minx >= maxx && b.maxy <= miny) {
      return dist(b.minx, b.maxy, maxx, miny);
    } else if (b.maxx <= minx) {
      return minx - b.maxx;
    } else if (b.minx >= maxx) {
      return b.minx - maxx;
    } else if (b.maxy <= miny) {
      return miny - b.maxy;
    } else if (b.miny >= maxy) {
      return b.miny - maxy;
    }
    return -1;
  }
  double dist(double x1, double y1, double x2, double y2) {
    return sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
  }
} b[500];

double d[500][500];

int main() {
  freopen("in.txt", "r", stdin);
  char c;
  int index = 0;
  while (~(scanf(" %c", &c))) {
    if (c == 'B') {
      int A, B, C, D;
      scanf("%d%d%d%d", &A, &B, &C, &D);
      b[index] = Box(A, B, C, D);
      for (int i = 0; i < index; i++) {
        double dist = 1 << 30;
        for (int j = 0; j < index; j++)
          dist = min(dist, d[i][j] + b[index].distTo(b[j]));
        d[i][index] = d[index][i] = dist;
      }
      for (int i = 0; i < index; i++) {
        for (int j = 0; j < index; j++) {
          d[i][j] = d[j][i] = min(d[i][j], d[index][i] + d[index][j]);
        }
      }
      index++;
    } else if (c == 'G') {
      int A, B;
      scanf("%d%d", &A, &B);
      printf("%.3f\n", d[A - 1][B - 1]);
    }
  }
}
