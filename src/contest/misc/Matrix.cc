#include <bits/stdc++.h>

using namespace std;

int n, t, x;

int bit[1002][1002];

void update(int idx, int idy, int v) {
  for (int x = idx; x <= 1001; x += (x & -x))
    for (int y = idy; y <= 1001; y += (y & -y))
      bit[x][y] += v;
}
int query(int idx, int idy) {
  int sum = 0;
  for (int x = idx; x > 0; x -= (x & -x))
    for (int y = idy; y > 0; y -= (y & -y))
      sum += bit[x][y];
  return sum;
}
int main() {
  scanf("%d", &x);
  for (int qq = 0; qq < x; qq++) {
    for (int a = 0; a <= 1001; a++)
      for (int b = 0; b <= 1001; b++)
        bit[a][b] = 0;
    scanf("%d%d", &n, &t);
    for (int j = 0; j < t; j++) {
      char c;
      scanf(" %c", &c);
      if (c == 'C') {
        int x1, y1, x2, y2;
        scanf("%d%d%d%d", &x1, &y1, &x2, &y2);
        update(x1, y1, 1);
        update(x1, y2 + 1, -1);
        update(x2 + 1, y1, -1);
        update(x2 + 1, y2 + 1, 1);
      } else {
        int x, y;
        scanf("%d%d", &x, &y);
        printf("%d\n", query(x, y) % 2);
      }
    }
  }
}
