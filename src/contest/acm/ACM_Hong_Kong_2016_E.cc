#include <bits/stdc++.h>
#define SIZE 100000
using namespace std;

int N;
long long cost[SIZE];

int main () {
    scanf("%d", &N);

    for (int i = 0; i < N - 1; i++)
        scanf("%lld", &cost[i]);

    long long ans = 0, currTime = 0, minCost = 1 << 30;
    for (int i = 0; i < N; i++) {
        int time;
        scanf("%d", &time);
        if (i) {
            ans += minCost * max(0LL, (time - currTime + 1) / 2 * 2);
            currTime += max(0LL, (time - currTime + 1) / 2 * 2);
        }
        minCost = min(minCost, cost[i]);
        ans += cost[i];
        currTime++;
    }
    printf("%lld\n", ans);
    return 0;
}
