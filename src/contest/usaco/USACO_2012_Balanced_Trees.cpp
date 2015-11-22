#include <bits/stdc++.h>

using namespace std;

#define pb push_back
#define SIZE 40001
#define mp make_pair

typedef pair<int, int> pi;
struct state{
    int x, par, i;
    state (int x, int par, int i) {
        this->x = x;
        this->par = par;
        this->i = i;
    }
};

int N;
vector<vector<int>> adj (SIZE);
int p[SIZE][2]; // parent
int c[SIZE][2]; // child
int child1[SIZE][2];
int child2[SIZE][2];
int maxI[SIZE][2];
int par[SIZE];
bool s[SIZE];
char ch;
queue<int> q;
int res = 0;

void solve (int i, int prev) {
    res = max(res, max(min(p[i][0], c[i][1]), min(p[i][1], c[i][0])));
    for (int j : adj[i]) {
        if (j != prev)
            solve(j, i);
    }
}
void dfs (int i, int prev) {
    if (i != 0)
        q.push(i);
    par[i] = prev;
    for (int j : adj[i]) {
        if (j == prev)
            continue;
        dfs(j, i);

        if (c[j][0] > child1[i][0]) {
            child2[i][0] = child1[i][0];
            child1[i][0] = c[j][0];
            maxI[i][0] = j;
        } else if (c[j][0] > child2[i][0]) {
            child2[i][0] = c[j][0];
        }

        if (c[j][1] > child1[i][1]) {
            child2[i][1] = child1[i][1];
            child1[i][1] = c[j][1];
            maxI[i][1] = j;
        } else if (c[j][1] > child2[i][1]) {
            child2[i][1] = c[j][1];
        }
    }
    c[i][0] = child1[i][0];
    c[i][1] = child1[i][1];
    c[i][s[i]]++;
    if (c[i][!s[i]])
        c[i][!s[i]]--;
}

int main () {
    scanf("%d", &N);
    for (int i = 1; i < N; i++) {
        int a;
        scanf("%d", &a);
        adj[i].pb(a-1);
        adj[a-1].pb(i);
    }
    for (int i = 0; i < N; i++) {
        scanf(" %c", &ch);
        if (ch == '(')
            s[i] = false;
        else
            s[i] = true;
    }
    dfs(0, -1);
    while (!q.empty()) {
        int curr = q.front();
        q.pop();
        int parent = par[curr];
        int maxC0 = curr == maxI[parent][0] ? child2[parent][0] : child1[parent][0];
        int maxC1 = curr == maxI[parent][1] ? child2[parent][1] : child1[parent][1];


        int add1 = (!s[parent] ? 1 : -1);
        int add2 = (s[parent] ? 1 : -1);
        p[curr][0] = max(0, max(p[parent][0], maxC0) + add1);
        p[curr][1] = max(0, max(p[parent][1], maxC1) + add2);
    }
    solve(0, -1);
    printf("%d\n", res);
    return 0;
}
