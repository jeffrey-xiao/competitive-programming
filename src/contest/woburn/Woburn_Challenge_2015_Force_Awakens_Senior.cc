#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define EPS 1e-6
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 1600000
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;
typedef pair<double, int> pdi;

int N, M;
double X1, Y1, X2, Y2;
ll lazy[SIZE], damage[SIZE], ships[SIZE];

priority_queue<pdi, vector<pdi>, greater<pdi>> pq;
static void pushUp(int n) {
  // damage[n] = damage[n << 1] + damage[n << 1 | 1];
  ships[n] = ships[n << 1] + ships[n << 1 | 1];
}

void pushDown(int n) {
  if (lazy[n] > 0) {
    lazy[n << 1] += lazy[n];
    lazy[n << 1 | 1] += lazy[n];
    damage[n << 1] += lazy[n] * ships[n << 1];
    damage[n << 1 | 1] += lazy[n] * ships[n << 1 | 1];
    lazy[n] = 0;
  }
}

ll query(int n, int l, int r, int ql, int qr) {
  if (l == ql && r == qr)
    return damage[n];
  pushDown(n);

  int mid = (l + r) >> 1;

  if (qr <= mid)
    return query(n << 1, l, mid, ql, qr);
  else if (ql > mid)
    return query(n << 1 | 1, mid + 1, r, ql, qr);
  return query(n << 1, l, mid, ql, mid) + query(n << 1 | 1, mid + 1, r, mid + 1, qr);
}

void updateDamage(ll c) {
  lazy[1] += c;
  damage[1] += c * ships[1];
}

void update(int n, int l, int r, int x) {
  if (l == abs(x) && abs(x) == r) {
    if (x < 0)
      ships[n] = 0;
    else
      ships[n] = 1;
    return;
  }

  pushDown(n);

  int mid = (l + r) >> 1;

  if (abs(x) <= mid)
    update(n << 1, l, mid, x);
  else
    update(n << 1 | 1, mid + 1, r, x);

  pushUp(n);
}
inline bool between(double x, double l, double h) {
  return l - EPS < x && x < h + EPS;
}
inline double dist(double x, double y) {
  return sqrt(x * x + y * y);
}

int main() {

  rint(N), rint(M);
  scanf("%lf%lf%lf%lf", &X1, &Y1, &X2, &Y2);

  for (int i = 1; i <= N; i++) {
    double x, y, dx, dy;
    scanf("%lf%lf%lf%lf", &x, &y, &dx, &dy);

    vector<double> events;

    events.clear();
    if (dx == 0) {
      if (x < X1 || x > X2)
        continue;
      events.push_back(Y1 - y);
      events.push_back(Y2 - y);
      if (dy < 0) {
        for (int j = 0; j < (int)events.size(); j++)
          events[j] = -events[j];
      }
    } else {
      double m = (double)dy / dx;
      double px = -1E6, py = y - m * (x + 1E6);
      double d = dist(x - px, y - py);
      y = py + m * (X1 - px);
      if (between(y, Y1, Y2))
        events.push_back(dist(X1 - px, y - py) - d);
      y = py + m * (X2 - px);
      if (between(y, Y1, Y2))
        events.push_back(dist(X2 - px, y - py) - d);
      if (dy != 0) {
        x = (m * px + Y1 - py) / m;
        if (between(x, X1, X2))
          events.push_back(dist(x - px, Y1 - py) - d);
        x = (m * px + Y2 - py) / m;
        if (between(x, X1, X2))
          events.push_back(dist(x - px, Y2 - py) - d);
      }
      if (dx < 0) {
        for (int j = 0; j < (int)events.size(); j++)
          events[j] = -events[j];
      }
    }
    if (events.size() >= 2) {
      sort(events.begin(), events.end());
      pq.push(make_pair(events[0] / dist(dx, dy) - EPS, i));
      pq.push(make_pair(events.back() / dist(dx, dy) + EPS, -i));
    }
  }
  int time = 0;
  for (int i = 0; i < M; i++) {
    int a, b, c;
    rint(a), rint(b), rint(c);

    if (a == 1) {
      time += b;
      while (!pq.empty() && pq.top().first <= time) {
        pdi e = pq.top();
        pq.pop();
        update(1, 1, N, e.second);
      }
      updateDamage(c);
    } else {
      if (b <= c) {
        printf("%lld\n", query(1, 1, N, b, c));
      } else {
        printf("0\n");
      }
    }
  }
  return 0;
}
