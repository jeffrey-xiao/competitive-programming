#include <bits/stdc++.h>

using namespace std;

struct point {
  int x, y;
  point() {}
  point(int x, int y) {
    this->x = x;
    this->y = y;
  }
};

int n, m, t;
vector<point> p;
bool comp(point p1, point p2) {
  if (p1.x == p2.x)
    return p1.y > p2.y;
  return p1.x < p2.x;
}
int main() {
  scanf("%d%d%d", &n, &m, &t);
  p.push_back(point(0, 0));
  p.push_back(point(n, 0));
  p.push_back(point(0, m));
  p.push_back(point(n, m));
  for (int x = 0; x < t; x++) {
    int a, b;
    scanf("%d%d", &a, &b);
    p.push_back(point(a, b));
  }
  sort(p.begin(), p.end(), comp);
  int maxA = 0;
  for (int i = 0; i < p.size(); i++) {
    int hi = 0, hibound = m, lo = m, lobound = 0;
    bool isMid = false;
    for (int j = i + 1; j < p.size(); j++) {
      if (p[i].x == p[j].x)
        continue;
      maxA = max(maxA, (m - hi) * (p[j].x - p[i].x));
      maxA = max(maxA, (lo) * (p[j].x - p[i].x));
      hi = max(hi, p[j].y);
      lo = min(lo, p[j].y);
      if (p[i].y == p[j].y) {
        isMid = true;
        maxA = max(maxA, (hibound - p[i].y) * (p[j].x - p[i].x));
        maxA = max(maxA, (p[i].y - lobound) * (p[j].x - p[i].x));
        continue;
      }

      if (isMid) {
        if (p[j].y > p[i].y)
          maxA = max(maxA, (hibound - p[i].y) * (p[j].x - p[i].x));
        else
          maxA = max(maxA, (p[i].y - lobound) * (p[j].x - p[i].x));
      } else
        maxA = max(maxA, (hibound - lobound) * (p[j].x - p[i].x));
      if (p[j].y > hibound || p[j].y < lobound) {
        continue;
      }
      if (p[j].y > p[i].y)
        hibound = min(hibound, p[j].y);
      else if (p[j].y < p[i].y)
        lobound = max(lobound, p[j].y);
    }
  }
  printf("%d\n", maxA);
}
