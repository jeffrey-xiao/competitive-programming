#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 500100
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int N;
stack<pair<int, ll>> s;
ll ans;

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  scanf("%d", &N);

  for (int i = 0; i < N; i++) {
    int h;
    scanf("%d", &h);
    ll pre = 0;
    ll c = 0;
    for (; !s.empty() && s.top().first < h; s.pop())
      c++;
    if (!s.empty() && s.top().first == h)
      pre += s.top().second + 1;
    else if (!s.empty() && s.top().first > h)
      pre++;
    ans += (c += pre);
    s.push({h, pre});
  }
  printf("%lld\n", ans);
  return 0;
}
