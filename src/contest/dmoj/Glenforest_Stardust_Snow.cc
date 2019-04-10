#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 52
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int dp[SIZE][SIZE][SIZE][SIZE];
pi snowflake[SIZE][SIZE];
int r, c, s, b, k, m;

int solve(int time, int col, int temp, int snow) {
  if (time == r + 1 || temp == 0 || snow == 0)
    return 0;
  int &res = dp[time][col][temp][snow];
  if (res != -1)
    return res;
  res = 0;
  for (int i = -m; i <= m; i++) {
    if (i + col < 1 || i + col > c)
      continue;
    res = max(res, solve(time + 1, i + col, temp, snow));
    if (snowflake[time + 1][i + col].first != -1 && temp > snowflake[time + 1][i + col].second) {
      res = max(
          res, snowflake[time + 1][i + col].first +
                   solve(time + 1, i + col, temp - snowflake[time + 1][i + col].second, snow - 1));
    }
  }
  return res;
}

int main() {
  scanf("%d%d%d%d%d%d", &r, &c, &s, &b, &k, &m);
  for (int i = 0; i < SIZE; i++) {
    for (int j = 0; j < SIZE; j++) {
      snowflake[i][j] = mp(-1, -1);
    }
  }
  for (int i = 0; i < s; i++) {
    int temp, value, c, r;
    scanf("%d%d%d%d", &temp, &value, &c, &r);
    snowflake[r][c] = mp(value, temp);
  }
  memset(dp, -1, sizeof dp);
  printf("%d\n", solve(0, 1, b, k));
}
