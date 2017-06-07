#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 500100
#define MAX_INT 100
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int N;
int val[SIZE], dp[SIZE][MAX_INT];
int main () {
	freopen("262144.in", "r", stdin);
	freopen("262144.out", "w", stdout);

    scanf("%d", &N);

    for (int i = 0; i < N; i++)
        for (int j = 0; j < MAX_INT; j++)
            dp[i][j] = -1;

    for (int i = 0; i < N; i++) {
        scanf("%d", val + i);
        dp[i][val[i]] = i + 1;
    }

    int ans = 0;
    for (int i = 2; i < MAX_INT; i++) {
        for (int j = 0; j < N; j++) {
            if (dp[j][i - 1] != -1 && dp[j][i - 1] < N)
                dp[j][i] = dp[dp[j][i - 1]][i - 1];
            if (dp[j][i] != -1)
                ans = max(ans, i);
        }
    }

    printf("%d\n", ans);
	return 0;
}
