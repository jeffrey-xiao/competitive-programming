#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

int main() {
  int T;
  scanf("%d", &T);

  for (int t = 0; t < T; t++) {
    ll K, N;
    scanf("%lld%lld", &K, &N);
    printf("%lld %lld %lld %lld\n", K, N * (N + 1) / 2, N * N, N * (N + 1));
  }

  return 0;
}
