#include <bits/stdc++.h>

#define pb push_back
#define mk make_pair
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) >> 1

using namespace std;

typedef long long LL;
typedef unsigned long long ULL;
typedef pair<int, int> PII;
typedef pair<double, double> PDD;
typedef pair<LL, LL> PLL;

const double EPS = 1e-9;
const int MAX_N = 100;

int T, N, M;
int row[MAX_N], col[MAX_N];
map<int, int> diag1, diag2;
int rook[MAX_N][MAX_N], bishop[MAX_N][MAX_N], added[MAX_N][MAX_N];
int main() {
  // freopen("in.txt", stdin);
  // freopen("out.txt", stdout);

  scanf("%d", &T);

  for (int t = 1; t <= T; t++) {
    printf("Case #%d: ", t);
    scanf("%d%d", &N, &M);
    for (int i = 0; i < N; i++)
      for (int j = 0; j < N; j++) {
        row[i] = col[j] = diag1[i + j] = diag2[i - j] = 1;
        rook[i][j] = bishop[i][j] = added[i][j] = 0;
      }

    for (int i = 0; i < M; i++) {
      char type;
      int r, c;
      scanf(" %c%d%d", &type, &r, &c);
      r--, c--;
      if (type != '+')
        row[r] = 0, col[c] = 0, rook[r][c] = 1;
      if (type != 'x')
        diag1[r + c] = diag2[r - c] = 0, bishop[r][c] = 1;
    }

    for (int i = 0; i < N; i++)
      if (row[i])
        for (int j = 0; j < N; j++)
          if (col[j]) {
            row[i] = col[j] = 0;
            added[i][j] = 1;
            rook[i][j] = 1;
            break;
          }

    map<int, int> occ;
    for (int i = 0; i < N; i++)
      for (int j = 0; j < N; j++)
        occ[i + j]++;

    vector<PII> diagOcc;
    for (PII p : occ)
      diagOcc.pb({p.second, p.first});
    sort(diagOcc.begin(), diagOcc.end());

    for (int x = 0; x < (int)diagOcc.size(); x++) {
      int diag = diagOcc[x].second;
      if (diag1[diag] == 0)
        continue;
      for (int i = 0; i < N; i++) {
        int j = diag - i;
        if (j < 0 || j >= N)
          continue;
        if (diag2[i - j]) {
          diag1[i + j] = diag2[i - j] = 0;
          bishop[i][j] = 1;
          added[i][j] = 1;
          break;
        }
      }
    }
    int ans = 0;
    vector<pair<char, PII>> loc;

    for (int i = 0; i < N; i++)
      for (int j = 0; j < N; j++) {
        ans += rook[i][j] + bishop[i][j];
        if (added[i][j]) {
          if (bishop[i][j] && rook[i][j])
            loc.push_back({'o', {i, j}});
          else if (bishop[i][j])
            loc.push_back({'+', {i, j}});
          else if (rook[i][j])
            loc.push_back({'x', {i, j}});
        }
      }
    printf("%d %d\n", ans, (int)loc.size());
    for (pair<char, PII> p : loc)
      printf("%c %d %d\n", p.first, p.second.first + 1, p.second.second + 1);
  }

  return 0;
}
