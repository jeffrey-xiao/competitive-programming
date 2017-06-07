#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 300100
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2
#define scan(x) do{while((x=getchar())<'0'); for(x-='0'; '0'<=(_=getchar()); x=(x<<3)+(x<<1)+_-'0');}while(0)
char _;

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int N, X;
ll ans;
vector<vector<int>> adj (SIZE);

int val[SIZE];
ll best[SIZE];
bool used[SIZE];

vector<int> pathSums;
unordered_map<int, int> occ;

int getSize (int u, int par) {
    int sz = 1;
    for (int v : adj[u]) {
        if (v == par || used[v])
            continue;
        sz += getSize(v, u);
    }
    return sz;
}

int getCentroid (int u, int par, int treeSize) {
    int sz = 1;
    bool valid = true;
    for (int v : adj[u]) {
        if (v == par || used[v])
            continue;
        int ret = getCentroid(v, u, treeSize);
        if (ret >= 0)
            return ret;
        valid &= -ret <= treeSize / 2;
        sz += -ret;
    }
    valid &= treeSize - sz <= treeSize / 2;
    return valid ? u : -sz;
}

ll getPathSums (int u, int prev, int sum) {
    sum += val[u];
    pathSums.pb(sum);

    ll ret = 0;

    ret += occ[-sum - 1];
    ret += occ[-sum - 2];

    for (int v : adj[u]) {
        if (used[v] || v == prev)
            continue;
        ret += getPathSums(v, u, sum);
    }

    best[u] += ret;
    return ret;
}

int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    scan(N);
    scan(X);

    for (int i = 0; i < N - 1; i++) {
        int a, b;
        scan(a);
        scan(b);
        adj[a - 1].pb(b - 1);
        adj[b - 1].pb(a - 1);
    }

    for (int i = 0; i < N; i++) {
        scan(val[i]);
        if (val[i] >= X)
            val[i] = 1;
        else
            val[i] = -1;
    }

    queue<int> q;
    int initial = getCentroid(0, -1, getSize(0, -1));
    q.push(initial);
    used[initial] = true;

    while (!q.empty()) {
        int curr = q.front();
        assert(used[curr]);
        q.pop();

        for (int i = 0; i < 2; i++) {
            occ.clear();

            if (i == 1)
                occ[val[curr]] = 1;

            for (int v : adj[curr]) {
                if (!used[v]) {
                    pathSums.clear();
                    best[curr] += i * getPathSums(v, -1, 0);

                    for (int u : pathSums) {
                        occ[u + val[curr]]++;
                    }
                }
            }
            reverse(adj[curr].begin(), adj[curr].end());
        }

        for (int v : adj[curr]) {
            if (!used[v]) {
                int next = getCentroid(v, -1, getSize(v, -1));
                q.push(next);
                used[next] = true;
            }
        }
    }

    for (int i = 0; i < N; i++)
        if (val[i] == -1)
            ans = max(ans, best[i]);
    printf("%lld\n", ans);
	return 0;
}
