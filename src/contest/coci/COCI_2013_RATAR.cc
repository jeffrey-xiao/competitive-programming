#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 51
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int N;
int val[SIZE][SIZE];

int getSum(int x1, int y1, int x2, int y2) {
  x1--, y1--;
  return val[x2][y2] - val[x1][y2] - val[x2][y1] + val[x1][y1];
}

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  rint(N);

  for (int i = 1; i <= N; i++) {
    for (int j = 1; j <= N; j++) {
      rint(val[i][j]);
      val[i][j] += val[i - 1][j] + val[i][j - 1] - val[i - 1][j - 1];
    }
  }

  int ans = 0;

  for (int i = 1; i <= N; i++) {
    for (int j = 1; j <= N; j++) {
      // top left and bottom right
      unordered_map<int, int> occ;
      for (int k = 1; k <= i; k++) {
        for (int l = 1; l <= j; l++) {
          int currSum = getSum(k, l, i, j);
          occ[currSum]++;
        }
      }

      for (int k = i + 1; k <= N; k++) {
        for (int l = j + 1; l <= N; l++) {
          ans += occ[getSum(i + 1, j + 1, k, l)];
        }
      }
      // top right and bottom left
      occ.clear();

      for (int k = i; k <= N; k++) {
        for (int l = 1; l <= j; l++) {
          int currSum = getSum(i, l, k, j);
          occ[currSum]++;
        }
      }

      for (int k = 1; k < i; k++) {
        for (int l = j + 1; l <= N; l++) {
          ans += occ[getSum(k, j + 1, i - 1, l)];
        }
      }
    }
  }

  printf("%d\n", ans);
  return 0;
}
