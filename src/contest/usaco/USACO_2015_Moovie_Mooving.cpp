#include <bits/stdc++.h>

#define m make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))

using namespace std;
typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int n, t;
int times[20];
int sizes[20];
int dp[1 << 20];

int count(int x){
    if(x)
        return 1 + count(x & (x - 1));
    else
        return 0;
}

int bsearch(int a[], int x, int s){
    int lo = 0;
    int hi = s-1;
    while(lo <= hi){
        int mid = lo + (hi - lo)/2;
        if(a[mid] > x)
            hi = mid-1;
        else
            lo = mid+1;
    }
    return hi;
}

int ts [20][1000];
int main(){
    scanf("%d%d", &n, &t);
    for(int x = 0; x < n; x++){
        scanf("%d%d", &times[x], &sizes[x]);
        for(int y = 0; y < sizes[x]; y++){
            scanf("%d", &ts[x][y]);
            if(ts[x][y] == 0){
                dp[1 << x] = times[x];
            }
        }
    }
    int minV = INF;
    for(int x = 0; x < 1 << n; x++){
        if(dp[x] == 0)
            continue;
        if(dp[x] >= t){
            minV = min(minV, count(x));
        }
        for(int y = 0; y < n; y++){
            if((x & (1 << y)) == 0){
                int next = bsearch(ts[y], dp[x], sizes[y]);
                if(next == -1 || ts[y][next] + times[y] < dp[x]){
                    continue;
                }
                dp[x | (1 << y)] = max(dp[x | (1 << y)], ts[y][next] + times[y]);
            }
        }
    }
    if(minV == INF)
        printf("%d\n", -1);
    else
        printf("%d\n", minV);
    return 0;
}
