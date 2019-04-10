#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007LL
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define MAX_N 501
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

ll pow(ll a, ll b) {
  if (b == 0)
    return 1;
  if (b == 1)
    return a;
  if (b % 2)
    return a * pow(a * a % MOD, b / 2) % MOD;
  return pow(a * a % MOD, b / 2);
}

int N;
ll val[MAX_N][MAX_N], det = 1;

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  scanf("%d", &N);

  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
      scanf("%lld", &val[i][j]);
      if (val[i][j] < 0)
        val[i][j] += MOD;
    }
  }

  for (int i = 0; i < N; i++) {
    int maxIndex = i;
    for (int j = i + 1; j < N; j++)
      if (val[j][i] > val[maxIndex][i])
        maxIndex = j;

    if (maxIndex != i) {
      det = -det;
      swap(val[i], val[maxIndex]);
    }

    for (int j = i + 1; j < N; j++) {
      ll f = val[j][i] * pow(val[i][i], MOD - 2) % MOD;
      for (int k = i; k < N; k++)
        val[j][k] = ((val[j][k] - f * val[i][k]) % MOD + MOD) % MOD;
    }
  }

  for (int i = 0; i < N; i++)
    det = det * val[i][i] % MOD;
  printf("%lld\n", (det + MOD) % MOD);
  return 0;
}
