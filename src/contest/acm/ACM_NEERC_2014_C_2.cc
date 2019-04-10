#include <iostream>
#include <map>
#include <unordered_map>
#include <vector>
using namespace std;

const int MAXN = 14 * 1e5 + 5;
int cnt = 0;
int n;
int p[MAXN], k[MAXN], st[MAXN], child[MAXN];
unordered_map<string, string> values[MAXN];
vector<pair<string, string>> all[MAXN];
vector<int> adj[MAXN];

void update(int node, int b, int e, int p, int q, string &key, string &val) {
  if (p <= b && q >= e) {
    if (values[node].find(key) == values[node].end())
      values[node][key] = val;
    return;
  }
  if (p > e || q < b)
    return;
  int mid = (b + e) / 2;
  update(2 * node, b, mid, p, q, key, val);
  update(2 * node + 1, mid + 1, e, p, q, key, val);
}
string query(int node, int b, int e, int p, string key) {
  if (b == e) {
    if (values[node].find(key) != values[node].end())
      return values[node][key];
    return "N/A";
  }
  int mid = (b + e) / 2;
  if (p <= mid) {
    string ans1 = query(2 * node, b, mid, p, key);
    if (ans1 != "N/A")
      return ans1;
  } else if (p > mid) {
    string ans2 = query(2 * node + 1, mid + 1, e, p, key);
    if (ans2 != "N/A")
      return ans2;
  }

  if (values[node].find(key) != values[node].end())
    return values[node][key];
  return "N/A";
}

void dfs(int v) {
  st[v] = ++cnt;
  child[v] = 1;
  for (int i = 0; i < (int)adj[v].size(); i++) {
    int u = adj[v][i];
    dfs(u);
    child[v] += child[u];
  }
  for (int i = 0; i < (int)all[v].size(); i++)
    update(1, 1, n, st[v], st[v] + child[v] - 1, all[v][i].first, all[v][i].second);
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
int main() {
  ios::sync_with_stdio(false);
  cin >> n;
  for (int i = 1; i <= n; i++) {
    cin >> p[i];
    adj[p[i]].push_back(i);
    cin >> k[i];
    for (int j = 0; j < k[i]; j++) {
      string s;
      cin >> s;
      all[i].push_back(make_pair(get(s).first, get(s).second));
    }
  }
  dfs(1);

  int q;
  cin >> q;
  for (int i = 1; i <= q; i++) {
    int c;
    cin >> c;
    string s;
    cin >> s;
    cout << query(1, 1, n, st[c], s) << endl;
  }

  return 0;
}
