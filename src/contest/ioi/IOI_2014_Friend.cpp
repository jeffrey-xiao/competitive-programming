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

int findSample (int n, int confidence[], int host[], int protocol[]) {
    int ret = 0;
    for (int i = n - 1; i >= 1; i--) {
        if (protocol[i] == 1)
            confidence[host[i]] += confidence[i];
        else if (protocol[i] == 2)
            confidence[host[i]] = max(confidence[host[i]], confidence[i]);
        else {
            ret += confidence[i];
            confidence[host[i]] = max(0, confidence[host[i]] - confidence[i]);
        }
    }
    return ret + confidence[0];
}

int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    int n;
    scanf("%d", &n);

    int confidence[n], host[n], protocol[n];

    for (int i = 0; i < n; i++)
        scanf("%d", &confidence[i]);

    for (int i = 1; i < n; i++)
        scanf("%d%d", &host[i], &protocol[i]);

    printf("%d\n", findSample(n, confidence, host, protocol));
	return 0;
}
