#include <bits/stdc++.h>
#include <limits>

#define mp make_pair
#define pb push_back
#define INF 1e20
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 500100
#define EPS 1e-9
#define NaN numeric_limits<ld>::quiet_NaN();
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;
typedef long double ld;

int N;

vector<pair<ld, ld>> polynomial;

ld evaluate(vector<pair<ld, ld>> polynomial, ld x) {
  ld ret = 0;
  for (auto monomial : polynomial)
    ret += monomial.first * powl(x, monomial.second);
  return ret;
}

ld findRoot(vector<pair<ld, ld>> polynomial, ld x1, ld x2) {
  ld y1 = evaluate(polynomial, x1), y2 = evaluate(polynomial, x2);
  bool neg1 = y1 < 0, neg2 = y2 < 0;

  if (abs(y1) <= 0)
    return x1;

  if (abs(y2) <= 0 || neg1 == neg2)
    return NaN;

  while (x2 - x1 > EPS) {
    ld x = (x2 + x1) / 2;
    if ((evaluate(polynomial, x) < 0) == neg1)
      x1 = x;
    else
      x2 = x;
  }
  return x1;
}

vector<ld> findAllRoots(vector<pair<ld, ld>> polynomial) {
  vector<pair<ld, ld>> diff;
  vector<ld> ret;

  for (auto monomial : polynomial)
    if (monomial.second > 0)
      diff.pb({monomial.first * monomial.second, monomial.second - 1});

  if (diff.empty())
    return ret;

  vector<ld> diffRoots = findAllRoots(diff);
  diffRoots.insert(diffRoots.begin(), -INF);
  diffRoots.pb(INF);

  for (int i = 0; i < (int)diffRoots.size() - 1; i++) {
    ld root = findRoot(polynomial, diffRoots[i], diffRoots[i + 1]);
    if (root != root)
      continue;
    if (ret.empty() || root != ret.back())
      ret.pb(root);
  }

  return ret;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  cin >> N;

  for (int i = 0; i < N; i++) {
    ld coefficient, power;
    cin >> coefficient >> power;
    polynomial.pb({coefficient, power});
  }

  vector<ld> res = findAllRoots(polynomial);

  if (res.size() == 0)
    cout << "NO REAL ROOTS\n";
  else {
    cout.precision(9);
    for (ld d : res)
      cout << fixed << d << "\n";
  }

  return 0;
}
