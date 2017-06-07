#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define MAX_N 100000
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int N;
vector<pi> edges;
vector<int> adj[MAX_N];
int color[MAX_N];

void dfs (int u, int p, int c) {
    color[u] = c;
    for (int v : adj[u])
        if (v != p)
            dfs(v, u, !c);
}

int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    scanf("%d", &N);

    for (int i = 0; i < N - 1; i++) {
        int u, v;
        scanf("%d%d", &u, &v);
        --u, --v;
        adj[u].push_back(v);
        adj[v].push_back(u);
        edges.push_back({u, v});
    }

    dfs(0, -1, 0);

    for (int i = 0; i < N - 1; i++)
        printf("%d\n", color[edges[i].first]);

	return 0;
}
