#include "secret.h"
#include <bits/stdc++.h>

using namespace std;

int seg[1001][1001];

void compute(int l, int r) {
  if (r - l <= 1)
    return;
  int m = (l + r) / 2;
  for (int i = m - 1; i >= l; i--)
    if (seg[i][m] == -1)
      seg[i][m] = Secret(seg[i][i + 1], seg[i + 1][m]);

  for (int i = m + 1; i <= r; i++)
    if (seg[m][i] == -1)
      seg[m][i] = Secret(seg[m][i - 1], seg[i - 1][i]);
  compute(l, m);
  compute(m, r);
}

void Init(int N, int A[]) {
  for (int i = 0; i <= N; i++)
    for (int j = 0; j <= N; j++)
      seg[i][j] = -1;
  for (int i = 0; i < N; i++)
    seg[i][i + 1] = A[i];
  compute(0, N);
}

int Query(int L, int R) {
  for (int i = L + 1; i <= R; i++)
    if (seg[L][i] != -1 && seg[i][R + 1] != -1)
      return Secret(seg[L][i], seg[i][R + 1]);
  return seg[L][R + 1];
}
