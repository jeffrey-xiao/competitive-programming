#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define MAX_R 5000
#define MAX_C 200
#define COMPRESSION_SIZE 15
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int R, C, E;
int H[MAX_R][MAX_C], V[MAX_R][MAX_C];
array<array<int, MAX_C>, MAX_C> seg[4 * ((MAX_R - 2) / COMPRESSION_SIZE)];

array<array<int, MAX_C>, MAX_C> computeNextRow(int row) {
  array<array<int, MAX_C>, MAX_C> ret;

  for (int i = 0; i < MAX_C; i++)
    for (int j = 0; j < MAX_C; j++)
      ret[i][j] = 0;

  // computing current row first

  // going right
  for (int i = 0; i < C; i++)
    for (int j = 0; j < i; j++)
      ret[j][i] = ret[j][i - 1] + H[row][i - 1];

  // going left
  for (int i = C - 1; i >= 0; i--)
    for (int j = C - 1; j > i; j--)
      ret[j][i] = ret[j][i + 1] + H[row][i];

  for (int i = 0; i < C; i++)
    for (int j = 0; j < C; j++)
      ret[i][j] += V[row][j];

  // computing next row

  // going right
  for (int i = 1; i < C; i++)
    for (int j = 0; j < C; j++)
      ret[j][i] = min(ret[j][i], ret[j][i - 1] + H[row + 1][i - 1]);

  // going left
  for (int i = C - 2; i >= 0; i--)
    for (int j = C - 1; j >= 0; j--)
      ret[j][i] = min(ret[j][i], ret[j][i + 1] + H[row + 1][i]);

  return ret;
}

// [X, Y], [Y, Z]
array<array<int, MAX_C>, MAX_C> combine(
    array<array<int, MAX_C>, MAX_C> a, array<array<int, MAX_C>, MAX_C> b) {
  array<array<int, MAX_C>, MAX_C> ret;
  array<array<int, MAX_C>, MAX_C> best;

  for (int i = 0; i < MAX_C; i++)
    for (int j = 0; j < MAX_C; j++)
      ret[i][j] = best[i][j] = 0;

  // i <= j
  for (int d = 0; d < C; d++) {
    for (int i = 0; i + d < C; i++) {
      int j = i + d;
      ret[i][j] = 1 << 30;
      if (d <= 0) {
        for (int k = 0; k < C; k++) {
          int next = a[i][k] + b[k][j];
          if (next < ret[i][j]) {
            ret[i][j] = next;
            best[i][j] = k;
          }
        }
      } else {
        for (int k = best[i][j - 1]; k <= best[i + 1][j]; k++) {
          int next = a[i][k] + b[k][j];
          if (next < ret[i][j]) {
            ret[i][j] = next;
            best[i][j] = k;
          }
        }
      }
    }
  }

  // i > j
  for (int d = 1; d < C; d++) {
    for (int i = C - 1; i - d >= 0; i--) {
      int j = i - d;
      ret[i][j] = 1 << 30;
      for (int k = best[i - 1][j]; k <= best[i][j + 1]; k++) {
        int next = a[i][k] + b[k][j];
        if (next < ret[i][j]) {
          ret[i][j] = next;
          best[i][j] = k;
        }
      }
    }
  }

  return ret;
}

void update(int n, int l, int r, int x) {
  if (l == x && x == r) {
    int a = x * COMPRESSION_SIZE;
    int b = min(R - 1, (x + 1) * COMPRESSION_SIZE);
    seg[n] = computeNextRow(a);
    for (int i = a + 1; i < b; i++)
      seg[n] = combine(seg[n], computeNextRow(i));
    return;
  }

  int mid = (l + r) >> 1;
  if (x <= mid)
    update(n << 1, l, mid, x);
  else
    update(n << 1 | 1, mid + 1, r, x);

  seg[n] = combine(seg[n << 1], seg[n << 1 | 1]);
}

void build(int n, int l, int r) {
  if (l == r) {
    int a = l * COMPRESSION_SIZE;
    int b = min(R - 1, (l + 1) * COMPRESSION_SIZE);
    seg[n] = computeNextRow(a);
    for (int i = a + 1; i < b; i++)
      seg[n] = combine(seg[n], computeNextRow(i));
    return;
  }

  int mid = (l + r) >> 1;
  build(n << 1, l, mid);
  build(n << 1 | 1, mid + 1, r);

  seg[n] = combine(seg[n << 1], seg[n << 1 | 1]);
}

int main() {
  freopen("in.txt", "r", stdin);
  freopen("out.txt", "w", stdout);

  for (int m = 0; m < 4 * ((MAX_R - 2) / COMPRESSION_SIZE); m++)
    for (int i = 0; i < MAX_C; i++)
      for (int j = 0; j < MAX_C; j++)
        seg[m][i][j] = 0;

  scanf("%d%d", &R, &C);

  for (int i = 0; i < R; i++)
    for (int j = 0; j < C - 1; j++)
      scanf("%d", &H[i][j]);

  for (int i = 0; i < R - 1; i++)
    for (int j = 0; j < C; j++)
      scanf("%d", &V[i][j]);

  build(1, 0, (R - 2) / COMPRESSION_SIZE);

  scanf("%d", &E);

  for (int i = 0; i < E; i++) {
    int command;
    scanf("%d", &command);

    // change horizontal road
    if (command == 1) {
      int r, c;
      scanf("%d%d", &r, &c);
      scanf("%d", &H[r][c]);
      if (0 <= r - 1 && r - 1 <= R - 2)
        update(1, 0, (R - 2) / COMPRESSION_SIZE, (r - 1) / COMPRESSION_SIZE);
      if (0 <= r && r <= R - 2)
        update(1, 0, (R - 2) / COMPRESSION_SIZE, (r) / COMPRESSION_SIZE);
    } else if (command == 2) {
      int r, c;
      scanf("%d%d", &r, &c);
      scanf("%d", &V[r][c]);
      update(1, 0, (R - 2) / COMPRESSION_SIZE, (r) / COMPRESSION_SIZE);
    } else {
      int x1, x2;
      scanf("%d%d", &x1, &x2);
      printf("%d\n", seg[1][x1][x2]);
    }
  }
  return 0;
}
