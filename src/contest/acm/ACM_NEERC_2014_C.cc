#include <bits/stdc++.h>

#define SIZE 300500

using namespace std;
int N, chain;
int chainId[SIZE], depth[SIZE], sz[SIZE], chainHead[SIZE], parent[SIZE];
vector<vector<int>> adj(SIZE);
vector<unordered_map<string, vector<pair<int, string>>>> toDepth(SIZE);
vector<vector<pair<string, string>>> properties(SIZE);

bool cmp(const pair<int, string> &l, const pair<int, string> &r) {
  return l.first < r.first;
}

pair<string, string> get(string s) {
  string ans1 = "", ans2 = "";
  int p = 0;
  while (p < (int)s.length() && s[p] != '=')
    ans1 += s[p++];
  p++;
  while (p < (int)s.length())
    ans2 += s[p++];
  return make_pair(ans1, ans2);
}

void hld(int u, int par) {
  chainId[u] = chain;
  for (int i = 0; i < (int)properties[u].size(); i++) {
    string key = properties[u][i].first;
    string value = properties[u][i].second;
    toDepth[chainId[u]][key].push_back({-depth[u], value});
  }

  if (chainHead[chain] == -1)
    chainHead[chain] = u;

  int maxIndex = -1;
  for (int v : adj[u])
    if (v != par && (maxIndex == -1 || sz[maxIndex] < sz[v]))
      maxIndex = v;

  if (maxIndex != -1)
    hld(maxIndex, u);

  for (int v : adj[u])
    if (v != par && v != maxIndex) {
      chain++;
      hld(v, u);
    }
}

static void dfs(int u, int par, int d) {
  sz[u] = 1;
  depth[u] = d;
  parent[u] = par;
  for (int v : adj[u])
    if (v != par) {
      dfs(v, u, d + 1);
      sz[u] += sz[v];
    }
}

int main() {
  ios::sync_with_stdio(false);
  cin >> N;
  for (int i = 0; i < SIZE; i++)
    chainHead[i] = -1;
  for (int i = 0; i < N; i++) {
    int par, num;
    cin >> par >> num;
    par--;
    if (par != -1) {
      adj[par].push_back(i);
      adj[i].push_back(par);
    }
    for (int j = 0; j < num; j++) {
      string in;
      cin >> in;
      auto res = get(in);
      properties[i].push_back({res.first, res.second});
    }
  }

  dfs(0, -1, 0);
  hld(0, -1);

  for (int i = 0; i < SIZE; i++)
    for (auto &val : toDepth[i])
      sort(val.second.begin(), val.second.end());

  int Q;
  cin >> Q;

  for (int i = 0; i < Q; i++) {
    int component;
    string key;
    cin >> component >> key;
    component--;
    bool found = false;
    while (component != -1) {
      vector<pair<int, string>> ts = toDepth[chainId[component]][key];
      auto iter = lower_bound(ts.begin(), ts.end(), make_pair(-depth[component], ""), cmp);
      if (!found && iter != ts.end()) {
        found = true;
        cout << (*iter).second << endl;
        break;
      }
      component = parent[chainHead[chainId[component]]];
    }
    if (!found) {
      cout << "N/A" << endl;
    }
  }
  return 0;
}
