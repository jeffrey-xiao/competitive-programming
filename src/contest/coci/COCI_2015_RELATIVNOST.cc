#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 10007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define MAX_N 100000
#define MAX_C 20
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int tree[MAX_N * 4][MAX_C + 1];
int A[MAX_N], B[MAX_N];
int N, C, Q;

void merge (int n) {
    for (int i = 0; i <= C; i++)
        tree[n][i] = 0;
    for (int i = 0; i <= C; i++) {
        for (int j = 0; j <= C; j++) {
            if (i + j >= C)
                tree[n][C] = (tree[n][C] + tree[n << 1][i] * tree[n << 1 | 1][j]) % MOD;
            else
                tree[n][i + j] = (tree[n][i + j] + tree[n << 1][i] * tree[n << 1 | 1][j]) % MOD;
        }
    }
}

void build (int n, int lo, int hi) {
    if (lo == hi) {
        tree[n][1] = A[lo] % MOD;
        tree[n][0] = B[lo] % MOD;
        return;
    }
    int mid = (lo + hi) >> 1;
    build(n << 1, lo, mid);
    build(n << 1 | 1, mid + 1, hi);
    merge(n);
}

void update (int n, int lo, int hi, int x) {
    if (lo == x && x == hi) {
        tree[n][1] = A[lo] % MOD;
        tree[n][0] = B[lo] % MOD;
        return;
    }
    int mid = (lo + hi) >> 1;
    if (x <= mid)
        update(n << 1, lo, mid, x);
    else
        update(n << 1 | 1, mid + 1, hi, x);
    merge(n);
}

int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    scanf("%d%d", &N, &C);

    for (int i = 0; i < N; i++)
        scanf("%d", A + i);
    for (int i = 0; i < N; i++)
        scanf("%d", B + i);

    build(1, 0, N - 1);

    scanf("%d", &Q);
    for (int i = 0; i < Q; i++) {
        int p, a, b;
        scanf("%d%d%d", &p, &a, &b);
        p--;
        A[p] = a % MOD;
        B[p] = b % MOD;
        update(1, 0, N - 1, p);
        printf("%d\n", tree[1][C] % MOD);
    }

	return 0;
}
