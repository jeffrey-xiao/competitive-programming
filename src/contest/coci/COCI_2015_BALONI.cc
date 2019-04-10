#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define MAX_N 1000000
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int N, H[MAX_N];
multiset<int> h;

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  scanf("%d", &N);
  for (int i = 0; i < N; i++)
    scanf("%d", H + i);
  for (int i = 0; i < N; i++) {
    if (h.count(H[i]))
      h.erase(h.find(H[i]));
    h.insert(H[i] - 1);
  }
  printf("%d\n", h.size());
  return 0;
}
