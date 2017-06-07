#include <bits/stdc++.h>

using namespace std;

#define MOD 1000000000007ll;

int n;
char str[200001];

int main () {
    scanf("%d%s", &n, &str);
    int lo = 1;
    int hi = n;
    while (lo <= hi) {
        int mid = lo + (hi - lo)/2;
        unordered_set<long long> hs;
        long long hash = 0;
        long long pow = 1;
        for (int i = 0; i < mid; i++) {
            pow = (26*pow)%MOD;
            hash = (26*hash + (long long)(str[i]))%MOD;
        }
        hs.insert(hash);
        bool success = false;
        for (int i = mid; i < n; i++) {
            hash = (26*hash - pow*(long long)(str[i-mid]) + (long long)(str[i]))%MOD;
            if (hs.count(hash)) {
                success = true;
                break;
            }
            hs.insert(hash);
        }
        if (success)
            lo = mid + 1;
        else
            hi = mid - 1;
    }
    printf("%d\n", hi);
}
