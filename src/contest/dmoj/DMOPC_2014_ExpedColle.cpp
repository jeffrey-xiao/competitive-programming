#include <bits/stdc++.h>

using namespace std;

#define mp make_pair
#define pb push_back

typedef long long ll;
typedef pair<int, int> pii;
typedef pair<int, ll> pil;

int n, r;

vector<int> pq [2001];
ll dp[2001];
inline void scan (int &x) {
    char c;
    while (c = getchar(), c < '0' || c > '9'); x = c - '0';
    while (c = getchar(), c >= '0' && c <= '9')
        x = x*10 + c - '0';
};
inline void scan (ll &x) {
    char c;
    while (c = getchar(), c < '0' || c > '9'); x = c - '0';
    while (c = getchar(), c >= '0' && c <= '9')
        x = x*10 + c - '0';
}

int main () {
    scanf("%d%d", &n, &r);
    for (int i = 0; i < n; i++) {
        int e;
        ll c, v, ca, cb, cm, va, vb, vm;
        scan(e);
        scan(c), scan(v), scan(ca), scan(cb), scan(cm), scan(va), scan(vb), scan(vm);
        for (int j = 0; j < e; j++) {
            if (c <= r) {
                pq[(int)c].push_back((int)(v));
            }
            c = (c*ca+cb)%cm;
            v = (v*va+vb)%vm;
        }
    }
    for (int i = 0; i <= r; i++) {
        sort(pq[i].begin(), pq[i].end());
        reverse(pq[i].begin(), pq[i].end());
    }
    memset(dp, -1, sizeof dp);
    dp[0] = 0;
    for (int i = 1; i <= r; i++)
        for (int k = 0; k < pq[i].size() && i*k <= r; k++)
            for (int j = r; j >= 0; j--)
                if (j - i >= 0 && dp[j-i] != -1 && dp[j-i] + pq[i][k]> dp[j])
                    dp[j] = dp[j-i] + pq[i][k];
    ll ans = 0;
    for (int i = 0; i <= r; i++)
        ans = max(ans, dp[i]);
    for (int i = 0; i < pq[0].size(); i++)
        ans += pq[0][i];
    printf("%lld", ans);
    return 0;
}

