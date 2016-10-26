#include <bits/stdc++.h>
#define SIZE 10100
typedef long long ll;
using namespace std;
ll dp[SIZE][4];
ll hp[SIZE];
ll ex[SIZE];
int main () {
    int T;
    scanf("%d", &T);
    for (int t = 0; t < T; t++) {
        ll N, D;
        for (int i = 0; i < SIZE; i++)
            dp[i][0] = dp[i][1] = dp[i][2] = dp[i][3] = 0;
        scanf("%lld%lld", &N, &D);
        for (int i = 1; i <= N; i++)
            scanf("%lld", &hp[i]);
        for (int i = 1; i <= N; i++)
            scanf("%lld", &ex[i]);
        for (int i = 1; i <= N; i++) {
            dp[i][0] = (hp[i] + D - 1) / D + min(dp[i - 1][2], dp[i - 1][3]);
            if (i)
                dp[i][1] = max(0LL, (hp[i] - ex[i - 1] + D - 1) / D) + min(dp[i - 1][0], dp[i - 1][1]);
            if (i < N)
                dp[i][2] = max(0LL, (hp[i] - ex[i + 1] + D - 1) / D) + min(dp[i - 1][2], dp[i - 1][3]);
            if ((i) && (i < N))
                dp[i][3] = max(0LL, (hp[i] - ex[i - 1] - ex[i + 1] + D - 1) / D) + min(dp[i - 1][0], dp[i - 1][1]);
        }
        ll ans = min(dp[N][0], dp[N][1]);
        printf("%lld\n", ans);
    }
}
