#include <bits/stdc++.h>
#define MOD (int)(1e9 + 7)

using namespace std;

typedef long long ll;

int main () {
    string s;
    cin >> s;
    ll cnt = 0;
    ll ways = 1;
    ll ans = 0;
    for (int i = s.size() - 1; i >= 0; i--) {
        if (s[i] == '1') {
            ans = (ans + (int)(s.size() - i - 1) * ways - cnt + MOD) % MOD;
            cnt = (cnt + ways) % MOD;
        } else if (s[i] == '?') {
            ans = (2 * ans + (int)(s.size() - i - 1) * ways - cnt + MOD) % MOD;
            cnt = (cnt * 2 + ways) % MOD;
            ways = (ways * 2) % MOD;
        }
    }
    printf("%lld\n", ans);
    return 0;
}
