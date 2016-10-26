#include <bits/stdc++.h>

#define SIZE 150

using namespace std;

int R, C, N, M;

vector<pair<int, int>> saved, deleted;
vector<int> rows, cols;
int g[SIZE][SIZE];
int prefix[SIZE][SIZE];

int main () {
    scanf("%d%d%d%d", &R, &C, &N, &M);

    rows.push_back(0); rows.push_back(R - 1);
    cols.push_back(0); cols.push_back(C - 1);

    for (int i = 0; i < N; i++) {
        int r, c;
        scanf("%d%d", &r, &c);
        r += 7;
        c += 2;
        deleted.push_back({r, c});
        rows.push_back(r);
        cols.push_back(c);
    }

    for (int i = 0; i < M; i++) {
        int r, c;
        scanf("%d%d", &r, &c);
        r += 7;
        c += 2;
        saved.push_back({r, c});
        rows.push_back(r);
        cols.push_back(c);
    }

    sort(rows.begin(), rows.end());
    rows.erase(unique(rows.begin(), rows.end()), rows.end());
    sort(cols.begin(), cols.end());
    cols.erase(unique(cols.begin(), cols.end()), cols.end());

    for (int i = 0; i < N; i++) {
        int nr = distance(rows.begin(), lower_bound(rows.begin(), rows.end(), deleted[i].first)) + 1;
        int nc = distance(cols.begin(), lower_bound(cols.begin(), cols.end(), deleted[i].second)) + 1;
        deleted[i] = {nr, nc};
        g[nr][nc]++;
    }

    for (int i = 0; i < M; i++) {
        int nr = distance(rows.begin(), lower_bound(rows.begin(), rows.end(), saved[i].first)) + 1;
        int nc = distance(cols.begin(), lower_bound(cols.begin(), cols.end(), saved[i].second)) + 1;
        saved[i] = {nr, nc};
        g[nr][nc]--;
    }

    for (int i = 1; i <= (int)rows.size(); i++)
        for (int j = 1; j <= (int)cols.size(); j++)
            prefix[i][j] = g[i][j] + prefix[i - 1][j];
    int ans = - 1 << 30;
    int rcnt = distance(rows.begin(), lower_bound(rows.begin(), rows.end(), R - 1));
    int ccnt = distance(cols.begin(), lower_bound(cols.begin(), cols.end(), C - 1));
    for (int i = 1; i <= rcnt; i++) {
        for (int j = i; j <= rcnt; j++) {
            int curr = 0;
            for (int k = 1; k <= ccnt; k++) {
                curr += prefix[j][k] - prefix[i - 1][k];
                ans = max(ans, curr);
                curr = max(curr, 0);
            }
        }
    }
    printf("%d\n", N - ans);
    return 0;
}
