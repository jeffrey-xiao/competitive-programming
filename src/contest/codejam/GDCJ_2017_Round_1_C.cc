#include "weird_editor.h"
#include <message.h>
#include <stdio.h>
#include <vector>

#define MASTER_NODE 0
#define DONE -1
#define SQRT 31622
#define MOD (long long)(1e9 + 7)

long long min(long long a, long long b) {
  if (a < b)
    return a;
  return b;
}
long long modpow(long a, long b) {
  if (b == 0)
    return 1;
  if (b == 1)
    return a;
  if (b % 2 == 0)
    return modpow(a * a % MOD, b / 2);
  return a * modpow(a * a % MOD, b / 2) % MOD;
}
int main() {
  long long N = GetNumberLength();
  long long nodes = NumberOfNodes();
  long long id = MyNodeId();

  long long intervalSize = (N + (nodes - 1) - 1) / (nodes - 1);

  if (id != MASTER_NODE) {
    long long l = intervalSize * (id - 1);
    long long r = min(N, intervalSize * (id));
    if (l >= N) {
      for (int i = 1; i <= 9; i++)
        PutLL(MASTER_NODE, 0);
      Send(MASTER_NODE);
      return 0;
    }
    std::vector<long long> curr(10);
    for (int i = 0; i < 10; i++)
      curr[i] = 0;
    for (long long i = l; i < r; i++) {
      if (GetDigit(i) == 0)
        continue;
      long long currDigit = GetDigit(i);
      for (int j = 0; j < currDigit; j++)
        curr[j] = 0;
      curr[currDigit]++;
    }
    for (int i = 9; i >= 1; i--) {
      PutLL(MASTER_NODE, curr[i]);
    }
    Send(MASTER_NODE);
  } else {
    std::vector<long long> curr(10);
    for (long long i = 1; i < nodes; i++) {
      Receive(i);
      for (int j = 9; j >= 1; j--) {
        long long currMsg = GetLL(i);
        curr[j] += currMsg;
        if (currMsg) {
          for (int k = j - 1; k >= 1; k--)
            curr[k] = 0;
        }
      }
    }
    long long totalSize = 0;
    long long ans = 0;
    std::vector<long long> memo(10);
    for (int i = 1; i <= 9; i++) {
      for (int j = 0; j < SQRT; j++) {
        memo[i] = (memo[i] * 10 + i) % MOD;
      }
    }
    long long tenmemo = modpow(10, SQRT);
    for (int i = 9; i >= 1; i--) {
      totalSize += curr[i];
      while (curr[i] >= SQRT) {
        ans = (ans * tenmemo + memo[i]) % MOD;
        curr[i] -= SQRT;
      }
      while (curr[i] >= 1) {
        ans = (ans * 10 + i) % MOD;
        curr[i]--;
      }
    }
    ans = (ans * modpow(10, N - totalSize)) % MOD;
    printf("%lld\n", ans);
  }

  return 0;
}
