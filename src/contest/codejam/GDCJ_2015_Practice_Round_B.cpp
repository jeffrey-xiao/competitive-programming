#include <message.h>
#include <bits/stdc++.h>
#include "sandwich.h"

using namespace std;

#define MASTER_NODE 0
#define INF (1L << 60)

typedef long long LL;

int main () {
  LL N = GetN();
  LL nodes = NumberOfNodes();
  LL id = MyNodeId();

  LL intervalSize = (N + (nodes - 1) - 1) / (nodes - 1);

  if (id != MASTER_NODE) {
    LL total = 0;
    LL ans = 0;
    LL l = intervalSize * (id - 1);
    LL r = min(N, intervalSize * id);

    if (l >= N) {
      PutLL(MASTER_NODE, 0);
      PutLL(MASTER_NODE, 0);
      PutLL(MASTER_NODE, 0);
      PutLL(MASTER_NODE, 0);
      Send(MASTER_NODE);
      return 0;
    }

    LL minPrefix[r - l + 1];
    LL maxPrefix[r - l + 1];
    memset(minPrefix, 0, sizeof minPrefix);
    memset(maxPrefix, 0, sizeof maxPrefix);

    for (LL i = l; i < r; i++)
      minPrefix[i - l + 1] = maxPrefix[i - l + 1] = GetTaste(i) + minPrefix[i - l];
    total = minPrefix[r - l];
    for (LL i = l; i < r; i++) {
      maxPrefix[i - l + 1] = max(maxPrefix[i - l + 1], maxPrefix[i - l]);
      ans = min(ans, minPrefix[i - l + 1] - maxPrefix[i - l + 1]);
      minPrefix[i - l + 1] = min(minPrefix[i - l + 1], minPrefix[i - l]);
    }

    PutLL(MASTER_NODE, ans);
    PutLL(MASTER_NODE, total);
    PutLL(MASTER_NODE, minPrefix[r - l]);
    PutLL(MASTER_NODE, total - maxPrefix[r - l]);
    Send(MASTER_NODE);
  } else {
    LL prefix[nodes + 1];
    LL suffix[nodes + 1];
    LL total[nodes + 1];
    LL totalTaste = 0;
    LL minTaste = 0;

    prefix[0] = prefix[nodes] = 0;
    suffix[0] = suffix[nodes] = 0;
    total[0] = total[nodes] = 0;

    for (int i = 1; i < nodes; i++) {
      Receive(i);
      minTaste = min(minTaste, GetLL(i));
    }
    for (int i = 1; i < nodes; i++) {
      total[i] = GetLL(i);
      totalTaste += total[i];
      prefix[i] = GetLL(i);
      suffix[i] = GetLL(i);
    }
    for (int i = 1; i < nodes; i++) {
      LL sum = 0;
      for (int j = i; j < nodes; j++) {
        sum += total[j];
        minTaste = min(minTaste, sum + suffix[i - 1] + prefix[j + 1]);
      }
      minTaste = min(minTaste, suffix[i - 1] + prefix[i]);
      minTaste = min(minTaste, suffix[i] + prefix[i + 1]);
    }
    printf("%lld\n", max(0LL, totalTaste - minTaste));
  }
  return 0;
}
