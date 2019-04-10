#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 50500
#define LSIZE 20
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;
typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

struct edge {
  int src, dest, cost;
  edge() {}
  edge(int src, int dest, int cost) {
    this->src = src;
    this->dest = dest;
    this->cost = cost;
  }
  edge(int dest, int cost) {
    this->dest = dest;
    this->cost = cost;
  }
};

struct node {
  int l, r, v;
  node *left;
  node *right;
};

vector<node *> roots;

node tree[SIZE * 3];
int it = 0;
int chainNo = 0;
int chainPos[SIZE], chainInd[SIZE], chainSize[SIZE], chainHead[SIZE];
int pa[SIZE][LSIZE], depth[SIZE], subtree[SIZE];
int id[SIZE];
int setSize[SIZE];

vector<vector<edge>> adj(SIZE);
int n, m;

void dfs(int curr_, int prev_, int depth_);

void HLD(int curr);

int LCA(int x, int y);

bool cmp(edge a, edge b);

int find(int x);

bool merge(int x, int y);

node *create(int l, int r) {
  node *curr = &(tree[it++]);
  curr->l = l;
  curr->r = r;
  curr->v = 0;
  if (l == r)
    return curr;
  int mid = m(l, r);
  curr->left = create(l, mid);
  curr->right = create(mid + 1, r);
  return curr;
}

void update(node *n, int x, int val) {
  if (n->l == n->r && n->l == x) {
    n->v = val;
    return;
  }
  int mid = m(n->l, n->r);
  if (x <= mid)
    update(n->left, x, val);
  else
    update(n->right, x, val);
  n->v = max(n->left->v, n->right->v);
}

int query(node *n, int l, int r) {
  if (n->l == l && n->r == r)
    return n->v;
  int mid = m(n->l, n->r);
  if (r <= mid)
    return query(n->left, l, r);
  else if (l > mid)
    return query(n->right, l, r);
  return max(query(n->left, l, mid), query(n->right, mid + 1, r));
}
int query(int a, int b) {
  int maxV = 0;
  int chainA = chainInd[a];
  int chainB = chainInd[b];
  while (chainA != chainB) {
    maxV = max(maxV, query(roots[chainA], chainPos[chainHead[chainA]], chainPos[a]));
    a = pa[chainHead[chainA]][0];
    chainA = chainInd[a];
  }
  int posA = chainPos[a];
  int posB = chainPos[b] + 1;
  if (posB <= posA) {
    maxV = max(maxV, query(roots[chainA], posB, posA));
  }
  return maxV;
}
int queryUp(int a, int b) {
  int lca = LCA(a, b);
  return max(query(a, lca), query(b, lca));
}

int main() {
  scanf("%d%d", &n, &m);

  vector<edge> e(m);
  vector<edge> unused;
  vector<edge> used;
  for (int x = 0; x < SIZE; x++) {
    for (int y = 0; y < LSIZE; y++)
      pa[x][y] = -1;
    chainHead[x] = -1;
    id[x] = x;
  }
  for (int x = 0; x < m; x++) {
    int a, b, c;
    scanf("%d%d%d", &a, &b, &c);
    a--, b--;
    e[x] = edge(a, b, c);
  }
  sort(e.begin(), e.end(), cmp);
  int totalCost = 0;
  for (edge i : e) {
    if (merge(i.src, i.dest)) {
      adj[i.src].pb(edge(i.dest, i.cost));
      adj[i.dest].pb(edge(i.src, i.cost));
      used.pb(i);
      totalCost += i.cost;
    } else
      unused.pb(i);
  }
  dfs(0, -1, 0);
  HLD(0);
  for (int x = 0; x < n && chainSize[x] != 0; x++) {
    roots.push_back(create(1, chainSize[x]));
  }

  for (edge e : used) {
    if (depth[e.src] < depth[e.dest]) {
      update(roots[chainInd[e.dest]], chainPos[e.dest], e.cost);
    } else {
      update(roots[chainInd[e.src]], chainPos[e.src], e.cost);
    }
  }

  for (int x = 1; x < LSIZE; x++)
    for (int y = 0; y < n; y++)
      if (pa[y][x - 1] != -1)
        pa[y][x] = pa[pa[y][x - 1]][x - 1];
  int diff = 1 << 30;
  for (edge e : unused) {
    int nextCost = e.cost - queryUp(e.src, e.dest);
    if (nextCost != 0)
      diff = min(diff, nextCost);
  }
  if (diff == 1 << 30 || m < n - 1)
    printf("-1\n");
  else
    printf("%d\n", totalCost + diff);
  return 0;
}
void dfs(int curr_, int prev_, int depth_) {
  depth[curr_] = depth_;
  pa[curr_][0] = prev_;
  subtree[curr_] = 1;
  for (edge e : adj[curr_]) {
    if (e.dest != prev_) {
      dfs(e.dest, curr_, depth_ + 1);
      subtree[curr_] += subtree[e.dest];
    }
  }
}
void HLD(int curr) {
  if (chainHead[chainNo] == -1)
    chainHead[chainNo] = curr;
  chainInd[curr] = chainNo;
  chainPos[curr] = chainSize[chainNo] + 1;
  chainSize[chainNo]++;

  int ind = -1, s = -1;

  for (edge e : adj[curr]) {
    if (subtree[e.dest] > s && depth[curr] < depth[e.dest]) {
      ind = e.dest;
      s = subtree[e.dest];
    }
  }

  if (ind != -1) {
    HLD(ind);
  }

  for (edge e : adj[curr]) {
    if (ind != e.dest && depth[curr] < depth[e.dest]) {
      chainNo++;
      HLD(e.dest);
    }
  }
}
int LCA(int x, int y) {
  if (depth[x] < depth[y])
    swap(x, y);
  for (int i = LSIZE - 1; i >= 0; i--)
    if (pa[x][i] != -1 && depth[pa[x][i]] >= depth[y])
      x = pa[x][i];
  if (x == y)
    return x;
  for (int i = LSIZE - 1; i >= 0; i--) {
    if (pa[x][i] != -1 && pa[x][i] != pa[y][i]) {
      x = pa[x][i];
      y = pa[y][i];
    }
  }
  return pa[x][0];
}
bool cmp(edge a, edge b) {
  return a.cost < b.cost;
}
int find(int x) {
  return x == id[x] ? x : (id[x] = find(id[x]));
}
bool merge(int x, int y) {
  int rootx = find(x);
  int rooty = find(y);
  if (rootx == rooty)
    return false;
  if (setSize[rootx] > setSize[rooty]) {
    setSize[rootx] += setSize[rooty];
    id[rooty] = rootx;
  } else {
    setSize[rooty] += setSize[rootx];
    id[rootx] = rooty;
  }
  return true;
}
