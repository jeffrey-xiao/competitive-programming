#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 200000
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int N, D;
string in;
int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

	cin >> N >> D >> in;
    vector<pi> rooms;
    int maxPos = 0;
    for (int i = 0; i < (int)in.size();) {
        if (in[i] == '#') {
            i++;
        } else {
            int l = i, r = i;
            while (r + 1 < (int)in.size() && in[r + 1] == '.')
                r++;
            i = r + 1;
            rooms.pb({l, r});
            maxPos = max(maxPos, l);
        }
    }

    int dp[rooms.size()];
    dp[0] = 1;
    int last = 0;
    priority_queue<pi> pq;
    pq.push({-1, 0});

    for (int i = 1; i < (int)rooms.size(); i++) {
        while (rooms[i].first - rooms[last].second > D)
            last++;
        if (last == 0)
            dp[i] = 1;
        else
            dp[i] = 1 + dp[last - 1];

        while (!pq.empty() && pq.top().second < last)
            pq.pop();
        pq.push({-dp[i], i});
        if (!pq.empty())
            dp[i] = min(dp[i], -pq.top().first);
    }
    printf("%d\n", dp[(int)rooms.size() - 1]);
	return 0;
}
