#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define MAX_S 1000
#define MAX_T 5
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int S, N, T;
int cost[MAX_S][MAX_T];
vector<int> greaterCost[MAX_T][MAX_S];
ll ans = 1L << 60;

void solve (int i, int bit, ll c) {
    if (i == T) {
        ans = min(ans, c);
        return;
    }

    if (c >= ans)
        return;

    for (int j = 0; j < S; j++) {
        if (bit & 1 << j) {
            int newCost = c + N * cost[j][i];
            int newBit = bit;
            for (int k : greaterCost[i][j])
                if (newBit & 1 << k)
                    newBit ^= 1 << k;
            if (__builtin_popcount(newBit) >= N && newCost < ans)
                solve(i + 1, newBit, newCost);
        }
    }
}

int main () {
    // freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    scanf("%d%d%d", &S, &N, &T);
    if (T == 1) {
        int c[S];
        for (int i = 0; i < S; i++)
            scanf("%d", &c[i]);
        sort(c, c + S);
        printf("%d\n", c[N - 1] * N);
    } else if (T == 2) {
        int x[S], y[S];
        vector<int> smallerX[S];
        for (int i = 0; i < S; i++)
            scanf("%d%d", &x[i], &y[i]);
        for (int i = 0; i < S; i++)
            for (int j = 0; j < S; j++)
                if (x[j] <= x[i])
                    smallerX[i].push_back(j);
        for (int i = 0; i < S; i++) {
            for (int j = 0; j < S; j++) {
                if (x[j] > x[i] || y[i] > y[j])
                    continue;
                int cnt = 0;
                for (int k : smallerX[i])
                    if (y[k] <= y[j])
                        cnt++;
                if (cnt >= N)
                    ans = min(ans, 1LL * x[i] * N + 1LL * y[j] * N);
            }
        }
        printf("%lld\n", ans);
    } else {
        for (int i = 0; i < S; i++)
            for (int j = 0; j < T; j++)
                scanf("%d", &cost[i][j]);

        for (int i = 0; i < T; i++)
            for (int j = 0; j < S; j++)
                for (int k = 0; k < S; k++)
                    if (cost[j][i] < cost[k][i])
                        greaterCost[i][j].push_back(k);

        solve(0, (1 << S) - 1, 0);
        printf("%lld\n", ans);
    }
	return 0;
}
