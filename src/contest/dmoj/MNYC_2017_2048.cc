#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 500100
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int N;
int val[60];
int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  scanf("%d", &N);

  for (int i = 0; i < N; i++) {
    int x;
    scanf("%d", &x);
    int cnt = 0;
    while (x) {
      x >>= 1;
      cnt++;
    }
    val[cnt]++;
  }

  ll ans = 0;

  for (int i = 1; i < 60; i++) {
    val[i] += val[i - 1] / 2;
    if (val[i])
      ans = 1LL << (i - 1);
  }

  printf("%lld\n", ans);
  return 0;
}
