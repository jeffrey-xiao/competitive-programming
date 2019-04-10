#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 10010
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int R, C;
char grid[SIZE][SIZE];

char get() {
  char ch = '\n';
  while ((ch = getchar()) == '\n')
    ;
  return ch;
}

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  rint(R), rint(C);

  vector<pi> p;
  for (int i = 0; i < R; i++) {
    for (int j = 0; j < C; j++) {
      char ch = get();
      if (ch == 'X')
        p.pb({j, i});
    }
  }
  sort(p.begin(), p.end());
  for (int i = 1; i < p.size(); i++) {
    printf("%.3f\n",
        atan2(p[i].first - p[i - 1].first, -p[i].second + p[i - 1].second) / 3.141592653 * 180);
  }
  if (p.size() == 0)
    printf("0.000\n");

  return 0;
}
