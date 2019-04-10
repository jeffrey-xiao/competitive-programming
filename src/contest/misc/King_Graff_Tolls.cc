#include <bits/stdc++.h>

using namespace std;

#define N 100100
#define LN 18
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

int n, m;

int chainNo = 0, chainInd[N], chainHead[N], chainPos[N], chainSize[N];
int depth[N], pa[N][LN], subtree[N];

vector<vector<int>> adj(N);
vector<vector<long long>> tree;

void HLD(int curr) {
  if (chainHead[chainNo] == -1)
    chainHead[chainNo] = curr;
  chainInd[curr] = chainNo;
  chainPos[curr] = chainSize[chainNo] + 1;
  chainSize[chainNo]++;

  int ind = -1;
  int s = -1;
  for (int i = 0; i < adj[curr].size(); i++) {
    if (subtree[adj[curr][i]] > s && depth[curr] < depth[adj[curr][i]]) {
      s = subtree[adj[curr][i]];
      ind = i;
    }
  }
  if (ind != -1)
    HLD(adj[curr][ind]);

  for (int i = 0; i < adj[curr].size(); i++) {
    if (i != ind && depth[adj[curr][i]] > depth[curr]) {
      chainNo++;
      HLD(adj[curr][i]);
    }
  }
}
// the depth of x is greater than the depth of y thus on a lower level than y
int LCA(int x, int y) {
  if (depth[x] < depth[y])
    swap(x, y);
  for (int i = LN - 1; i >= 0; i--) {
    if (pa[x][i] != -1 && depth[pa[x][i]] >= depth[y])
      x = pa[x][i];
  }
  if (x == y)
    return x;
  for (int i = LN - 1; i >= 0; i--) {
    if (pa[x][i] != -1 && pa[x][i] != pa[y][i]) {
      x = pa[x][i], y = pa[y][i];
    }
  }
  return pa[x][0];
}

void dfs(int curr, int prev, int depth_) {
  pa[curr][0] = prev;
  depth[curr] = depth_;
  subtree[curr] = 1;
  for (int x = 0; x < adj[curr].size(); x++) {
    if (adj[curr][x] != prev) {
      dfs(adj[curr][x], curr, depth_ + 1);
      subtree[curr] += subtree[adj[curr][x]];
    }
  }
}
// y will be higher up than x (I.E have a lower number)
long long query_up(int x, int y) {
  long long sum = 0;
  while (true) {
    int xchain = chainInd[x], ychain = chainInd[y];
    if (xchain == ychain) {
      long long sum1 = 0;
      long long sum2 = 0;
      for (int z = chainPos[x]; z > 0; z -= (z & -z)) {
        sum1 += tree[xchain][z];
      }
      for (int z = chainPos[y] - 1; z > 0; z -= (z & -z)) {
        sum2 += tree[xchain][z];
      }
      sum += sum1 - sum2;
      break;
    }
    long long sum1 = 0;
    long long sum2 = 0;
    for (int z = chainPos[x]; z > 0; z -= (z & -z)) {
      sum1 += tree[xchain][z];
    }
    for (int z = chainPos[chainHead[xchain]] - 1; z > 0; z -= (z & -z)) {
      sum2 += tree[xchain][z];
    }
    sum += sum1 - sum2;
    x = pa[chainHead[xchain]][0];
  }
  return sum;
}

long long query(int x, int y) {
  int lca = LCA(x, y);
  int treeIndex = chainInd[lca];
  int treePos = chainPos[lca];
  long long a = query_up(x, 0);
  long long b = query_up(y, 0);
  long long c = 2 * query_up(lca, 0);
  long long sum1 = 0;
  long long sum2 = 0;
  for (int z = treePos; z > 0; z -= (z & -z)) {
    sum1 += tree[treeIndex][z];
  }
  for (int z = treePos - 1; z > 0; z -= (z & -z)) {
    sum2 += tree[treeIndex][z];
  }
  long long d = sum1 - sum2;
  return a + b - c + d;
}

int main() {
  scanf("%d", &n);

  // setting up adjacency list
  for (int x = 0; x < n - 1; x++) {
    int a, b;
    scanf("%d%d", &a, &b);
    a--, b--;
    adj[a].push_back(b);
    adj[b].push_back(a);
  }

  // initializing all values
  for (int x = 0; x < N; x++) {
    for (int y = 0; y < LN; y++)
      pa[x][y] = -1;
    chainHead[x] = -1;
    chainSize[x] = 0;
  }
  dfs(0, -1, 0);
  HLD(0);

  // computing pa array for LCA
  for (int y = 1; y < LN; y++) {
    for (int x = 0; x < n; x++) {
      if (pa[x][y - 1] != -1) {
        pa[x][y] = pa[pa[x][y - 1]][y - 1];
      }
    }
  }

  // constructing binary indexed trees for chains
  for (int x = 0; x < N && chainSize[x] != 0; x++) {
    tree.push_back(vector<long long>(chainSize[x] + 1));
    for (int y = 0; y <= chainSize[x]; y++)
      tree[x][y] = 0;
  }

  // get queries
  scanf("%d", &m);
  for (int x = 0; x < m; x++) {
    char c;
    int a, b;
    scanf(" %c%d%d", &c, &a, &b);
    if (c == 'T') {
      int i = chainInd[a - 1];
      int pos = chainPos[a - 1];

      long long sum1 = 0;
      long long sum2 = 0;
      for (int z = pos; z > 0; z -= (z & -z)) {
        sum1 += tree[i][z];
      }
      for (int z = pos - 1; z > 0; z -= (z & -z)) {
        sum2 += tree[i][z];
      }
      long long val = b - sum1 + sum2;
      for (int y = pos; y <= chainSize[i]; y += (y & -y)) {
        tree[i][y] += val;
      }
    } else if (c == 'Q') {
      long long ans = query(a - 1, b - 1);
      int i1 = chainInd[a - 1];
      int i2 = chainInd[b - 1];
      int pos1 = chainPos[a - 1];
      int pos2 = chainPos[b - 1];

      long long sum1 = 0;
      long long sum2 = 0;
      for (int z = pos1; z > 0; z -= (z & -z)) {
        sum1 += tree[i1][z];
      }
      for (int z = pos1 - 1; z > 0; z -= (z & -z)) {
        sum2 += tree[i1][z];
      }
      ans -= (sum1 - sum2);

      sum1 = 0;
      sum2 = 0;
      for (int z = pos2; z > 0; z -= (z & -z)) {
        sum1 += tree[i2][z];
      }
      for (int z = pos2 - 1; z > 0; z -= (z & -z)) {
        sum2 += tree[i2][z];
      }
      ans -= (sum1 - sum2);

      cout << ans << endl;
    }
  }
  return 0;
}
