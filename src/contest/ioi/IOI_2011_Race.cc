#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 200000
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int n, k;
int dist[SIZE];
int cnt[SIZE];
bool exclude[SIZE];
vector<pi> adj[SIZE];

int getSize (int curr, int par) {
    int sz = 1;
    for (pi next : adj[curr])
        if (next.first != par && !exclude[next.first])
            sz += getSize(next.first, curr);
    return sz;
}

int getCentroid (int curr, int par, int treeSize) {
    int n = treeSize;
    int sz = 1;
    bool valid = true;
    for (pi next : adj[curr]) {
        if (next.first == par || exclude[next.first])
            continue;
        int ret = getCentroid(next.first, curr, treeSize);
        if (ret >= 0)
            return ret;
        valid &= -ret <= n / 2;
        sz += -ret;
    }
    valid &= n - sz <= n / 2;
    return valid ? curr : -sz;
}

queue<pi> currTree;
void getDist (int u, int par, int distTo, int cntTo, int branch) {
    cnt[u] = cntTo;
    dist[u] = distTo;
    currTree.push(mp(u, branch));
    for (pi v : adj[u])
        if (v.first != par && !exclude[v.first])
            getDist(v.first, u, distTo + v.second, cntTo + 1, par == -1 ? v.first : branch);
}

int main () {
    rint(n);
    rint(k);

    for (int i = 0; i < n-1; i++) {
        int a, b, c;
        rint(a), rint(b), rint(c);
        adj[a].pb(mp(b, c));
        adj[b].pb(mp(a, c));
    }
    queue<int> q;
    q.push(0);
    int ans = 1 << 30;
    while (!q.empty()) {
        int curr = q.front();
        q.pop();
        int centroid = getCentroid(curr, -1, getSize(curr, -1));
        getDist(centroid, -1, 0, 0, -1);
        unordered_map<int, int> hm;
        queue<int> addToHm;
        int prev = -2;
        while (!currTree.empty()) {
            pi curr = currTree.front();
            currTree.pop();
            if (curr.second != prev) {
                prev = curr.second;
                while (!addToHm.empty()) {
                    int add = addToHm.front();
                    addToHm.pop();
                    if (hm.count(dist[add])) {
                        if (hm[dist[add]] > cnt[add])
                            hm[dist[add]] = cnt[add];
                    } else {
                        hm[dist[add]] = cnt[add];
                    }
                }
            }
            if (hm.count(k - dist[curr.first])) {
                ans = min(ans, cnt[curr.first] + hm[k - dist[curr.first]]);
            }
            addToHm.push(curr.first);
        }
        exclude[centroid] = true;
        for (pi v : adj[centroid])
            if (!exclude[v.first])
                q.push(v.first);
    }
    printf("%d\n", ans == 1 << 30 ? -1 : ans);
	return 0;
}
