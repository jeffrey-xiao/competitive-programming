#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 1000010
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

struct Edge {
  int dest, cost, prev;
  Edge(int dest, int cost, int prev) {
    this->dest = dest;
    this->cost = cost;
    this->prev = prev;
  }
  Edge() {}
};

int n;
bool inCycle[SIZE], used[SIZE * 2];
ll sz[SIZE], nextDist[SIZE];
int disc[SIZE], lo[SIZE];

Edge e[SIZE * 2];
int last[SIZE];
int cnt = 0;

stack<int> s;
vector<int> currCycle;

void addEdge(int i, int j, int dist) {
  e[cnt] = Edge(j, dist, last[i]);
  last[i] = cnt++;
  e[cnt] = Edge(i, dist, last[j]);
  last[j] = cnt++;
}

void dfs(int i) {
  disc[i] = lo[i] = cnt++;
  s.push(i);
  for (int j = last[i]; j != -1; j = e[j].prev) {
    if (used[j])
      continue;
    used[j] = used[j ^ 1] = true;
    if (disc[e[j].dest] == -1) {
      dfs(e[j].dest);
      lo[i] = min(lo[i], lo[e[j].dest]);
    } else {
      lo[i] = min(lo[i], disc[e[j].dest]);
    }
  }
  if (disc[i] == lo[i]) {
    vector<int> curr;
    while (s.top() != i) {
      curr.pb(s.top());
      s.pop();
    }
    curr.pb(s.top());
    s.pop();
    if (curr.size() > 1) {
      currCycle = curr;
      for (int j : curr)
        inCycle[j] = true;
    }
  }
}

ll computeSizes(int i, int prev) {
  for (int j = last[i]; j != -1; j = e[j].prev)
    if (!inCycle[e[j].dest] && e[j].dest != prev)
      sz[i] = max(sz[i], computeSizes(e[j].dest, i) + e[j].cost);
  return sz[i];
}

pair<int, ll> getFarthest(int i, int root, int par, ll dist) {
  pair<int, ll> res = mp(i, dist);
  for (int j = last[i]; j != -1; j = e[j].prev) {
    if (e[j].dest == par || (inCycle[e[j].dest] && e[j].dest != root))
      continue;
    pair<int, ll> ret = getFarthest(e[j].dest, root, i, dist + e[j].cost);
    if (ret.second > res.second)
      res = ret;
  }
  return res;
}

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  rint(n);

  for (int i = 0; i < n; i++)
    disc[i] = last[i] = -1;

  for (int i = 0; i < n; i++) {
    int j, dist;
    rint(j), rint(dist);
    addEdge(i, j - 1, dist);
  }
  ll ans = 0;
  for (int i = 0; i < n; i++)
    if (disc[i] == -1) {
      dfs(i);
      ll currDist = 0;
      ll maxSubtree = 0;
      for (int j = 0; j < (int)currCycle.size(); j++) {
        computeSizes(currCycle[j], -1);
        pair<int, ll> s = getFarthest(currCycle[j], currCycle[j], -1, 0);
        s = getFarthest(s.first, currCycle[j], -1, 0);
        maxSubtree = max(maxSubtree, s.second);
        for (int k = last[currCycle[j]]; k != -1; k = e[k].prev)
          if (e[k].dest == currCycle[(j + 1) % currCycle.size()] && used[k]) {
            currDist += nextDist[currCycle[j]] = e[k].cost;
            used[k] = used[k ^ 1] = false;
            break;
          }
      }
      ll maxDist = 0;
      ll maxCurr = sz[currCycle[0]] + nextDist[currCycle[0]];
      for (int j = 1; j < (int)currCycle.size(); j++) {
        maxDist = max(maxDist, maxCurr + sz[currCycle[j]]);
        maxCurr = nextDist[currCycle[j]] + max(maxCurr, sz[currCycle[j]]);
      }
      // backward dynamic programming
      maxCurr = currDist + sz[currCycle[0]] - nextDist[currCycle[0]];
      for (int j = 1; j < (int)currCycle.size(); j++) {
        maxDist = max(maxDist, maxCurr + sz[currCycle[j]]);
        maxCurr = max(maxCurr, currDist + sz[currCycle[j]]) - nextDist[currCycle[j]];
      }
      ans += max(maxDist, maxSubtree);
    }
  printf("%lld\n", ans);
  return 0;
}
