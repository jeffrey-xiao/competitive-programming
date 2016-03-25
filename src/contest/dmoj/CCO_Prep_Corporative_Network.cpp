#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 20010
#define LN 15
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int par[SIZE][LN], sum[SIZE][LN];
int id[SIZE], depth[SIZE];
bool notRoot[SIZE];

vector<vector<int>> adj (SIZE);

int T, N;

struct query {
    int a, b;
    query (int a, int b) {
        this->a = a;
        this->b = b;
    }
};

void dfs (int u, int d) {
    depth[u] = d;
    for (int v : adj[u])
        dfs(v, d + 1);
}

int root (int i) {
    return i == id[i] ? i : (id[i] = root(id[i]));
}
// j is par of i
void join (int i, int j) {
    i = root(i);
    j = root(j);
    id[i] = j;
}

int lca (int a, int b) {
    if (a == b)
        return 0;

    int ret = 0;
    for (int i = LN - 1; i >= 0; i--) {
        if (par[b][i] != -1 && depth[par[b][i]] > depth[a]) {
            ret += sum[b][i];
            b = par[b][i];
        }
    }
    ret += sum[b][0];
    return ret;
}

int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    rint(T);

    for (int t = 1; t <= T; t++) {
        rint(N);

        memset(par, -1, sizeof par);
        memset(sum, 0, sizeof sum);
        memset(notRoot, false, sizeof notRoot);

        for (int i = 0; i < N; i++)
            adj[i].clear();

        for (int i = 0; i < N; i++) {
            id[i] = i;
            depth[i] = -1;
        }

        vector<query> q;

        char c;
        scanf(" %c", &c);
        while (c != 'O') {
            if (c == 'E') {
                int a;
                rint(a);
                q.pb(query(a - 1, -1));
            } else {
                int u, v;
                rint(u), rint(v);
                u--, v--;
                adj[v].pb(u);
                notRoot[u] = true;
                par[u][0] = v;
                sum[u][0] = abs(v - u) % 1000;
                q.pb(query(u, v));
            }
            scanf(" %c", &c);
        }

        for (int i = 0; i < N; i++)
            if (!notRoot[i])
                dfs(i, 0);

        for (int i = 1; i < LN; i++) {
            for (int j = 0; j < N; j++) {
                if (par[j][i - 1] != -1) {
                    par[j][i] = par[par[j][i - 1]][i - 1];
                    sum[j][i] = sum[j][i - 1] + sum[par[j][i - 1]][i - 1];
                }
            }
        }

        for (query qu : q) {
            if (qu.b == -1)
                printf("%d\n", lca(root(qu.a), qu.a));
            else
                join(qu.a, qu.b);
        }

    }

	return 0;
}
