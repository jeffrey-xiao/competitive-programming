#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000009
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 1010
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int N, M, K;
int A[SIZE], B[SIZE];
ll dp[2][SIZE][SIZE];

int main () {
	freopen("team.in", "r", stdin);
	freopen("team.out", "w", stdout);

    scanf("%d%d%d", &N, &M, &K);

    for (int i = 1; i <= N; i++)
        scanf("%d", A + i);
    for (int i = 1; i <= M; i++)
        scanf("%d", B + i);

    sort(A, A + N + 1);
    sort(B, B + M + 1);

    for (int i = 1; i <= K; i++) {
        for (int j = 1; j <= N; j++) {
            for (int k = 1; k <= M; k++) {
                if (A[j] > B[k])
                    dp[i % 2][j][k] = (i == 1 ? 1 : 0) + dp[(i - 1) % 2][j - 1][k - 1];
                else
                    dp[i % 2][j][k] = 0;
                dp[i % 2][j][k] += dp[i % 2][j - 1][k] + dp[i % 2][j][k - 1] - dp[i % 2][j - 1][k - 1];
                dp[i % 2][j][k] = (dp[i % 2][j][k] % MOD + MOD) % MOD;
            }
        }
    }

    printf("%lld\n", dp[K % 2][N][M]);
	return 0;
}
