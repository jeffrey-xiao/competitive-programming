#include "oops.h"
#include <bits/stdc++.h>
#include <message.h>

using namespace std;

#define MASTER_NODE 0
#define INF (1L << 60)
#define DONE -1

typedef long long LL;

int main() {
  LL N = GetN();
  LL nodes = NumberOfNodes();
  LL id = MyNodeId();

  LL intervalSize = (N + (nodes - 1) - 1) / (nodes - 1);

  if (id != MASTER_NODE) {
    LL minValue = INF;
    LL maxValue = -INF;
    LL l = intervalSize * (id - 1);
    LL r = min(N, intervalSize * id);

    if (l >= N) {
      PutLL(MASTER_NODE, minValue);
      PutLL(MASTER_NODE, maxValue);
      Send(MASTER_NODE);
      return 0;
    }

    for (LL i = l; i < r; i++) {
      LL currValue = GetNumber(i);
      minValue = min(minValue, currValue);
      maxValue = max(maxValue, currValue);
    }
    PutLL(MASTER_NODE, minValue);
    PutLL(MASTER_NODE, maxValue);
    Send(MASTER_NODE);
  } else {
    LL minValue = INF;
    LL maxValue = -INF;
    for (int i = 1; i < nodes; i++) {
      LL sender = Receive(-1);
      minValue = min(minValue, GetLL(sender));
      maxValue = max(maxValue, GetLL(sender));
    }
    printf("%lld\n", maxValue - minValue);
  }
  return 0;
}
