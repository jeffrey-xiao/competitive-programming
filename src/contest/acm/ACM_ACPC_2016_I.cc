#include <bits/stdc++.h>
using namespace std;

int main () {
    int T;
    scanf("%d", &T);
    for (int t = 0; t < T; t++) {
        int N, W;
        scanf("%d", &N);
        vector<int> sz;
        for (int i = 0; i < N; i++) {
            scanf("%d", &W);
            int sum = 0;
            for (int j = 0; j < W; j++) {
                int val;
                scanf("%d", &val);
                sum += val;
            }
            sz.push_back(sum);
        }
        double totalTime = 0;
        int currTime = 0;
        sort(sz.begin(), sz.end());
        for (int i = 0; i < (int)sz.size(); i++) {
            currTime += sz[i];
            totalTime += currTime;
        }
        printf("%lf\n", totalTime / N);
    }
    return 0;
}
