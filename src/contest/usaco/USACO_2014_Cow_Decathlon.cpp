#include <bits/stdc++.h>

using namespace std;

struct award{
    int minValue;
    int points;
    award(int minValue_, int points_){
        minValue = minValue_;
        points = points_;
    }
};

int n;
int k;
int skill[20][20];
vector<vector<award> > awards (21);
int dp[1 << 20];

int main(){
    scanf("%d%d",&n,&k);

    for(int x = 0; x < k; x++){
        int a, b, c;
        scanf("%d%d%d",&a,&b,&c);
        awards[a].push_back(award(b,c));
    }
    for(int x = 0; x < n; x++)
        for(int y = 0; y < n; y++)
            scanf("%d", &skill[x][y]);
    for(int x = 1; x < (1 << n); x++){
        int maxV = 0;
        int length = __builtin_popcount(x);
        for(int y = 0; y < n; y++)
            if(x & (1 << y))
                maxV = max(maxV, dp[x ^ (1 << y)] + skill[y][length-1]);

        int next = maxV;
        for(int y = 0; y < awards[length].size(); y++)
            if(maxV >= awards[length][y].minValue)
                next += awards[length][y].points;
        dp[x] = next;
    }
    printf("%d",dp[(1 << n) - 1]);

    return 0;
}
