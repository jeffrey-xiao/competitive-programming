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

struct edge {
    int x, y, weight, index;
    edge (int x, int y, int weight, int index) {
        this->x = x;
        this->y = y;
        this->weight = weight;
        this->index = index;
    }
    bool operator<(const edge& e) const {
        return weight < e.weight;
    }
};

int N, M;
int id[500], sz[500], status[250000];

static int root (int i) {
    return i == id[i] ? i : (id[i] = root(id[i]));
}

static void join (int i, int j) {
    if (sz[i] >= sz[j]) {
        sz[i] += sz[j];
        id[j] = i;
    } else {
        sz[j] += sz[i];
        id[i] = j;
    }
}

int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    rint(N), rint(M);

    for (int i = 0; i < N; i++) {
        id[i] = i;
        sz[i] = 1;
    }

    vector<edge> e;

    for (int i = 0; i < M; i++) {
        int u, v, cost;
        scanf("%d%d%d", &u, &v, &cost);
        e.pb(edge(u - 1, v - 1, cost, i));
    }

    sort(e.begin(), e.end());

    vector<edge> prev;
    int prevCost = 0;

    for (int i = 0; i < M; i++) {
        if (e[i].weight == prevCost) {
            prev.pb(e[i]);
        } else {
            prevCost = e[i].weight;

            vector<edge> possiblyUseful;
            for (edge ed : prev) {
                if (root(ed.x) == root(ed.y)) {
                    status[ed.index] = -1;
                } else {
                    possiblyUseful.pb(ed);
                }
            }

            bool allUseful = true;
            for (edge ed : possiblyUseful) {
                int rx = root(ed.x);
                int ry = root(ed.y);
                if (rx == ry) {
                    allUseful = false;
                } else {
                    join(rx, ry);
                }
            }

            if (allUseful) {
                for (edge ed : possiblyUseful) {
                    status[ed.index] = 1;
                }
            }

            prev.clear();
            prev.pb(e[i]);
        }
    }
    vector<edge> possiblyUseful;
    for (edge ed : prev) {
        if (root(ed.x) == root(ed.y)) {
            status[ed.index] = -1;
        } else {
            possiblyUseful.pb(ed);
        }
    }

    bool allUseful = true;
    for (edge ed : possiblyUseful) {
        int rx = root(ed.x);
        int ry = root(ed.y);
        if (rx == ry) {
            allUseful = false;
        } else {
            join(rx, ry);
        }
    }

    if (allUseful) {
        for (edge ed : possiblyUseful) {
            status[ed.index] = 1;
        }
    }

    for (int i = 0; i < M; i++) {
        if (status[i] == -1) {
            printf("not useful\n");
        } else if (status[i] == 0) {
            printf("so so\n");
        } else {
            printf("useful\n");
        }
    }
	return 0;
}
