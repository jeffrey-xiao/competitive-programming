#include <bits/stdc++.h>

using namespace std;

#define MOD 10301
#define SIZE 1001

int n, a, b;

int dp1[SIZE][SIZE][2];
int dp2[SIZE][SIZE][2];

int solve2(int left, int s, int change) {
  if (dp2[left][s][change] != -1)
    return dp2[left][s][change];
  int res = change;
  if (left > 0)
    res = (res + solve2(left - 1, s + 1, 0)) % MOD;
  if (left >= s)
    res = (res + solve2(left - s, s, 1)) % MOD;
  return dp2[left][s][change] = res;
}

int solve1(int left, int s, int change) {
  if (dp1[left][s][change] != -1)
    return dp1[left][s][change];
  int res = 0;
  if (change != 0)
    res = (res + solve2(left, b, 1)) % MOD;
  if (left > 0)
    res = (res + solve1(left - 1, s + 1, 0)) % MOD;
  if (left >= s)
    res = (res + solve1(left - s, s, 1)) % MOD;
  return dp1[left][s][change] = res;
}
int main() {
  scanf("%d%d%d", &n, &a, &b);
  memset(dp1, -1, sizeof dp1);
  memset(dp2, -1, sizeof dp2);
  printf("%d\n", solve1(n - a - b, a, 1));
  return 0;
}
