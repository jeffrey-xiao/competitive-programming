#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 100100
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int N, M;
vector<vector<int>> adj (SIZE);
bool isPho[SIZE], marked[SIZE];
int dist[100000];
int totalDist = 0;

void computeDist (int u, int prev, int depth) {
    dist[u] = depth;
    for (int v : adj[u]) {
        if (v != prev && marked[v]) {
            computeDist(v, u, depth + 1);
        }
    }
}

void mark (int u, int prev) {
    if (isPho[u])
        marked[u] = true;
    for (int v : adj[u]) {
        if (v == prev)
            continue;
        mark(v, u);
        if (marked[v]) {
            marked[u] = true;
            totalDist += 2;
        }
    }
}

int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    rint(N);
    rint(M);

    for (int i = 0; i < M; i++) {
        int x;
        rint(x);
        isPho[x] = true;
    }

    for (int i = 0; i < N - 1; i++) {
        int a, b;
        rint(a), rint(b);
        adj[a].pb(b);
        adj[b].pb(a);
    }
    for (int i = 0; i < N; i++) {
        if (isPho[i]) {
            mark(i, -1);
            break;
        }
    }

    memset(dist, 0, sizeof dist);

    for (int i = 0; i < N; i++) {
        if (marked[i]) {
            computeDist(i, -1, 0);
            break;
        }
    }

    int maxIndex = -1;
    for (int i = 0; i < N; i++)
        if (marked[i] && (maxIndex == -1 || (dist[maxIndex] < dist[i])))
            maxIndex = i;

    memset(dist, 0, sizeof dist);
    computeDist(maxIndex, -1, 0);

    int longestDist = 0;

    for (int i = 0; i < N; i++)
        longestDist = max(longestDist, dist[i]);

    printf("%d\n", totalDist - longestDist);

	return 0;
}
