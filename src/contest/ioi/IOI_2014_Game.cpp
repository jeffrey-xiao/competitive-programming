#include <bits/stdc++.h>
#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rfloat(x) scanf("%lf", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 1550
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<double, double> pd;
typedef pair<ll, ll> pll;

int id[SIZE];
int size[SIZE];
int c[SIZE][SIZE];
int N;
void initialize (int n) {
    N = n;
    for (int i = 0; i < n; i++) {
        id[i] = i;
        size[i] = 1;
        for (int j = 0; j < n; j++)
            if (i != j)
                c[i][j] = 1;
    }
}
int find (int x) {
    return x == id[x] ? x : id[x] = find(id[x]);
}
bool merge (int x, int y) {
    int rootx = find(x);
    int rooty = find(y);
    if (size[rootx] < size[rooty])
        swap(rootx, rooty);
    for (int i = 0; i < N; i++) {
        if (rootx == i)
            continue;
        c[rootx][i] += c[rooty][i];
    }
    size[rootx] += size[rooty];
    id[rooty] = rootx;
    return true;
}
int hasEdge (int u, int v) {
    int rootu = find(u);
    int rootv = find(v);
    if (rootu == rootv) {
        c[rootu][rootv]--;
        c[rootv][rootu]--;
        return 1;
    }
    int countU = 0, countV = 0;
    for (int i = 0; i < N; i++) {
        countU += c[rootu][i];
        countV += c[rootv][i];
    }
    c[rootu][rootv]--;
    c[rootv][rootu]--;
    if (countU == 1 || countV == 1) {
        merge(u, v);
        return 1;
    }

    return 0;
}


