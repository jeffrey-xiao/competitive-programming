#include <bits/stdc++.h>
#define SIZE 500
using namespace std;

int green[SIZE], red[SIZE], cnt[SIZE], nCnt[SIZE];

int main () {
    int P;
    scanf("%d", &P);
    for (int t = 0; t < P; t++) {
        int K, N;
        scanf("%d%d", &K, &N);
        for (int i = 0; i < N; i++)
            scanf("%d", &red[i]);
        for (int i = 0; i < N; i++)
            scanf("%d", &green[i]);
        for (int i = 0; i < N; i++)
            cnt[i] = 1;
        for (int iter = 0; iter < 50000; iter++) {
            int a = rand() % 2;
            for (int i = 0; i < N; i++)
                nCnt[i] = 0;
            for (int i = 0; i < N; i++) {
                if (a)
                    nCnt[red[i]] += cnt[i];
                else
                    nCnt[green[i]] += cnt[i];
            }
            for (int i = 0; i < N; i++)
                cnt[i] = nCnt[i];
        }
        int zeroes = 0;
        for (int i = 0; i < N; i++)
            if (cnt[i] == 0)
                zeroes++;
        if (zeroes == N - 1)
            printf("%d YES\n", K);
        else
            printf("%d NO\n", K);
    }
    return 0;
}
