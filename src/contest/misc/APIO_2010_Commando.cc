#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

struct ConvexHullTrick {
  vector<ll> M, B;
  int ptr = 0;

  void addLine(ll m, ll b) {
    int len = M.size();

    while (len > 1 && (B[len - 2] - B[len - 1]) * (m - M[len - 1]) >=
                          (B[len - 1] - b) * (M[len - 1] - M[len - 2]))
      len--;
    M.resize(len);
    B.resize(len);
    M.push_back(m);
    B.push_back(b);
  }

  ll getMax(ll x) {
    if (ptr >= (int)M.size())
      ptr = (int)M.size() - 1;
    while (ptr < (int)M.size() - 1 && M[ptr + 1] * x + B[ptr + 1] >= M[ptr] * x + B[ptr])
      ptr++;
    return M[ptr] * x + B[ptr];
  }
};

int main() {
  int T;
  scanf("%d", &T);
  for (int t = 0; t < T; t++) {
    int N;
    scanf("%d", &N);

    ll A[N + 1];
    ll dp[N + 1];
    memset(A, 0, sizeof A);
    memset(dp, 0, sizeof dp);

    int a, b, c;
    scanf("%d%d%d", &a, &b, &c);

    for (int i = 1; i <= N; i++) {
      scanf("%lld", &A[i]);
      A[i] += A[i - 1];
    }

    ConvexHullTrick cht;

    dp[1] = a * A[1] * A[1] + b * A[1] + c;
    cht.addLine(-2 * a * A[1], a * A[1] * A[1] - b * A[1] + dp[1]);

    for (int i = 2; i <= N; i++) {
      dp[i] =
          max(a * A[i] * A[i] + b * A[i] + c, A[i] * A[i] * a + A[i] * b + c + cht.getMax(A[i]));
      cht.addLine(-2 * a * A[i], a * A[i] * A[i] - b * A[i] + dp[i]);
    }

    printf("%lld\n", dp[N]);
  }
  return 0;
}
