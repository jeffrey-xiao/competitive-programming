#include <bits/stdc++.h>

using namespace std;

int T;

int bits (int n) {
    int ret = 0;
    while (n) {
        ret += n & 1;
        n /= 2;
    }
    return ret;
}

int main () {
    int T;
    cin >> T;
    for (int t = 0; t < T; t++) {
        string s;
        cin >> s;
        int ans = 0;
        int curr = 0;
        for (int i = 0; i < (int)s.size(); i++) {
            curr = curr * 10 + (s[i] - '0');
            ans = max(ans, bits(curr));
        }
        cout << ans << endl;
    }
    return 0;
}
