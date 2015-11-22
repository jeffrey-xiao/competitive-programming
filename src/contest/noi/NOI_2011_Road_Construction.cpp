#include <bits/stdc++.h>

using namespace std;

struct edge{
    int src;
    int dest;
    int cost;
    edge(int src, int dest, int cost){
        this->src = src;
        this->dest = dest;
        this->cost = cost;
    }
};

int n;
long long size[1000000];
bool v[1000000];
vector<vector<int> > adj (1000000);
vector<edge> e;

static long long dfs(int i){
    v[i] = true;
    long long total = 0;
    for(int x = 0; x < adj[i].size(); x++){
        if(!v[adj[i][x]])
            total += dfs(adj[i][x]);
    }
    size[i] = total+1;
    return size[i];
}
int main(){
    scanf("%d",&n);
    for(int x = 0; x < n-1; x++){
        int a, b, c;
        scanf("%d%d%d",&a,&b,&c);
        a--;
        b--;
        adj[a].push_back(b);
        adj[b].push_back(a);
        e.push_back(edge(a,b,c));
    }
    dfs(0);
    long long cost = 0;
    for(int x = 0; x < e.size(); x++){

        long long a = size[e[x].src];
        long long b = size[e[x].dest];
        cost += e[x].cost * abs((a > b) ? (n-2*b):(n-2*a));
    }
    printf("%lld", cost);
    return 0;
}
