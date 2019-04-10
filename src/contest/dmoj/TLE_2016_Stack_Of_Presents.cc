#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define MAX_N 100000
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int N;
int W[MAX_N];

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  scanf("%d", &N);
  for (int i = 0; i < N; i++)
    scanf("%d", &W[i]);

  sort(W, W + N);
  int sum = 0;
  int ans = 0;
  for (int i = 0; i < N; i++) {
    if (sum <= W[i]) {
      sum += W[i];
      ans++;
    }
  }
  printf("%d\n", ans);
  return 0;
}
