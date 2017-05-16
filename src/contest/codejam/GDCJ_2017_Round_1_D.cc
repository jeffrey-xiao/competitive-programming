#include <message.h>
#include <stdio.h>
#include "todd_and_steven-2.h"

#define MASTER_NODE 0
#define DONE -1
#define MOD (long long)(1e9 + 7)

long long min (long long a, long long b) {
  if (a < b)
    return a;
  return b;
}

int main() {
  long long N = GetToddLength();
  long long M = GetStevenLength();
  long long nodes = NumberOfNodes();
  long long id = MyNodeId();

  long long toddSize = (N + (nodes - 1) - 1) / (nodes - 1);
  long long stevenSize = (M + (nodes - 1) - 1) / (nodes - 1);

  if (id != MASTER_NODE) {
    long long currAns = 0;
    long long toddL = toddSize * (id - 1);
    long long toddR = min(N, toddSize * (id));
    long long stevenL = stevenSize * (id - 1);
    long long stevenR = min(M, stevenSize * (id));
    if (toddL < N) {
      for (long long i = toddL; i < toddR; i++) {
        long long currValue = GetToddValue(i);
        long long lo = 0;
        long long hi = M - 1;
        while (lo <= hi) {
          long long mid = (lo + hi) / 2;
          if (GetStevenValue(mid) <= currValue)
            lo = mid + 1;
          else
            hi = mid - 1;
        }
        currAns += (currValue ^ (i + hi + 1)) % MOD;
        currAns %= MOD;
      }
    }

    if (stevenL < M) {
      for (long long i = stevenL; i < stevenR; i++) {
        long long currValue = GetStevenValue(i);
        long long lo = 0;
        long long hi = N - 1;
        while (lo <= hi) {
          long long mid = (lo + hi) / 2;
          if (GetToddValue(mid) <= currValue)
            lo = mid + 1;
          else
            hi = mid - 1;
        }
        currAns += (currValue ^ (i + hi + 1L)) % MOD;
        currAns %= MOD;
      }
    }

    PutLL(MASTER_NODE, currAns);
    Send(MASTER_NODE);
  } else {
    long long ans = 0;
    for (long long i = 1; i < nodes; i++) {
      long long src = Receive(i);
      ans = (ans + GetLL(src)) % MOD;
    }
    printf("%lld\n", ans);
  }

  return 0;
}
