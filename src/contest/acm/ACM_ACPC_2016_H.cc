#include <bits/stdc++.h>
using namespace std;
int main() {
  int T;
  scanf("%d", &T);
  for (int i = 0; i < T; i++) {
    int v;
    scanf("%d", &v);
    printf("%d\n", (v + 399) / 400);
  }
  return 0;
}
