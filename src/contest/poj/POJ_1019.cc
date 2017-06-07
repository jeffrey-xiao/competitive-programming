#include <iostream>
#include <stdio.h>

using namespace std;

long long dp[33000];
int len (int i) {
    int cnt = 0;
    while (i != 0) {
        i /= 10;
        cnt++;
    }
    return cnt;
}
int digit (int cnt, int k) {
    for (int i = 0; i < cnt; i++)
        k /= 10;
    return k % 10;
}
int main () {
    int t;
    scanf("%d", &t);
    int i;
    for (i = 1; dp[i] <= 1ll << 31; i++) {
        dp[i+1] = 2*dp[i] - dp[i-1] + len(i);
    }
    for (int i = 0; i < t; i++) {
        int n;
        scanf("%d", &n);
        int j = 1;
        while (dp[j+1] < n) {
            j++;
        }
        j--;
        int sz = 0;
        for (int k = 1;; k++) {
            sz += len(k);
            if (sz >= n - dp[j+1]) {
                printf("%d\n", digit(sz - (n - dp[j+1]), k));
                break;
            }
        }
    }
    return 0;
}
