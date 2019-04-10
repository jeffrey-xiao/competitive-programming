#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 200000
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  string s;
  cin >> s;
  s = "." + s + ".";
  size_t index;
  int startPos = 0;
  while ((index = s.find(".Frankenstein.", startPos)) != string::npos) {
    s.replace(index, 14, ".Frankenstein's.monster.");
    startPos = index + 23;
  }

  cout << s.substr(1, s.size() - 2) << endl;
  return 0;
}
