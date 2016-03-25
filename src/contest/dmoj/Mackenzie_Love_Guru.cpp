#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 10
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

int modpow (int base, int pow) {
    if (pow == 0)
        return 1;
    if (pow == 1)
        return base;
    if (pow % 2 == 0)
        return modpow(base * base % MOD, pow / 2) % MOD;
    return base * modpow(base * base % MOD, pow / 2) % MOD;
}

int main () {

    string a, b;
    cin >> a >> b;

    int compatA = 0;
    int compatB = 0;

    for (int i = 0; i < a.size(); i++) {
        compatA = (compatA + modpow(tolower(a[i]) - 'a' + 1, i + 1)) % MOD;
    }

    for (int i = 0; i < b.size(); i++) {
        compatB = (compatB + modpow(tolower(b[i]) - 'a' + 1, i + 1)) % MOD;
    }

    if (compatA == 0)
        compatA = MOD;
    if (compatB == 0)
        compatB = MOD;

    printf("%d\n", compatA + compatB);

	return 0;
}
