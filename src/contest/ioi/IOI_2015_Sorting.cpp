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

int n, m;

int val[SIZE], valIndex[SIZE], x[3*SIZE], y[3*SIZE];
int sorted[SIZE];
unordered_map<int, int> toIndex;

int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    rint(n);

    for (int i = 0; i < n; i++) {
        rint(val[i]);
        sorted[i] = val[i];
        valIndex[i] = i;
        toIndex[val[i]] = i;
    }

    sort(sorted, sorted + n);

    rint(m);
    for (int i = 0; i < m; i++)
        rint(x[i]), rint(y[i]);

    int lo = 0, hi = n-1;
    vector<int> s1, s2;

    while (lo <= hi) {
        int mid = (hi + lo) / 2;

        for (int i = mid - 1; i >= 0; i--)
            swap(valIndex[x[i]], valIndex[y[i]]);

        int cnt = 0;
        for (int i = 0; i < n; i++) {
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
            swap(valIndex[x[i]], valIndex[y[i]]);
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
        swap(valIndex[x[i]], valIndex[y[i]]);

    for (int i = 0; i < n; i++) {
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
        swap(valIndex[x[i]], valIndex[y[i]]);

    for (int i = 0; i < lo; i++) {
        swap(val[x[i]], val[y[i]]);
        toIndex[val[x[i]]] = x[i];
        toIndex[val[y[i]]] = y[i];

        if (i >= ans1.size())
            continue;

        int si = toIndex[ans1[i]];
        int sj = toIndex[ans2[i]];

        ans1[i] = si;
        ans2[i] = sj;

        swap(val[si], val[sj]);
        toIndex[val[si]] = si;
        toIndex[val[sj]] = sj;
    }
    printf("%d\n", lo);
    for (int i = 0; i < lo; i++) {
        if (i < s1.size())
            printf("%d %d\n", ans1[i], ans2[i]);
        else
            printf("0 0\n");
    }
	return 0;
}
