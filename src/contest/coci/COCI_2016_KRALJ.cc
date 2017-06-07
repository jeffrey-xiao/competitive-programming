#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define MAX_N 500002
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int N;
int A[MAX_N], elf[MAX_N], dwarf[MAX_N];
vector<int> assignedElves[MAX_N];
// number of elves with label less than i
int occ[MAX_N];

int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    scanf("%d", &N);

    for (int i = 1; i <= N; i++) {
        scanf("%d", A + i);
        assignedElves[A[i]].push_back(i);
        occ[A[i]]++;
    }

    for (int i = 1; i <= N; i++)
        scanf("%d", dwarf + i);

    for (int i = 1; i <= N; i++)
        scanf("%d", elf + i);

    int minIndex = 1;

    for (int i = 1; i <= N; i++) {
        occ[i] += occ[i - 1];
        if (occ[minIndex] - minIndex >= occ[i] - i)
            minIndex = i;
    }
    int start = minIndex % N + 1;

    set<int> strengths;

    int ans = 0;

    for (int i = start; i < start + N; i++) {
        int pos = (i - 1) % N + 1;
        for (int elfPos : assignedElves[pos])
            strengths.insert(elf[elfPos]);

        auto best = strengths.upper_bound(dwarf[pos]);
        if (best != strengths.end()) {
            strengths.erase(*best);
            ans++;
        } else {
            strengths.erase(strengths.begin());
        }
    }

    printf("%d\n", ans);
	return 0;
}
