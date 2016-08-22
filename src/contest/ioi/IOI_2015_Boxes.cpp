#include <bits/stdc++.h>

using namespace std;

#define SIZE 10000002

typedef long long ll;

ll dp1[SIZE], dp2[SIZE];

ll delivery(int N, int K, int L, int positions[]) {
    for (int i = 1; i <= N; i++) {
        if (i >= K)
			dp1[i] = dp1[i - K] + min(2 * positions[i - 1], L);
		else
			dp1[i] = min(2 * positions[i - 1], L);
	}
	for (int i = N; i >= 1; i--) {
		if (i + K <= N)
			dp2[i] = dp2[i + K] + min(2 * (L - positions[i - 1]), L);
		else
			dp2[i] = min(2 * (L - positions[i - 1]), L);
	}

	ll ans = 1ll << 60;
	for (int i = 0; i <= N; i++)
		ans = min(ans, dp1[i] + dp2[i + 1]);

    return ans;
}

int main () {
    int N, K, L;
    scanf("%d%d%d", &N, &K, &L);
    int positions[N];
	for (int i = 0; i < N; i++)
		scanf("%d", &positions[i]);

	printf("%lld\n", delivery(N, K, L, positions));
	return 0;
}
