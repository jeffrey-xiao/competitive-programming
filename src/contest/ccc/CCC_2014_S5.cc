#include <bits/stdc++.h>

using namespace std;

int n;

struct point {
  int x;
  int y;
  point(int x, int y) {
    this->x = x;
    this->y = y;
  }
};

struct line {
  int i1;
  int i2;
  int dist;
  line(int i1, int i2, int dist) {
    this->i1 = i1;
    this->i2 = i2;
    this->dist = dist;
  }
};

vector<point> points;
int bestDist[2001];
int prevbestNum[2001];
int bestNum[2001];
vector<line> lines;

bool compare(line p1, line p2) {
  return p2.dist > p1.dist;
}

int main() {
  scanf("%d", &n);
  points.push_back(point(0, 0));
  int count = 0;
  for (int x = 1; x <= n; x++) {
    if (x != 0) {
      int a, b;
      scanf("%d%d", &a, &b);
      points.push_back(point(a, b));
    }
    for (int y = x - 1; y >= 0; y--) {
      int x1 = points[x].x - points[y].x;
      int y1 = points[x].y - points[y].y;
      lines.push_back(line(y, x, x1 * x1 + y1 * y1));
      count++;
    }
  }
  sort(lines.begin(), lines.begin() + (n) * (n + 1) / 2, compare);
  for (int x = 0; x < (n) * (n + 1) / 2; x++) {
    line next = lines[x];
    if (next.dist > bestDist[next.i1]) {
      bestDist[next.i1] = next.dist;
      prevbestNum[next.i1] = bestNum[next.i1];
    }
    if (next.dist > bestDist[next.i2]) {
      bestDist[next.i2] = next.dist;
      prevbestNum[next.i2] = bestNum[next.i2];
    }
    // adding necessary previous row memorization in order to calc curr row
    if (next.i1 == 0) {
      bestNum[next.i1] = max(bestNum[next.i1], prevbestNum[next.i2]);
    } else {
      bestNum[next.i1] = max(bestNum[next.i1], prevbestNum[next.i2] + 1);
      bestNum[next.i2] = max(bestNum[next.i2], prevbestNum[next.i1] + 1);
    }
  }
  printf("%d", bestNum[0] + 1);
  return 0;
}
