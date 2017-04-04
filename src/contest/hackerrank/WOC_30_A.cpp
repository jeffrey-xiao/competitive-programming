#include <bits/stdc++.h>

using namespace std;

#define MAX_T 100

int C[MAX_T];

int main () {
  int N, T;
  scanf("%d%d", &N, &T);

  for (int i = 0; i < T; i++)
    scanf("%d", C + i);

  int ans = 0;
  int curr = N;

  for (int i = 0; i < T - 1; i++) {
    curr -= C[i];
    if (curr < 5) {
      ans += N - curr;
      curr += N - curr;
    }
  }
  printf("%d\n", ans);
  return 0;
}
