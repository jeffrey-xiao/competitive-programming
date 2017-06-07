#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 10010
#define EPS 1e-9
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef long double ld;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int C, R, M, Q;
ll mm[45][45];

int rowSum[15][45];
int colSum[15][45];

ll pow (ll base, ll p) {
    if (p == 0)
        return 1;
    if (p == 1)
        return base;
    if (p % 2 == 0)
        return pow(base * base, p / 2);
    return base * pow(base * base, p / 2);
}

int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    rint(C), rint(R), rint(M), rint(Q);

    for (int i = 1; i <= R; i++)
        for (int j = 1; j <= C; j++)
            mm[i][j] = 1;

    for (int i = 0; i < M; i++) {
        char c;
        int x, y, f;
        scanf(" %c%d%d%d", &c, &x, &y, &f);
        if (c == 'c') {
            colSum[f + 7][x]++;
            colSum[f + 7][y + 1]--;
        } else {
            rowSum[f + 7][x]++;
            rowSum[f + 7][y + 1]--;
        }
    }

    for (int k = 0; k <= 14; k++) {
        for (int i = 1; i <= R; i++) {
            rowSum[k][i] += rowSum[k][i - 1];
            for (int j = 1; j <= C; j++) {
                mm[i][j] *= pow(k - 7, rowSum[k][i]);
            }
        }

        for (int i = 1; i <= C; i++) {
            colSum[k][i] += colSum[k][i - 1];
            for (int j = 1; j <= R; j++) {
                mm[j][i] *= pow(k - 7, colSum[k][i]);
            }
        }
    }
    for (int i = 1; i <= R; i++) {
        for (int j = 1; j <= C; j++) {
            mm[i][j] += mm[i - 1][j] + mm[i][j - 1] - mm[i - 1][j - 1];
        }
    }
    int cnt = 0;

    unordered_map<ll, int> hm;
    for (int i = 1; i <= R; i++) {
        for (int j = 1; j <= i; j++) {
            for (int k = 1; k <= C; k++) {
                for (int l = 1; l <= k; l++) {
                    ll sum = mm[i][k] - mm[j - 1][k] - mm[i][l - 1] + mm[j - 1][l - 1];
                    if (!hm.count(sum))
                        hm[sum] = 0;
                    hm[sum]++;
                    cnt++;
                }
            }
        }
    }
    for (int q = 0; q < Q; q++) {
        ll B, O;
        scanf("%lld%lld", &B, &O);
        if (O == 0 && B == 0) {
            printf("%d\n", cnt);
        } else if (B == 0 || O % B != 0) {
            printf("0\n");
        } else {
            if (!hm.count(O / B))
                printf("0\n");
            else
                printf("%d\n", hm[O / B]);
        }
    }
	return 0;
}
