#include <bits/stdc++.h>

using namespace std;

#define SIZE 10000002

typedef long long ll;
ll n, k, l;
ll pos[SIZE], dp1[SIZE], dp2[SIZE];

int main () {
	scanf("%lld%lld%lld", &n, &k, &l);
	for (int i = 1; i <= n; i++)
		scanf("%lld", &pos[i]);
	for (int i = 1; i <= n; i++) {
		if (i >= k)
			dp1[i] = dp1[i-k] + min(2*pos[i], l);
		else
			dp1[i] = min(2*pos[i], l);
	}
	for (int i = n; i >= 1; i--) {
		if (i + k <= n)
			dp2[i] = dp2[i+k] + min(2*(l - pos[i]), l);
		else
			dp2[i] = min(2*(l - pos[i]), l);
	}
	ll ans = 1ll << 60;
	for (int i = 0; i <= n; i++)
		ans = min(ans, dp1[i] + dp2[i+1]);
	printf("%lld\n", ans);
	return 0;	
}
