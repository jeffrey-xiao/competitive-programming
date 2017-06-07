// Andy Rock's Solution

#include <bits/stdc++.h>
using namespace std;

int N, P;
long long int a[1000001];
int perm[1000001], bit[1000002];

int queryAndUpdate (int pos) {
    int ans = 0;
    for(int i = pos; i > 0;      i -= i & -i)
        ans += bit[i];
    for(int i = pos; i <= N + 1; i += i & -i)
        bit[i]++;

    return ans;
}

bool cmp (int x, int y) {
    return a[x] < a[y];
}

int main () {
    scanf("%d", &N);
    for(int i = 1; i <= N; i++)
        scanf("%lld", a + i);

    scanf("%d", &P);
    for(int i = 1; i <= N; i++)
        a[i] += a[i-1] - P;

    for(int i = 0; i <= N; i++)
        perm[i] = i;
    sort(perm, perm + N + 1, cmp);

    long long int ans = 0;
    for(int i = 0; i <= N; i++)
        ans += queryAndUpdate(perm[i] + 1);

    printf("%lld\n", ans);
}
