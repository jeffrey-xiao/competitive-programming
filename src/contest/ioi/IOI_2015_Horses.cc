#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 500100
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef long double ld;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int N;
ld A[SIZE], tree[4 * SIZE], lazy[4 * SIZE];
ll mult[4 * SIZE];
int maxValue[4 * SIZE];
int X[SIZE], Y[SIZE];

void build2 (int n, int l, int r) {
    if (l == r) {
        mult[n] = X[l];
        return;
    }
    int mid = (l + r) >> 1;
    build2(n << 1, l, mid);
    build2(n << 1 | 1, mid + 1, r);
    mult[n] = (mult[n << 1] * mult[n << 1 | 1]) % MOD;
}

void update2 (int n, int l, int r, int i) {
    if (l == i && r == i) {
        mult[n] = X[i];
        return;
    }
    int mid = (l + r) >> 1;
    if (i <= mid)
        update2(n << 1, l, mid, i);
    else
        update2(n << 1 | 1, mid + 1, r, i);
    mult[n] = (mult[n << 1] * mult[n << 1 | 1]) % MOD;
}

ll query2 (int n, int l, int r, int ql, int qr) {
    if (l == ql && r == qr)
        return mult[n];
    int mid = (l + r) >> 1;
    if (qr <= mid)
        return query2(n << 1, l, mid, ql, qr);
    else if (ql > mid)
        return query2(n << 1 | 1, mid + 1, r, ql, qr);
    return (query2(n << 1, l, mid, ql, mid) * query2(n << 1 | 1, mid + 1, r, mid + 1, qr)) % MOD;
}

void build1 (int n, int l, int r) {
    if (l == r) {
        tree[n] = A[l];
        maxValue[n] = l;
        return;
    }
    int mid = (l + r) >> 1;
    build1(n << 1, l, mid);
    build1(n << 1 | 1, mid + 1, r);
    if (tree[n << 1] > tree[n << 1 | 1]) {
        tree[n] = tree[n << 1];
        maxValue[n] = maxValue[n << 1];
    } else {
        tree[n] = tree[n << 1 | 1];
        maxValue[n] = maxValue[n << 1 | 1];
    }
}

void update1 (int n, int l, int r, int ql, int qr, ld val) {
    if (l == ql && r == qr) {
        tree[n] += val;
        lazy[n] += val;
        return;
    }
    if (lazy[n] != 0) {
        lazy[n << 1] += lazy[n];
        lazy[n << 1 | 1] += lazy[n];
        tree[n << 1] += lazy[n];
        tree[n << 1 | 1] += lazy[n];
        lazy[n] = 0;
    }
    int mid = (l + r) >> 1;
    if (qr <= mid)
        update1(n << 1, l, mid, ql, qr, val);
    else if (ql > mid)
        update1(n << 1 | 1, mid + 1, r, ql, qr, val);
    else {
        update1(n << 1, l, mid, ql, mid, val);
        update1(n << 1 | 1, mid + 1, r, mid + 1, qr, val);
    }
    if (tree[n << 1] > tree[n << 1 | 1]) {
        tree[n] = tree[n << 1];
        maxValue[n] = maxValue[n << 1];
    } else {
        tree[n] = tree[n << 1 | 1];
        maxValue[n] = maxValue[n << 1 | 1];
    }
}

int init (int n, int x[], int y[]) {
    N = n;

    for (int i = 1; i <= N; i++) {
        X[i] = x[i - 1];
        Y[i] = y[i - 1];
    }

    for (int i = 1; i <= N; i++)
        A[i] = log(X[i]) + A[i - 1];

    for (int i = 1; i <= N; i++)
        A[i] += log(Y[i]);

    build1(1, 1, N);
    build2(1, 1, N);

    return query2(1, 1, N, 1, maxValue[1]) * Y[maxValue[1]] % MOD;
}

int updateX (int pos, int val) {
    pos++;
    ld diff = log(val) - log(X[pos]);
    X[pos] = val;
    update1(1, 1, N, pos, N, diff);
    update2(1, 1, N, pos);

    return query2(1, 1, N, 1, maxValue[1]) * Y[maxValue[1]] % MOD;
}

int updateY (int pos, int val) {
    pos++;
    ld diff = log(val) - log(Y[pos]);
    Y[pos] = val;
    update1(1, 1, N, pos, pos, diff);

    return query2(1, 1, N, 1, maxValue[1]) * Y[maxValue[1]] % MOD;
}

int main () {
    int N, M;
    scanf("%d", &N);
    int X[N], Y[N];

    for (int i = 0; i < N; i++)
        scanf("%d", &X[i]);

    for (int i = 0; i < N; i++)
        scanf("%d", &Y[i]);

    printf("%d\n", init(N, X, Y));

    scanf("%d", &M);

    for (int i = 0; i < M; i++) {
        int a, b, c;
        scanf("%d%d%d", &a, &b, &c);
        if (a == 1)
            printf("%d\n", updateX(b, c));
        else
            printf("%d\n", updateY(b, c));
    }
	return 0;
}
