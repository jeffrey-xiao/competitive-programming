#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 100000
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int n;
int a[SIZE], b[SIZE], c[SIZE], val[SIZE];
int order[SIZE], id[SIZE], sz[SIZE];

vector<unordered_map<int, ll>> states(SIZE);
vector<vector<pi>> adj(SIZE);
ll ans[SIZE];

void dfs(int i, int par) {
  for (pi e : adj[i]) {
    if (e.first != par) {
      val[e.first] = val[i] ^ e.second;
      dfs(e.first, i);
    }
  }
}

int root(int i) {
  return i == id[i] ? i : (id[i] = root(id[i]));
}

void merge(int i, int j) {
  if (sz[i] >= sz[j]) {
    sz[i] += sz[j];
    id[j] = i;
  } else {
    sz[j] += sz[i];
    id[i] = j;
  }
}

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  rint(n);

  for (int i = 0; i < n - 1; i++) {
    rint(a[i]), rint(b[i]), rint(c[i]);
    a[i]--, b[i]--;
    adj[a[i]].pb(mp(b[i], c[i]));
    adj[b[i]].pb(mp(a[i], c[i]));
  }

  dfs(0, -1);

  for (int i = 0; i < n; i++) {
    states[i][val[i]] = 1ll;
    id[i] = i;
    sz[i] = 1;
  }

  for (int i = 0; i < n - 1; i++) {
    rint(order[i]);
    order[i]--;
  }

  ll total = 0;
  for (int i = n - 2; i >= 0; i--) {
    int id1 = root(a[order[i]]);
    int id2 = root(b[order[i]]);

    merge(id1, id2);

    if (id2 == id[id2])
      swap(id1, id2);

    for (pair<int, ll> entry : states[id2]) {
      total += states[id1][entry.first] * entry.second;
      states[id1][entry.first] += entry.second;
    }
    ans[i] = total;
  }

  for (int i = 0; i < n; i++)
    printf("%lld\n", ans[i]);

  return 0;
}
