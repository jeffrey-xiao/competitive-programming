#include <iostream>
#include <stack>
#include <vector>
#include <unordered_set>
#include <algorithm>
#include <cstdio>
#include <queue>

using namespace std;

#define SIZE 500500
#define pb push_back
#define mp make_pair

typedef pair<int, int> pi;

int id[SIZE], low[SIZE], disc[SIZE], cValues[SIZE], maxV[SIZE];
bool inStack[SIZE], isEnd[SIZE], inQueue[SIZE];
int n, m;
int counter = 0, curr = 0;
stack<int> SCC;
vector<vector<int>> adj (SIZE);
vector<vector<int>> g (SIZE);

bool Compare(pi p1, pi p2){
    return p1.second > p2.second;
}

void computeSCC(int x){
    low[x] = disc[x] = curr++;
    SCC.push(x);
    inStack[x] = true;
    for(int i : adj[x]){
        if(disc[i] == -1){
            computeSCC(i);
            low[x] = min(low[x], low[i]);
        }else if(inStack[i]){
            low[x] = min(low[x], disc[i]);
        }
    }
    if(low[x] == disc[x]){
        while(SCC.top() != x){
            inStack[SCC.top()] = false;
            id[SCC.top()] = counter;
            SCC.pop();
        }
        inStack[SCC.top()] = false;
        id[SCC.top()] = counter++;
        SCC.pop();
    }
}

int main(){
    scanf("%d%d", &n, &m);
    for(int x = 0; x < n; x++)
        low[x] = disc[x] = -1;
    for(int x = 0; x < m; x++){
        int a, b;
        scanf("%d%d", &a, &b);
        a--, b--;
        adj[a].pb(b);
    }
    for(int x = 0; x < n; x++)
        if(disc[x] == -1)
            computeSCC(x);
    for(int x = 0; x < adj.size(); x++){
        for(int i : adj[x]){
            if(id[x] == id[i])
                continue;
            g[id[x]].push_back(id[i]);
        }
    }
    for(int x = 0; x < n; x++){
        int money;
        scanf("%d", &money);
        cValues[id[x]] += money;
    }
    int ans = 0;
    int start, k;
    scanf("%d%d", &start, &k);
    start--;
    for(int x = 0; x < k; x++){
        int e;
        scanf("%d", &e);
        isEnd[id[e-1]] = true;
    }
    maxV[id[start]] = cValues[id[start]];
    queue<int> pq;
    pq.push(id[start]);
    while(!pq.empty()){
        int curr = pq.front();
        pq.pop();
        inQueue[curr] = false;
        if(isEnd[curr]){
            ans = max(ans, maxV[curr]);
        }
        for(int i : g[curr]){
            if(maxV[i] >= maxV[curr] + cValues[i])
                continue;

            maxV[i] = maxV[curr] + cValues[i];
            if(!inQueue[i]){
                inQueue[i] = true;
                pq.push(i);
            }
        }
    }
    printf("%d\n", ans);
    return 0;
}
