#include <bits/stdc++.h>

using namespace std;

#define SIZE 100005

int D[SIZE], W[SIZE];

long long L[SIZE];
long long R[SIZE];
long long LR[SIZE];

int N;

int main () {
    scanf("%d", &N);

    for (int i = 0; i < N; i++) {
        scanf("%d%d", &D[i], &W[i]);
    }
    for (int i = 0; i < N; i++) {
        if (i == 0) {
            L[i] = D[i];
            R[i] = D[i];
            LR[i] = max(0, D[i] - W[i+1]);
        } else {
            L[i] = max(0, D[i] - W[i-1]) + min(L[i-1], R[i-1]);
            R[i] = D[i] + LR[i-1];
            long long c1 = max(0, D[i] - W[i+1]) + LR[i-1];
            long long c2 = min(L[i-1], R[i-1]) + max(0, D[i] - W[i+1] - W[i-1]);
            LR[i] = min(c1,c2);
        }
    }
    printf("%lld", min(L[N-1], R[N-1]));
    return 0;
}
