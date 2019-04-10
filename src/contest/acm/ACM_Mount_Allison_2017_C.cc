#include <bits/stdc++.h>
#define M_PI 3.141592653589
#define EPS 1e-9
using namespace std;

int N;
int K[40000];

typedef complex<double> C;
typedef long long LL;
vector<C> roots;

vector<C> getRoots(int N) {
  vector<C> ret;
  for (int i = 0; i < N; i++)
    ret.push_back(polar(1.0, 2 * i * M_PI / N));
  return ret;
}

template <class T> void FFT(T *in, C *out, int sz, int step = 1) {
  if (sz == 1) {
    // coefficient becomes (1, 0) when the sz of the polynomial is 1
    *out = *in;
  } else {
    // storing the results of the even degrees in the first half of the assigned out
    FFT(in, out, sz >> 1, step << 1);
    // storing the results of the odd degrees in the second half of the assigned out
    FFT(in + step, out + (sz >> 1), sz >> 1, step << 1);
    for (int i = 0, j = 0; i < (sz >> 1); i++, j += step) {
      auto temp = out[i + (sz >> 1)] * roots[j];
      out[i + (sz >> 1)] = out[i] - temp;
      out[i] = out[i] + temp;
    }
  }
}

vector<double> multiplyPolynomial(vector<double> a, vector<double> b) {
  int N = (int)(a.size() + b.size() - 1);
  while (N & (N - 1))
    N++;

  a.resize(N);
  b.resize(N);
  vector<double> c(N);
  roots = getRoots(N);
  vector<C> x(N), y(N);
  FFT(a.data(), x.data(), N);
  FFT(b.data(), y.data(), N);

  for (int i = 0; i < N; i++) {
    x[i] *= y[i];
    roots[i] = conj(roots[i]);
  }
  FFT(x.data(), y.data(), N);
  vector<double> ret(N);
  for (int i = 0; i < N; i++) {
    ret[i] = (int)((real(y[i]) + 0.5) / N);
    if (ret[i] != 0)
      ret[i] = 1;
  }
  while (ret.size() > 1 && ret.back() == 0)
    ret.pop_back();
  return ret;
}

int numdistinct() {
  vector<double> a(60001), b(60001);
  for (int i = 0; i < N; i++)
    a[K[i]]++, b[K[i]]++;
  a = multiplyPolynomial(a, b);
  b = a;

  a = multiplyPolynomial(a, b);
  int ans = 0;
  for (int i = 0; i < (int)a.size(); i++)
    if (a[i] > EPS)
      ans++;
  return ans;
}

double expected() {
  double result = 0.0;
  for (int i = 0; i < N; ++i) {
    result += K[i];
  }
  return result * 4 / N;
}

int main() {
  cin >> N;
  for (int i = 0; i < N; ++i) {
    cin >> K[i];
  }
  sort(K, K + N);

  int a = K[N - 1] * 4;
  int b = K[0] * 4;
  int c = numdistinct();
  double d = expected();

  cout << a << ' ' << b << ' ' << c << ' ' << d << endl;
  return 0;
}
