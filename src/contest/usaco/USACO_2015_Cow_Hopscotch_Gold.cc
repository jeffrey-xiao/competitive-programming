#include <bits/stdc++.h>

using namespace std;

#define MOD 1000000007
#define pb push_back

int r, c, k;
int g[751][751], prefix[751][751];
int szx[750 * 750 + 1];
int szy[750 * 750 + 1];
int ***bit;

inline void update(int x, int y, int **tree, int v, int k) {
  for (int idx = x; idx < szx[k]; idx += (idx & -idx)) {
    for (int idy = y; idy < szy[k]; idy += (idy & -idy)) {
      tree[idx][idy] = (tree[idx][idy] + v) % MOD;
    }
  }
}

inline int query(int x, int y, int **tree) {
  int sum = 0;
  if (x == 0 || y == 0)
    return 0;
  for (int idx = x; idx > 0; idx -= (idx & -idx)) {
    for (int idy = y; idy > 0; idy -= (idy & -idy)) {
      sum = (sum + tree[idx][idy]) % MOD;
    }
  }
  return sum;
}

int main() {
  scanf("%d %d %d", &r, &c, &k);
  unordered_map<int, int> toCx[k];
  unordered_map<int, int> toCy[k];
  for (int i = 1; i <= r; i++) {
    for (int j = 1; j <= c; j++) {
      scanf("%d", &g[i][j]);
      g[i][j]--;
    }
  }
  for (int i = 1; i <= r; i++) {
    for (int j = 1; j <= c; j++) {
      int a = g[i][j];
      if (!toCx[a].count(i)) {
        toCx[a][i] = toCx[a].size();
      }
    }
  }
  for (int j = 1; j <= c; j++) {
    for (int i = 1; i <= r; i++) {
      int a = g[i][j];
      if (!toCy[a].count(j)) {
        toCy[a][j] = toCy[a].size();
      }
    }
  }
  bit = new int **[k];
  for (int i = 0; i < k; i++) {
    int x = toCx[i].size() + 1;
    int y = toCy[i].size() + 1;
    szx[i] = x;
    szy[i] = y;
    bit[i] = new int *[x];
    for (int j = 0; j < x; j++) {
      bit[i][j] = new int[y];
      for (int z = 0; z < y; z++)
        bit[i][j][z] = 0;
    }
  }

  int sum = 0;
  update(1, 1, bit[g[1][1]], 1, g[1][1]);
  prefix[1][1] = 1;
  for (int i = 1; i <= r; i++) {
    for (int j = 1; j <= c; j++) {
      if (i == 1 && j == 1)
        continue;
      sum = 0;
      int id = g[i][j];
      int x = toCx[id].at(i);
      int y = toCy[id].at(j);
      sum = (prefix[i - 1][j - 1] - query(x - 1, y - 1, bit[id])) % MOD;

      update(x, y, bit[id], sum, id);
      prefix[i][j] =
          ((((sum + prefix[i - 1][j]) % MOD) + prefix[i][j - 1]) % MOD - prefix[i - 1][j - 1]) %
          MOD;
    }
  }
  if (sum < 0)
    printf("%d\n", sum + MOD);
  else
    printf("%d\n", sum % MOD);
  return 0;
}
