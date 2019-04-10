#include <bits/stdc++.h>

using namespace std;

#define SIZE 200000
map<string, int> toIndex;
map<int, string> toName;

int c, n;
int total;
bool v[SIZE];
vector<vector<int>> adj(SIZE);
void dfs(int x) {
  total++;
  v[x] = true;
  for (int i = 0; i < adj[x].size(); i++) {
    if (!v[adj[x][i]])
      dfs(adj[x][i]);
  }
}
int main() {
  scanf("%d", &n);
  for (int x = 0; x < n; x++) {
    string a;
    string b;
    cin >> a >> b;
    if (!toIndex.count(a)) {
      toIndex[a] = c;
      toName[c++] = a;
    }
    if (!toIndex.count(b)) {
      toIndex[b] = c;
      toName[c++] = b;
    }
    int i = toIndex[a];
    int j = toIndex[b];
    adj[i].push_back(j);
  }
  printf("%d\n", c);
  for (int x = 0; x < c; x++) {
    memset(v, false, sizeof(v));
    total = -1;
    dfs(x);
    string s = toName[x];
    cout << s << " " << total << endl;
  }
  return 0;
}
