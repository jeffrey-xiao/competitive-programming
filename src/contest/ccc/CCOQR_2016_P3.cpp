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

int N;
vector<unordered_map<int, int>> adj (SIZE);
set<pi> v;

int maxV[SIZE], last[SIZE], first[SIZE];

int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    rint(N);

    for (int i = 0; i < N; i++) {
        int m;
        rint(m);
        int a[m];
        for (int j = 0; j < m; j++) {
            rint(a[j]);
            a[j]--;
        }
        for (int j = 0; j < m; j++) {
            adj[i][a[j]] = a[(j - 1 + m) % m];
        }
    }

    for (int i = 0; i < N; i++)
        first[i] = 1 << 30;

    for (int i = 0; i < N; i++) {
        for (pi p : adj[i]) {
            int prev = i;
            int curr = p.first;
            int cnt = 1;

            if (v.count(mp(prev, curr)))
                continue;

            v.insert(mp(prev, curr));

            unordered_set<int> inloop;
            inloop.insert(prev);
            inloop.insert(curr);

            first[prev] = 0;
            first[curr] = 1;
            last[prev] = 0;
            last[curr] = 1;

            while (true) {
                cnt++;

                int prevprev = prev;
                prev = curr;
                curr = adj[curr][prevprev];


                if (v.count(mp(prev, curr))) {
                    cnt--;
                    break;
                }

                v.insert(mp(prev, curr));

                first[curr] = min(first[curr], cnt);
                maxV[curr] = max(maxV[curr], cnt - last[curr]);
                last[curr] = cnt;

                inloop.insert(curr);
            }
            for (int v : inloop) {
                maxV[v] = max(maxV[v], cnt - last[v] + first[v]);
                first[v] = 1 << 30;
                last[v] = 0;
            }
        }
    }
    int q;
    rint(q);

    for (int i = 0; i < q; i++) {
        int a;
        rint(a);
        printf("%d\n", maxV[a - 1]);
    }

	return 0;
}
