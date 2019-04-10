#include <bits/stdc++.h>

using namespace std;

#define SIZE 30001

struct command {
  int id, a, b;
  command(int id, int a, int b) {
    this->id = id;
    this->a = a;
    this->b = b;
  }
};

int id[SIZE], sz[SIZE];
int chain[SIZE], chainIndex[SIZE], head[SIZE], subsize[SIZE], depth[SIZE], parent[SIZE];
int val[SIZE];
int chainNo = -1;
int currIndex = 1;
int tree[SIZE * 4];
int n, q;
vector<int> adj[SIZE];

int root(int i) {
  return i == id[i] ? i : (id[i] = root(id[i]));
}
void merge(int i, int j) {
  int ri = root(i);
  int rj = root(j);
  if (sz[ri] > sz[rj]) {
    sz[ri] += sz[rj];
    id[rj] = ri;
  } else {
    sz[rj] += sz[ri];
    id[ri] = rj;
  }
}

void update(int n, int lo, int hi, int i, int val) {
  if (lo == i && i == hi) {
    tree[n] = val;
    return;
  }
  int mid = (hi + lo) >> 1;
  if (i <= mid)
    update(n << 1, lo, mid, i, val);
  else
    update(n << 1 | 1, mid + 1, hi, i, val);
  tree[n] = tree[n << 1] + tree[n << 1 | 1];
}
int query(int n, int lo, int hi, int qlo, int qhi) {
  if (lo == qlo && hi == qhi)
    return tree[n];
  int mid = (hi + lo) >> 1;
  if (qhi <= mid)
    return query(n << 1, lo, mid, qlo, qhi);
  else if (qlo > mid)
    return query(n << 1 | 1, mid + 1, hi, qlo, qhi);
  else
    return query(n << 1, lo, mid, qlo, mid) + query(n << 1 | 1, mid + 1, hi, mid + 1, qhi);
}

void dfs(int i, int d, int prev) {
  parent[i] = prev;
  depth[i] = d;
  subsize[i] = 1;
  for (int j : adj[i])
    if (j != prev) {
      subsize[i] += subsize[j];
      dfs(j, d + 1, i);
    }
}
void hld(int i, int prev) {
  if (head[chainNo] == -1)
    head[chainNo] = i;
  chain[i] = chainNo;
  chainIndex[i] = currIndex++;
  int maxIndex = -1;
  for (int j : adj[i])
    if (j != prev && (maxIndex == -1 || subsize[maxIndex] < subsize[j]))
      maxIndex = j;
  if (maxIndex != -1)
    hld(maxIndex, i);
  for (int j : adj[i])
    if (j != prev && j != maxIndex) {
      chainNo++;
      hld(j, i);
    }
}
int getSum(int a, int b) {
  int res = 0;
  while (chain[a] != chain[b]) {
    if (depth[head[chain[a]]] < depth[head[chain[b]]]) {
      res += query(1, 1, n, chainIndex[head[chain[b]]], chainIndex[b]);
      b = parent[head[chain[b]]];
    } else {
      res += query(1, 1, n, chainIndex[head[chain[a]]], chainIndex[a]);
      a = parent[head[chain[a]]];
    }
  }
  int i = min(chainIndex[a], chainIndex[b]);
  int j = max(chainIndex[a], chainIndex[b]);
  return res + query(1, 1, n, i, j);
}
int main() {
  scanf("%d", &n);
  for (int i = 1; i <= n; i++) {
    scanf("%d", &val[i]);
    id[i] = i;
    sz[i] = 1;
    head[i] = -1;
    chain[i] = -1;
  }
  scanf("%d", &q);
  vector<command> c;
  for (int i = 0; i < q; i++) {
    string in;
    cin >> in;
    int a, b;
    scanf("%d%d", &a, &b);
    if (in == "excursion")
      c.push_back(command(0, a, b));
    else if (in == "bridge") {
      c.push_back(command(1, a, b));
      if (root(a) != root(b)) {
        merge(a, b);
        adj[a].push_back(b);
        adj[b].push_back(a);
      }
    } else {
      c.push_back(command(2, a, b));
    }
  }
  for (int i = 1; i <= n; i++) {
    if (chain[i] == -1) {
      dfs(i, 0, -1);
      chainNo++;
      hld(i, -1);
    }
    id[i] = i;
    sz[i] = 1;
    update(1, 1, n, chainIndex[i], val[i]);
  }
  for (command co : c) {
    if (co.id == 0) {
      if (root(co.a) != root(co.b))
        puts("impossible\n");
      else
        printf("%d\n", getSum(co.a, co.b));
    } else if (co.id == 1) {
      if (root(co.a) != root(co.b)) {
        puts("yes\n");
        merge(co.a, co.b);
      } else
        puts("no\n");
    } else {
      val[co.a] = co.b;
      update(1, 1, n, chainIndex[co.a], co.b);
    }
  }
}
