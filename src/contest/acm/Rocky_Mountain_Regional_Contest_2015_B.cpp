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

struct Edge {
    int dest, cost, next;
    Edge (int dest, int cost, int next): dest(dest), cost(cost), next(next) {}
};

struct Network {
    int N, src, sink;
    vector<int> last, dist;
    vector<Edge> e;

    Network (int N, int src, int sink): N(N), src(src), sink(sink), last(N), dist(N) {
        fill(last.begin(), last.end(), -1);
    }

    void addEdge (int x, int y, int xy, int yx) {
        e.push_back(Edge(y, xy, last[x]));
        last[x] = (int)e.size() - 1;
        e.push_back(Edge(x, yx, last[y]));
        last[y] = (int)e.size() - 1;
    }

    bool getPath () {
        fill(dist.begin(), dist.end(), -1);
        queue<int> q;
        q.push(src);
        dist[src] = 0;

        while (!q.empty()) {
            int curr = q.front(); q.pop();
            for (int i = last[curr]; i != -1; i = e[i].next) {
                if (e[i].cost > 0 && dist[e[i].dest] == -1) {
                    dist[e[i].dest] = dist[curr] + 1;
                    q.push(e[i].dest);
                }
            }
        }

        return dist[sink] != -1;
    }

    int dfs (int curr, int flow) {
        if (curr == sink)
            return flow;
        int ret = 0;
        for (int i = last[curr]; i != -1; i = e[i].next) {
            if (e[i].cost > 0 && dist[e[i].dest] == dist[curr] + 1) {
                int res = dfs(e[i].dest, min(flow, e[i].cost));
                ret += res;
                e[i].cost -= res;
                e[i ^ 1].cost += res;
                flow -= res;
                if (flow == 0)
                    break;
            }
        }
        return ret;
    }

    int getFlow () {
        int res = 0;
        while (getPath())
            res += dfs(src, 1 << 30);
        return res;
    }
};

vector<pair<int, int>> edges;
int incoming[100001];
bool used[100001];
map<ll, int> cnt;
int main () {
    int T;
    scanf("%d", &T);

    for (int t = 0; t < T; t++) {
        int N;
        scanf("%d", &N);
        int src = 0;
        int sink = N * 3 + 2 - 1;

        Network network(sink + 1, src, sink);
        memset(incoming, 0, sizeof incoming);
        memset(used, 0, sizeof used);
        edges.clear();
        cnt.clear();

        for (int i = 0; i < N; i++) {
            int u, v;
            scanf("%d%d", &u, &v);
            edges.push_back({u, v});
            incoming[u]++;
            incoming[v]++;
            if (u < v)
                swap(u, v);
            ll index = (u - 1);
            index *= 2 * N;
            index += v - 1;
            cnt[index]++;
        }

        bool valid = true;
        for (auto key : cnt) {
            if (key.second > 2) {
                printf("impossible\n");
                valid = false;
            }
        }

        if (!valid)
            continue;

        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (incoming[edges[i].first] == 1) {
                used[edges[i].first] = true;
                ans++;
            } else if (incoming[edges[i].second] == 1) {
                used[edges[i].second] = true;
                ans++;
            } else {
                network.addEdge(0, (i + 1), 1, 0);
                network.addEdge((i + 1), N + edges[i].first, 1, 0);
                network.addEdge((i + 1), N + edges[i].second, 1, 0);
            }
        }

        for (int i = 1; i <= 2 * N; i++)
            network.addEdge(N + i, sink, 1, 0);

        if (network.getFlow() == N - ans)
            printf("possible\n");
        else
            printf("impossible\n");
    }

	return 0;
}
