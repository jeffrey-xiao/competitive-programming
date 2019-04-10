#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 2050
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int N, M, T;
vector<pi> houses;
priority_queue<int> best, candidates;
int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  scanf("%d%d%d", &N, &M, &T);

  for (int i = 0; i < N; i++) {
    int P, C;
    scanf("%d%d", &P, &C);
    houses.pb({P, C});
  }

  sort(houses.begin(), houses.end());
  int ans = 0;
  int curr = 0;
  int prevTime = M;
  for (int i = 0; i < N; i++) {
    int timeLeft = prevTime - 2 * (houses[i].first - (i > 0 ? houses[i - 1].first : 0));
    candidates.push(houses[i].second);
    bool done = false;
    while (timeLeft <= 0) {
      if (best.empty()) {
        done = true;
        break;
      }
      timeLeft += T;
      curr += best.top();
      candidates.push(-best.top());
      best.pop();
    }

    if (done)
      break;

    while (timeLeft >= T && !candidates.empty()) {
      curr += candidates.top();
      best.push(-candidates.top());
      candidates.pop();
      timeLeft -= T;
    }

    while (!candidates.empty() && !best.empty() && candidates.top() > -best.top()) {
      curr += candidates.top() + best.top();
      candidates.push(-best.top());
      best.pop();
      best.push(-candidates.top());
      candidates.pop();
    }
    prevTime = timeLeft;
    ans = max(ans, curr);
  }
  printf("%d\n", ans);
  return 0;
}
