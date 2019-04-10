#include <bits/stdc++.h>

using namespace std;

#define SIZE 500500
#define MOD 1000000007
#define pb push_back
#define mp make_pair

typedef pair<int, int> pi;

int id[SIZE], low[SIZE], disc[SIZE], cValues[SIZE], maxV[SIZE], money[SIZE], dp[SIZE][2],
    maxMoney[SIZE][2];
bool inStack[SIZE], isEnd[SIZE], inQueue[SIZE];
int n, m, last;
int counter = 0, curr = 0;
stack<int> SCC;
vector<vector<int>> adj(SIZE);
vector<unordered_set<int>> g(SIZE);

bool Compare(pi p1, pi p2) {
  return p1.second > p2.second;
}

void computeSCC(int x) {
  low[x] = disc[x] = curr++;
  SCC.push(x);
  inStack[x] = true;
  for (int i : adj[x]) {
    if (disc[i] == -1) {
      computeSCC(i);
      low[x] = min(low[x], low[i]);
    } else if (inStack[i]) {
      low[x] = min(low[x], disc[i]);
    }
  }
  if (low[x] == disc[x]) {
    while (SCC.top() != x) {
      inStack[SCC.top()] = false;
      id[SCC.top()] = counter;
      SCC.pop();
    }
    inStack[SCC.top()] = false;
    last = counter;
    id[SCC.top()] = counter++;
    SCC.pop();
  }
}
int compute(int i, bool canTake) {
  if (maxMoney[i][canTake] != -1)
    return maxMoney[i][canTake];
  int maxV = 0;
  dp[i][canTake] = 0;
  if (id[n - 1] == i) {
    dp[i][canTake] = 1;
    return canTake ? cValues[i] : 0;
  }
  for (int next : g[i]) {
    if (canTake) {

      int nextValue = compute(next, false);
      if (maxV < nextValue + cValues[i]) {
        maxV = nextValue + cValues[i];
        dp[i][canTake] = dp[next][false];
      } else if (maxV == nextValue + cValues[i]) {
        maxV = nextValue + cValues[i];
        dp[i][canTake] = (dp[i][canTake] + dp[next][false]) % MOD;
      }
      nextValue = compute(next, true);
      if (maxV < nextValue) {
        maxV = nextValue;
        dp[i][canTake] = dp[next][true];
      } else if (maxV == nextValue) {
        dp[i][canTake] = (dp[i][canTake] + dp[next][true]) % MOD;
      }
    } else {
      int nextValue = compute(next, true);
      if (maxV < nextValue) {
        maxV = nextValue;
        dp[i][canTake] = dp[next][true];
      } else if (maxV == nextValue)
        dp[i][canTake] = (dp[i][canTake] + dp[next][true]) % MOD;
    }
  }
  if (dp[i][canTake] == 0) {
    dp[i][canTake] = 1;
    maxV = canTake ? cValues[i] : 0;
  }
  return maxMoney[i][canTake] = maxV;
}
int main() {
  scanf("%d%d", &n, &m);
  for (int x = 0; x < n; x++)
    low[x] = disc[x] = -1;
  for (int i = 0; i < n; i++) {
    scanf("%d", &money[i]);
  }
  for (int x = 0; x < m; x++) {
    int a, b;
    scanf("%d%d", &a, &b);
    if (a == b)
      continue;
    a--, b--;
    adj[a].pb(b);
  }
  for (int x = 0; x < n; x++)
    if (disc[x] == -1)
      computeSCC(x);
  for (int x = 0; x < n; x++) {
    for (int i : adj[x]) {
      if (id[x] == id[i])
        continue;
      g[id[x]].insert(id[i]);
    }
  }
  for (int x = 0; x < n; x++) {
    cValues[id[x]] += money[x];
  }
  memset(maxMoney, -1, sizeof(maxMoney));
  printf("%d ", compute(id[0], true));
  printf("%d\n", dp[id[0]][1]);
  return 0;
}
