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

vector<vector<int>> adj(SIZE);
int sz[SIZE];
bool vis[SIZE];
int depth[SIZE];

stack<int> s;
int curr;
int dfs(int i, int start, int d) {
  vis[i] = true;

  depth[i] = d;
  s.push(i);
  curr++;
  for (int j : adj[i]) {
    if (vis[j]) {
      if (depth[j] == 0) {
        sz[i] = sz[j] + 1;
        return 1 << 30;
      } else {
        sz[i] = d - depth[j];
        return depth[j];
      }
    }
    int nextDepth = dfs(j, start, d + 1);
    if (d >= nextDepth)
      sz[i] = sz[j];
    else
      sz[i] = sz[j] + 1;
    return nextDepth;
  }
  return 1 << 30;
}

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);
  rint(N);

  for (int i = 0; i < N; i++) {
    int val;
    rint(val);
    val--;
    adj[i].pb(val);
  }

  for (int i = 0; i < N; i++) {
    if (!vis[i]) {
      curr = 0;
      dfs(i, i, 1);
      while (!s.empty()) {
        depth[s.top()] = 0;
        s.pop();
      }
    }
  }

  for (int i = 0; i < N; i++)
    printf("%d\n", sz[i] + 1);
  return 0;
}
