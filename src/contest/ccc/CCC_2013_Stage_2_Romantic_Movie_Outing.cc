#include <bits/stdc++.h>

using namespace std;

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) cin >> (x)
#define SIZE 100100
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2
typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int bit[3100][3100];
bool taken[1501][1001];
int L, T;

void update (int idxx, int idxy, int value) {
    for (int x = idxx; x <= 3002; x+= (x & -x)) {
		for (int y = idxy; y <= 3002; y += (y & -y)) {
			bit[x][y] += value;
		}
	}
}
int query (int idxx, int idxy) {
    int sum = 0;
	for (int x = idxx; x > 0; x -= (x & -x)) {
		for (int y = idxy; y > 0; y -= (y & -y)) {
			sum += bit[x][y];
		}
	}
	return sum;
}
pi getPoint (int r, int c) {
    return mp(r - c + 1500, r + c);
}
int main () {
    rint(L), rint(T);
    for (int i = 0; i < T; i++) {
        char ch;
        int r, c;

        scanf(" %c%d%d", &ch, &r, &c);
        pi p = getPoint(r, c);
        if (ch == 'E') {
            if (r <= 1500) {
                update(p.first, p.second, 1);
                taken[r][c] = true;
            }
        } else if (ch == 'L') {
            if (r <= 1500) {
                update(p.first, p.second, -1);
                taken[r][c] = false;
            }
        } else {
            pi p2 = getPoint(r, c+1);
            if (taken[r][c] || taken[r][c+1]) {
                printf("No\n");
            } else {
                printf("%d\n", query(p.first, p.second) + query(p2.first, p2.second));
            }
        }
    }
    int minFirst = 1 << 30;
    int minSecond = 1 << 30;
    for (int r = L+1; r <= L + 501; r++) {
        for (int c = 1; c <= 1000; c++) {
            pi p = getPoint(r, c);
            if (taken[r][c])
                continue;
            int res = query(p.first, p.second);
            if (res <= minFirst) {
                minSecond = minFirst;
                minFirst = res;
            } else if (res < minSecond) {
                minSecond = res;
            }
        }
    }
    printf("%d\n", minFirst + minSecond);
}
