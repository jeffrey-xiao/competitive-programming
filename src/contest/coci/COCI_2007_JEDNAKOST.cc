#include <bits/stdc++.h>

using namespace std;

#define INF 1 << 30

int n;
char A[1001];
int zero[1000];
int S;
int dp[1001][5001];

int solve(int i, int t) {
  int &ref = dp[i][t];
  if (i == n)
    return ref = (t == 0 ? 0 : INF);
  if (ref != -1)
    return ref;

  ref = INF;
  int next = 0;
  for (int j = zero[i]; j < n; j++) {
    next = next * 10 + A[j] - '0';
    if (next > t)
      break;
    ref = min(ref, solve(j + 1, t - next) + 1);
  }
  return ref;
}

void print(int i, int t) {
  if (i == n) {
    printf("=%d\n", S);
    return;
  }
  if (i != 0)
    printf("+");
  int next = 0;
  for (int j = i; j < n; j++) {
    printf("%c", A[j]);
    next = next * 10 + A[j] - '0';
    if (solve(i, t) == 1 + solve(j + 1, t - next)) {
      print(j + 1, t - next);
      return;
    }
  }
}

int main() {
  scanf("%[^=]=%d", A, &S);
  n = strlen(A);
  memset(dp, -1, sizeof dp);
  zero[n - 1] = n - 1;
  for (int i = n - 2; i >= 0; i--)
    zero[i] = (A[i] == '0') ? zero[i + 1] : i;
  print(0, S);

  return 0;
}
