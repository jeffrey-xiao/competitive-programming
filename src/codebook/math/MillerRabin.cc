#include <bits/stdc++.h>
using namespace std;

typedef unsigned long long ULL;

ULL mulmod(ULL a, ULL b, ULL c) {
  ULL x = 0, y = a % c;
  for (; b > 0; b >>= 1) {
    if (b & 1)
      x = (x + y) % c;
    y = (y << 1) % c;
  }
  return x % c;
}

ULL powmod(ULL a, ULL b, ULL c) {
  ULL x = 1, y = a;
  for (; b > 0; b >>= 1) {
    if (b & 1)
      x = mulmod(x, y, c);
    y = mulmod(y, y, c);
  }
  return x % c;
}

inline ULL rand64U() {
  return ((ULL)rand() << 48) | ((ULL)rand() << 32) | ((ULL)rand() << 16) | ((ULL)rand());
}

bool isPrime(long long N, int k = 5) {
  if (N < 2 || (N != 2 && !(N & 1)))
    return 0;

  ULL s = N - 1, p = N - 1, x, R;

  while (!(s & 1))
    s >>= 1;

  for (int i = 0; i <= k - 1; i++) {
    R = powmod(rand64U() % p + 1, s, N);
    for (x = s; x != p && R != 1 && R != p; x <<= 1)
      R = mulmod(R, R, N);
    if (R != p && !(x & 1))
      return 0;
  }
  return 1;
}

int t;
int main() {
  srand(time(NULL));

  scanf("%d", &t);

  for (int qq = 0; qq < t; qq++) {
    long long N;
    scanf("%lld", &N);

    if (isPrime(N))
      printf("PRIME\n");
    else
      printf("NOT\n");
  }
}
