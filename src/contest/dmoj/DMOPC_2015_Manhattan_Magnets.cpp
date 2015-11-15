#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 4000
#define RSIZE SIZE*2 - 1
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int movex[4] = {-1,1,0,0};
int movey[4] = {0,0,-1,1};

int rg[RSIZE + 2][RSIZE + 2];

int m, n;
pi magnet[10];

void rotate (int& x, int& y) {
    int px = x;
    int py = y;
    x = px + py;
    y = py - px + SIZE - 1;
}

int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    rint(m);
    for (int i = 0; i < m; i++) {
        int x, y;
        rint(x), rint(y);
        magnet[i] = mp(x, y);
    }
    rint(n);
    for (int i = 0; i < n; i++) {
        int x, y;
        rint(x), rint(y);
        int d = 1 << 30;
        for (int j = 0; j < m; j++)
            d = min(d, abs(x - magnet[j].first) + abs(y - magnet[j].second));
        d--;
        if (d == -1)
            continue;
        rotate(x, y);
        x -= d;
        y -= d;
        int x1 = max(1, x + 1), y1 = max(1, y + 1), x2 = min(RSIZE + 1, x + 2*d + 2), y2 = min(RSIZE + 1, y + 2*d + 2);
        rg[x1][y1]++;
        rg[x2][y1]--;
        rg[x1][y2]--;
        rg[x2][y2]++;
    }
    for (int i = 1; i <= RSIZE; i++)
        for (int j = 1; j <= RSIZE; j++)
            rg[i][j] += rg[i-1][j] + rg[i][j-1] - rg[i-1][j-1];
    int ans = 0;
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            int x = i, y = j;
            rotate(x, y);
            ans = max(ans, rg[x + 1][y + 1]);
        }
    }

    printf("%d\n", ans);
	return 0;
}
