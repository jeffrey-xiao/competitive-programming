#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 1500
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int N, R, C;
int poss[] = {-1, 1};

struct State {
    int x, y, dp;
    State () {}
    State (int x, int y, int dp) {
        this->x = x;
        this->y = y;
        this->dp = dp;
    }
};

struct Point {
    int pos, p, dp;
    Point () {}
    Point (int pos, int p) {
        this->pos = pos;
        this->p = p;
    }

    bool operator < (const Point& point) const {
        return p < point.p;
    }
};

Point p[SIZE * SIZE];
State br[SIZE][4];
State bc[SIZE][4];

int getMaxBr (int i, Point p) {
    int px = p.pos / N;
    int py = p.pos % N;
    for (int x = 0; x < 4; x++) {
        if (abs(py - br[i][x].y) <= 1 && abs(px - br[i][x].x) <= 1)
            continue;
        return br[i][x].dp;
    }
    return -INF;
}

int getMaxBc (int i, Point p) {
    int px = p.pos / N;
    int py = p.pos % N;
    for (int x = 0; x < 4; x++) {
        if (abs(py - bc[i][x].y) <= 1 && abs(px - bc[i][x].x) <= 1)
            continue;
        return bc[i][x].dp;
    }
    return -INF;
}

void updateBr (int i, Point p) {
    int px = p.pos / N;
    int py = p.pos % N;
    State c = State();
    c.x = px;
    c.y = py;
    c.dp = p.dp;
    for (int x = 0; x < 4; x++) {
        if (c.dp > br[i][x].dp) {
            State temp = c;
            c = br[i][x];
            br[i][x] = temp;
        }
    }
}

void updateBc (int i, Point p) {
    int px = p.pos / N;
    int py = p.pos % N;
    State c = State();
    c.x = px;
    c.y = py;
    c.dp = p.dp;
    for (int x = 0; x < 4; x++) {
        if (c.dp > bc[i][x].dp) {
            State temp = c;
            c = bc[i][x];
            bc[i][x] = temp;
        }
    }
}

int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    scanf("%d%d%d", &N, &R, &C);
    R--, C--;

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < 4; j++) {
            br[i][j].dp = -INF;
            bc[i][j].dp = -INF;
        }
    }

    int cnt = 0;
    for (int x = 0; x < N; x++) {
        for (int y = 0; y < N; y++) {
            int leaves;
            rint(leaves);
            p[cnt] = Point(x * N + y, leaves);

            if (x == R && y == C)
                p[cnt].dp = 1;
            else
                p[cnt].dp = -INF;
            cnt++;
        }
    }

    sort(p, p + N * N);

    int y = 0;
    int ans = 0;

    for (int x = 0; x < N * N; x = y) {
        for (y = x; y < N * N && p[x].p == p[y].p; y++) {
            int px = p[y].pos / N;
            int py = p[y].pos % N;
            int best = p[y].dp;
            if (px > 0)
                best = max(best, 1 + getMaxBr(px - 1, p[y]));
            if (px + 1 < N)
                best = max(best, 1 + getMaxBr(px + 1, p[y]));
            if (py > 0)
                best = max(best, 1 + getMaxBc(py - 1, p[y]));
            if (py + 1 < N)
                best = max(best, 1 + getMaxBc(py + 1, p[y]));
            p[y].dp = best;
            ans = max(p[y].dp, ans);
        }
        for (y = x; y < N * N && p[x].p == p[y].p; y++) {
            int px = p[y].pos / N;
            int py = p[y].pos % N;
            updateBr(px, p[y]);
            updateBc(py, p[y]);
        }
    }

    printf("%d\n", ans);
	return 0;
}
