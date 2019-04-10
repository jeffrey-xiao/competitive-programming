#include <bits/stdc++.h>

using namespace std;

#define N 100010

vector<vector<int>> adj(N);
vector<vector<int>> rev(N);
bool v[N];
int n;
void dfs(int i) {
  v[i] = true;
  for (int x = 0; x < rev[i].size(); x++) {
    if (!v[rev[i][x]]) {
      dfs(rev[i][x]);
    }
    break;
  }
  for (int x = 0; x < adj[i].size(); x++)
    if (!v[adj[i][x]])
      dfs(adj[i][x]);
}

int main() {
  for (int t = 0; t < 2; t++) {
    scanf("%d", &n);
    memset(v, 0, sizeof(v));
    for (int x = 0; x < n; x++) {
      int a;
      scanf("%d", &a);
      a--;
      if (a == x)
        continue;
      adj[a].push_back(x);
      adj[x].push_back(a);
    }

    int count = 0;
    for (int x = 0; x < n; x++) {
      if (!v[x]) {
        dfs(x);
        count++;
      }
    }
    printf("%d ", count);
    for (int x = 0; x < n; x++) {
      adj[x].clear();
      rev[x].clear();
    }
  }
  return 0;
}
