#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define MAX_N 1000
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int N;
int val[MAX_N];
int dp[MAX_N][MAX_N];

int get(int l, int r) {
  return l > r ? 0 : dp[l][r];
}

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  scanf("%d", &N);

  for (int i = 0; i < N; i++)
    scanf("%d", &val[i]);

  // gap
  for (int i = 0; i < N; i++) {
    for (int j = 0; j + i < N; j++) {
      int l = j, r = j + i;
      if (l == r)
        dp[l][r] = 1;
      else if (l == r + 1)
        dp[l][r] = val[l] == val[r] ? 1 : 2;
      else {
        dp[l][r] = INF;
        for (int x = l + 1; x <= r; x++)
          if (val[x] == val[l])
            dp[l][r] = min(dp[l][r], get(l + 1, x) + get(x + 1, r));
        for (int x = l; x <= r - 1; x++)
          if (val[x] == val[r])
            dp[l][r] = min(dp[l][r], get(x, r - 1) + get(l, x - 1));
        for (int x = l; x < r; x++)
          dp[l][r] = min(dp[l][r], dp[l][x] + dp[x + 1][r] - (val[x] == val[x + 1]));
      }
    }
  }
  printf("%d\n", dp[0][N - 1]);
  return 0;
}
