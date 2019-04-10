#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 200000
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int N, K;
vector<vector<pi>> adj(SIZE);
bool used[SIZE];
int ans = 0;

bool dfs(int u, int p) {
  bool ret = used[u];
  for (auto v : adj[u]) {
    if (v.first == p)
      continue;
    ret |= dfs(v.first, u);
  }

  for (auto v : adj[u]) {
    if (v.first == p)
      continue;
    if (ret && used[v.first])
      ans += v.second;
  }

  return used[u] = ret;
}

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  scanf("%d%d", &N, &K);

  for (int i = 0; i < N - 1; i++) {
    int u, v, cost;
    scanf("%d%d%d", &u, &v, &cost);
    u--, v--;
    adj[u].pb({v, cost});
    adj[v].pb({u, cost});
  }

  for (int i = 0; i < K; i++) {
    int x;
    scanf("%d", &x);
    used[x - 1] = true;
  }

  used[0] = 1;
  dfs(0, -1);
  printf("%d\n", ans);
  return 0;
}
