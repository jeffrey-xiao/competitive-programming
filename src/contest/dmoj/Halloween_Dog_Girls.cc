#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define BASE 137
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 5001
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

string in;
int N;

ll power[SIZE], h[SIZE];
bool done[SIZE];
ll getHash (int l, int r) {
    return (h[r] - h[l - 1] * power[r - l + 1] % MOD + MOD) % MOD;
}

int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    cin >> in;
    in = " " + in;

    power[0] = 1ll;
    N = in.length();
    for (int i = 1; i < N; i++) {
        power[i] = (power[i - 1] * BASE) % MOD;
        h[i] = (h[i-1] * BASE + (in[i] - 'a' + 1)) % MOD;
    }

    ll ans = 0;

    unordered_map<ll, int> hashOcc;
    set<ll> visited;

    for (int gap = 1; gap < N; gap++) {
        memset(done, 0, sizeof done);
        for (int i = 1; i <= N - gap; i++) {
            if (done[i])
                continue;

            int cnt = 1;

            ll currHash = getHash(i, i + gap - 1);
            if (visited.count(currHash))
                continue;
            for (int j = i + gap; j <= N - gap; j += gap, cnt++) {
                int l = j;
                int r = j + gap - 1;

                if (currHash != getHash(l, r))
                    break;
                done[l] = true;
                visited.insert(getHash(i, r));
            }

            if (cnt <= 1)
                continue;

            hashOcc[currHash] = max(hashOcc[currHash], cnt);
        }
    }

    for (auto& kv : hashOcc) {
        ans += kv.second - 1;
    }
    printf("%lld\n", ans);
	return 0;
}
