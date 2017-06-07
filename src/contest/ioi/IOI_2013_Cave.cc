#include <bits/stdc++.h>

using namespace std;

void exploreCave (int N) {
    bool found[N];
    int v[N];
    int pos[N];
    memset(found, false, sizeof found);
    for (int i = N-1; i >= 0; i--) {
        for (int j = 0; j < N; j++) {
            if (found[j])
                continue;
            v[j] = 0;
        }
        int open = (tryCombination(v) == N-i-1);
        int lo = 0;
        int hi = i;
        while (lo < hi) {
            int cnt = 0;
            int lo1 = lo;
            int hi1 = lo + (hi - lo)/2;
            for (int j = 0; j < N; j++) {
                if (found[j])
                    continue;
                v[j] = 0;
                if (lo1 <= cnt && cnt <= hi1)
                    v[j] = 1;
                cnt++;
            }
            int res = tryCombination(v);
            if ((res == N-i-1 && open) || (res != N-i-1 && !open)) {
                lo = lo + (hi - lo)/2 + 1;
                hi = hi;
            } else {
                lo = lo;
                hi = lo + (hi - lo)/2;
            }
        }
        int index = 0;
        for (int j = 0; j < N; j++) {
            if (found[j])
                continue;

            if (lo == 0) {
                index = j;
                break;
            }
            --lo;
        }
        found[index] = true;
        pos[index] = N-i-1;
        v[index] = open;
    }
    answer(v, pos);
}
