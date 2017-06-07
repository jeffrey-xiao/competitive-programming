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

int N, M, Q;

vector<pair<int, int>> adj[MAX_N];
int vis[MAX_N], mark[MAX_N];
ll dist[MAX_N];
ll maxDist;

pair<int, ll> dfs (int u, int p, ll cost) {
    pair<int, ll> ret = {u, cost};
    vis[u]++;
    for (auto edge : adj[u]) {
        if (edge.first == p)
            continue;
        auto res = dfs(edge.first, u, cost + edge.second);
        if (res.second > ret.second)
            ret = res;
    }
    return ret;
}

void markPath (int u, int p, ll cost) {
    if (cost == maxDist)
        mark[u] = 1;
    for (auto edge : adj[u]) {
        if (edge.first == p)
            continue;
        markPath(edge.first, u, cost + edge.second);
        if (mark[edge.first])
            mark[u] = 1;
    }
}

ll computeDist (int u, int p, ll cost) {
    dist[u] = cost;
    ll maxDistFrom = 0;
    for (auto edge : adj[u]) {
        if (edge.first == p)
            continue;
        maxDistFrom = max(maxDistFrom, edge.second + computeDist(edge.first, u, cost + edge.second));
        dist[u] = max(dist[u], maxDistFrom);
    }
    return maxDistFrom;
}

int getRadius (int u, int p) {
    int ret = u;
    for (auto edge : adj[u]) {
        if (edge.first == p || !mark[edge.first])
            continue;
        int res = getRadius(edge.first, u);
        if (dist[res] < dist[ret])
            ret = res;
    }
    return ret;
}

int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    scanf("%d%d%d", &N, &M, &Q);

    for (int i = 0; i < M; i++) {
        int u, v, len;
        scanf("%d%d%d", &u, &v, &len);
        --u, --v;
        adj[u].push_back({v, len});
        adj[v].push_back({u, len});
    }

    if (Q == 1) {
        ll ans = 0;
        bool first = 1;
        for (int i = 0; i < N; i++) {
            if (!vis[i]) {
                auto ret = dfs(i, -1, 0);
                ret = dfs(ret.first, -1, 0);
                ans += ret.second + (first ? 0 : 1);
                first = 0;
            }
        }
        printf("%lld\n", ans);
    } else if (Q == 2) {
        ll ans = 0;
        vector<ll> radius;
        for (int i = 0; i < N; i++) {
            if (!vis[i]) {
                auto ret = dfs(i, -1, 0);
                maxDist = dfs(ret.first, -1, 0).second;
                markPath(ret.first, -1, 0);
                computeDist(ret.first, -1, 0);
                int u = getRadius(ret.first, -1);
                radius.push_back(dist[u]);
            }
        }
        sort(radius.begin(), radius.end());
        ans = *(radius.end() - 1);
        if (radius.size() >= 2)
            ans = max(ans, *(radius.end() - 2) + 1);
        printf("%lld\n", ans);
    }

	return 0;
}
