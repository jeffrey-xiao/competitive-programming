#include <bits/stdc++.h>

using namespace std;

#define SIZE 500001
#define pb push_back
#define mp make_pair

typedef pair<int, int> pi;
typedef long long ll;

vector<vector<pi>> adj (SIZE);
int n, k;
ll total;
bool inTree[SIZE];
ll max1[SIZE], max2[SIZE], maxi[SIZE], maxChain[SIZE], minV[SIZE];


void adjust(ll v, int i, int j) {
    if (v >= max1[i]) {
        max2[i] = max1[i];
        max1[i] = v;
        maxi[i] = j;
    } else if (v > max2[i]) {
        max2[i] = v;
    }
}
void dfs2 (int i, int prev, ll prevV) {
    maxChain[i] = max(prevV, max1[i]);
    for (pi next : adj[i]) {
        if (next.first == prev || !inTree[next.first])
            continue;
        if (maxi[i] == next.first)
            dfs2(next.first, i, max(prevV, max2[i]) + next.second);
        else
            dfs2(next.first, i, max(prevV, max1[i]) + next.second);
    }
}
void dfs1 (int i, int prev) {
    bool hasNext = false;
    for (pi next : adj[i]) {
        if (!inTree[next.first] || next.first == prev)
            continue;
        hasNext = true;
        dfs1(next.first, i);
        adjust(max1[next.first] + next.second, i, next.first);
    }
    if (!hasNext)
        max1[i] = 0;
}
bool buildTree (int i, int prev) {
    bool has = false;
    for (pi next : adj[i]) {
        if (next.first == prev)
            continue;
        if (buildTree(next.first, i)) {
            has = true;
            total += next.second;
        }

    }
    return inTree[i] |= has;
}

int main () {
    scanf("%d%d", &n, &k);
    memset(max1, - 1 << 30, sizeof max1);
    memset(max2, - 1 << 30, sizeof max2);
    memset(minV, -1, sizeof minV);
    for (int i = 0; i < n-1; i++) {
        int a, b, c;
        scanf("%d%d%d", &a, &b, &c);
        a--, b--;
        adj[a].pb(mp(b, c));
        adj[b].pb(mp(a, c));
    }
    for (int i = 0; i < k; i++) {
        int a;
        scanf("%d", &a);
        inTree[a-1] = true;
    }
    for (int i = 0; i < n; i++) {
        if (inTree[i]) {
            buildTree(i, -1);
            break;
        }
    }
    for (int i = 0; i < n; i++) {
        if (inTree[i]) {
            dfs1(i, -1);
            dfs2(i, -1, 0);
            break;
        }
    }
    queue<int> q;
    for (int i = 0; i < n; i++) {
        if (inTree[i]) {
            q.push(i);
            minV[i] = 0;
        }
    }
    while (!q.empty()) {
        int curr = q.front();
        q.pop();
        for (pi next : adj[curr]) {
            if (minV[next.first] != -1) {
                continue;
            }
            minV[next.first] = minV[curr] + next.second;
            maxChain[next.first] = maxChain[curr];
            q.push(next.first);
        }
    }
    for (int i = 0; i < n; i++) {
        printf("%lld\n", total*2 - maxChain[i] + minV[i]);
    }
    return 0;
}
