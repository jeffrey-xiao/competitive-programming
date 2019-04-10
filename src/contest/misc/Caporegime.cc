#include <bits/stdc++.h>

using namespace std;

int n, m;
bool adj[15][15];
int dp[1 << 15];
pair<int, int> nextV[1 << 15];
vector<int> groups;
int solve(int s) {
  int &ref = dp[s];
  if (ref != -1)
    return ref;
  ref = 1 << 30;
  if (s == 0) {
    nextV[0] = make_pair(-1, 0);
    return ref = 0;
  }
  int nstate = 0;
  int g = 0;
  for (int next : groups) {
    if (s & next == next) {
      int v = 1 + solve(s ^ next);
      if (v < ref) {
        ref = v;
        nstate = s ^ next;
        g = next;
      }
    }
  }
  nextV[s] = make_pair(nstate, g);
  return ref;
}

int main() {
  scanf("%d%d", &n, &m);
  for (int i = 0; i < m; i++) {
    int a, b;
    scanf("%d%d", &a, &b);
    a--, b--;
    adj[a][b] = 1;
    adj[b][a] = 1;
  }
  memset(dp, -1, sizeof dp);
  for (int i = 1; i < (1 << n); i++) {
    bool valid = true;
    for (int j = 0; j < n; j++) {
      for (int k = j + 1; k < n; k++) {
        if (adj[j][k] && (i & 1 << j) && (i & 1 << k))
          valid = false;
      }
    }
    if (valid)
      groups.push_back(i);
  }

  printf("%d\n", solve((1 << n) - 1));
  int curr = (1 << n) - 1;
  while (curr != -1) {
    for (int i = 0; i < n; i++) {
      if (nextV[curr].second & 1 << i)
        printf("%d ", i + 1);
    }
    printf("\n");
    curr = nextV[curr].first;
  }
}
