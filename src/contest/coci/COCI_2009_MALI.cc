#include <bits/stdc++.h>

using namespace std;

int n;
int A[101], B[101], TA[101], TB[101];

int main() {
  scanf("%d", &n);
  for (int x = 0; x < n; x++) {
    int a, b;
    scanf("%d%d", &a, &b);
    A[a]++;
    B[b]++;
    for (int y = 1; y <= 100; y++) {
      TA[y] = A[y];
      TB[y] = B[y];
    }
    int maxV = -(1 << 30);
    int ia = 1;
    int ib = 100;
    while (true) {
      while (ia <= 100 && TA[ia] == 0)
        ia++;
      while (ib >= 1 && TB[ib] == 0)
        ib--;
      if (ia == 101 || ib == 0)
        break;
      maxV = max(maxV, ia + ib);
      if (TA[ia] >= TB[ib]) {
        TA[ia] -= TB[ib];
        TB[ib] = 0;
      } else {
        TB[ib] -= TA[ia];
        TA[ia] = 0;
      }
    }
    printf("%d\n", maxV);
  }
  return 0;
}
