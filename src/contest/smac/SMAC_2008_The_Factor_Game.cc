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

int n;

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  rint(n);
  while (n != 1) {
    vector<int> primes;
    int newN = n;
    int cnt = 2;
    while (newN != 1) {
      if (cnt * cnt > newN) {
        primes.pb(newN);
        newN = 1;
      } else if (newN % cnt == 0) {
        newN /= cnt;
        primes.pb(cnt);
      } else
        cnt++;
    }
    int num = 1;
    for (int x = 0; x < primes.size() / 2; x++)
      num *= primes[x];
    printf("%d %d\n", num, n / num);
    fflush(NULL);
    rint(n);
  }

  return 0;
}
