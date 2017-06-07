#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 3000001
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int n, k;
ll ans;
int val[SIZE], occ[SIZE];
priority_queue<int> mx;
priority_queue<int, vector<int>, greater<int>> mn;
int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    rint(n);
    rint(k);

    for (int i = 0; i < n; i++)
        rint(val[i]);


    for (int i = 0, j = 0; i < n; i++) {
        if (i == j) {
            occ[val[j]]++;
            mx.push(val[j]);
            mn.push(val[j]);
            j++;
        }

        while (occ[mn.top()] == 0)
            mn.pop();

        while (occ[mx.top()] == 0)
            mx.pop();

        while (j < n && max(val[j], mx.top()) - min(val[j], mn.top()) <= k) {
            occ[val[j]]++;
            mx.push(val[j]);
            mn.push(val[j]);
            j++;
        }
        occ[val[i]]--;
        ans += j - i;
    }

    printf("%lld\n", ans);
	return 0;
}
