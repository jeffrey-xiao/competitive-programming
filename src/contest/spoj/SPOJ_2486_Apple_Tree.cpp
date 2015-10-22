#include <iostream>
#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>
#include <cmath>
#include <cstring>

using namespace std;

#define SIZE 102
#define pb push_back
int n, k;
vector<vector<int> > adj (SIZE);
int v[SIZE];
int dp[SIZE][SIZE*2][2];

void dfs (int u, int prev) {
//    printf("HERE %d %d\n", u, prev);
    for (int i = 0; i <= k; i++) {
        dp[u][i][0] = dp[u][i][1] = v[u];
    }
    for (int w = 0; w < adj[u].size(); w++) {
        int v = adj[u][w];
        if (v == prev)
            continue;
        dfs(v, u);
        for (int j = k; j >= 0; j--) {
            for (int k = j; k >= 0; k--) {
                dp[u][j+1][0] = max(dp[u][j+1][0], dp[v][k][0] + dp[u][j-k][1]);
                dp[u][j+2][0] = max(dp[u][j+2][0], dp[v][k][1] + dp[u][j-k][0]);
                dp[u][j+2][1] = max(dp[u][j+2][1], dp[v][k][1] + dp[u][j-k][1]);
            }
        }
    }
}

int main () {
    while (~scanf("%d %d", &n, &k)) {
        for (int i = 0; i < n; i++) {
            scanf("%d", &v[i]);
            adj[i].clear();
        }
        memset(dp, 0, sizeof dp);
        for (int i = 0; i < n-1; i++) {
            int a, b;
            scanf("%d%d", &a, &b);
            a--, b--;
            adj[a].pb(b);
            adj[b].pb(a);
        }
        dfs(0, -1);
        int ans = 0;
        for (int i = 0; i <= k; i++) {
            ans = max(ans, dp[0][i][0]);
            ans = max(ans, dp[0][i][1]);
        }
        printf("%d\n", ans);
    }
    return 0;
}
