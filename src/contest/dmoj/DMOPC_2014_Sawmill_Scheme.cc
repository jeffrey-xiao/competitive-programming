#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define VSIZE 50500

using namespace std;
typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int n, m;

vector<vector<int>> adj (1000000);
double prob[1000000];
bool sawmill[1000000];

int main(){
    scanf("%d%d", &n, &m);
    for(int x = 0; x < m; x++){
        int a, b;

        scanf("%d%d", &a, &b);
        a--, b--;
        adj[a].pb(b);
        sawmill[a] = true;
    }
    prob[0] = 1.0;
    for(int x = 0; x < n; x++)
        for(int i : adj[x])
            prob[i] += prob[x] / adj[x].size();
    for(int x = 0; x < n; x++)
        if(!sawmill[x])
            printf("%.10f\n", prob[x]);
    return 0;
}
