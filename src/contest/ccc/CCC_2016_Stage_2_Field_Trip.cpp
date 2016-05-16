#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 1000100
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

vector<vector<int>> adj (SIZE);
bool vis[SIZE];
int N, M, K;

pair<int, bool> findComponent (int u) {
    pair<int, bool> ret = {0, 0};
    vis[u] = true;

    queue<pi> q;
    q.push({u, -1});
    vis[u] = true;

    while (!q.empty()) {
        pi curr = q.front();
        q.pop();

        ret.first++;

        for (int next : adj[curr.first]) {
            if (next == curr.second)
                continue;
            if (vis[next])
                ret.second = true;
            else {
                q.push({next, curr.first});
                vis[next] = true;
            }
        }
    }
    return ret;
}

int main () {
    // freopen("in.txt", "r", stdin);
    // freopen("out.txt", "w", stdout);

    scanf("%d%d%d", &N, &M, &K);

    for (int i = 0; i < M; i++) {
        int a, b;
        rint(a), rint(b);
        a--, b--;

        adj[a].pb(b);
        adj[b].pb(a);
    }

    int ans = 0;
    int minConnections = 0;

    for (int i = 0; i < N; i++) {
        if (!vis[i]) {
            pair<int, bool> res = findComponent(i);

            if (res.second) {
                if (res.first == K) {
                    ans += res.first;
                } else if (res.first > K) {
                    ans += res.first / K * K;
                    minConnections += (res.first + K - 1) / K;
                }
            } else {
                ans += res.first / K * K;
                minConnections += (res.first + K - 1) / K - 1;
            }
        }
    }

    printf("%d %d\n", ans, minConnections);
    return 0;
}
