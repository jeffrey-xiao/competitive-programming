#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<29
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 255
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int n, m, k, c;

int sum[SIZE][SIZE];
int tree[SIZE*4];

inline int getSum (int x1, int y1, int x2, int y2) {
    return sum[x2][y2] - sum[x2][y1-1] - sum[x1-1][y2] + sum[x1-1][y1-1];
}

void update (int n, int l, int r, int x, int val) {
    if (l == x && x == r) {
        tree[n] = min(tree[n], val);
        return;
    }
    int mid = (l + r) >> 1;
    if (x <= mid)
        update(n << 1, l, mid, x, val);
    else
        update(n << 1 | 1, mid+1, r, x, val);
    tree[n] = min(tree[n << 1], tree[n << 1 | 1]);
}

int query (int n, int l, int r, int ql, int qr) {
    if (ql > qr)
        return INF;
    if (ql == l && qr == r)
        return tree[n];
    int mid = (l + r) >> 1;
    if (qr <= mid)
        return query(n << 1, l, mid, ql, qr);
    else if (ql > mid)
        return query(n << 1 | 1, mid+1, r, ql, qr);
    else
        return min(query(n << 1, l, mid, ql, mid), query(n << 1 | 1, mid+1, r, mid+1, qr));
}

int main () {
    rint(m);
    rint(n);
    rint(c);
    rint(k);
    for (int i = 0; i < c; i++) {
        int y, x;
        rint(y), rint(x);
        sum[x][y]++;
    }

    for (int i = 1; i <= n; i++)
        for (int j = 1; j <= m; j++)
            sum[i][j] += sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1];

    for (int i = 0; i < SIZE*4; i++)
        tree[i] = INF;
    int ans = INF;
    for (int i = 1; i <= n; i++) {
        for (int j = i; j <= n; j++) {
            int l = 1, r = 1;
            while (r <= m) {
                int sum = getSum(i, l, j, r);
                if (sum == k) {
                    int val = ((j-i+1) + (r - l + 1))*2;
                    update(1, 1, n, j, val);
                    ans = min(ans, query(1, 1, n, 1, i-1) + val);
                    l++;
                    if (l > r)
                        r++;
                } else if (sum < k) {
                    r++;
                } else if (sum > k) {
                    l++;
                    if (l > r)
                        r++;
                }
            }
        }
    }
    for (int i = 0; i < SIZE*4; i++)
        tree[i] = INF;
    for (int i = 1; i <= m; i++) {
        for (int j = i; j <= m; j++) {
            int l = 1, r = 1;
            while (r <= n) {
                int sum = getSum(l, i, r, j);
                if (sum == k) {
                    int val = ((j-i+1) + (r - l + 1))*2;
                    update(1, 1, m, j, val);
                    ans = min(ans, query(1, 1, m, 1, i-1) + val);
                    l++;
                    if (l > r)
                        r++;
                } else if (sum < k) {
                    r++;
                } else if (sum > k) {
                    l++;
                    if (l > r)
                        r++;
                }
            }
        }
    }
    if (ans < INF)
        printf("%d\n", ans);
	else
        puts("NO");
	return 0;
}
