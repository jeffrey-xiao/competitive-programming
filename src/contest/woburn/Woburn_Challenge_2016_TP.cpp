#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 2050
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int N, M;
long double dp[SIZE][2 * SIZE];

long double choose2 (int j) {
    return (long double )(j) * (j - 1) / 2.0;
}

int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    scanf("%d%d", &N, &M);
    dp[0][2] = 1.0;

    for (int i = 0; i < M - 1; i++) {
        for (int j = 2; j < 2 * SIZE; j++) {
            dp[i + 1][j] += dp[i][j] * choose2(j);
            if (j + 1 < 2 * SIZE)
                dp[i + 1][j + 1] += dp[i][j] * j * (N - j);
            if (j + 2 < 2 * SIZE)
                dp[i + 1][j + 2] += dp[i][j] * choose2(N - j);
        }
        for (int j = 2; j < 2 * SIZE; j++)
            dp[i + 1][j] /= choose2(N);
    }
    long double  ans = 0;
    for (int i = 2; i < 2 * SIZE; i++)
        ans += i * dp[M - 1][i];
    printf("%Lf\n", ans);
	return 0;
}
