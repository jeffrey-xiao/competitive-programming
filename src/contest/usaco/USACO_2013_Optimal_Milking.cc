#include <bits/stdc++.h>

using namespace std;

#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2
#define K (1 << 16)
struct node {
  // first character represents left endpoint
  // second character represents right endpoint
  // u represents unmilked　and m represents milked

  int uu, um, mm, mu;
};

node tree[1 << 17];

void update(int n) {
  tree[n].mm = max(tree[l(n)].mu + tree[r(n)].mm, tree[l(n)].mm + tree[r(n)].um);
  tree[n].uu = max(tree[l(n)].uu + tree[r(n)].mu, tree[l(n)].um + tree[r(n)].uu);
  tree[n].mu = max(tree[l(n)].mm + tree[r(n)].uu, tree[l(n)].mu + tree[r(n)].mu);
  tree[n].um = max(tree[l(n)].uu + tree[r(n)].mm, tree[l(n)].um + tree[r(n)].um);
}
int n, d;

int main() {
  scanf("%d%d", &n, &d);

  for (int x = 0; x < n; x++) {
    int a;
    scanf("%d", &a);
    tree[K + x].mm = a;
  }
  for (int x = K - 1; x > 0; x--)
    update(x);
  long long maxV = 0;
  for (int x = 0; x < d; x++) {
    int a, b;
    scanf("%d%d", &a, &b);
    tree[K + a - 1].mm = b;
    for (int y = (K + a - 1) / 2; y > 0; y = y >> 1)
      update(y);
    maxV += max(max(tree[1].mm, tree[1].mu), max(tree[1].um, tree[1].uu));
  }
  printf("%lld\n", maxV);
  return 0;
}
