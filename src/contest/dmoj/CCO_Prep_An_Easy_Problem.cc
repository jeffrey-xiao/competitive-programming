#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 100000
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int N;
vector<vector<int>> occ (32);
vector<vector<int>> adj (SIZE);
bool vis[SIZE];
int len[SIZE];
queue<int> q;

void dfs (int u) {
    vis[u] = true;
    for (int v : adj[u])
        if (!vis[v])
            dfs(v);
    q.push(u);
}

int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    rint(N);

    for (int i = 0; i < N; i++) {
        int val;
        rint(val);
        for (int j = 0; j < 32; j++)
            if ((val & 1 << j) > 0)
                occ[j].pb(i);
    }

    for (int i = 0; i < 32; i++)
        for (int j = 1; j < occ[i].size(); j++)
            adj[occ[i][j - 1]].pb(occ[i][j]);

    for (int i = 0; i < N; i++)
        if (!vis[i])
            dfs(i);

    while (!q.empty()) {
        int curr = q.front();
        q.pop();

        int maxV = 0;
        for (int j : adj[curr])
            maxV = max(maxV, len[j]);

        len[curr] = maxV + 1;
    }

    int ans = 0;
    for (int i = 0; i < N; i++)
        ans = max(ans, len[i]);

    printf("%d\n", ans);
	return 0;
}
