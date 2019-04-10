#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define POW 31
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define MAX_SIZE 1000001
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

string s1, s2;
ll h1[MAX_SIZE], h2[MAX_SIZE], p[MAX_SIZE];
int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  cin >> s1 >> s2;
  s1 = " " + s1;
  s2 = " " + s2;
  for (int i = 1; i < (int)s1.size(); i++)
    h1[i] = (h1[i - 1] * POW + s1[i]) % MOD;
  for (int i = 1; i < (int)s2.size(); i++)
    h2[i] = (h2[i - 1] * POW + s2[i]) % MOD;
  p[0] = 1;
  for (int i = 1; i < MAX_SIZE; i++)
    p[i] = (p[i - 1] * POW) % MOD;

  int end = min(s1.size(), s2.size());
  int len = (int)s1.size();
  for (int i = end; i >= 0; i--) {
    if (h2[i] == ((h1[len - 1] - h1[len - 1 - i] * p[i]) % MOD + MOD) % MOD) {
      cout << s1.substr(1, s1.size() - i - 1) << s2.substr(1) << endl;
      return 0;
    }
  }

  return 0;
}
