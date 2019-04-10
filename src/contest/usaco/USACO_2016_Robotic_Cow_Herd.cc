#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 1000000
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int N, K;
vector<vector<ll>> add(SIZE);
ll minCost[SIZE];
int currentCount;
ll ans = 0;

void count(int i, ll budget) {
  if (currentCount >= K)
    return;

  if (i != -1 && budget < add[i][0]) {
    i = upper_bound(minCost, minCost + i, (int)budget) - minCost - 1;
  }

  if (i == -1) {
    currentCount++;
    return;
  }

  count(i - 1, budget);
  for (int j = 0; j < (int)add[i].size() && budget >= add[i][j]; j++)
    if (currentCount < K)
      count(i - 1, budget - add[i][j]);
}

void totalCost(int i, ll budget) {
  if (i != -1 && budget < add[i][0]) {
    i = upper_bound(minCost, minCost + i, (int)budget) - minCost - 1;
  }

  if (i == -1) {
    ans -= budget + 1;
    return;
  }

  totalCost(i - 1, budget);
  for (int j = 0; j < (int)add[i].size() && budget >= add[i][j]; j++)
    totalCost(i - 1, budget - add[i][j]);
}

int main() {
  freopen("roboherd.in", "r", stdin);
  freopen("roboherd.out", "w", stdout);

  scanf("%d%d", &N, &K);

  ll lo = 0, hi = 0;
  ll baseCost = 0;
  for (int i = 0; i < N; i++) {
    vector<ll> parts;

    int sz;
    scanf("%d", &sz);

    for (int j = 0; j < sz; j++) {
      ll cost;
      scanf("%lld", &cost);
      parts.push_back(cost);
    }

    sort(parts.begin(), parts.end());

    baseCost += parts.front();
    if (sz == 1) {
      i--;
      N--;
    }

    for (int j = 1; j < sz; j++)
      add[i].push_back(parts[j] - parts[0]);
    hi += add[i].back();
  }

  sort(add.begin(), add.begin() + N);
  for (int i = 0; i < N; i++) {
    minCost[i] = add[i][0];
  }

  while (lo <= hi) {
    ll mid = (lo + hi) / 2;
    currentCount = 0;
    count(N - 1, mid);
    if (currentCount < K)
      lo = mid + 1;
    else
      hi = mid - 1;
  }

  totalCost(N - 1, lo - 1);
  ans += (baseCost + lo) * K;
  printf("%lld\n", ans);
  return 0;
}
