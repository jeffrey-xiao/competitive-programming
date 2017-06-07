#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 2502
#define BIT_SIZE 100001
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int N, K, M, T;
int val[SIZE][SIZE], bit[BIT_SIZE];

struct state {
    int col, val;
    state (int col, int val) {
        this->col = col;
        this->val = val;
    }
    bool operator < (const state& rhs) const {
        return col < rhs.col;
    }
};

void update (int x, int val) {
    if (x == 0)
        return;
    for (int i = x; i < BIT_SIZE; i += (i & -i))
        bit[i] += val;
}

int query (int x) {
    x = min(BIT_SIZE - 1, x);
    int sum = 0;
    for (int i = x; i > 0; i -= (i & -i))
        sum += bit[i];
    return sum;
}

int main () {
    // freopen("in.txt", "r", stdin);
    // freopen("out.txt", "w", stdout);

    rint(N);
    rint(K);

    rint(M);

    for (int i = 0; i < M; i++) {
        int len;
        rint(len);
        int R[len];
        int C[len];

        for (int j = 0; j < len; j++) {
            scanf("%d%d", &R[j], &C[j]);
        }

        for (int j = 1; j < 1 << len; j++) {
            int sz = 0;
            int minR = 1, maxR = N, minC = 1, maxC = N;
            for (int k = 0; k < len; k++) {
                if ((j & 1 << k) > 0) {
                    sz++;

                    int u = max(0, R[k] - K + 1);
                    int d = R[k];

                    int l = max(0, C[k] - K + 1);
                    int r = C[k];
                    minR = max(minR, u);
                    maxR = min(maxR, d);

                    minC = max(minC, l);
                    maxC = min(maxC, r);
                }
            }
            if (minR <= maxR && minC <= maxC) {
                maxR++;
                maxC++;
                if (sz % 2 == 0) {
                    val[minR][minC]--;
                    val[minR][maxC]++;
                    val[maxR][minC]++;
                    val[maxR][maxC]--;
                } else {
                    val[minR][minC]++;
                    val[minR][maxC]--;
                    val[maxR][minC]--;
                    val[maxR][maxC]++;
                }
            }
        }
    }

    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            val[i][j] += val[i - 1][j] + val[i][j - 1] - val[i - 1][j - 1];
        }
    }

    vector<set<state>> rows (N + 2);

    for (int i = 1; i <= N - K + 1; i++) {
        for (int j = 1; j <= N - K + 1; j++) {
            if (val[i][j] != 0) {
                update(val[i][j], 1);
                rows[i].insert(state(j, val[i][j]));
            }
        }
    }
    rint(T);

    double total = (N - K + 1) * (N - K + 1);
    for (int t = 0; t < T; t++) {
        int type;
        rint(type);
        if (type == 1) {
            int r, c;
            rint(r), rint(c);

            int left = max(1, c - K + 1);
            int right = min(N - K + 1, c);
            int top = min(N - K + 1, r);
            int bot = max(1, r - K + 1);
            for (int i = top; i >= bot; i--) {
                auto iter = rows[i].lower_bound(state(left, 0));
                while (iter != rows[i].end() && iter->col <= right) {
                    update(iter->val, -1);
                    iter = rows[i].erase(iter);
                }
            }
        } else if (type == 2) {
            int amt;
            rint(amt);
            printf("%.5f\n", (query(BIT_SIZE - 1) - query(amt - 1)) / total);
        }
    }

	return 0;
}
