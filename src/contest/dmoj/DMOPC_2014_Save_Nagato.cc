#include <bits/stdc++.h>

using namespace std;

#define pb push_back
#define SIZE 500005

int n;
vector<vector<int>> adj(SIZE);
int path1[SIZE]; // longest path
int index1[SIZE];
int path2[SIZE]; // second longest path
int longest[SIZE];

void dfs(int i, int prev){
    for(int x = 0; x < adj[i].size(); x++){
        int next = adj[i][x];
        if(prev != next){
            dfs(next, i);
            if(path1[next] + 1 > path1[i]){
                path2[i] = path1[i];
                path1[i] = path1[next] + 1;
                index1[i] = x;
            }else if(path1[next] + 1 > path2[i]){
                path2[i] = path1[next] + 1;
            }
        }
    }
    path1[i] = max(path1[i], 0);
}

int dp(int i, int prev, int maxV){
    longest[i] = max(maxV, path1[i]);
    for(int x = 0; x < adj[i].size(); x++){
        int next = adj[i][x];
        if(next == prev)
            continue;
        if(x == index1[i])
            dp(next, i, max(maxV, path2[i]) + 1);
        else
            dp(next, i, max(maxV, path1[i]) + 1);
    }
}

int main(){
    scanf("%d", &n);
    for(int x = 0; x < n; x++){
        path1[x] = path2[x] = - (1 << 30);
    }
    for(int x = 0; x < n-1; x++){
        int a, b;
        scanf("%d%d", &a, &b);
        a--, b--;
        adj[a].pb(b);
        adj[b].pb(a);
    }
    dfs(0, -1);
    dp(0, -1, 0);
    for(int x = 0; x < n; x++)
        printf("%d\n", longest[x]+1);
    return 0;
}
