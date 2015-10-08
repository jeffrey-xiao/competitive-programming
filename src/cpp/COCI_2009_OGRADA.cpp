#include <bits/stdc++.h>

using namespace std;

int n, x;
int leftB[1000000], in[1000000], q[1000000];
int main () {
    scanf("%d%d", &n, &x);
    for (int i = 0; i < n; i++) {
        scanf("%d", &in[i]);
        leftB[i] = -1;
    }
    int l = 0, r = 0;
    for (int i = 0; i < n; i++) {
        if (r > l && q[l] <= i - x)
            l++;
        while (r > l && in[i] <= in[q[r-1]])
            r--;
        q[r++] = i;
        if (i >= x-1) {
            leftB[i] = in[q[l]];
        }

    }
    long long ans = 0;
    int cnt = 0;
    int lastUsed = 1 << 30;
    l = 0; r = 0;
    for (int i = n-1; i >= 0; i--) {
        if (r > l && q[l] >= i + x)
            l++;
        while (r > l && leftB[i] >= leftB[q[r-1]])
            r--;
        q[r++] = i;
        ans += in[i] - leftB[q[l]];
        if (lastUsed - i >= x || (leftB[lastUsed] < in[i] && leftB[lastUsed] < leftB[q[l]])) {
            lastUsed = q[l];
            cnt++;
        }
    }
    printf("%lld\n%d\n", ans, cnt);
}
