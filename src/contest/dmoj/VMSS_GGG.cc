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

int n, m;
unordered_map<int, int> hm;
vector<int> vals;
int dp[1000001];
int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  rint(n);
  for (int i = 0; i < n; i++) {
    int val;
    rint(val);
    hm[val] = i;
  }

  rint(m);
  memset(dp, INF, sizeof dp);
  dp[0] = 0;

  for (int i = 0; i < m; i++) {
    int val;
    rint(val);
    if (hm.count(val))
      vals.pb(hm[val]);
  }

  int len = 0;
  for (int i = 0; i < vals.size(); i++) {
    int lo = 1;
    int hi = len;
    while (lo <= hi) {
      int mid = (lo + hi) >> 1;
      if (vals[dp[mid]] >= vals[i])
        hi = mid - 1;
      else
        lo = mid + 1;
    }
    dp[lo] = i;
    if (lo > len)
      len = lo;
  }
  printf("%d\n", len);
  return 0;
}
