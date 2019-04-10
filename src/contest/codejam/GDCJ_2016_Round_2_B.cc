#include "again.h"
#include <bits/stdc++.h>
#include <message.h>

using namespace std;

#define MASTER_NODE 0
#define INF (1L << 60)
#define DONE -1
#define MOD (LL)(1e9 + 7)

typedef long long LL;

int main() {
  LL N = GetN();
  LL nodes = NumberOfNodes();
  LL id = MyNodeId();

  if (id == MASTER_NODE) {
    LL sumA = 0, sumB = 0, currSumB = 0;
    for (LL i = 1; i < nodes; i++) {
      LL sender = Receive(-1);
      sumA = (sumA + GetLL(sender)) % MOD;
      sumB = (sumB + GetLL(sender)) % MOD;
    }

    for (LL i = 1; i < nodes; i++) {
      PutLL(i, sumA);
      Send(i);
    }

    for (LL i = 0; i < N; i += nodes)
      currSumB = (currSumB + GetB(i)) % MOD;

    LL total = sumA * (sumB + currSumB) % MOD;

    for (LL i = 0; i < N; i += nodes)
      total = (total - currSumB * GetA(i) % MOD) % MOD;
    for (LL i = 1; i < nodes; i++) {
      LL sender = Receive(-1);
      total = (total - GetLL(sender)) % MOD;
    }

    printf("%lld\n", (total + MOD) % MOD);
  } else {
    LL intervalSize = (N + (nodes - 1) - 1) / (nodes - 1);
    LL l = intervalSize * (id - 1);
    LL r = min(N, intervalSize * id);

    LL sumA = 0, sumB = 0, totalSub = 0;
    for (LL i = l; i < r; i++)
      sumA = (sumA + GetA(i)) % MOD;
    for (LL i = id; i < N; i += nodes) {
      LL currB = GetB(i);
      sumB = (sumB + currB) % MOD;
    }

    PutLL(MASTER_NODE, sumA);
    PutLL(MASTER_NODE, sumB);
    Send(MASTER_NODE);

    Receive(MASTER_NODE);
    sumA = GetLL(MASTER_NODE);

    for (LL i = nodes - id; i < N; i += nodes)
      totalSub = (totalSub + sumB * GetA(i) % MOD) % MOD;
    PutLL(MASTER_NODE, totalSub);
    Send(MASTER_NODE);
  }
  return 0;
}
