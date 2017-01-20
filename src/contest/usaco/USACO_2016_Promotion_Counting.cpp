#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define MAX_N 100001
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int N;
int P[MAX_N], ans[MAX_N], bit[MAX_N];
vector<int> adj[MAX_N];
vector<int> sorted;

void update (int x, int val) {
    for (int i = x; i < MAX_N; i += (i & -i))
        bit[i] += val;
}

int query (int x) {
    int sum = 0;
    for (int i = x; i > 0; i -= (i & -i))
        sum += bit[i];
    return sum;
}

void dfs (int u) {
    ans[u] -= query(MAX_N - 1) - query(P[u]);
    for (int v : adj[u])
        dfs(v);
    ans[u] += query(MAX_N - 1) - query(P[u]);
    update(P[u], 1);
}

int main () {
	freopen("promote.in", "r", stdin);
	freopen("promote.out", "w", stdout);

    scanf("%d", &N);

    for (int i = 0; i < N; i++) {
        scanf("%d", P + i);
        sorted.push_back(P[i]);
    }

    sort(sorted.begin(), sorted.end());

    for (int i = 0; i < N; i++)
        P[i] = distance(sorted.begin(), lower_bound(sorted.begin(), sorted.end(), P[i])) + 1;

    for (int i = 1; i < N; i++) {
        int par;
        scanf("%d", &par);
        par--;
        adj[par].push_back(i);
    }

    dfs(0);

    for (int i = 0; i < N; i++)
        printf("%d\n", ans[i]);

	return 0;
}
