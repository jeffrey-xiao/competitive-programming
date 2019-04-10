#include <bits/stdc++.h>

using namespace std;

#define l(x) (x) << 1
#define r(x) (x) << 1 | 1
#define m(x, y) (x + y) / 2

struct node {
  int left, right, mid;
  int leftColor;
  int rightColor;
  int segCount;
  bool propagate;
};

node seg_tree[1500005];
int colors[5000005];
int n, c, d = 0;
bool isFlip = false;

void pushUp(int n) {
  // update the current node with the information from children
  seg_tree[n].leftColor = seg_tree[l(n)].leftColor;
  seg_tree[n].rightColor = seg_tree[r(n)].rightColor;
  seg_tree[n].segCount = seg_tree[l(n)].segCount + seg_tree[r(n)].segCount -
                         (seg_tree[l(n)].rightColor == seg_tree[r(n)].leftColor);
}

void pushDown(int n) {
  // update the children node with the information from the current node
  seg_tree[l(n)].leftColor = seg_tree[l(n)].rightColor = seg_tree[n].leftColor;
  seg_tree[r(n)].leftColor = seg_tree[r(n)].rightColor = seg_tree[n].leftColor;
  seg_tree[l(n)].segCount = seg_tree[r(n)].segCount = 1;
  seg_tree[l(n)].propagate = seg_tree[r(n)].propagate = true;
  seg_tree[n].propagate = false;
}

void update(int l, int r, int num, int color) {
  if (seg_tree[num].left == l && seg_tree[num].right == r) {
    seg_tree[num].leftColor = color;
    seg_tree[num].rightColor = color;
    seg_tree[num].segCount = 1;
    seg_tree[num].propagate = true;
    return;
  }
  int mid = seg_tree[num].mid;
  if (seg_tree[num].propagate)
    pushDown(num);

  if (r <= mid)
    update(l, r, l(num), color);

  else if (l > mid && mid != 0)
    update(l, r, r(num), color);

  else if (mid != 0) {
    update(l, mid, l(num), color);
    update(mid + 1, r, r(num), color);
  }
  pushUp(num);
}
int countColor(int l, int r, int num) {
  if (seg_tree[num].left == l && seg_tree[num].right == r)
    return seg_tree[num].segCount;

  if (seg_tree[num].propagate)
    pushDown(num);

  int mid = seg_tree[num].mid;

  if (r <= mid)
    return countColor(l, r, 2 * num);
  else if (l > mid && mid != 0)
    return countColor(l, r, 2 * num + 1);
  else if (mid != 0)
    return countColor(l, mid, 2 * num) + countColor(mid + 1, r, 2 * num + 1) -
           (seg_tree[l(num)].rightColor == seg_tree[r(num)].leftColor);

  pushUp(num);
}
int getColor(int x, int num) {
  if (seg_tree[num].left == x && seg_tree[num].right == x) {
    return seg_tree[num].leftColor;
  }
  if (seg_tree[num].propagate) {
    pushDown(num);
  }

  int mid = seg_tree[num].mid;

  if (x <= mid)
    return getColor(x, l(num));
  else if (x > mid && mid != 0)
    return getColor(x, r(num));
}
void build(int l, int r, int num) {
  seg_tree[num].left = l;
  seg_tree[num].right = r;
  seg_tree[num].propagate = false;
  seg_tree[num].mid = m(l, r);
  if (l == r) {
    seg_tree[num].leftColor = seg_tree[num].rightColor = colors[l];
    seg_tree[num].segCount = 1;
    return;
  }
  build(l, seg_tree[num].mid, l(num));
  build(seg_tree[num].mid + 1, r, r(num));
  pushUp(num);
}

static void convert(int &x, int &y) {
  if (!isFlip) {
    x = (x - d + n) % n;
    y = (y - d + n) % n;
  } else {
    x = (n - x + 2 - d + n) % n;
    y = (n - y + 2 - d + n) % n;
    swap(x, y);
  }
  x = (x ? x : n);
  y = (y ? y : n);
}

int main() {
  scanf("%d%d", &n, &c);

  for (int x = 1; x <= n; x++)
    scanf("%d", &colors[x]);
  build(1, n, 1);
  int q;
  scanf("%d", &q);

  for (int x = 0; x < q; x++) {
    string c;
    cin >> c;
    if (c == "R") {
      int k;
      scanf("%d", &k);
      if (!isFlip)
        d = (d + k) % n;
      else
        d = (d - k + n) % n;
    } else if (c == "F") {
      isFlip = !isFlip;
    } else if (c == "S") {
      int a, b;
      scanf("%d%d", &a, &b);
      convert(a, b);
      int colorA = getColor(a, 1);
      int colorB = getColor(b, 1);
      update(a, a, 1, colorB);
      update(b, b, 1, colorA);
    } else if (c == "P") {
      int a, b, x;
      scanf("%d%d%d", &a, &b, &x);
      convert(a, b);
      if (a <= b)
        update(a, b, 1, x);
      else {
        update(a, n, 1, x), update(1, b, 1, x);
      }
    } else if (c == "CS") {
      int x, y;
      scanf("%d%d", &x, &y);
      convert(x, y);
      int result = 0;
      if (x <= y)
        result = countColor(x, y, 1);
      else {
        result = countColor(1, y, 1) + countColor(x, n, 1);
        if (getColor(1, 1) == getColor(n, 1))
          result--;
      }

      printf("%d\n", result);
    } else if (c == "C") {
      int result = countColor(1, n, 1);
      if (getColor(1, 1) == getColor(n, 1) && result != 1)
        result--;
      printf("%d\n", result);
    }
  }
}
