#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 300002
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int N, K, M;
int song[SIZE];
int nextSong[SIZE];
int sorted[SIZE];
int dp[SIZE][2];
int a[SIZE];
int b[SIZE];

int solve (int i, int j, bool top) {
    int x = j ? nextSong[i] : i+1;
    int hi = j ? song[sorted[i]]+K-1 : song[sorted[i]];

    if (top) {
        for (int y = i; y < x; y++) {
            a[sorted[y]] = hi-K+1;
            b[sorted[y]] = hi;
        }
    }
    if (x > M)
        return 0;

    int& ref = dp[i][j];

    if (!top && ref != -1)
        return ref;

    if (K + solve(x, 1, false) < song[sorted[x]]-hi + solve(x, 0, false))
        ref = K + solve(x, 1, top);
    else
        ref = song[sorted[x]]-hi + solve(x, 0, top);
    return ref;
}

bool cmp (int a, int b) {
    return song[a] < song[b];
}

int main () {
    scanf("%d%d", &N, &K);
    scanf("%d", &M);

    song[0] = -1000000000;
    for (int i = 1; i <= M; i++)
        scanf("%d", &song[i]);
    song[M+1] = 1000000001;

    for (int i = 0; i < M+2; i++)
        sorted[i] = i;
    sort(sorted, sorted+M+2, cmp);

    nextSong[0] = 1;
    for (int i = 1; i <= M; i++)
        for (nextSong[i] = nextSong[i-1]; song[sorted[nextSong[i]]] - song[sorted[i]] < K; nextSong[i]++);

    memset(dp, -1, sizeof dp);

    printf("%d\n", solve(0, 0, true));
    for (int i = 1; i <= M; i++) {
        if (a[i] < 1) {
            a[i] = 1;
            b[i] = K;
        }
        if (b[i] > N) {
            a[i] = N-K+1;
            b[i] = N;
        }
        printf("%d %d\n", a[i], b[i]);
    }
    return 0;
}
