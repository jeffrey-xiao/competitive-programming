#include <bits/stdc++.h>

using namespace std;

#define r(x) x << 1 | 1
#define l(x) x << 1
#define m(x, y) (x + y) / 2
#define INF 2 << 30

struct node {
  int left, right;
  int leftSeg, rightSeg;
  int seg;
  int index;
  bool propagate;
};
struct state {
  int index;
  int seg;
  state(int index, int seg) {
    this->index = index;
    this->seg = seg;
  }
};
node tree[1500005];
int n, m;

void pushUp(int n) {
  // update current node with information from children
  tree[n].seg = tree[r(n)].seg;
  tree[n].index = tree[r(n)].index;
  if ((tree[l(n)].seg == tree[n].seg && tree[l(n)].index < tree[n].index) ||
      (tree[l(n)].seg > tree[n].seg)) {
    tree[n].seg = tree[l(n)].seg;
    tree[n].index = tree[l(n)].index;
  }
  int posSeg = tree[l(n)].rightSeg + tree[r(n)].leftSeg;
  if (posSeg > tree[n].seg ||
      (posSeg == tree[n].seg && tree[l(n)].right - tree[l(n)].rightSeg + 1 < tree[n].index)) {
    tree[n].seg = tree[l(n)].rightSeg + tree[r(n)].leftSeg;
    tree[n].index = tree[l(n)].right - tree[l(n)].rightSeg + 1;
  }
  if (tree[r(n)].rightSeg + tree[l(n)].leftSeg == tree[n].right - tree[n].left + 1) {
    tree[n].leftSeg = tree[n].rightSeg = tree[n].seg = tree[r(n)].rightSeg + tree[l(n)].leftSeg;
    tree[n].index = tree[l(n)].left;
  } else {
    if (tree[r(n)].seg == tree[r(n)].right - tree[r(n)].left + 1)
      tree[n].rightSeg = tree[r(n)].seg + tree[l(n)].rightSeg;
    else
      tree[n].rightSeg = tree[r(n)].rightSeg;
    if (tree[l(n)].seg == tree[l(n)].right - tree[l(n)].left + 1)
      tree[n].leftSeg = tree[l(n)].seg + tree[r(n)].leftSeg;
    else
      tree[n].leftSeg = tree[l(n)].leftSeg;
  }
}

void pushDown(int n) {
  // update children with information from current node
  if (tree[n].seg == 0) {
    tree[l(n)].leftSeg = tree[l(n)].rightSeg = tree[l(n)].seg = 0;
    tree[r(n)].leftSeg = tree[r(n)].rightSeg = tree[r(n)].seg = 0;
  } else {
    tree[l(n)].leftSeg = tree[l(n)].rightSeg = tree[l(n)].seg =
        tree[l(n)].right - tree[l(n)].left + 1;
    tree[r(n)].leftSeg = tree[r(n)].rightSeg = tree[r(n)].seg =
        tree[r(n)].right - tree[r(n)].left + 1;
    tree[l(n)].index = tree[l(n)].left;
    tree[r(n)].index = tree[r(n)].left;
  }
  tree[l(n)].propagate = true;
  tree[r(n)].propagate = true;
  tree[n].propagate = false;
}
void build(int l, int r, int n) {
  tree[n].left = l;
  tree[n].right = r;
  tree[n].propagate = false;
  if (l == r) {
    tree[n].leftSeg = tree[n].rightSeg = tree[n].seg = 1;
    tree[n].index = l;
    return;
  }
  int mid = m(l, r);
  build(l, mid, l(n));
  build(mid + 1, r, r(n));
  pushUp(n);
}
void update(int l, int r, int n, bool isRemove) {
  if (tree[n].left == l && tree[n].right == r) {
    if (isRemove)
      tree[n].leftSeg = tree[n].rightSeg = tree[n].seg = tree[n].right - tree[n].left + 1;
    else
      tree[n].leftSeg = tree[n].rightSeg = tree[n].seg = 0;
    tree[n].propagate = true;
    return;
  }

  int mid = m(tree[n].left, tree[n].right);

  if (tree[n].propagate)
    pushDown(n);

  if (r <= mid)
    update(l, r, l(n), isRemove);

  else if (l > mid && mid != 0)
    update(l, r, r(n), isRemove);

  else if (mid != 0) {
    update(l, mid, l(n), isRemove);
    update(mid + 1, r, r(n), isRemove);
  }
  pushUp(n);
}
state query(int n, int s) {
  if (tree[n].left == tree[n].right)
    return state(tree[n].index, tree[n].seg);

  if (tree[n].propagate)
    pushDown(n);

  if (tree[l(n)].seg >= s) {
    if (tree[l(n)].seg == s)
      return state(tree[l(n)].index, tree[l(n)].seg);
    return query(l(n), s);
  }
  int midSeg = tree[l(n)].rightSeg + tree[r(n)].leftSeg;
  int midIndex = tree[l(n)].right - tree[l(n)].rightSeg + 1;
  if (midSeg >= s)
    return state(midIndex, midSeg);
  if (tree[r(n)].seg >= s) {
    if (tree[r(n)].seg == s)
      return state(tree[r(n)].index, tree[r(n)].seg);
    return query(r(n), s);
  }
  return state(INF, INF);
}

int main() {
  scanf("%d%d", &n, &m);
  build(1, n, 1);
  int counter = 0;
  for (int x = 0; x < m; x++) {
    char c;
    scanf(" %c", &c);
    if (c == 'A') {
      int x;
      scanf("%d", &x);
      state next = query(1, x);
      if (next.seg >= x)
        update(next.index, next.index + x - 1, 1, false);
      else
        counter++;
    } else if (c == 'L') {
      int x, y;
      scanf("%d%d", &x, &y);
      update(x, y, 1, true);
    }
  }
  printf("%d", counter);
  return 0;
}
