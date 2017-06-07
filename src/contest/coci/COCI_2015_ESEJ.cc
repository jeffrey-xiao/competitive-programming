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

int A, B, n;

int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    scanf("%d%d", &A, &B);

    for (char i = 'a'; i <= 'z'; i++)
        for (char j = 'a'; j <= 'z'; j++)
            for (char k = 'a'; k <= 'z'; k++)
                for (char l = 'a'; l <= 'z' && n < max(A, B / 2); l++, n++)
                    printf("%c%c%c%c ", i, j, k, l);
	printf("\n");
	return 0;
}
