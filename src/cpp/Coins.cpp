#include <iostream>
#include <stdio.h>
#include <cstring>
using namespace std;



int n, m;
bool can[100001];
int cnt[100001];
int val[100];
int num[100];

int main () {
    scanf("%d%d", &n, &m);
    while (n != 0 && m != 0) {

        for (int i = 0; i < n; i++)
            scanf("%d", &val[i]);
        for (int i = 0; i < n; i++)
            scanf("%d", &num[i]);
        memset(can, false, sizeof can);
        can[0] = true;
        for (int i = 0; i < n; i++) {
            memset(cnt, 0, sizeof cnt);
            for (int j = 0; j <= m; j++) {
                if (can[j] && cnt[j] < num[i] && j + val[i] <= m && !can[j + val[i]]) {
                    can[j + val[i]] = true;
                    cnt[j + val[i]] = cnt[j]+1;
                }
            }
        }
        int ans = 0;
        for (int i = 1; i <= m; i++)
            if (can[i])
                ans ++;
        printf("%d\n", ans);
        scanf("%d%d", &n, &m);
    }
}
