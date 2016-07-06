#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 150000
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

struct State {
    int len, cycle, firstOcc, secondOcc;
    State () {}
    State (int len, int cycle, int firstOcc, int secondOcc) {
        this->len = len;
        this->cycle = cycle;
        this->firstOcc = firstOcc;
        this->secondOcc = secondOcc;
    }
};

int N, M, P, Q;
vector<vector<int>> adj (SIZE);
map<ll, State> vis;
map<ll, int> curr;

ll getStateIndex (int prev, int u) {
    if (find(adj[u].begin(), adj[u].end(), prev) != adj[u].end())
        return 1ll * prev * N + u;
    return 1ll * adj[u][adj[u].size() - 1] * N + u;
}

int dfs (int u, int prev, int depth, int firstOcc, int secondOcc) {
    rep++;
    if (u == P) {
        if (firstOcc == -1)
            firstOcc = depth;
        else
            secondOcc = depth;
    }

    int nextNode;
    if (adj[u].size() == 1)
        nextNode = adj[u][0];
    else if (adj[u][0] == prev)
        nextNode = adj[u][1];
    else
        nextNode = adj[u][0];

    ll currState = getStateIndex(prev, u);
    ll nextState = getStateIndex(u, nextNode);

    curr[currState] = depth;

    if (curr.count(nextState)) {
        int cycleLength = depth - curr[nextState] + 1;
        set<int> val;

        if (firstOcc >= curr[nextState])
            val.insert((firstOcc - curr[nextState] + 1) % cycleLength);

        if (secondOcc >= curr[nextState])
            val.insert((secondOcc - curr[nextState] + 1) % cycleLength);

        val.erase(-1);

        State newState = State(0, cycleLength, -1, -1);

        for (int x : val) {
            if (newState.firstOcc == -1)
                newState.firstOcc = x;
            else
                newState.secondOcc = x;
        }

        vis[currState] = newState;

        return curr[nextState];
    } else if (vis.count(nextState)) {
        State next = vis[nextState];

        set<int> val;

        val.insert(max(-1, firstOcc - depth));
        val.insert(max(-1, secondOcc - depth));

        if (next.firstOcc != -1)
            val.insert(next.firstOcc + 1);

        if (next.secondOcc != -1)
            val.insert(next.secondOcc + 1);

        val.erase(-1);

        State newState = State(next.len + 1, next.cycle, -1, -1);

        for (int x : val) {
            if (newState.firstOcc == -1)
                newState.firstOcc = x;
            else
                newState.secondOcc = x;
        }

        vis[currState] = newState;

        return -1;
    } else {
        int res = dfs(nextNode, u, depth + 1, firstOcc, secondOcc);
        State next = vis[nextState];

        if (res == -1 || depth < res) {
            set<int> val;

            val.insert(max(-1, firstOcc - depth));
            val.insert(max(-1, secondOcc - depth));

            if (next.firstOcc != -1)
                val.insert(next.firstOcc + 1);

            if (next.secondOcc != -1)
                val.insert(next.secondOcc + 1);

            val.erase(-1);

            State newState = State(next.len + 1, next.cycle, -1, -1);

            for (int x : val) {
                if (newState.firstOcc == -1)
                    newState.firstOcc = x;
                else
                    newState.secondOcc = x;
            }

            vis[currState] = newState;
        } else {
            set<int> val;

            val.insert(max(-1, firstOcc - depth));
            val.insert(max(-1, secondOcc - depth));

            if (next.firstOcc != -1)
                val.insert((next.firstOcc + 1) % next.cycle);

            if (next.secondOcc != -1)
                val.insert((    next.secondOcc + 1) % next.cycle);

            val.erase(-1);

            State newState = State(0, next.cycle, -1, -1);

            for (int x : val) {
                if (newState.firstOcc == -1)
                    newState.firstOcc = x;
                else
                    newState.secondOcc = x;
            }

            vis[currState] = newState;
        }

        return res;
    }
}

int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    scanf("%d%d%d", &N, &M, &P);

    for (int i = 0; i < M; i++) {
        int u, v;
        scanf("%d%d", &u, &v);

        if (adj[u].size() < 2)
            adj[u].pb(v);
        if (adj[v].size() < 2)
            adj[v].pb(u);
    }

    vector<State> v;

    for (int i = 0; i < N; i++) {
        curr.clear();
        if (!vis.count(1ll * adj[i][adj[i].size() - 1] * N + i))
            dfs(i, adj[i][adj[i].size() - 1], 0, -1, -1);
        v.pb(vis[1ll * adj[i][adj[i].size() - 1] * N + i]);
    }

    scanf("%d", &Q);

    for (int i = 0; i < Q; i++) {
        int K;
        scanf("%d", &K);

        int cnt = 0;
        for (int j = 0; j < N; j++) {

            State next = v[j];

            // never can reach restaurant
            if (next.firstOcc == -1) {
                continue;
            }

            // restaurant can only be reached once
            if (next.firstOcc < next.len) {
                if (next.firstOcc == K) {
                    cnt++;
                    continue;
                }
            }

            // restaurant is on cycle
            else if (K >= next.firstOcc && (K - next.firstOcc) % next.cycle == 0) {
                cnt++;
                continue;
            }

            // never can reach restaurant
            if (next.secondOcc == -1)
                continue;

            if (next.secondOcc < next.len) {
                if (next.secondOcc == K) {
                    cnt++;
                    continue;
                }
            }

            // restaurant is on cycle
            else if (K >= next.secondOcc && (K - next.secondOcc) % next.cycle == 0) {
                cnt++;
                continue;
            }

        }
        printf("%d ", cnt);
    }
	return 0;
}
