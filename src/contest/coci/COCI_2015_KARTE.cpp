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

char in[1001];
bool contains[4][13];
int getSuit (char c) {
    switch (c) {
        case 'P':
            return 0;
        case 'K':
            return 1;
        case 'H':
            return 2;
        case 'T':
            return 3;
    }
}

int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    scanf("%s", &in);

    for (int i = 0; in[i]; i += 3) {
        int suit = getSuit(in[i]);
        int num = (in[i + 1] - '0') * 10 + in[i + 2] - '0' - 1;
        if (contains[suit][num]) {
            printf("GRESKA\n");
            return 0;
        }
        contains[suit][num] = true;
    }

    for (int i = 0; i < 4; i++) {
        int cnt = 0;
        for (int j = 0; j < 13; j++)
            cnt += !contains[i][j];
        printf("%d ", cnt);
    }
    printf("\n");
	return 0;
}
