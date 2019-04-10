#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define MAX_N 1000000
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

ll gcd(ll a, ll b) {
  return b == 0 ? a : gcd(b, a % b);
}

struct State {
  int maxValue, index;
  State(int maxValue, int index) : maxValue(maxValue), index(index) {}
  State() {}
} dp[MAX_N][2];

struct Fraction {
  ll a, b;

  Fraction(ll a, ll b) {
    ll g = gcd(a, b);
    if (g != 0) {
      a /= g;
      b /= g;
    }
    this->a = a;
    this->b = b;
  }

  Fraction() {}

  bool operator<(const Fraction &f) const {
    return this->a * f.b < f.a * this->b;
  }
} best;

int N;
vector<int> adj[MAX_N];
int val[MAX_N];

void compute(int u, int par, int maxLen) {
  // consider one path of ones
  if (val[u] == 1) {
    Fraction curr = Fraction(1, dp[u][0].maxValue + dp[u][1].maxValue - 1);
    if (curr < best)
      best = curr;
  }

  int maxChild = 0;
  for (int v : adj[u]) {
    if (v != par) {
      compute(v, u, val[u] == 1 ? maxLen + 1 : 0);
      maxChild = max(maxChild, dp[v][0].maxValue);
    }
  }

  // consider two paths of ones
  if (par != -1) {
    int maxPar = 0;
    if (dp[par][0].index != u)
      maxPar = dp[par][0].maxValue;
    else
      maxPar = dp[par][1].maxValue;
    maxPar = max(maxPar, maxLen);

    Fraction curr = Fraction(val[u], maxChild + maxPar + 1);
    if (curr < best)
      best = curr;
  }
}

void replace(int u, State s) {
  if (dp[u][0].maxValue < s.maxValue) {
    dp[u][1] = dp[u][0];
    dp[u][0] = s;
  } else if (dp[u][1].maxValue < s.maxValue) {
    dp[u][1] = s;
  }
}

void dfs(int u, int par) {
  dp[u][0] = State(0, -1);
  dp[u][1] = State(0, -1);
  if (val[u] != 1) {
    for (int v : adj[u])
      if (v != par)
        dfs(v, u);
  } else {
    dp[u][0] = State(1, -1);
    dp[u][1] = State(1, -1);
    for (int v : adj[u]) {
      if (v == par)
        continue;
      dfs(v, u);
      State currState = State(dp[v][0].maxValue + 1, v);
      replace(u, currState);
    }
  }
}

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  scanf("%d", &N);

  int minValue = 1 << 30;

  for (int i = 0; i < N - 1; i++) {
    int u, v;
    scanf("%d%d", &u, &v);
    u--, v--;
    adj[u].push_back(v);
    adj[v].push_back(u);
  }

  for (int i = 0; i < N; i++) {
    scanf("%d", val + i);
    minValue = min(minValue, val[i]);
  }

  if (minValue != 1)
    printf("%d/%d\n", minValue, 1);
  else {
    best = Fraction(minValue, 1);
    dfs(0, -1);
    compute(0, -1, 0);
    printf("%lld/%lld\n", best.a, best.b);
  }

  return 0;
}
