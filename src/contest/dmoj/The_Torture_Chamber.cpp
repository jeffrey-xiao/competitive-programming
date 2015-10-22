#include <bits/stdc++.h>
using namespace std;

long long start, end2;
bool sieve1[1000000];
bool sieve2[20000100];
int main () {
    scanf("%lld%lld", &start, &end2);
    if (start != 1)
        start--;
    end2--;
    int end1 = (int)(sqrt(end2));

    vector<long long> ll;
    for (int x = 3; x < end1; x += 2)
        sieve1[x] = true;
    for (int x = 1; x < end1; x++) {
        if (!sieve1[x]) {
            ll.push_back(x+1);
        }
    }
    long long cnt = 0;
    for (long long x : ll) {
        long long next = (start + x)/x;
        for (long long y = next*x - 1; y >= 0 && y < end2; y+=x)
            if (y-start >= 0) {
                if (!sieve2[(int)(y-start)])
                    cnt++;
                sieve2[(int)(y-start)] = true;
            }
    }
    printf("%lld\n", (end2-start) - cnt);
    return 0;
}
