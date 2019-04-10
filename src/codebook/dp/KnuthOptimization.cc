/*
 * Using Knuth's Optimization to reduce the runtime of this dynamic programming algorithm from
 * O(N^3) to O(N^2). The asymptotic time complexity can be proven by analyzing the telescoping
 * series.
 *
 * Time complexity: O(N^2)
 *
 * Reference problem: http://acm.zju.edu.cn/onlinejudge/showProblem.do?problemId=1860
 */

#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 1005
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

ll dp[SIZE][SIZE];
int mid[SIZE][SIZE];
int pos[SIZE];
int n, m;

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  while (scanf("%d%d", &n, &m) != EOF) {
    for (int i = 1; i <= m; i++)
      rint(pos[i]);
    pos[0] = 0;
    pos[m + 1] = n;

    for (int i = 0; i <= m + 1; i++) {       // length of section of cuts to compute
      for (int j = 0; j + i <= m + 1; j++) { // section of cuts to compute: [j, j + i]
        if (i < 2) {
          dp[j][j + i] = 0ll;
          mid[j][j + i] = j;
          continue;
        }
        dp[j][j + i] = 1ll << 60;
        for (int k = mid[j][i + j - 1]; k <= mid[j + 1][i + j]; k++) { // optimal place to cut
          ll next = dp[j][k] + dp[k][j + i] + pos[j + i] - pos[j];
          if (next < dp[j][j + i]) {
            dp[j][j + i] = next;
            mid[j][j + i] = k;
          }
        }
      }
    }
    printf("%lld\n", dp[0][m + 1]);
  }
  return 0;
}
