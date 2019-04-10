#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define MAX_N 20
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int N, M;
int A[MAX_N][MAX_N];

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  scanf("%d%d", &N, &M);

  for (int i = 0; i < M; i++) {
    int a, b;
    scanf("%d%d", &a, &b);
    --a, --b;
    A[a][b] = A[b][a] = 1;
  }

  int ans = 0;
  for (int i = 0; i < 1 << N; i++) {
    bool valid = 1;
    for (int j = 0; j < N; j++)
      for (int k = j + 1; k < N; k++)
        if (i & 1 << j && i & 1 << k && A[j][k])
          valid = 0;
    ans += valid;
  }
  printf("%d\n", ans);
  return 0;
}
