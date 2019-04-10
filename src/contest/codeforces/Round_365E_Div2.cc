#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 1001
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

struct State {
  int val, prev;
  ll sum;
  State() {}
  State(int val, int prev, ll sum) {
    this->val = val;
    this->prev = prev;
    this->sum = sum;
  }
};

int N;
ll K;
vector<ll> factors;
ll val[SIZE];
State dp[SIZE][7000];

vector<pair<ll, int>> factor(ll X) {
  vector<pair<ll, int>> ret;
  for (long i = 2; i * i <= X; i++) {
    if (X % i == 0) {
      ret.pb({i, 0});
      while (X % i == 0) {
        ret[ret.size() - 1].second++;
        X /= i;
      }
    }
  }

  if (X != 1)
    ret.pb({X, 1});

  return ret;
}

void fillFactors(vector<pair<ll, int>> primes, int i, ll curr) {
  if (i == primes.size()) {
    factors.pb(curr);
    return;
  }
  ll currPower = 1;
  for (int j = 0; j <= primes[i].second; j++) {
    fillFactors(primes, i + 1, curr * currPower);
    currPower *= primes[i].first;
  }
}

unordered_map<ll, int> toIndex;

int getFactorIndex(ll x) {
  return toIndex[x];
}

ll gcf(ll a, ll b) {
  return b ? gcf(b, a % b) : a;
}

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  scanf("%d%lld", &N, &K);

  int minIndex = 1;
  for (int i = 1; i <= N; i++) {
    scanf("%lld", &val[i]);
    if (val[i] < val[minIndex])
      minIndex = i;
  }

  if (K == 1) {
    printf("%d\n%d", 1, minIndex);
    return 0;
  }

  vector<pair<ll, int>> f = factor(K);
  fillFactors(f, 0, 1);

  sort(factors.begin(), factors.end());

  for (int i = 0; i < factors.size(); i++)
    toIndex[factors[i]] = i;

  int valPowers[N + 1][f.size()];
  int factorPowers[factors.size()][f.size()];
  vector<ll> powers[f.size()];

  for (int i = 0; i < f.size(); i++) {
    powers[i].pb(1l);
    for (int j = 1; j <= f[i].second; j++)
      powers[i].pb(powers[i][j - 1] * f[i].first);
  }

  for (int i = 1; i <= N; i++) {
    ll curr = val[i];
    for (int j = 0; j < f.size(); j++) {
      valPowers[i][j] = 0;
      while (curr % f[j].first == 0) {
        curr /= f[j].first;
        valPowers[i][j]++;
      }
    }
  }

  for (int i = 0; i < factors.size(); i++) {
    ll curr = factors[i];
    for (int j = 0; j < f.size(); j++) {
      factorPowers[i][j] = 0;
      while (curr % f[j].first == 0) {
        curr /= f[j].first;
        factorPowers[i][j]++;
      }
    }
  }

  for (int i = 0; i <= N; i++)
    for (int j = 0; j < factors.size(); j++)
      dp[i][j] = State(1l << 30, -1l, 1l << 30);

  dp[0][0] = State(0l, -1l, 0l);

  for (int i = 1; i <= N; i++) {
    for (int j = 0; j < factors.size(); j++) {
      dp[i][j] = State(dp[i - 1][j].val, j, dp[i - 1][j].sum);
      ll x = 1;
      for (int k = 0; k < f.size(); k++) {
        x *= powers[k][min(valPowers[i][k], factorPowers[j][k])];
      }

      ll nextFactor = factors[j] / x;
      int index = getFactorIndex(nextFactor);
      if ((dp[i - 1][index].val + 1 < dp[i][j].val) ||
          (dp[i - 1][index].val + 1 == dp[i][j].val &&
              dp[i - 1][index].sum + val[i] <= dp[i][j].sum)) {
        dp[i][j] = State(dp[i - 1][index].val + 1, index, dp[i - 1][index].sum + val[i]);
      }
    }
  }

  int i = N;
  int j = factors.size() - 1;
  if (dp[i][j].val != 1 << 30) {
    printf("%d\n", dp[i][j].val);
    while (i != 0) {
      State curr = dp[i][j];
      State prev = dp[i - 1][curr.prev];
      if (curr.sum - prev.sum != 0)
        printf("%d ", i);
      i--;
      j = curr.prev;
    }
  } else {
    printf("-1\n");
  }

  return 0;
}
