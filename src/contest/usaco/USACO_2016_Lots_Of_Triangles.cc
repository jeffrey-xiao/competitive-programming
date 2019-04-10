#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 305
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int N;
int cnt[SIZE][SIZE], ans[SIZE];
ll X[SIZE], Y[SIZE];

ll cross(ll x1, ll y1, ll x2, ll y2) {
  return x1 * y2 - x2 * y1;
}

ll area(int i, int j, int k) {
  return abs(X[i] * Y[j] + X[j] * Y[k] + X[k] * Y[i] - X[j] * Y[i] - X[k] * Y[j] - X[i] * Y[k]);
}

ll areaUnder(int i, int j) {
  ll dx = abs(X[i] - X[j]);
  ll dy = min(Y[i], Y[j]);
  ll maxY = max(Y[i], Y[j]);
  return dx * dy * 2L + dx * (maxY - dy);
}

int main() {
  freopen("triangles.in", "r", stdin);
  freopen("triangles.out", "w", stdout);

  scanf("%d", &N);

  for (int i = 0; i < N; i++) {
    scanf("%lld%lld", X + i, Y + i);
    X[i]++;
    Y[i]++;
  }

  for (int i = 0; i < N; i++) {
    for (int j = i + 1; j < N; j++) {
      ll xlo = min(X[i], X[j]);
      ll xhi = max(X[i], X[j]);
      ll ylo, yhi;

      if (xlo == X[i]) {
        ylo = Y[i];
        yhi = Y[j];
      } else {
        ylo = Y[j];
        yhi = Y[i];
      }

      for (int k = 0; k < N; k++) {
        if (k == i || k == j)
          continue;
        if (xlo < X[k] && X[k] <= xhi) {
          ll x = X[k] - xlo;
          ll y = Y[k] - ylo;
          ll ret = cross(xhi - xlo, yhi - ylo, x, y);
          if (ret <= 0)
            cnt[i][j]++;
        }
      }
    }
  }

  for (int i = 0; i < N; i++) {
    for (int j = i + 1; j < N; j++) {
      for (int k = j + 1; k < N; k++) {
        ll t = area(i, j, k);
        if (areaUnder(i, j) == 0)
          ans[abs(cnt[j][k] - cnt[i][k]) - (X[i] < X[k] ? 0 : 1)]++;
        else if (areaUnder(j, k) == 0)
          ans[abs(cnt[i][j] - cnt[i][k]) - (X[j] < X[i] ? 0 : 1)]++;
        else if (areaUnder(i, k) == 0)
          ans[abs(cnt[j][k] - cnt[i][j]) - (X[i] < X[j] ? 0 : 1)]++;
        else if (areaUnder(i, j) - areaUnder(j, k) - areaUnder(i, k) == t)
          ans[cnt[i][j] - cnt[j][k] - cnt[i][k] - 1]++;
        else if (areaUnder(j, k) - areaUnder(i, j) - areaUnder(i, k) == t)
          ans[cnt[j][k] - cnt[i][j] - cnt[i][k] - 1]++;
        else if (areaUnder(i, k) - areaUnder(i, j) - areaUnder(j, k) == t)
          ans[cnt[i][k] - cnt[i][j] - cnt[j][k] - 1]++;
        else if (areaUnder(i, k) + areaUnder(j, k) - areaUnder(i, j) == t)
          ans[cnt[i][k] + cnt[j][k] - cnt[i][j]]++;
        else if (areaUnder(j, k) + areaUnder(i, j) - areaUnder(i, k) == t)
          ans[cnt[j][k] + cnt[i][j] - cnt[i][k]]++;
        else if (areaUnder(i, k) + areaUnder(i, j) - areaUnder(j, k) == t)
          ans[cnt[i][k] + cnt[i][j] - cnt[j][k]]++;
      }
    }
  }

  for (int i = 0; i <= N - 3; i++)
    printf("%d\n", ans[i]);

  return 0;
}
