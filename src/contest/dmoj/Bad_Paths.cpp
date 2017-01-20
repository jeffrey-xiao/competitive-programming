#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 500100
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

ll fact[1000001];
ll prefix[1000001];

ll modpow (ll base, ll pow, ll mod) {
    if (pow == 0)
        return 1;
    if (pow == 1)
        return base;
    if (pow % 2 == 0)
        return modpow(base * base % mod, pow / 2, mod);
    return base * modpow(base * base % mod, pow / 2, mod) % mod;
}

// O(log P)
ll divMod (ll i, ll j, ll p) {
    return i * modpow(j, p - 2, p) % p;
}

int N;

int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    fact[0] = 1;
    prefix[0] = 1;
    for (int i = 1; i <= 1000000; i++) {
        fact[i] = fact[i - 1] * i % MOD;
        prefix[i] = (prefix[i - 1] + divMod(1, fact[i], MOD)) % MOD;
        assert(divMod(1, fact[i], MOD) * fact[i] % MOD == 1);
    }

    scanf("%d", &N);

    for (int i = 0; i < N; i++) {
        int val;
        scanf("%d", &val);
        ll total = (fact[val] * prefix[val] - (val + 1)) % MOD;
        ll tree = (ll)val * (val - 1) % MOD;
        ll ans = ((total - tree) % MOD + MOD) % MOD;
        ans = divMod(ans, 2, MOD);
        printf("%lld\n", (ans + MOD) % MOD);
        assert(ans >= 0);
    }

	return 0;
}
