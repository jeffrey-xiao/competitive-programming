#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define SIZE 20000

using namespace std;
const int INF = 1<<30;
int n, m, k, q;
vector<vector<pair<int, int> > > adj(SIZE);
vector<vector<pair<int, int> > > rev(SIZE);
vector<int> hubs;
int minDistTo[200][20000];
int minDistFrom[200][20000];
struct comp{
    bool operator()(pair<int, int> p1, pair<int, int> p2){
        return p1.second > p2.second;
    }
};
static void path(int i, vector<vector<pair<int, int> > > adj, int (&minV)[20000]){
    for(int x = 0; x < n; x++)
        minV[x] = INF;
    minV[i] = 0;
    priority_queue<pair<int, int>, vector<pair<int, int> >, comp> pq;
    pq.push(mp(i, 0));
    while(!pq.empty()){
        pair<int, int> curr = pq.top();
        pq.pop();
        if(minV[curr.first] != curr.second)
            continue;
        for(int x = 0; x < adj[curr.first].size(); x++){
            pair<int, int> next = adj[curr.first][x];
            if(minV[next.first] <= next.second + curr.second)
                continue;
            minV[next.first] = next.second + curr.second;
            pq.push(mp(next.first, minV[next.first]));
        }
    }
}
int main(){
    scanf("%d%d%d%d",&n,&m,&k,&q);
    for(int x = 0; x < m; x++){
        int a, b, c;
        scanf("%d%d%d",&a,&b,&c);
        --a; --b;
        adj[a].pb(mp(b,c));
        rev[b].pb(mp(a,c));
    }
    for(int x = 0; x < k; x++){
        int a;
        scanf("%d",&a);
        --a;
        hubs.pb(a);
    }
    for(int y = 0; y < k; y++){
        path(hubs[y], adj, minDistTo[y]);
        path(hubs[y], rev, minDistFrom[y]);
    }
    int count = 0;
    long long totalCost = 0;
    for(int x = 0; x < q; x++){
        long long minC = INF;
        int a, b;
        scanf("%d%d",&a,&b);
        --a; --b;
        for(int y = 0; y < k; y++){
            long long dist1 = minDistTo[y][b];
            long long dist2 = minDistFrom[y][a];
            minC = min(minC, dist1 + dist2);
        }
        if(minC != INF){
            totalCost += minC;
            count++;
        }
    }
    printf("%d\n%lld", count, totalCost);
    return 0;
}
