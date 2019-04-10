#include <bits/stdc++.h>
using namespace std;
struct Point {
  int x, y;
  Point(int x, int y) : x(x), y(y) {}
  bool operator<(const Point &p) const {
    return make_pair(x, y) < make_pair(p.x, p.y);
  }
};

int ccw(Point p1, Point p2, Point p3) {
  return (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x);
}

vector<Point> convexHull(vector<Point> pts) {
  vector<Point> u, l;
  sort(pts.begin(), pts.end());

  for (int i = 0; i < (int)pts.size(); i++) {
    int j = (int)l.size();
    while (j >= 2 && ccw(l[j - 2], l[j - 1], pts[i]) <= 0) {
      l.erase(l.end() - 1);
      j = (int)l.size();
    }
    l.push_back(pts[i]);
  }
  for (int i = (int)pts.size() - 1; i >= 0; i--) {
    int j = (int)u.size();
    while (j >= 2 && ccw(u[j - 2], u[j - 1], pts[i]) <= 0) {
      u.erase(u.end() - 1);
      j = (int)u.size();
    }
    u.push_back(pts[i]);
  }

  u.erase(u.end() - 1);
  l.erase(l.end() - 1);
  l.reserve(l.size() + u.size());
  l.insert(l.end(), u.begin(), u.end());

  return l;
}

int main() {
  int N, M;
  scanf("%d", &M);
  for (int t = 0; t < M; t++) {
    vector<Point> p;
    scanf("%d", &N);

    for (int i = 0; i < N; i++) {
      int x, y;
      scanf("%d%d", &x, &y);
      p.push_back(Point(x, y));
    }

    p = convexHull(p);

    double ans = 0;
    for (int i = 0; i < (int)p.size(); i++) {
      double dx = p[i].x - p[(i + 1) % (int)p.size()].x;
      double dy = p[i].y - p[(i + 1) % (int)p.size()].y;
      ans += sqrt(dx * dx + dy * dy);
    }
    printf("%.2f\n", ans);
  }
}
