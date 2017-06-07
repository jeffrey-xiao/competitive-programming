#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 10000000
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

const int FULL_MASK = 0b1111111111111111111111111111111;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int N, K;
unordered_map<int, double> dp;
string in;
double lookup[SIZE];
int rev (int mask, int len) {
    int ret = 0;
    for (int i = 0; i < len; i++)
        if ((mask & 1 << i) > 0)
            ret |= 1 << (len - i - 1);
    return ret;
}

int remove (int bitmask, int i) {
    int lowerMask = (1 << i) - 1;
    int upperMask = FULL_MASK ^ lowerMask ^ 1 << i;
    return (bitmask & upperMask) >> 1 | (bitmask & lowerMask);
}

double compute (int mask, int k) {
    if (k == K)
        return 0.0;
    if ((mask | 1 << (N - k)) < SIZE) {
        if (lookup[mask | 1 << (N - k)] != -1)
            return lookup[mask | 1 << (N - k)];
    } else {
        if (dp.find(mask | 1 << (N - k)) != dp.end())
            return dp[mask | 1 << (N - k)];
    }

    if ((rev(mask, N - k) | 1 << (N - k)) < SIZE) {
        if (lookup[rev(mask, N - k) | 1 << (N - k)] != -1)
            return lookup[rev(mask, N - k) | 1 << (N - k)];
    } else {
        if (dp.find(rev(mask, N - k) | 1 << (N - k)) != dp.end())
            return dp[rev(mask, N - k) | 1 << (N - k)];
    }

    double ret = 0;

    for (int i = 0; i < (N - k + 1) / 2; i++) {
        bool a = (mask & 1 << i) == 0;
        bool b = (mask & 1 << (N - k - i - 1)) == 0;
        int maskA = remove(mask, i);
        int maskB = remove(mask, N - k - i - 1);
        double prob = ((N - k) % 2 == 1 && i == (N - k) / 2) ? 1.0 / (N - k) : 2.0 / (N - k);

        ret += max(compute(maskA, k + 1) + !a, compute(maskB, k + 1) + !b) * prob;
    }
    if ((mask | 1 << (N - k)) < SIZE)
        lookup[mask | 1 << (N - k)] = ret;
    else
        dp[mask | 1 << (N - k)] = ret;
    return ret;
}

int main () {
    ios_base::sync_with_stdio(false); cin.tie(NULL);
	//freopen("in.txt", "r", stdin);
	//freopen("out.txt", "w", stdout);
    fill_n(lookup, SIZE, -1);
    dp.reserve(2048);
    cin >> N >> K;

    int initial = 0;
    cin >> in;
    for (int i = 0; i < N; i++)
        if (in[i] == 'W')
            initial |= 1 << i;
    printf("%.9f\n", compute(initial, 0));
	return 0;
}
