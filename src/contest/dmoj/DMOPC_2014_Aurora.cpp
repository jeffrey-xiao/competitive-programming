#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

int n, m;
ll a, b, c;
int in[100001];
ll prefix[100001], suffix[100001];
int main () {
    scanf("%d%d%lld%lld%lld", &n, &m, &a, &b, &c);
    for (int i = 1; i <= n; i++) {
        scanf("%d", &in[i]);
    }
    sort(in+1, in+n+1);
    for (int i = 1; i <= n; i++)
        prefix[i] = (in[i] - 1) * b + prefix[i-1];
    for (int i = n; i >= 1; i--)
        suffix[i] = suffix[i+1] + (in[i] - 1)*a + c * (n-i);
    ll minV = 1ll << 60;
    for (int i = 0; i <= n; i++)
        minV = min(minV, prefix[i] + suffix[i+1]);
    cout << minV << endl;
    return 0;
}
