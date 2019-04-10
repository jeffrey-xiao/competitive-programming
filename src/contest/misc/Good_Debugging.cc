#include <bits/stdc++.h>

using namespace std;

#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

#define SIZE 1000000

struct node {
  int l, r;
  int bugs;
  bool propagate;
};

node tree[3 * SIZE];
int n, m, l;

void pushUp(int n) {
  // update current node with information from children
  tree[n].bugs = tree[l(n)].bugs + tree[r(n)].bugs;
}
void pushDown(int n) {
  // update children node with information from current
  tree[n].propagate = false;
  tree[l(n)].propagate = !tree[l(n)].propagate;
  tree[r(n)].propagate = !tree[r(n)].propagate;
  tree[l(n)].bugs = tree[l(n)].r - tree[l(n)].l + 1 - tree[l(n)].bugs;
  tree[r(n)].bugs = tree[r(n)].r - tree[r(n)].l + 1 - tree[r(n)].bugs;
}

void build(int l, int r, int n) {
  tree[n].l = l;
  tree[n].r = r;
  tree[n].propagate = false;
  if (l == r) {
    tree[n].bugs = 1;
    return;
  }
  int mid = m(l, r);
  build(l, mid, l(n));
  build(mid + 1, r, r(n));
  pushUp(n);
}

void update(int l, int r, int n) {
  if (tree[n].l == l && tree[n].r == r) {
    tree[n].propagate = !tree[n].propagate;
    tree[n].bugs = r - l + 1 - tree[n].bugs;
    return;
  }
  if (tree[n].propagate)
    pushDown(n);

  int mid = m(tree[n].l, tree[n].r);

  if (r <= mid)
    update(l, r, l(n));
  else if (l > mid && mid != 0)
    update(l, r, r(n));
  else if (mid != 0)
    update(l, mid, l(n)), update(mid + 1, r, r(n));
  pushUp(n);
}
int query(int n, int left) {
  if (tree[n].l == tree[n].r)
    return tree[n].l;

  if (tree[n].propagate)
    pushDown(n);
  int total = tree[l(n)].bugs + tree[r(n)].bugs;
  if (tree[l(n)].bugs < left)
    query(r(n), left - tree[l(n)].bugs);
  else
    query(l(n), left);
}

int main() {
  scanf("%d%d%d", &n, &m, &l);
  build(1, n, 1);
  for (int x = 0; x < m; x++) {
    int a, b;
    scanf("%d%d", &a, &b);
    update(a, b, 1);
    if (tree[1].bugs < l) {
      printf("AC?\n");
    } else {
      int lo = query(1, l);
      printf("%d\n", lo);
    }
  }
  return 0;
}
