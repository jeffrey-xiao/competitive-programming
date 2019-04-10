#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 500100
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int C, R;
unordered_set<char> symbols;

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  cin >> C >> R;
  cin.ignore();

  string line;
  int ans = 0;
  for (int j = 0; j < R; j++) {
    getline(cin, line);
    for (int i = 0; i < (int)line.size(); i++)
      if (line[i] != ' ') {
        symbols.insert(line[i]);
        ans++;
      }
  }
  ans += (int)symbols.size();
  if (symbols.count('.'))
    ans--;
  printf("%d\n", ans);
  return 0;
}
