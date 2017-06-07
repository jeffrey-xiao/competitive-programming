#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define MAX_N 5000
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

struct Line {
    int x1, y1, x2, y2;
    Line () {}
    Line (int x1, int y1, int x2, int y2) {
        this->x1 = x1;
        this->y1 = y1;
        this->x2 = x2;
        this->y2 = y2;
        if (x1 > x2 || (x1 == x2 && y1 > y2)) {
            swap(this->x1, this->x2);
            swap(this->y1, this->y2);
        }
    }
};

int N;
Line l[MAX_N];
bool vis[MAX_N];
vector<int> adj[MAX_N];
stack<int> ret;

bool overlap (Line l1, Line l2) {
    return min(l1.x2, l2.x2) >= max(l1.x1, l2.x1);
}

int cross (int x1, int y1, int x2, int y2) {
    return x1 * y2 - x2 * y1;
}

int ccw (int x1, int y1, int x2, int y2, int x3, int y3) {
    if (x1 == x2 && x2 == x3)
        return y3 - min(y1, y2);
    return cross(x2 - x1, y2 - y1, x3 - x1, y3 - y1);
}

void dfs (int u) {
    vis[u] = true;
    for (int v : adj[u])
        if (!vis[v])
            dfs(v);
    ret.push(u);
}

int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    scanf("%d", &N);

    for (int i = 0; i < N; i++) {
       int x1, y1, x2, y2;
       scanf("%d%d%d%d", &x1, &y1, &x2, &y2);
       l[i] = Line(x1, y1, x2, y2);
    }

    for (int i = 0; i < N; i++) {
        for (int j = i + 1; j < N; j++) {
            if (overlap(l[i], l[j])) {
                if (l[j].x1 <= l[i].x1 && l[i].x1 <= l[j].x2) {
                    if (ccw(l[j].x1, l[j].y1, l[j].x2, l[j].y2, l[i].x1, l[i].y1) < 0) {
                        adj[i].push_back(j);
                    } else {
                        adj[j].push_back(i);
                    }
                } else if (l[j].x1 <= l[i].x2 && l[i].x2 <= l[j].x2) {
                    if (ccw(l[j].x1, l[j].y1, l[j].x2, l[j].y2, l[i].x2, l[i].y2) < 0) {
                        adj[i].push_back(j);
                    } else {
                        adj[j].push_back(i);
                    }
                } else if (l[i].x1 <= l[j].x2 && l[j].x2 <= l[i].x2) {
                    if (ccw(l[i].x1, l[i].y1, l[i].x2, l[i].y2, l[j].x2, l[j].y2) < 0) {
                        adj[j].push_back(i);
                    } else {
                        adj[i].push_back(j);
                    }
                } else if (l[i].x1 <= l[j].x1 && l[j].x1 <= l[i].x2) {
                    if (ccw(l[i].x1, l[i].y1, l[i].x2, l[i].y2, l[j].x1, l[j].y1) < 0) {
                        adj[j].push_back(i);
                    } else {
                        adj[i].push_back(j);
                    }
                }
            }
        }
    }

    for (int i = 0; i < N; i++)
        if (!vis[i])
            dfs(i);

    while (!ret.empty()) {
        printf("%d ", ret.top() + 1);
        ret.pop();
    }

	return 0;
}
