#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define MAX_N 100000
#define MAX_K 40
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

struct State {
    int color, d, sum;
    double time = 0;
    State (int color, int d, int sum): color(color), d(d), sum(sum) {}
};

vector<State> *curr[MAX_K], *prevState[MAX_K];
int N, K, L;
double ans[MAX_K];

void insert (int d, int color) {
    for (int i = 0; i < K; i++)
        prevState[i] = curr[i];
    for (int i = 0; i < K; i++) {
        int next = (color + i) % K;
        curr[i] = prevState[next];
    }

    for (int i = 0; i < K; i++) {
        curr[i]->push_back(State((color + i) % K, d, 0));
    }
}

void query (int d, int color) {
    if (curr[color]->size() != 0) {
        curr[color]->front().sum += 1;
        curr[color]->front().time += (d + curr[color]->front().d) / 2.0;
        curr[color]->back().sum -= 1;
        ans[color] += (d - curr[color]->back().d) / 2.0;
    } else {
        ans[color] += d;
    }
}

int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    scanf("%d%d%d", &N, &K, &L);

    for (int i = 0; i < K; i++)
        curr[i] = new vector<State>();

    for (int i = 0; i < N; i++) {
        int d, color;
        char dir;
        scanf("%d%d %c", &d, &color, &dir);
        if (dir == 'D') {
            ans[color] += L - d;
            insert(d, color);
        } else {
            query(d, color);
        }
    }

    for (int i = 0; i < K; i++) {
        for (int j = 1; j < (int)curr[i]->size(); j++) {
            (*curr[i])[j].sum += (*curr[i])[j - 1].sum;
            ans[(*curr[i])[j].color] += ((*curr[i])[j].d - (*curr[i])[j - 1].d) / 2.0 * (*curr[i])[j - 1].sum;
        }
        for (int j = 0; j < (int)curr[i]->size(); j++)
            ans[(*curr[i])[j].color] += (*curr[i])[j].time;
    }

    for (int i = 0; i < K; i++)
        printf("%.1f\n", ans[i]);

	return 0;
}
