#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define l(x) x << 1
#define SIZE 50000
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int n, m, t;
int weak[SIZE], small[SIZE];
vector<pi> toys;

// first is weight, second is size
class cmp_weight {
 public:
  bool operator()(const pi &p1, const pi &p2) const {
    if (p1.first == p2.first)
      return p1.second < p2.second;
    return p1.first < p2.first;
  }
};
class cmp_size {
 public:
  bool operator()(const pi &p1, const pi &p2) const {
    if (p1.second == p2.second)
      return p1.first < p2.first;
    return p1.second < p2.second;
  }
};

bool isPossible(int t) {
  queue<pi> curr;
  for (pi p : toys) {
    curr.push(p);
  }
  priority_queue<pi, vector<pi>, cmp_size> pq;
  for (int i = 0; i < n; i++) {
    int sz = curr.size();
    for (int j = 0; j < sz; j++) {
      if (weak[i] > curr.front().first) {
        pq.push(curr.front());
        curr.pop();
      } else {
        break;
      }
    }
    sz = min(t, (int)pq.size());
    for (int k = 0; k < sz; k++) {
      pq.pop();
    }
  }
  int sz = curr.size();
  for (int i = 0; i < sz; i++) {
    pq.push(curr.front());
    curr.pop();
  }
  for (int i = m - 1; i >= 0; i--) {
    sz = min(t, (int)pq.size());
    for (int j = 0; j < sz; j++) {
      if (pq.top().second >= small[i])
        return false;
      pq.pop();
    }
  }
  return pq.size() == 0;
}

int main() {
  rint(n);
  rint(m);
  rint(t);
  for (int i = 0; i < n; i++) {
    rint(weak[i]);
  }
  for (int i = 0; i < m; i++) {
    rint(small[i]);
  }
  sort(weak, weak + n);
  sort(small, small + m);
  for (int i = 0; i < t; i++) {
    int a, b;
    rint(a), rint(b);
    toys.pb(mp(a, b));
  }
  sort(toys.begin(), toys.end(), cmp_weight());
  int lo = 0, hi = 500000;
  while (lo <= hi) {
    int mid = lo + (hi - lo) / 2;
    if (isPossible(mid)) {
      hi = mid - 1;
    } else {
      lo = mid + 1;
    }
  }
  printf("%d\n", lo == 500001 ? -1 : lo);
}
