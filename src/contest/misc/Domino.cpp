#include <bits/stdc++.h>

using namespace std;

#define SIZE 100001
#define mp make_pair

typedef pair<int, int> pi;

int n;
int dp[SIZE];
pi d[SIZE];

int solve (int i) {
    if (i == -1)
        return 0;
    int& res = dp[i];
    if (res != -1)
        return res;
    res = 1 << 30;
    // pushing the current domino left
    int reach = d[i].first - d[i].second;
    for (int j = i; j >= 0; j--) {
        if (reach <= d[j].first) {
            res = min(res, solve(j-1) + 1);
            reach = min(reach, d[j].first - d[j].second);
        } else
                break;
    }
    // pushing the current domino push (I.E. it will be pushed by another dominoe)
    int lastPushableDomino = d[i].first;
    for (int j = i; j >= 0; j--) {
        if (d[j].second + d[j].first >= lastPushableDomino) {
            res = min(res, solve(j-1) + 1);
            lastPushableDomino = d[j].first;
        }
    }
    return res;
}

int main () {
    scanf("%d", &n);
    for (int i = 0; i < n; i++) {
        dp[i] = -1;
        int a, b;
        scanf("%d%d", &a, &b);
        d[i] = mp(a, b);
    }
    sort(d, d + n);
    printf("%d\n", solve(n-1));
}
