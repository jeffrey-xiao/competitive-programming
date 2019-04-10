#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 5001
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int N, M, Q;

int disc[SIZE], lo[SIZE], id[SIZE], sz[SIZE], cache[SIZE];
int val[SIZE][SIZE];

int cnt = 0, comCnt = 0;

stack<int> s;
vector<vector<int>> adj(SIZE);
vector<unordered_set<int>> g(SIZE);

bool inStack[SIZE];

int compute(int u, int v) {
  if (cache[u] != -2)
    return cache[u];
  if (u == v)
    return cache[u] = 0;
  int ret = -1;
  for (int x : g[u]) {
    int res = compute(x, v);
    if (res != -1) {
      ret = max(ret, res + sz[u]);
    }
  }
  return cache[u] = ret;
}

void dfs(int u) {
  disc[u] = lo[u] = ++cnt;
  inStack[u] = true;
  s.push(u);

  for (int v : adj[u]) {
    if (disc[v] == 0) {
      dfs(v);
      lo[u] = min(lo[u], lo[v]);
    } else if (inStack[v]) {
      lo[u] = min(lo[u], disc[v]);
    }
  }
  if (disc[u] == lo[u]) {
    while (s.top() != u) {
      inStack[s.top()] = false;
      sz[comCnt]++;
      id[s.top()] = comCnt;
      s.pop();
    }
    inStack[s.top()] = false;
    sz[comCnt]++;
    id[s.top()] = comCnt++;
    s.pop();
  }
}

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  rint(N);
  rint(M);
  rint(Q);

  for (int i = 0; i < M; i++) {
    int x, y;
    rint(x);
    rint(y);
    adj[x - 1].pb(y - 1);
  }

  for (int i = 0; i < N; i++)
    if (disc[i] == 0)
      dfs(i);

  for (int i = 0; i < N; i++)
    for (int j : adj[i])
      if (id[i] != id[j])
        g[id[i]].insert(id[j]);

  for (int i = 0; i < comCnt; i++) {
    for (int j = 0; j < comCnt; j++)
      cache[j] = -2;

    for (int j = 0; j < comCnt; j++) {
      if (i == j)
        continue;
      val[j][i] = compute(j, i);
    }
  }
  for (int i = 0; i < Q; i++) {
    int u, v;
    rint(u);
    rint(v);
    u--, v--;

    if (id[u] == id[v]) {
      printf("Indeterminate\n");
      continue;
    }
    if (val[id[u]][id[v]] != -1) {
      printf("%d %d\n", u + 1, val[id[u]][id[v]] - sz[id[u]]);
      continue;
    } else if (val[id[v]][id[u]] != -1) {
      printf("%d %d\n", v + 1, val[id[v]][id[u]] - sz[id[v]]);
      continue;
    } else {
      printf("Indeterminate\n");
    }
  }

  return 0;
}
