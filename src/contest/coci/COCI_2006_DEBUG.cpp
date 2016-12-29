#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define MAX_SIZE 300
#define SIZE 18
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int R, C;
int g[MAX_SIZE][MAX_SIZE];
ll pre[4][MAX_SIZE][MAX_SIZE];

bool canExpandBig2 (int i, int j, int currSize) {
    for (int k = -(currSize + SIZE); k <= currSize + SIZE + 1; k++) {
        if (pre[1][i + k][j - currSize] != pre[3][i - k + 1][j + currSize + 1]) return false;
        if (pre[0][i - currSize][j + k] != pre[2][i + currSize + 1][j - k + 1]) return false;
    }
    return true;
}

bool canExpandBig1 (int i, int j, int currSize ) {
    for (int k = -(currSize + SIZE); k <= currSize + SIZE; k++) {
        if (pre[1][i + k][j - currSize] != pre[3][i - k][j + currSize]) return false;
        if (pre[0][i - currSize][j + k] != pre[2][i + currSize][j - k]) return false;
    }
    return true;
}

bool canExpandSmall2 (int i, int j, int currSize) {
    for (int k = -(currSize + 1); k <= currSize + 2; k++) {
        if (g[i + k][j - currSize - 1] != g[i + 1 - k][j + currSize + 2]) return false;
        if (g[i - currSize - 1][j + k] != g[i + currSize + 2][j + 1 - k]) return false;
    }
    return true;
}

bool canExpandSmall1 (int i, int j, int currSize) {
    for (int k = -(currSize + 1); k <= currSize + 1; k++) {
        if (g[i + k][j - currSize - 1] != g[i - k][j + currSize + 1]) return false;
        if (g[i - currSize - 1][j + k] != g[i + currSize + 1][j - k]) return false;
    }
    return true;
}

int main () {
	//freopen("in.txt", "r", stdin);
	//freopen("out.txt", "w", stdout);

    scanf("%d%d", &R, &C);

    for (int i = 0; i < R; i++) {
        for (int j = 0; j < C; j++) {
            char val;
            scanf(" %c", &val);
            g[i][j] = val == '0' ? 0 : 1;
        }
    }

    // computing top and left
    for (int i = 0; i < R; i++) {
        for (int j = 0; j < C; j++) {
            if (i > 0) {
                pre[0][i][j] = (pre[0][i - 1][j] << 1) | g[i - 1][j];
                pre[0][i][j] &= (1LL << SIZE) - 1;
            }

            if (j > 0) {
                pre[1][i][j] = (pre[1][i][j - 1] << 1) | g[i][j - 1];
                pre[1][i][j] &= (1LL << SIZE) - 1;
            }
        }
    }

    // computing bottom and right
    for (int i = R - 1; i >= 0; i--) {
        for (int j = C - 1; j >= 0; j--) {
            if (i <= R - 2) {
                pre[2][i][j] = (pre[2][i + 1][j] << 1) | g[i + 1][j];
                pre[2][i][j] &= (1LL << SIZE) - 1;
            }

            if (j <= C - 2) {
                pre[3][i][j] = (pre[3][i][j + 1] << 1) | g[i][j + 1];
                pre[3][i][j] &= (1LL << SIZE) - 1;
            }
        }
    }

    int ans = -1;
    // attempting to expand every 1x1 square
    for (int i = 0; i < R; i++) {
        for (int j = 0; j < C; j++) {
            int currSize = 0;

            while (i - currSize >= SIZE && j - currSize >= SIZE &&
                    i + currSize + SIZE < R && j + currSize + SIZE < C && canExpandBig1(i, j, currSize))
                currSize += SIZE;

            while (i - currSize >= 1 && j - currSize >= 1 &&
                    i + currSize + 1 < R && j + currSize + 1 < C && canExpandSmall1(i, j, currSize))
                currSize++;

            if (currSize > 0)
                ans = max(ans, currSize * 2 + 1);
        }
    }

    // attempting to expand every 2x2 square
    for (int i = 0; i < R - 1; i++) {
        for (int j = 0; j < C - 1; j++) {
            int currSize = 0;
            if (g[i][j] != g[i + 1][j + 1] || g[i + 1][j] != g[i][j + 1])
                continue;


            while (i - currSize >= SIZE && j - currSize >= SIZE &&
                    i + currSize + SIZE + 1 < R && j + currSize + SIZE + 1 < C && canExpandBig2(i, j, currSize))
                currSize += SIZE;

            while (i - currSize >= 1 && j - currSize >= 1 &&
                    i + currSize + 2 < R && j + currSize + 2 < C && canExpandSmall2(i, j, currSize))
                currSize++;

            ans = max(ans, currSize * 2 + 2);
        }
    }

    printf("%d\n", ans);
	return 0;
}
