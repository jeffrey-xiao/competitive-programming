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

ll R, C, K;

int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    scanf("%lld%lld%lld", &R, &C, &K);
    ll r = ceil(sqrt(K));
    ll c = (K + r - 1) / r;
    if (r > R) {
        r = R;
        c = (K + r - 1) / r;
    }
    if (c > C) {
        c = C;
        r = (K + c - 1) / c;
    }
    printf("%lld\n", 2 * r + 2 * c);
	return 0;
}
