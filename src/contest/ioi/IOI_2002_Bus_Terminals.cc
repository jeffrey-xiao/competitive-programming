#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 500
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

struct Point {
  int x, y, index;
  Point() {}
  Point(int x, int y, int index) {
    this->x = x;
    this->y = y;
    this->index = index;
  }

  bool operator<(const Point &P) const;
};

int N, ans = 1 << 30, stackIndex;
Point curr;
Point points[SIZE], sorted[SIZE];
int hubA[SIZE];

int dist(Point p1, Point p2) {
  int d1 = abs(p1.x - p2.x);
  int d2 = abs(p1.y - p2.y);
  return d1 + d2;
}

bool Point::operator<(const Point &p) const {
  int dist1 = dist(*this, curr);
  int dist2 = dist(p, curr);
  return dist1 < dist2;
}

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  scanf("%d", &N);

  for (int i = 0; i < N; i++) {
    int x, y;
    scanf("%d%d", &x, &y);
    points[i] = Point(x, y, i);
    sorted[i] = Point(x, y, i);
  }

  // case 1: all connected to one hub
  for (int i = 0; i < N; i++) {
    int max1 = 0;
    int max2 = 0;

    for (int j = 0; j < N; j++) {
      if (i == j)
        continue;
      int currDist = dist(points[i], points[j]);
      if (currDist > max1) {
        max2 = max1;
        max1 = currDist;
      } else if (currDist > max2) {
        max2 = currDist;
      }
    }

    ans = min(ans, max1 + max2);
  }

  // case 2: connected to two hubs
  for (int i = 0; i < N; i++) {
    curr = points[i];
    sort(sorted, sorted + N);
    for (int j = i + 1; j < N; j++) {

      stackIndex = 0;
      int aMax1 = 0, aMax2 = 0, bMax1 = 0, bMax2 = 0;
      int hubDist = dist(points[i], points[j]);

      for (int k = 0; k < N; k++) {
        if (sorted[k].index == i || sorted[k].index == j)
          continue;

        int dist1 = dist(points[i], sorted[k]);
        int dist2 = dist(points[j], sorted[k]) + hubDist;

        // could be connected to hub A or hub B
        if (dist1 < dist2) {
          hubA[stackIndex++] = sorted[k].index;
          if (dist1 > aMax1) {
            aMax2 = aMax1;
            aMax1 = dist1;
          } else if (dist1 > aMax2) {
            aMax2 = dist1;
          }
        }

        // has to be connected to hub B
        else {
          if (dist2 - hubDist > bMax1) {
            bMax2 = bMax1;
            bMax1 = dist2 - hubDist;
          } else if (dist2 - hubDist > bMax2) {
            bMax2 = dist2 - hubDist;
          }
        }
      }

      int currMaxDist = max(aMax1 + bMax1 + hubDist, max(aMax1 + aMax2, bMax1 + bMax2));
      ans = min(ans, currMaxDist);

      // moving connections from hub A to hub B
      // stack will be non-increasing
      while (stackIndex > 0) {
        int curr = hubA[--stackIndex];

        int distB = dist(points[curr], points[j]);
        if (distB > bMax1) {
          bMax2 = bMax1;
          bMax1 = distB;
        } else if (distB > bMax2) {
          bMax2 = distB;
        }

        aMax1 = aMax2;
        aMax2 = stackIndex >= 2 ? dist(points[hubA[stackIndex - 2]], points[i]) : 0;

        currMaxDist = max(aMax1 + bMax1 + hubDist, max(aMax1 + aMax2, bMax1 + bMax2));
        ans = min(ans, currMaxDist);
      }
    }
  }

  printf("%d\n", ans);
  return 0;
}
