#include <bits/stdc++.h>
#define M_PI 3.141592653589
using namespace std;

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

vector<int> multiply(vector<int> a, vector<int> b) {
  int N = (int)(a.size() + b.size());
  while (N & (N - 1))
    N++;

  a.resize(N);
  b.resize(N);
  roots = getRoots(N);
  vector<C> x(N), y(N);
  FFT(a.data(), x.data(), N);
  FFT(b.data(), y.data(), N);

  for (int i = 0; i < N; i++) {
    x[i] *= y[i];
    roots[i] = conj(roots[i]);
  }
  FFT(x.data(), y.data(), N);
  vector<int> ret(N);
  for (int i = 0; i < N; i++) {
    ret[i] = (int)((real(y[i]) + 0.5) / N);
  }

  for (int i = 0; i < (int)ret.size(); i++) {
    if (ret[i] >= 10) {
      if (i == (int)ret.size() - 1)
        ret.push_back(ret[i] / 10);
      else
        ret[i + 1] += ret[i] / 10;
      ret[i] %= 10;
    }
  }
  while (ret.size() > 1 && ret.back() == 0)
    ret.pop_back();
  return ret;
}

int main() {
  vector<int> a, b;
  for (char c = getchar_unlocked(); c != '\n'; c = getchar_unlocked())
    a.push_back(c - '0');
  for (char c = getchar_unlocked(); c != '\n'; c = getchar_unlocked())
    b.push_back(c - '0');
  reverse(a.begin(), a.end());
  reverse(b.begin(), b.end());
  vector<int> c = multiply(a, b);
  for (int i = (int)c.size() - 1; i >= 0; i--)
    printf("%d", c[i]);
  printf("\n");
  return 0;
}
