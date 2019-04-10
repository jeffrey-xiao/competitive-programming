#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 1000010
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2
#define scan(x)                                                                                    \
  do {                                                                                             \
    while ((x = getchar()) < '0')                                                                  \
      ;                                                                                            \
    for (x -= '0'; '0' <= (_ = getchar()); x = (x << 3) + (x << 1) + _ - '0')                      \
      ;                                                                                            \
  } while (0)

using namespace std;

char _;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

struct mine {
  int pos, l, r, index, cnt;
  mine(int pos, int l, int r, int cnt) {
    this->pos = pos;
    this->l = l;
    this->r = r;
    this->cnt = cnt;
  }
  mine() {}
};

int N, M;
map<int, mine> mineMap;
mine mines[SIZE];

// seg tree
int minIndex[4 * SIZE], maxIndex[4 * SIZE];

vector<vector<int>> adj(SIZE);

// SCC
int lo[SIZE], disc[SIZE], id[SIZE], sz[SIZE];
int cnt = 0, comCnt = 0;
deque<int> q;

// condensed graph
vector<unordered_set<int>> g(SIZE);
bool vis[SIZE], inStack[SIZE];
int lRange[SIZE], rRange[SIZE];

int ans[SIZE];

void order(int u) {
  vis[u] = true;
  for (int v : g[u])
    if (!vis[v])
      order(v);

  q.push_back(u);
}

void dfs(int u) {
  disc[u] = lo[u] = ++cnt;
  q.push_back(u);
  inStack[u] = true;

  for (int v : adj[u]) {
    if (disc[v] == 0) {
      dfs(v);
      lo[u] = min(lo[u], lo[v]);
    } else if (inStack[v]) {
      lo[u] = min(lo[u], disc[v]);
    }
  }

  if (disc[u] == lo[u]) {
    while (q.back() != u) {
      inStack[q.back()] = false;
      sz[comCnt] += mines[q.back()].cnt;
      id[q.back()] = comCnt;
      q.pop_back();
    }
    inStack[q.back()] = false;
    sz[comCnt] += mines[q.back()].cnt;
    id[q.back()] = comCnt;
    q.pop_back();

    comCnt++;
  }
}

int queryMin(int n, int l, int r, int ql, int qr) {
  if (l == ql && r == qr) {
    return minIndex[n];
  }

  int mid = (l + r) >> 1;

  if (qr <= mid) {
    return queryMin(n << 1, l, mid, ql, qr);
  } else if (ql > mid) {
    return queryMin(n << 1 | 1, mid + 1, r, ql, qr);
  } else {
    int index1 = queryMin(n << 1 | 1, mid + 1, r, mid + 1, qr);
    int index2 = queryMin(n << 1, l, mid, ql, mid);
    if (mines[index1].l <= mines[index2].l)
      return index1;
    else
      return index2;
  }
}

int queryMax(int n, int l, int r, int ql, int qr) {
  if (l == ql && r == qr) {
    return maxIndex[n];
  }

  int mid = (l + r) >> 1;

  if (qr <= mid) {
    return queryMax(n << 1, l, mid, ql, qr);
  } else if (ql > mid) {
    return queryMax(n << 1 | 1, mid + 1, r, ql, qr);
  } else {
    int index1 = queryMax(n << 1 | 1, mid + 1, r, mid + 1, qr);
    int index2 = queryMax(n << 1, l, mid, ql, mid);
    if (mines[index1].r >= mines[index2].r)
      return index1;
    else
      return index2;
  }
}

void build(int n, int l, int r) {
  if (l == r) {
    minIndex[n] = l;
    maxIndex[n] = l;
    return;
  }

  int mid = (l + r) >> 1;

  build(n << 1, l, mid);
  build(n << 1 | 1, mid + 1, r);

  if (mines[minIndex[n << 1]].l < mines[minIndex[n << 1 | 1]].l) {
    minIndex[n] = minIndex[n << 1];
  } else {
    minIndex[n] = minIndex[n << 1 | 1];
  }

  if (mines[maxIndex[n << 1]].r > mines[maxIndex[n << 1 | 1]].r) {
    maxIndex[n] = maxIndex[n << 1];
  } else {
    maxIndex[n] = maxIndex[n << 1 | 1];
  }
}

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  scan(N);
  scan(M);

  for (int i = 0; i < N; i++) {
    int pos, l, r;
    scan(pos);
    scan(l);
    scan(r);

    if (!mineMap.count(pos)) {
      mine m = mine(pos, INF, -INF, 0);
      mineMap[pos] = m;
    }
    mine old = mineMap[pos];
    mineMap[pos] = mine(pos, min(old.l, pos - l), max(old.r, pos + r), old.cnt + 1);
  }

  N = mineMap.size();

  int cnt = 1;

  for (pair<int, mine> e : mineMap) {
    mine m = e.second;
    m.index = cnt;
    e.second.index = cnt;
    mines[cnt++] = m;
  }

  for (int i = 1; i <= N; i++)
    mineMap[mines[i].pos].index = i;

  build(1, 1, N);

  for (int i = 1; i <= N; i++) {
    auto lower = mineMap.lower_bound(mines[i].l);

    auto higher = mineMap.lower_bound(mines[i].r);
    if (higher->first != mines[i].r)
      --higher;
    if (lower != mineMap.end() && higher != mineMap.begin()) {
      int lowerIndex = lower->second.index;
      int higherIndex = higher->second.index;

      int lo = queryMin(1, 1, N, lowerIndex, higherIndex);
      int hi = queryMax(1, 1, N, lowerIndex, higherIndex);

      if (lo != i)
        adj[i].pb(lo);

      if (hi != i)
        adj[i].pb(hi);
    }
  }

  for (int i = 1; i <= N; i++)
    if (disc[i] == 0)
      dfs(i);

  for (int i = 0; i < comCnt; i++) {
    lRange[i] = INF;
    rRange[i] = -INF;
  }

  for (int i = 1; i <= N; i++) {
    lRange[id[i]] = min(lRange[id[i]], mines[i].l);
    rRange[id[i]] = max(rRange[id[i]], mines[i].r);
  }

  for (int i = 1; i <= N; i++)
    for (int j : adj[i])
      if (id[i] != id[j])
        g[id[i]].insert(id[j]);

  for (int i = 0; i < comCnt; i++)
    if (!vis[i])
      order(i);

  for (int u : q) {
    for (int v : g[u]) {
      lRange[u] = min(lRange[u], lRange[v]);
      rRange[u] = max(rRange[u], rRange[v]);
    }
  }

  priority_queue<pair<int, pi>, vector<pair<int, pi>>, greater<pair<int, pi>>> pq;

  for (int i = 0; i < comCnt; i++) {
    pq.push(mp(lRange[i], mp(-2, sz[i])));
    pq.push(mp(rRange[i] + 1, mp(-1, sz[i])));
  }

  for (int i = 0; i < M; i++) {
    int val;
    scan(val);
    pq.push(mp(val, mp(i, 0)));
  }

  int curr = 0;

  while (!pq.empty()) {
    pair<int, pi> currState = pq.top();
    pq.pop();
    if (currState.second.first == -2) {
      curr += currState.second.second;
    } else if (currState.second.first == -1) {
      curr -= currState.second.second;
    } else {
      ans[currState.second.first] = curr;
    }
  }

  for (int i = 0; i < M; i++)
    printf("%d\n", ans[i]);

  return 0;
}
