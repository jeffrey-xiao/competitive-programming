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

int Q, N, M;
vector<vector<int>> adj;
bool vis[MAX_N];
long calc[MAX_N + 1];

int bfs (int n) {
    queue<int> q;
    q.push(n);
    vis[n] = true;
    int ret = 1;
    while (!q.empty()) {
        int u = q.front();
        q.pop();
        for (int v : adj[u])
            if (!vis[v]) {
                vis[v] = true;
                q.push(v);
                ret++;
            }

    }
    return ret;
}

int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    scanf("%d", &Q);

    for (int i = 1; i <= MAX_N; i++)
        calc[i] = (ll)(i) * (i - 1) + calc[i - 1];

    for (int q = 0; q < Q; q++) {
        scanf("%d%d", &N, &M);

        adj.clear();
        ll ans = 0;

        memset(vis, 0, sizeof(vis));

        for (int i = 0; i < N; i++)
            adj.push_back(vector<int>());

        for (int i = 0; i < M; i++) {
            int u, v;
            scanf("%d%d", &u, &v);
            u--, v--;
            adj[u].push_back(v);
            adj[v].push_back(u);
        }

        vector<int> sizes;
        for (int i = 0; i < N; i++)
            if (!vis[i]) {
                int sz = bfs(i);
                if (sz > 1)
                    sizes.push_back(sz);
            }

        sort(sizes.begin(), sizes.end());
        reverse(sizes.begin(), sizes.end());

        ll edgesLeft = M;
        for (int i = 0; i < (int)sizes.size(); i++) {
            edgesLeft -= sizes[i] - 1;
            ans += (edgesLeft * sizes[i] * (sizes[i] - 1)) + calc[sizes[i]];
        }

        printf("%lld\n", ans);
    }

	return 0;
}
