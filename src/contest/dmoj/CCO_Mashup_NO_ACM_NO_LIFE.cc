#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 301
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int dist[SIZE][SIZE], labelCnt[SIZE][SIZE];
int label[SIZE];
long double c[SIZE][SIZE];
unordered_map<int, int> cnt;

int T, N, K;
vector<vector<int>> adj(SIZE);
unordered_set<int> labels;

long double choose(int n, int k) {
  long double ret = 1;
  for (int i = 0; i < k; i++)
    ret = ret * (n - i) / (i + 1);
  return ret;
}

void init() {
  for (int i = 0; i < SIZE; i++)
    adj[i].clear();

  for (int i = K; i <= N; i++)
    c[i - 2][K - 2] = choose(i - 2, K - 2);
}

void bfs(int start) {
  queue<int> q;

  int currDist[SIZE];
  for (int i = 0; i < N; i++)
    currDist[i] = 1 << 30;

  currDist[start] = 0;
  q.push(start);

  while (!q.empty()) {
    int u = q.front();
    q.pop();

    for (int v : adj[u]) {
      if (currDist[v] != 1 << 30)
        continue;
      currDist[v] = currDist[u] + 1;
      q.push(v);
    }
  }
  for (int i = 0; i < N; i++)
    dist[start][i] = currDist[i];
}

void dfs(int u, int par, int start) {
  if (cnt.count(label[u]) == 0)
    cnt[label[u]] = 0;
  cnt[label[u]]++;
  labelCnt[start][u] = cnt.size();

  for (int v : adj[u]) {
    if (v == par)
      continue;
    dfs(v, u, start);
  }

  if (cnt[label[u]] == 1) {
    cnt.erase(label[u]);
  } else {
    cnt[label[u]]--;
  }
}

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  rint(T);

  for (int t = 1; t <= T; t++) {
    rint(N);
    rint(K);

    init();

    for (int i = 0; i < N - 1; i++) {
      int a, b;
      rint(a);
      rint(b);

      adj[a - 1].pb(b - 1);
      adj[b - 1].pb(a - 1);
    }

    for (int i = 0; i < N; i++)
      rint(label[i]);

    for (int i = 0; i < N; i++)
      bfs(i);

    long double ans = 0;

    for (int i = 0; i < N; i++) {
      dfs(i, -1, i);
      assert(cnt.size() == 0);
    }

    for (int i = 0; i < N; i++) {
      for (int j = i + 1; j < N; j++) {
        int labels = labelCnt[i][j];
        int diameter = dist[i][j];
        int cnt = 2;
        for (int k = 0; k < N; k++) {
          if (k == i || k == j)
            continue;
          if (dist[k][i] > diameter || dist[k][j] > diameter)
            continue;
          if (dist[k][i] == diameter && k < j)
            continue;
          if (dist[k][j] == diameter && k < i)
            continue;
          cnt++;
        }
        if (cnt >= K) {
          ans += labels * c[cnt - 2][K - 2];
        }
      }
    }
    printf("%.10Lf\n", ans / choose(N, K));
  }

  return 0;
}
