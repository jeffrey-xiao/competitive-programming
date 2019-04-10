#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 701
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int r, c, h, lc;
int mx[SIZE][SIZE][8], mn[SIZE][SIZE][8];
double logV[SIZE + 1];
int getMax(int c1, int c2, int r) {
  int sz = (int)(logV[c2 - c1 + 1] / logV[2]);
  return max(mx[r][c1][sz], mx[r][c2 - (1 << sz) + 1][sz]);
}
int getMin(int c1, int c2, int r) {
  int sz = (int)(logV[c2 - c1 + 1] / logV[2]);
  return min(mn[r][c1][sz], mn[r][c2 - (1 << sz) + 1][sz]);
}
int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  for (int i = 1; i <= SIZE; i++)
    logV[i] = log(i);
  rint(c), rint(r), rint(h);
  lc = 1 + min((int)(logV[c] / logV[2]), (int)(logV[100] / logV[2]));
  for (int i = 1; i <= r; i++)
    for (int j = 1; j <= c; j++) {
      rint(mx[i][j][0]);
      mn[i][j][0] = mx[i][j][0];
    }
  for (int i = 1; i <= r; i++)
    for (int l = 1; l < lc; l++)
      for (int j = 1; j + (1 << l) - 1 <= c; j++) {
        mx[i][j][l] = max(mx[i][j][l - 1], mx[i][j + (1 << (l - 1))][l - 1]);
        mn[i][j][l] = min(mn[i][j][l - 1], mn[i][j + (1 << (l - 1))][l - 1]);
      }
  int ans = 0;
  int lor = 0, hir = 0, loc = 0, hic = 0;
  deque<pi> minq;
  deque<pi> maxq;
  for (int i = 1; i <= c; i++) {
    int to = min(c, i + 99);
    for (int j = i; j <= to; j++) {
      int lo = 1, hi = 1;
      minq.clear();
      maxq.clear();
      while (hi <= r) {
        int maxV = getMax(i, j, hi);
        int minV = getMin(i, j, hi);
        while (!minq.empty() && minq.back().first >= minV)
          minq.pop_back();
        if (!minq.empty() && minq.front().second < lo)
          minq.pop_front();
        minq.push_back(mp(minV, hi));
        while (!maxq.empty() && maxq.back().first <= maxV)
          maxq.pop_back();
        if (!maxq.empty() && maxq.front().second < lo)
          maxq.pop_front();
        maxq.push_back(mp(maxV, hi));
        if (maxq.front().first - minq.front().first <= h) {
          if ((hi - lo + 1) * (j - i + 1) > ans) {
            ans = (hi - lo + 1) * (j - i + 1);
            loc = i;
            hic = j;
            lor = r - hi + 1;
            hir = r - lo + 1;
          }
          hi++;
        } else {
          hi++;
          lo++;
        }
      }
    }
  }
  printf("%d %d %d %d\n", loc, lor, hic, hir);
  return 0;
}
