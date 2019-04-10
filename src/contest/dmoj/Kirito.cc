#include <bits/stdc++.h>

using namespace std;

#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) >> 1
#define SIZE 2001
int tree[SIZE * 3];
int lcp[SIZE];
string grid[SIZE];
int n, m, q;

void update(int n, int l, int r, int x) {
  if (x == l && x == r) {
    tree[n] = lcp[x];
    return;
  }
  int mid = m(l, r);
  if (x <= mid)
    update(l(n), l, mid, x);
  else
    update(r(n), mid + 1, r, x);
  tree[n] = min(tree[l(n)], tree[r(n)]);
}
void update(int i) {
  if (i <= 1 || i > n)
    return;
  int j = 0;
  for (; j < m; j++)
    if (grid[i][j] != grid[i - 1][j])
      break;
  lcp[i] = j;
  update(1, 1, n, i);
}
void build(int n, int l, int r) {
  if (l == r) {
    tree[n] = lcp[l];
    return;
  }
  int mid = m(l, r);
  build(l(n), l, mid);
  build(r(n), mid + 1, r);
  tree[n] = min(tree[l(n)], tree[r(n)]);
}
int query(int n, int l, int r, int ql, int qr) {
  if (l == ql && r == qr) {
    return tree[n];
  }
  int mid = m(l, r);
  if (qr <= mid)
    return query(l(n), l, mid, ql, qr);
  else if (ql > mid)
    return query(r(n), mid + 1, r, ql, qr);
  else
    return min(query(l(n), l, mid, ql, mid), query(r(n), mid + 1, r, mid + 1, qr));
}
int main() {
  scanf("%d%d", &n, &m);
  for (int i = 1; i <= n; i++)
    cin >> grid[i];
  for (int i = 2; i <= n; i++) {
    int j = 0;
    for (; j < m; j++)
      if (grid[i][j] != grid[i - 1][j])
        break;
    lcp[i] = j;
  }
  build(1, 1, n);
  scanf("%d", &q);
  for (int i = 0; i < q; i++) {
    int l, r;
    scanf("%d%d", &l, &r);
    if (l == r)
      printf("%d\n", m * (r - l + 1));
    else {
      printf("%d\n", query(1, 1, n, l + 1, r) * (r - l + 1));
      string temp = grid[l];
      grid[l] = grid[r];
      grid[r] = temp;
      update(l);
      update(l + 1);
      update(r);
      update(r + 1);
    }
  }
}
