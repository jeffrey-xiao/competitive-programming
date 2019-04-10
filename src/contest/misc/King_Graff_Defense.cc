#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 1000000
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

struct edge {
  int a, b;
  bool bridge;
  edge(int a_, int b_) {
    a = a_;
    b = b_;
    bridge = false;
  }
  edge() {}
};

vector<vector<edge>> adj(SIZE);
int low[SIZE], disc[SIZE];
bool v[SIZE];
int cnt = 0;
int n, m;

long double getSize(int i) {
  long double ret = 1;
  v[i] = true;
  for (edge j : adj[i])
    if (!j.bridge && !v[j.a])
      ret += getSize(j.a);
  return ret;
}

void dfs(int i, int prev) {
  disc[i] = low[i] = cnt++;
  v[i] = true;
  for (int j = 0; j < adj[i].size(); j++) {
    edge next = adj[i][j];
    if (!v[next.a]) {
      dfs(next.a, i);
      low[i] = min(low[i], low[next.a]);
      if (low[next.a] > disc[i]) {
        adj[i][j].bridge = true;
        adj[next.a][next.b].bridge = true;
      }
    } else if (next.a != prev && disc[next.a] < low[i])
      low[i] = disc[next.a];
  }
}

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  rint(n), rint(m);

  for (int i = 0; i < m; i++) {
    int a, b;
    rint(a), rint(b);
    a--, b--;
    adj[a].pb(edge(b, adj[b].size()));
    adj[b].pb(edge(a, adj[a].size() - 1));
  }
  for (int i = 0; i < n; i++)
    if (!v[i])
      dfs(i, -1);
  memset(v, false, sizeof v);
  long double total = 0;
  for (int i = 0; i < n; i++) {
    if (!v[i]) {
      long double size = getSize(i);
      total += (size) * (size - 1) / 2;
    }
  }
  printf("%.5Lf\n", 1 - total / ((long double)n * (long double)(n - 1) / 2));
  return 0;
}
