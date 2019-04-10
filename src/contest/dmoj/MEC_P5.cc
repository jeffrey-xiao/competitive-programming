#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 10010
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int N;

int ccw(pi p1, pi p2, pi p3) {
  return (p2.first - p1.first) * (p3.second - p1.second) -
         (p2.second - p1.second) * (p3.first - p1.first);
}
double getDist(int x1, int y1, int x2, int y2) {
  double x = x1 - x2;
  double y = y1 - y2;
  return sqrt(x * x + y * y);
}
int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  rint(N);

  int first = 0;
  int last = 0;

  vector<pi> points;

  for (int x = 0; x < N; x++) {
    int val;
    rint(val);
    points.pb({val, x});
    if (x == 0)
      first = points[x].first;
    if (x == N - 1)
      last = points[x].first;
  }
  points.pb({0, 0});
  points.pb({0, N - 1});

  vector<pi> u, l;
  sort(points.begin(), points.end());

  for (int x = 0; x < N + 1; x++) {
    int i = l.size();
    while (i >= 2 && ccw(l[i - 2], l[i - 1], points[x]) <= 0) {

      l.pop_back();
      i = l.size();
    }
    l.pb(points[x]);
  }
  for (int x = N + 1; x >= 0; x--) {
    int i = u.size();
    while (i >= 2 && ccw(u[i - 2], u[i - 1], points[x]) <= 0) {

      u.pop_back();
      i = u.size();
    }
    u.pb(points[x]);
  }
  u.pop_back();
  l.pop_back();

  for (int i = 0; i < u.size(); i++)
    l.pb(u[i]);

  double length = 0;
  int j = l.size() - 1;
  for (int i = 0; i < l.size(); i++) {
    length += getDist(l[j].first, l[j].second, l[i].first, l[i].second);
    j = i;
  }
  printf("%.1f\n", length - first - last - N + 1);
  return 0;
}
