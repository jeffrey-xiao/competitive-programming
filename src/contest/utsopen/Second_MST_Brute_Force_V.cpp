#include <bits/stdc++.h>

using namespace std;

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define VSIZE 10010
#define ESIZE 50010

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int cost[ESIZE*2], to[ESIZE*2], nextE[ESIZE*2];
int id[VSIZE], size[VSIZE], last[VSIZE], now[VSIZE];
int n, m, E;

void addEdge(int x, int y, int xy, int yx){
    to[E] = y, cost[E] = xy, nextE[E] = last[x];
    last[x] = E;
    E++;

    to[E] = x, cost[E] = yx, nextE[E] = last[y];
    last[y] = E;
    E++;
}

int find(int x){
    return x == id[x] ? x : (id[x] = find(id[x]));
}
bool merge(int x, int y){
    int rootx = find(x);
    int rooty = find(y);
    if(rootx == rooty)
        return 0;
    if(size[rootx] > size[rooty]){
        size[rootx] += size[rooty];
        id[rooty] = rootx;
    }
    else{
        size[rooty] += size[rootx];
        id[rootx] = rooty;
    }
    return 1;
}

int dfs(int x, int y, int m, int prev){
    if(x == y)
        return m;
    for(int &e = now[x]; e != -1; e = nextE[e]){
        if(to[e] != prev){
            int v = dfs(to[e], y, max(m, cost[e]), x);
            if(v != -1)
                return v;
        }
    }
    return -1;
}

int main(){
    for(int x = 0; x < VSIZE; x++){
        last[x] = -1;
        id[x] = x;
    }
    scanf("%d%d", &n, &m);
    vector<pair<int, pi>> edges;
    vector<pair<int, pi>> unused;
    for(int x = 0; x < m; x++){
        int a, b, c;
        scanf("%d%d%d", &a, &b, &c);
        a--, b--;
        edges.pb(mp(c, mp(a, b)));

    }
    sort(edges.begin(), edges.end());
    int totalCost = 0;
    for(int x = 0; x < edges.size(); x++){
        if(merge(edges[x].second.first, edges[x].second.second)){
            totalCost += edges[x].first;
            addEdge(edges[x].second.first, edges[x].second.second, edges[x].first, edges[x].first);
        }else{
            unused.pb(edges[x]);
        }
    }
    int minV = 1 << 30;
    for(pair<int, pi> p : unused){
        for(int x = 0; x < VSIZE; x++)
            now[x] = last[x];
        int nextV = p.first - dfs(p.second.first, p.second.second, 0, -1);
        if(nextV != 0)
            minV = min(minV, nextV);
    }
    if(m < n-1 || minV == 1 << 30)
        printf("-1\n");
    else
        printf("%d\n", totalCost + minV);
    return 0;
}
