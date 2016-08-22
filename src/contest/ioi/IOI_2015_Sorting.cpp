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

int val[SIZE], valIndex[SIZE];
int sorted[SIZE];
unordered_map<int, int> toIndex;

int findSwapPairs (int N, int S[], int M, int X[], int Y[], int P[], int Q[]) {
    for (int i = 0; i < N; i++) {
        val[i] = S[i];
        sorted[i] = val[i];
        valIndex[i] = i;
        toIndex[val[i]] = i;
    }

    sort(sorted, sorted + N);

    int lo = 0, hi = N - 1;
    vector<int> s1, s2;

    while (lo <= hi) {
        int mid = (hi + lo) / 2;

        for (int i = mid - 1; i >= 0; i--)
            swap(valIndex[X[i]], valIndex[Y[i]]);

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (sorted[valIndex[i]] != val[i]) {
                int j = toIndex[sorted[valIndex[i]]];

                s1.pb(i);
                s2.pb(j);
                swap(val[i], val[j]);
                toIndex[val[j]] = j;
                toIndex[val[i]] = i;
                cnt++;
            }
        }

        for (int i = s1.size() - 1; i >= 0; i--) {
            swap(val[s1[i]], val[s2[i]]);
            toIndex[val[s1[i]]] = s1[i];
            toIndex[val[s2[i]]] = s2[i];
        }
        for (int i = 0; i < mid; i++)
            swap(valIndex[X[i]], valIndex[Y[i]]);
        if (cnt <= mid)
            hi = mid - 1;
        else
            lo = mid + 1;
        s1.clear();
        s2.clear();
    }

    // rebuilding sequence

    vector<int> ans1, ans2;

    for (int i = lo - 1; i >= 0; i--)
        swap(valIndex[X[i]], valIndex[Y[i]]);

    for (int i = 0; i < N; i++) {
        if (sorted[valIndex[i]] != val[i]) {
            int j = toIndex[sorted[valIndex[i]]];
            s1.pb(i);
            s2.pb(j);
            ans1.pb(val[i]);
            ans2.pb(val[j]);
            swap(val[i], val[j]);
            toIndex[val[j]] = j;
            toIndex[val[i]] = i;
        }
    }

    for (int i = s1.size() - 1; i >= 0; i--) {
        swap(val[s1[i]], val[s2[i]]);
        toIndex[val[s1[i]]] = s1[i];
        toIndex[val[s2[i]]] = s2[i];
    }
    for (int i = 0; i < lo; i++)
        swap(valIndex[X[i]], valIndex[Y[i]]);

    for (int i = 0; i < lo; i++) {
        swap(val[X[i]], val[Y[i]]);
        toIndex[val[X[i]]] = X[i];
        toIndex[val[Y[i]]] = Y[i];

        if (i >= (int)ans1.size())
            continue;

        int si = toIndex[ans1[i]];
        int sj = toIndex[ans2[i]];

        ans1[i] = si;
        ans2[i] = sj;

        swap(val[si], val[sj]);
        toIndex[val[si]] = si;
        toIndex[val[sj]] = sj;
    }

    for (int i = 0; i < M; i++)
        P[i] = Q[i] = 0;

    for (int i = 0; i < min((int)s1.size(), lo); i++)
        P[i] = ans1[i], Q[i] = ans2[i];

    return lo;
}

int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    int N, M;
    scanf("%d", &N);

    int S[N];

    for (int i = 0; i < N; i++) {
        scanf("%d", &S[i]);
    }

    scanf("%d", &M);

    int X[M], Y[M];

    for (int i = 0; i < M; i++)
        scanf("%d%d", &X[i], &Y[i]);

    int P[M], Q[M];

    int ans = findSwapPairs(N, S, M, X, Y, P, Q);

    printf("%d\n", ans);
    for (int i = 0; i < ans; i++)
        printf("%d %d\n", P[i], Q[i]);
	return 0;
}
