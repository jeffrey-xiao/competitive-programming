#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 3000
#define DOWN 0
#define LEFT 1
#define RIGHT 2
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int N, R;
int A[SIZE][SIZE], B[SIZE][SIZE], dp[SIZE][SIZE][3];
int getMax(int i, int j) {
  return max(dp[i][j][DOWN], max(dp[i][j][LEFT], dp[i][j][RIGHT]));
}
int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  scanf("%d%d", &N, &R);

  for (int i = 0; i < N; i++) {
    rint(A[0][i]);
    A[0][i] *= -1;
    int curr = i;
    for (int j = 1; j < N; j++)
      A[j][(curr += R) % N] = A[0][i];
  }

  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N - 1; j++) {
      B[i][j] = A[i][j + 1] - A[i][j];
      for (int k = 0; k < 3; k++) {
        dp[i][j][k] = -1 << 30;
      }
    }
  }

  // B is N x N - 1

  for (int i = 0; i < N; i++) {
    // going down
    for (int j = 0; j < N - 1; j++) {
      dp[i][j][DOWN] = B[i][j] + (i > 0 ? getMax(i - 1, j) : 0);
    }

    // going left
    for (int j = N - 3; j >= 0; j--) {
      dp[i][j][LEFT] = B[i][j] + max(dp[i][j + 1][LEFT], dp[i][j + 1][DOWN]);
    }

    // going right
    for (int j = 1; j < N - 1; j++) {
      dp[i][j][RIGHT] = B[i][j] + max(dp[i][j - 1][RIGHT], dp[i][j - 1][DOWN]);
    }
  }

  int ans = -1 << 30;
  for (int j = 0; j < N - 1; j++)
    ans = max(ans, getMax(N - 1, j));
  printf("%d\n", ans);

  return 0;
}
