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

int T, N, P, R[50], Q[50][50];
int main() {
  // freopen("in.txt", stdin);
  // freopen("out.txt", stdout);

  scanf("%d", &T);

  for (int t = 1; t <= T; t++) {
    scanf("%d%d", &N, &P);

    for (int i = 0; i < N; i++)
      scanf("%d", &R[i]);
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < P; j++) {
        scanf("%d", &Q[i][j]);
      }
      sort(Q[i], Q[i] + P);
    }
    int index[N];
    memset(index, 0, sizeof(index));
    int ans = 0;
    bool valid = true;
    while (valid) {
      int maxVal = -(1 << 30);
      int minVal = 1 << 30;
      int minIndex = -1;
      int minValue = 1 << 30;
      for (int i = 0; i < N; i++) {
        int r = (int)floor(Q[i][index[i]] * 10.0 / 9 / R[i]);
        int l = (int)ceil(Q[i][index[i]] * 10.0 / 11 / R[i]);
        maxVal = max(maxVal, l);
        minVal = min(minVal, r);
        if (l < minValue) {
          minValue = l;
          minIndex = i;
        }
      }
      if (maxVal <= minVal) {
        ans++;
        for (int j = 0; j < N; j++)
          if (++index[j] >= P)
            valid = false;
      } else {
        index[minIndex]++;
        if (index[minIndex] >= P)
          valid = false;
      }
    }
    printf("Case #%d: %d\n", t, ans);
  }

  return 0;
}
