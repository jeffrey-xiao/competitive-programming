#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define MAX_N 5005
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int N;
ll dp[MAX_N][MAX_N];
pair<ll, ll> deck[MAX_N];

ll solve (int i, int j) {
    if (j >= N - 1)
        return 0;
    if (dp[i][j] != -1)
        return dp[i][j];
    if (i == j)
        return dp[i][j] = deck[i].first + solve(i + 1, j + deck[i].second);
    return dp[i][j] = min(solve(i + 1, j), deck[i].first + solve(i + 1, j + deck[i].second));
}

int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    scanf("%d", &N);

    for (int i = 0; i < N; i++) {
        ll c, d;
        scanf("%lld%lld", &c, &d);
        deck[i] = {c, d};
    }

    for (int i = 0; i < MAX_N; i++)
        for (int j = 0; j < MAX_N; j++)
            dp[i][j] = -1;

    printf("%lld\n", solve(0, 0));

	return 0;
}
