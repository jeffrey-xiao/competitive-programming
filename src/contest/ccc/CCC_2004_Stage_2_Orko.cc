#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define rfloat(x) scanf("%f", &(x))
#define SIZE 300001
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

bool isA[20];
int dp[1 << 20][2];

int compute (int i, bool isATurn, int sz) {
    if (sz == 0)
        return 0;
    if (dp[i][isATurn] != -1)
        return dp[i][isATurn];
    int best = 0;
    for (int j = 0; j < 20; j++) {
        if ((i & 1 << j) && isA[j] == isATurn) {
            int minV = 10;
            i ^= 1 << j;
            bool valid = false;
            for (int k = 0; k < 5; k++) {
                int next = (j/5)*5 + k;
                if ((i & 1 << next) != 0 && isA[next] == !isATurn) {
                    valid = true;
                    i ^= 1 << next;
                    if (k > j % 5) {
                        minV = min(minV, sz - 1 - compute(i, !isATurn, sz-1));
                    } else {
                        minV = min(minV, 1 + compute(i, isATurn, sz - 1));
                    }
                    i ^= 1 << next;
                }
            }
            if (!valid) {
                for (int k = 0; k < 20; k++) {
                    if ((i & 1 << k) != 0 && isA[k] != isATurn) {
                        i ^= (1 << k);
                        minV = min(minV, 1 + compute(i, isATurn, sz - 1));
                        i ^= (1 << k);
                    }

                }
            }
            i ^= 1 << j;
            best = max(best, minV);
        }
    }
    return dp[i][isATurn ? 1 : 0] = best;
}

int main () {
    string s;
    getline(cin, s);
    while (s != "* * * * * * * * * *") {
        memset(isA, false, sizeof isA);
        memset(dp, -1, sizeof dp);
        stringstream os(s);
        string temp;
        while (os >> temp) {
            int num = 0;
            if (temp[0] == 'R')
                num = 0;
            else if (temp[0] == 'Y')
                num = 5;
            else if (temp[0] == 'G')
                num = 10;
            else
                num = 15;
            num += temp[1] - '1';
            isA[num] = true;
        }
        printf("%d\n", compute((1 << 20) - 1, true, 10));
        getline(cin, s);
    }
}
