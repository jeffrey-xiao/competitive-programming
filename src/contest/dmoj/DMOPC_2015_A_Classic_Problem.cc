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

int N, M, K;
bitset<1500> adj[1500];

int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    rint(N);
    rint(M);
    rint(K);

    for (int i = 0; i < M; i++) {
        int x, y;
        rint(x), rint(y);
        x--, y--;
        adj[x][y] = 1;
        adj[y][x] = 1;
    }

    for (int i = 0; i < N; i++) {
        bitset<1500> curr;
        curr[i] = true;
        for (int j = 0; j < K; j++) {
            bitset<1500> next;
            next |= curr;
            for (int k = 0; k < N; k++) {
                if (curr[k])
                    next |= adj[k];
            }
            curr = next;
        }
        int cnt = 0;
        for (int i = 0; i < 1500; i++)
            if (curr[i])
                cnt++;
        printf("%d\n", cnt);
    }

	return 0;
}
