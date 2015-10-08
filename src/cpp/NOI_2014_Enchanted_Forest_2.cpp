#include <bits/stdc++.h>

using namespace std;

#define mp make_pair
#define pb push_back
typedef pair<int, int> pi;

int n, m;
vector<vector<pair<int, pi>>> adj (50000);
int minB[50000];
int main () {
    scanf("%d%d", &n, &m);

    set<int> uniA;
    for (int i = 0; i < m; i++) {
        int a, b, c, d;
        scanf("%d%d%d%d", &a, &b, &c, &d);
        a--, b--;
        uniA.insert(c);
        adj[a].pb(mp(b, mp(c, d)));
        adj[b].pb(mp(a, mp(c, d)));
    }
    int res = 1 << 20;
    bool flag[n];
    for (int a : uniA) {
        for (int i = 0; i < n; i++) {
            minB[i] = 1 << 20;
        }
        memset(flag, false, sizeof flag);
        minB[0] = 0;
        queue<int> q;
        q.push(0);
        flag[0] = true;
        while (!q.empty()) {
            int curr = q.front();
            flag[curr] = false;
            q.pop();
            for (pair<int, pi> next : adj[curr]) {
                if (next.second.first > a)
                    continue;
                if (max(minB[curr], next.second.second) < minB[next.first]) {
                    minB[next.first] = max(minB[curr], next.second.second);
                    if (!flag[next.first]) {
                        q.push(next.first);
                        flag[next.first] = true;
                    }
                }
            }
        }
        res = min(res, a + minB[n-1]);
    }
    if (res == 1 << 20)
        printf("%d\n", -1);
    else
        printf("%d\n", res);
}
