#include <bits/stdc++.h>

using namespace std;

#define SIZE 100001

int N, M;

long long bit[SIZE];
long long cnt[SIZE];

void update (int x, long long (&tree)[SIZE], int v) {
    for (; x < SIZE; x += (x & -x))  {
        tree[x] += v;
    }
}
long long query (int x, long long (&tree)[SIZE]) {
    long long sum = 0;
    for (; x > 0; x -= (x & -x))
        sum += tree[x];
    return sum;
}

int main () {
    scanf("%d%d", &N, &M);
    for (int i = 1; i <= N; i++) {
        int a;
        scanf("%d", &a);
        update(i, bit, a);
        update(a, cnt, 1);
    }
    for (int i = 0; i < M; i++) {
        char c;
        scanf(" %c", &c);
        if (c == 'C') {
            int a, b;
            scanf("%d%d", &a, &b);
            int v = query(a, bit) - query(a-1, bit);
            update(a, bit, b - v);
            update(v, cnt, -1);
            update(b, cnt, 1);
        } else if (c == 'S') {
            int a, b;
            scanf("%d%d", &a, &b);
            printf("%lld\n", query(b, bit) - query(a-1, bit));
        } else {
            int a;
            scanf("%d", &a);
            printf("%lld\n", query(a, cnt));
        }
    }
    return 0;
}
