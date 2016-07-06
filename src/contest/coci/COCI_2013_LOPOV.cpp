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

int N, K;
vector<pi> items;
multiset<int> bags;

int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    scanf("%d%d", &N, &K);

    for (int i = 0; i < N; i++) {
        int mass, value;
        scanf("%d%d", &mass, &value);
        items.pb({value, mass});
    }

    for (int i = 0; i < K; i++) {
        int c;
        scanf("%d", &c);
        bags.insert(c);
    }

    sort(items.begin(), items.end());

    ll ans = 0;

    for (int i = N - 1; i >= 0; i--) {
        if (bags.lower_bound(items[i].second) != bags.end()) {
            ans += items[i].first;
            bags.erase(bags.lower_bound(items[i].second));
        }
    }

    printf("%lld\n", ans);
	return 0;
}
