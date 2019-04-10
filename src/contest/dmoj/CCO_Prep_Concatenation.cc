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
typedef unsigned long long ull;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

ull n, mod;
ull a[4] = {0, 1, 1}, b[4];
ull ed, st;

struct matrix {
  ull m[4][4];
};

matrix multi(matrix a, matrix b) {
  matrix ans;
  for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
      ans.m[i][j] = 0;
      for (int k = 0; k < 3; k++) {
        ans.m[i][j] = (ans.m[i][j] + a.m[i][k] * b.m[k][j] % mod) % mod;
      }
    }
  }
  return ans;
}

matrix mpow(matrix a, ull n) {
  matrix ans;
  bool flag = false;
  while (n) {
    if (n & 1) {
      if (!flag) {
        ans = a;
        flag = true;
      } else {
        ans = multi(ans, a);
      }
    }
    a = multi(a, a);
    n /= 2;
  }
  return ans;
}

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  scanf("%llu %llu", &n, &mod);

  for (ed = 10, st = 1;; ed *= 10, st = ed / 10) {
    ull seg = min(n, st * 10 - 1);
    matrix x;
    x.m[0][0] = ed % mod;
    x.m[0][1] = 0;
    x.m[0][2] = 0;
    x.m[1][0] = 1;
    x.m[1][1] = 1;
    x.m[1][2] = 0;
    x.m[2][0] = 0;
    x.m[2][1] = 1;
    x.m[2][2] = 1;
    x = mpow(x, seg - st + 1);

    for (int i = 0; i < 3; i++) {
      b[i] = 0;
      for (int j = 0; j < 3; j++)
        b[i] = (b[i] + a[j] * x.m[j][i] % mod) % mod;
    }

    for (int i = 0; i < 3; i++)
      a[i] = b[i];

    if (n < ed)
      break;
  }
  printf("%llu\n", a[0]);
  return 0;
}
