#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 998244353
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define MAX_N 1000
#define MAX_K 1000
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int N, M, K;
ll prob[MAX_N][MAX_N];
ll dice[MAX_K];
ll state[MAX_N];
int nextVal[MAX_N];

void getOccurrences(int u) {
  int index = u;
  for (int i = 0; i < M; i++) {
    prob[index][u] = (prob[index][u] + dice[i]) % MOD;
    u = nextVal[u];
  }
}

// O(log power)
ll modpow(ll base, ll pow, ll mod) {
  if (pow == 0)
    return 1;
  if (pow == 1)
    return base;
  if (pow % 2 == 0)
    return modpow(base * base % mod, pow / 2, mod);
  return base * modpow(base * base % mod, pow / 2, mod) % mod;
}

ll divMod(ll i, ll j, ll p) {
  return i * modpow(j, p - 2, p) % p;
}

void multiply() {
  ll ret[N];
  fill_n(ret, N, 0);
  for (int j = 0; j < N; j++)
    for (int k = 0; k < N; k++)
      ret[j] = (ret[j] + state[k] * prob[k][j]) % MOD;
  for (int i = 0; i < N; i++)
    state[i] = ret[i];
}

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  scanf("%d%d%d", &N, &M, &K);

  for (int i = 0; i < N; i++) {
    scanf("%d", nextVal + i);
    nextVal[i]--;
  }

  for (int i = 0; i < M; i++)
    scanf("%lld", dice + i);

  for (int i = 0; i < N; i++)
    getOccurrences(i);

  fill_n(state, N, divMod(1, N, MOD));

  for (int i = 0; i < K; i++)
    multiply();

  for (int i = 0; i < N; i++)
    printf("%lld\n", state[i]);

  return 0;
}
