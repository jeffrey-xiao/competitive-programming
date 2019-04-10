#include <bits/stdc++.h>

using namespace std;

#define SIZE 30000

inline int scan(int &x) {
  char c;
  while (c = getchar(), c < '0' || c > '9')
    ;
  x = c - '0';
  while (c = getchar(), c >= '0' && c <= '9')
    x = x * 10 + c - '0';
}

int tree[32768][51];

int B, N, D;
int cost[SIZE + 1], value[SIZE + 1];
int ans[51];
void pushup(int par[], int c[]) {
  for (int i = 50; i >= 0; i--) {
    for (int j = i; j >= 0; j--) {
      if (par[i - j] != -1 && c[j] != -1 && par[i] < par[i - j] + c[j]) {
        par[i] = par[i - j] + c[j];
      }
    }
  }
}

void build(int l, int r, int n) {
  memset(tree[n], -1, sizeof tree[n]);
  tree[n][0] = 0;
  if (l == r) {
    tree[n][cost[l]] = value[l];
    return;
  }
  int mid = (l + r) >> 1;
  build(l, mid, n << 1);
  build(mid + 1, r, n << 1 | 1);
  pushup(tree[n], tree[n << 1]);
  pushup(tree[n], tree[n << 1 | 1]);
}
void update(int x, int n, int l, int r) {
  memset(tree[n], -1, sizeof tree[n]);
  tree[n][0] = 0;
  if (l == x && r == x) {
    tree[n][cost[x]] = value[x];
    return;
  }
  int mid = (l + r) >> 1;
  if (x <= mid)
    update(x, n << 1, l, mid);
  else
    update(x, n << 1 | 1, mid + 1, r);
  pushup(tree[n], tree[n << 1]);
  pushup(tree[n], tree[n << 1 | 1]);
}
void query(int l, int r, int n, int x, int y) {
  if (x == l && y == r) {
    pushup(ans, tree[n]);
    return;
  }
  int mid = (x + y) >> 1;
  if (r <= mid)
    query(l, r, n << 1, x, mid);
  else if (l > mid)
    query(l, r, n << 1 | 1, mid + 1, y);
  else {
    query(l, mid, n << 1, x, mid);
    query(mid + 1, r, n << 1 | 1, mid + 1, y);
  }
}
int main() {
  freopen("in.txt", "r", stdin);
  scan(B);
  scan(N);
  scan(D);
  for (int i = 1; i <= N; i++) {
    scan(cost[i]);
    scan(value[i]);
  }
  build(1, N, 1);
  for (int i = 0; i < D; i++) {
    int a, b, x, y;
    scan(a);
    scan(b);
    scan(x);
    scan(y);
    memset(ans, -1, sizeof ans);
    ans[0] = 0;
    cost[a] = b;
    update(a, 1, 1, N);
    query(x, y, 1, 1, N);
    int res = 0;
    for (int i = 0; i <= B; i++) {
      res = max(res, ans[i]);
    }
    printf("%d\n", res);
  }
}
