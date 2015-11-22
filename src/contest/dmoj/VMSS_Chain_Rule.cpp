#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define VSIZE 100000
#define ESIZE 600000

using namespace std;
typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

vector<vector<pi>> adj (VSIZE);
vector<vector<pi>> rev (VSIZE);

int bestAdj[VSIZE];
int bestRev[VSIZE];
int E;

int cap[ESIZE], to[ESIZE], nextE[ESIZE];
int last[VSIZE], q[VSIZE], dist[VSIZE];
int now[VSIZE];

void addEdge(int x, int y, int xy, int yx){
    to[E] = y, cap[E] = xy, nextE[E] = last[x];
    last[x] = E;
    E++;

    to[E] = x, cap[E] = yx, nextE[E] = last[y];
    last[y] = E;
    E++;
}

int n, m;

void shortestPath(int (&best)[VSIZE], int s){
    fill(best, best + VSIZE, -(1 << 30));
    best[s] = 0;
    priority_queue<pi> pq;
    pq.push(mp(0, s));
    while(!pq.empty()){
        pi curr = pq.top();
        pq.pop();
        for(int e = last[curr.second]; e != -1; e = nextE[e]){
            if(cap[e] + curr.first <= best[to[e]])
                continue;
            best[to[e]] = cap[e] + curr.first;
            pq.push(mp(best[to[e]], to[e]));
        }
    }
}
int main(){
    scanf("%d%d", &n, &m);
    fill(last, last + VSIZE, -1);
    for(int x = 0; x < m; x++){
        int a, b, c;
        scanf("%d%d%d", &a, &b, &c);
        addEdge(a, b, -c, -c);
    }
    shortestPath(bestAdj, 0);
    shortestPath(bestRev, n-1);
    int maxV = 0;
    for(int x = 0; x < n; x++)
        maxV = max(maxV, -(bestAdj[x] + bestRev[x]));
    printf("%d\n", maxV);
    return 0;
}
