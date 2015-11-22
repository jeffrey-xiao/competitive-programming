#include <bits/stdc++.h>

using namespace std;

#define MOD 1000000007ll

int n, m;
bool v[100000];
vector<vector<int> > adj(100000);
long long comp = 0;

long long dfs(int x){
    comp++;
    v[x] = true;
    long long total = 0;
    for(int i = 0; i < adj[x].size(); i++){
        if(!v[adj[x][i]])
            total += dfs(adj[x][i]);
    }
    return adj[x].size() + total;
}

int main(){
    scanf("%d%d", &n, &m);

    for(int x = 0; x  < m; x++){
        int a, b;
        scanf("%d%d", &a, &b);
        a--; b--;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }
    long long total = 1;
    for(int x = 0; x < n; x++){
        if(!v[x]){
            comp = 0;
            long long count = dfs(x)/2;
            if(comp == count)
                total *= 2;
            else if(comp - 1 == count)
                total *= comp;
            total %= MOD;
        }
    }
    printf("%d\n", total%MOD);
    return 0;
}
