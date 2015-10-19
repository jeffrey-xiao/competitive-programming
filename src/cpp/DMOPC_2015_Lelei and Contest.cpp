#include <bits/stdc++.h>

using namespace std;
#define SIZE 200001
typedef long long ll;
ll a[SIZE];
ll tree[SIZE*4];
ll lazy[SIZE*4];

ll pow (ll n, ll k, ll m) {
    if (k == 0)
        return 1;
    if (k == 1)
        return n;
    if (k % 2 == 0)
        return pow(n*n%m, k/2, m)%m;
    return n*pow(n*n%m, k/2, m)%m;
}
ll query (int n, int l, int r, int ql, int qr, int m) {
    if (l == ql && r == qr) {
        return tree[n];
    }
    int mid = (l + r) >> 1;
    if (lazy[n] != 0) {
        lazy[n << 1] += lazy[n];
        lazy[n << 1 | 1] += lazy[n];
        tree[n << 1] = (tree[n << 1] + lazy[n] * (mid - l + 1))%m;
        tree[n << 1 | 1] = (tree[n << 1 | 1] + lazy[n] * (r - (mid+1) + 1)) % m;
        lazy[n] = 0;
    }
    if (qr <= mid)
        return query(n << 1, l, mid, ql, qr, m);
    else if (ql > mid)
        return query(n << 1 | 1, mid+1, r, ql, qr, m);
    return (query(n << 1, l, mid, ql, mid, m) + query(n << 1 | 1, mid+1, r, mid+1, qr, m))%m;
}
void update (int n, int l, int r, int ql, int qr, int val, int m) {
    if (l == ql && r == qr) {
        ll add = val;
        tree[n] = (tree[n] + (r - l + 1) * add) % m;
        lazy[n] += add;
        return;
    }
    int mid = (l + r) >> 1;
    if (lazy[n] != 0) {
        lazy[n << 1] += lazy[n];
        lazy[n << 1 | 1] += lazy[n];
        tree[n << 1] = (tree[n << 1] + lazy[n] * (mid - l + 1))%m;
        tree[n << 1 | 1] = (tree[n << 1 | 1] + lazy[n] * (r - (mid+1) + 1)) % m;
        lazy[n] = 0;
    }
    if (qr <= mid)
        update(n << 1, l, mid, ql, qr, val, m);
    else if (ql > mid)
        update(n << 1 | 1, mid+1, r, ql, qr, val, m);
    else {
        update(n << 1, l, mid, ql, mid, val, m);
        update(n << 1 | 1, mid+1, r, mid+1, qr, val, m);
    }
    tree[n] = (tree[n << 1] + tree[n << 1 | 1])%m;
}

void build (int n, int l, int r, int m) {
    if (l == r) {
        tree[n] = pow(a[l], m, m);
        return;
    }
    int mid = (l + r) >> 1;
    build(n << 1, l, mid, m);
    build(n << 1 | 1, mid+1, r, m);
    tree[n] = (tree[n << 1] + tree[n << 1 | 1])%m;
}

int main () {
    int m, n, q;
    scanf("%d%d%d\n", &m, &n, &q);
    for (int i = 1; i <= n; i++)
        scanf("%d", &a[i]);
    build(1, 1, n, m);
    for (int i = 0; i < q; i++) {
        int com, l, r, x;
        scanf("%d%d%d", &com, &l, &r);
        if (com == 1) {
            scanf("%d", &x);
            update(1, 1, n, l, r, pow(x, m, m), m);
        } else {
            printf("%d\n",query(1, 1, n, l, r, m));
        }
    }
    return 0;
}
