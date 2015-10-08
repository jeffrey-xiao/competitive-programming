#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

int N, M;
ll K;
ll dp[301][301][2][2][2][2];
int main () {
	scanf("%d%d%lld", &N, &M, &K);
	memset(dp, -1, sizeof dp);
	for (ll i = 1; i <= N; i++) {
		for (ll j = 1; j <= M; j++) {
            for (int w = 0; w < 2; w++) {
                for (int x = 0; x < 2; x++) {
                    for (int y = 0; y < 2; y++) {
                        for (int z = 0; z < 2; z++) {
                            if (w == 0 && x == 0 && y == 0 && z == 0) {
                                dp[i][j][0][0][0][0] = 1ll << 20;
                                continue;
                            }
                            ll& ref = dp[i][j][w][x][y][z];
                            ref = (i*j-K)*(i*j-K);
                            if (i > 1) {
                                for (int k = 1; k < i; k++) {
                                    ref = min(ref, dp[k][j][0][x][y][z] + dp[i-k][j][w][0][y][z]);
                                }
                            }
                            if (j > 1) {
                                for (int k = 1; k < j; k++) {
                                    ref = min(ref, dp[i][k][w][x][0][z] + dp[i][j-k][w][x][y][0]);
                                }
                            }
                        }
                    }
                }
            }
		}
	}
	printf("%lld", dp[N][M][1][1][1][1]);
}
