#include <bits/stdc++.h>

using namespace std;

#define mp make_pair
#define pb push_back
typedef pair<int, int> pi;

int n, m;
vector<vector<pair<int, pi>>> adj (50000);
pi minA[50000];
pi minB[50000];
pi minT[50000];
int minBB[50000];
int main () {
    scanf("%d%d", &n, &m);
    set<int> uniA;
    for (int i = 0; i < n; i++) {
        minA[i] = mp(1 << 25, 1 << 25);
        minB[i] = mp(1 << 25, 1 << 25);
        minT[i] = mp(1 << 25, 1 << 25);
    }
    minA[0] = minB[0] = minT[0] = mp(0, 0);
    for (int i = 0; i < m; i++) {
        int a, b, c, d;
        scanf("%d%d%d%d", &a, &b, &c, &d);
        a--, b--;

        uniA.insert(c);
        adj[a].pb(mp(b, mp(c, d)));
        adj[b].pb(mp(a, mp(c, d)));
    }
    if (uniA.size() < 5000) {
        int res = 1 << 20;
        bool flag[n];
        for (int a : uniA) {
            for (int i = 0; i < n; i++) {
                minBB[i] = 1 << 20;
            }
            memset(flag, false, sizeof flag);
            minBB[0] = 0;
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
                    if (max(minBB[curr], next.second.second) < minBB[next.first]) {
                        minBB[next.first] = max(minBB[curr], next.second.second);
                        if (!flag[next.first]) {
                            q.push(next.first);
                            flag[next.first] = true;
                        }
                    }
                }
            }
            res = min(res, a + minBB[n-1]);
        }
        if (res == 1 << 20)
            printf("%d\n", -1);
        else
            printf("%d\n", res);
    } else {

        queue<int> q;
        q.push(0);
        while (!q.empty()) {
            int curr = q.front();
            q.pop();
            pi a = minA[curr];
            pi b = minB[curr];
            pi t = minT[curr];
            for (pair<int, pi> next : adj[curr]) {
                pi na = minA[next.first];
                pi nb = minB[next.first];
                pi nt = minT[next.first];
                if (max(a.first, next.second.first) < na.first || (max(a.first, next.second.first) == na.first && max(a.second, next.second.second) < na.second)) {
                    minA[next.first] = mp(max(a.first, next.second.first), max(a.second, next.second.second));
                    q.push(next.first);
                }
                if (max(b.second, next.second.second) < nb.second || (max(b.second, next.second.second) == nb.second && max(b.first, next.second.first) < nb.first)) {
                    minB[next.first] = mp(max(b.first, next.second.first), max(b.second, next.second.second));
                    q.push(next.first);
                }
                if (max(t.second, next.second.second) + max(t.first, next.second.first) < nt.first + nt.second) {
                    minT[next.first] = mp(max(t.first, next.second.first), max(t.second, next.second.second));
                    q.push(next.first);
                }
            }
        }
        int res = min(min(minA[n-1].first + minA[n-1].second, minB[n-1].first + minB[n-1].second), minT[n-1].first + minT[n-1].second);
        if (res > 100000)
            printf("%d\n", -1);
        else
            printf("%d\n", res);
    }
}
