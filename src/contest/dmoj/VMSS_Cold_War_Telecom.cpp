#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 500100
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

vector<vector<int>> adj (SIZE);
int low[SIZE], disc[SIZE];
bool v[SIZE];
set<int> cutVertices;
int cnt = 0;
int n, m;

void dfs (int i, int prev) {
    disc[i] = low[i] = cnt++;
    v[i] = true;
    int children = 0;
    for (int j : adj[i]) {
        if (!v[j]) {
            children++;
            dfs(j, i);
            low[i] = min(low[i], low[j]);
            if ((disc[i] == 0 && children > 1) || (disc[i] > 0 && low[j] >= disc[i])) {
                cutVertices.insert(i + 1);
            }
        } else if (j != prev && disc[j] < low[i]) {
            low[i] = disc[j];
        }
    }
}

int main () {
    // freopen("in.txt", "r", stdin);
    // freopen("out.txt", "w", stdout);

    scanf("%d%d", &n, &m);

    for (int i = 0; i < m; i++) {
        int a, b;
        scanf("%d%d", &a, &b);
        a--, b--;
        adj[a].pb(b);
        adj[b].pb(a);
    }

    for (int i = 0; i < n; i++)
        if (!v[i])
            dfs(i, -1);

    printf("%d\n", cutVertices.size());
    for (int u : cutVertices)
        printf("%d\n", u);

	return 0;
}
