#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 300100
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

struct City {
    int index;
    ll cnt;

    City () {}

    City (int index, long cnt) {
        this->index = index;
        this->cnt = cnt;
    }

    bool operator < (const City& c) const {
        return cnt < c.cnt;
    }
};

int N, S, D;
City c[SIZE];
int toPos[SIZE], cnt[SIZE];
ll tree[4 * SIZE];
int dp1[SIZE], dp2[SIZE], dp3[SIZE], dp4[SIZE];
ll val1[SIZE], val2[SIZE], val3[SIZE], val4[SIZE];

void update (int n, int l, int r, int x) {
    if (l == x && x == r) {
        cnt[n] = (cnt[n] + 1) % 2;
        tree[n] = cnt[n] * c[l].cnt;
        return;
    }
    int mid = (l + r) >> 1;
    if (x <= mid)
        update(n << 1, l, mid, x);
    else
        update(n << 1 | 1, mid + 1, r, x);
    tree[n] = tree[n << 1] + tree[n << 1 | 1];
    cnt[n] = cnt[n << 1] + cnt[n << 1 | 1];
}

ll query (int n, int num) {
    if (num <= 0)
        return 0;
    if (cnt[n] <= num)
        return tree[n];
    return query(n << 1, num - cnt[n << 1 | 1]) + query(n << 1 | 1, num);
}

void solve2 (int d1, int d2, int l, int r) {
    if (d1 > d2 || l > r)
        return;
    int targetD = (d1 + d2) >> 1;
    ll maxValue = -1;
    int maxIndex = -1;
    for (int i = r; i >= l; i--) {
        int timeOnVisiting = targetD - (S - i);
        update(1, 1, N, toPos[i]);
        ll res = query(1, timeOnVisiting);
        if (res >= maxValue) {
            maxValue = res;
            maxIndex = i;
        }
    }
    dp2[targetD] = maxIndex;
    val2[targetD] = maxValue;
    for (int i = l; i <= dp2[targetD]; i++)
        update(1, 1, N, toPos[i]);
    solve2(targetD + 1, d2, l, dp2[targetD]);
    for (int i = dp2[targetD] + 1; i <= r; i++)
        update(1, 1, N, toPos[i]);
    solve2(d1, targetD - 1, dp2[targetD], r);
}

void solve1 (int d1, int d2, int l, int r) {
    if (d1 > d2 || l > r)
        return;
    int targetD = (d1 + d2) >> 1;
    ll maxValue = -1;
    int maxIndex = -1;
    for (int i = l; i <= r; i++) {
        int timeOnVisiting = targetD - (i - S) * 2;
        update(1, 1, N, toPos[i]);
        ll res = query(1, timeOnVisiting);
        if (res >= maxValue) {
            maxValue = res;
            maxIndex = i;
        }
    }
    dp1[targetD] = maxIndex;
    val1[targetD] = maxValue;
    for (int i = dp1[targetD]; i <= r; i++)
        update(1, 1, N, toPos[i]);
    solve1(targetD + 1, d2, dp1[targetD], r);
    for (int i = l; i < dp1[targetD]; i++)
        update(1, 1, N, toPos[i]);
    solve1(d1, targetD - 1, l, dp1[targetD]);
}

void solve4 (int d1, int d2, int l, int r) {
    if (d1 > d2 || l > r)
        return;
    int targetD = (d1 + d2) >> 1;
    ll maxValue = -1;
    int maxIndex = -1;
    for (int i = r; i >= l; i--) {
        int timeOnVisiting = targetD - (S - i) * 2;
        update(1, 1, N, toPos[i]);
        ll res = query(1, timeOnVisiting);
        if (res >= maxValue) {
            maxValue = res;
            maxIndex = i;
        }
    }
    dp4[targetD] = maxIndex;
    val4[targetD] = maxValue;
    for (int i = l; i <= dp4[targetD]; i++)
        update(1, 1, N, toPos[i]);
    solve4(targetD + 1, d2, l, dp4[targetD]);
    for (int i = dp4[targetD] + 1; i <= r; i++)
        update(1, 1, N, toPos[i]);
    solve4(d1, targetD - 1, dp4[targetD], r);
}

void solve3 (int d1, int d2, int l, int r) {
    if (d1 > d2 || l > r)
        return;
    int targetD = (d1 + d2) >> 1;
    ll maxValue = -1;
    int maxIndex = -1;
    for (int i = l; i <= r; i++) {
        int timeOnVisiting = targetD - (i - S);
        update(1, 1, N, toPos[i]);
        ll res = query(1, timeOnVisiting);
        if (res >= maxValue) {
            maxValue = res;
            maxIndex = i;
        }
    }
    dp3[targetD] = maxIndex;
    val3[targetD] = maxValue;
    for (int i = dp3[targetD]; i <= r; i++)
        update(1, 1, N, toPos[i]);
    solve3(targetD + 1, d2, dp3[targetD], r);
    for (int i = l; i < dp3[targetD]; i++)
        update(1, 1, N, toPos[i]);
    solve3(d1, targetD - 1, l, dp3[targetD]);
}

ll findMaxAttraction (int n, int start, int d, int attraction[]) {
    N = n;
    S = start + 1;

    for (int i = 1; i <= N; i++)
        c[i] = City(i, attraction[i - 1]);

    sort(c + 1, c + N + 1);

    for (int i = 1; i <= N; i++)
        toPos[c[i].index] = i;

    ll ret = 0;

    for (int i = S; i <= n; i++) {
        update(1, 1, N, toPos[i]);
        int timeOnVisiting = d - (i - S);
        ret = max(ret, query(1, timeOnVisiting));
    }

    for (int i = S; i <= N; i++)
        update(1, 1, N, toPos[i]);

    solve1(0, d, S, N);
    solve2(0, d, 1, S - 1);
    solve3(0, d, S + 1, N);
    solve4(0, d, 1, S);

    for (int i = 0; i <= d; i++)
        ret = max(ret, max(val3[i] + val4[d - i], val1[i] + val2[d - i]));

    return ret;
}

int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    int n, start, d;
    scanf("%d%d%d", &n, &start, &d);

    int attraction[n];

    for (int i = 0; i < n; i++)
        scanf("%d", &attraction[i]);

    printf("%lld\n", findMaxAttraction(n, start, d, attraction));
	return 0;
}
