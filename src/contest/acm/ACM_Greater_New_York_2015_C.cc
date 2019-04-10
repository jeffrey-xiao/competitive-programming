#include <bits/stdc++.h>
#define INF 1000000000LL

using namespace std;
typedef long long ll;

ll *matmul(ll a, ll b, ll c, ll d, ll e, ll f, ll g, ll h) {
  ll *res = new ll[4];
  res[0] = a * e + b * g;
  res[1] = a * f + b * h;
  res[2] = c * e + d * g;
  res[3] = c * f + d * h;
  for (int i = 0; i < 4; ++i) {
    res[i] %= INF;
  }
  return res;
}

ll *matsq(ll a, ll b, ll c, ll d) {
  return matmul(a, b, c, d, a, b, c, d);
}

ll *matpow(ll a, ll b, ll c, ll d, ll p) {
  ll *pwr = new ll[4];
  ll *res = new ll[4];
  pwr[0] = a;
  pwr[1] = b;
  pwr[2] = c;
  pwr[3] = d;
  res[0] = 1;
  res[1] = 0;
  res[2] = 0;
  res[3] = 1;

  while (p > 0) {
    if (p % 2 == 1) {
      ll *nres = matmul(pwr[0], pwr[1], pwr[2], pwr[3], res[0], res[1], res[2], res[3]);
      delete[] res;
      res = nres;
    }
    ll *npwr = matsq(pwr[0], pwr[1], pwr[2], pwr[3]);
    delete[] pwr;
    pwr = npwr;
    p /= 2;
  }
  delete[] pwr;
  return res;
}

int main() {
  int P;
  cin >> P;
  for (int i = 0; i < P; ++i) {
    int K;
    ll Y;
    cin >> K >> Y;
    ll *res = matpow(0, 1, 1, 1, Y);
    cout << K << " " << res[1] << endl;
    delete[] res;
  }
}
