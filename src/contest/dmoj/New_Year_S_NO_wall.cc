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
node tree[100000 * 4];
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
    tree[n].leftSeg = tree[n].rightSeg = tree[n].seg = 0;
    tree[n].index = l;
    return;
  }
  int mid = m(l, r);
  build(l, mid, l(n));
  build(mid + 1, r, r(n));
  pushUp(n);
}
void update(int l, int r, int n, bool isAdd) {
  if (tree[n].left == l && tree[n].right == r) {
    if (isAdd)
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
    update(l, r, l(n), isAdd);

  else if (l > mid && mid != 0)
    update(l, r, r(n), isAdd);

  else if (mid != 0) {
    update(l, mid, l(n), isAdd);
    update(mid + 1, r, r(n), isAdd);
  }
  pushUp(n);
}

state query(int n) {
  if (tree[n].left == tree[n].right)
    return state(tree[n].index, tree[n].seg);

  if (tree[n].propagate)
    pushDown(n);

  int midSeg = tree[l(n)].rightSeg + tree[r(n)].leftSeg;
  int midIndex = tree[l(n)].right - tree[l(n)].rightSeg + 1;

  if (tree[l(n)].seg >= max(tree[r(n)].seg, midSeg)) {
    return state(tree[l(n)].index, tree[l(n)].seg);
  } else if (midSeg >= tree[r(n)].seg) {
    return state(midIndex, midSeg);
  } else {
    return state(tree[r(n)].index, tree[r(n)].seg);
  }
}

int main() {
  scanf("%d%d", &n, &m);

  build(1, n, 1);

  for (int i = 0; i < m; i++) {
    int t;
    scanf("%d", &t);
    if (t == 1 || t == 0) {
      int l, r;
      scanf("%d%d", &l, &r);
      update(l, r, 1, t == 1);

      if (tree[1].propagate)
        pushDown(1);
      int maxSize = max(tree[l(1)].seg, tree[r(1)].seg);
      int midSeg = tree[l(1)].rightSeg + tree[r(1)].leftSeg;
      printf("%d\n", max(maxSize, midSeg));

    } else {
      state res = query(1);
      if (res.seg != 0)
        update(res.index, res.index + res.seg - 1, 1, 0);
    }
  }
  return 0;
}
