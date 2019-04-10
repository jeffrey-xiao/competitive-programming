#include <bits/stdc++.h>

using namespace std;

const int SQRT = 200;
const int MAX_SIZE = 40010;

int N, Q;
int val[MAX_SIZE], block[MAX_SIZE], ans[MAX_SIZE], occ[MAX_SIZE];
int prefix[SQRT + 1][SQRT + 1][SQRT + 1];

struct Query {
  int l, r, x, y, index;
  Query(int l, int r, int x, int y, int index) : l(l), r(r), x(x), y(y), index(index){};

  bool operator<(const Query &q) const {
    if (block[l] == block[q.l])
      return block[l] < block[q.l];
    return r < q.r;
  }
};

int solveSmall(int i, int x, int y) {
  if (i == 0)
    return 0;
  int ret = prefix[x][block[i] - 1][y];
  for (int j = (block[i] - 1) * SQRT + 1; j <= i; j++)
    if (val[j] % x == y)
      ret++;
  return ret;
}

int main() {
  scanf("%d%d", &N, &Q);

  for (int i = 1; i <= N; i++)
    scanf("%d", &val[i]);

  for (int i = 1; i <= N; i++)
    block[i] = (i - 1) / SQRT + 1;

  for (int i = 1; i <= SQRT; i++) {
    for (int j = 1; j <= N; j++)
      prefix[i][block[j]][val[j] % i]++;
    for (int j = 1; j <= block[N]; j++)
      for (int k = 0; k < i; k++)
        prefix[i][j][k] += prefix[i][j - 1][k];
  }

  vector<Query> queries;

  for (int q = 0; q < Q; q++) {
    int l, r, x, y;
    scanf("%d%d%d%d", &l, &r, &x, &y);
    l++, r++;
    if (x <= SQRT)
      ans[q] = solveSmall(r, x, y) - solveSmall(l - 1, x, y);
    else
      queries.push_back(Query(l, r, x, y, q));
  }

  sort(queries.begin(), queries.end());

  int l = 1;
  int r = 0;

  for (Query query : queries) {
    while (l < query.l)
      occ[val[l++]]--;
    while (l > query.l)
      occ[val[--l]]++;
    while (r > query.r)
      occ[val[r--]]--;
    while (r < query.r)
      occ[val[++r]]++;

    int curr = query.y;
    while (curr <= 40000) {
      ans[query.index] += occ[curr];
      curr += query.x;
    }
  }

  for (int i = 0; i < Q; i++)
    printf("%d\n", ans[i]);

  return 0;
}
