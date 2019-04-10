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

int N, ans;
int p[] = {0, 1, 2, 3, 4, 5, 6, 7}, g[8][8];

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  scanf("%d", &N);
  for (int i = 0; i < N; i++) {
    int u, v;
    scanf("%d%d", &u, &v);
    --u, --v;
    g[u][v] = g[v][u] = 1;
  }

  do {
    if (g[p[0]][p[1]] && g[p[0]][p[3]] && g[p[1]][p[2]] && g[p[2]][p[3]] && g[p[0]][p[5]] &&
        g[p[1]][p[4]] && g[p[2]][p[7]] && g[p[3]][p[6]] && g[p[4]][p[5]] && g[p[4]][p[7]] &&
        g[p[5]][p[6]] && g[p[6]][p[7]])
      ans = 1;
  } while (next_permutation(p, p + 8));
  printf("%s\n", ans ? "Ja" : "Nej");
  return 0;
}
