#include <bits/stdc++.h>

using namespace std;

#define SIZE 1000001
typedef long long ll;
int n, m;
ll price[SIZE+1];
// BUY IS EQUAL TO BUYING THE CURRENT STOCK, ELSE IT IS EQUAL TO SELLING THE PREV STOCK
int main () {
    freopen("in.txt", "r", stdin);
    scanf("%d%d", &n, &m);
    for (int i = 1; i <= n; i++) {
        double in;
        cin >> in;
        price[i] = (ll)(in*1000);
    }
    ll bestBuy = 0;
    ll bestSell = 0;
    for (int i = n; i >= 1; i--) {
        bestBuy = max(bestBuy, bestSell - price[i]);
        bestSell = max(bestSell, bestBuy + price[i] - 2*m);
        //printf("%d %d %d\n", i, prevSell - price[i] - m, prevBuy + price[i] - m);
    }
    printf("%lld", bestBuy);
    return 0;
}
