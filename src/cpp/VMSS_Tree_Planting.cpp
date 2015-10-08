#include <bits/stdc++.h>

using namespace std;

#define MOD 1000000007
int bit[4100][4100];
int n, ans;

void update (int x, int y, int val) {
    for (int indx = x; indx < 4100; indx += (indx & -indx))
        bit[indx][y] = (bit[indx][y] + val) % MOD;
}

int sum (int x, int y) {
    int sum = 0;
    for (int indx = x; indx > 0; indx -= (indx & -indx))
        sum = (sum + bit[indx][y]) % MOD;
    return sum;
}

pair<int, int> getPoint (int x, int y) {
    return make_pair(x - y + 2000, x + y);
}
int main () {
    scanf("%d", &n);
    for (int i = 0; i < n; i++) {
        int j, r, c, t;
        scanf("%d%d%d%d", &j, &r, &c, &t);

        if (j == 1) {
            pair<int, int> p1 = getPoint(r, c);
            update(p1.first, p1.second, t);
        } else {
            pair<int, int> p1 = getPoint(r - t - 1, c + t + 1);
            pair<int, int> p2 = getPoint(r, c);
            ans = (ans + sum(p2.first, p2.second) - sum(p1.first, p1.second))%MOD;
        }
    }
    printf("%d\n", ans);
    return 0;
}
