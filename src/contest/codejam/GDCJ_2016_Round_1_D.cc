#include "crates.h"
#include <bits/stdc++.h>
#include <message.h>

using namespace std;

#define MASTER_NODE 0
#define INF (1L << 60)
#define MOD (LL)(1e9 + 7)
#define DONE -1

typedef long long LL;

int main() {
  LL N = GetNumStacks();
  LL nodes = NumberOfNodes();
  LL id = MyNodeId();
  LL intervalSize = (N + (nodes - 1) - 1) / (nodes - 1);

  if (id != MASTER_NODE) {
    LL l = intervalSize * (id - 1) + 1;
    LL r = min(N, intervalSize * id) + 1;

    if (l >= N + 1) {
      PutLL(MASTER_NODE, 0);
      Send(MASTER_NODE);

      Receive(MASTER_NODE);
      GetLL(MASTER_NODE);
      GetLL(MASTER_NODE);

      PutLL(MASTER_NODE, 0);
      Send(MASTER_NODE);

      Receive(MASTER_NODE);
      GetLL(MASTER_NODE);

      PutLL(MASTER_NODE, 0);
      Send(MASTER_NODE);
      return 0;
    }

    LL total = 0;
    for (LL i = l; i < r; i++)
      total += GetStackHeight(i);
    PutLL(MASTER_NODE, total);
    Send(MASTER_NODE);

    Receive(MASTER_NODE);
    LL desiredHeight = GetLL(MASTER_NODE);
    LL cutoff = GetLL(MASTER_NODE);
    LL totalDesired = 0;

    for (LL i = l; i < r; i++)
      totalDesired += (desiredHeight + (i <= cutoff));
    PutLL(MASTER_NODE, totalDesired);
    Send(MASTER_NODE);

    Receive(MASTER_NODE);
    LL sum = GetLL(MASTER_NODE);
    LL ans = 0;

    for (LL i = l; i < r; i++) {
      sum += GetStackHeight(i) - (desiredHeight + (i <= cutoff));
      ans = (ans + abs(sum)) % MOD;
    }

    PutLL(MASTER_NODE, ans);
    Send(MASTER_NODE);
  } else {
    LL totals[nodes];
    LL desiredTotals[nodes];
    memset(totals, 0, sizeof(totals));
    memset(desiredTotals, 0, sizeof(desiredTotals));

    for (int i = 1; i < nodes; i++) {
      Receive(i);
      totals[i] = totals[i - 1] + GetLL(i);
    }

    for (int i = 1; i < nodes; i++) {
      PutLL(i, totals[nodes - 1] / N);
      PutLL(i, totals[nodes - 1] % N);
      Send(i);
    }

    for (int i = 1; i < nodes; i++) {
      Receive(i);
      desiredTotals[i] = desiredTotals[i - 1] + GetLL(i);
      PutLL(i, totals[i - 1] - desiredTotals[i - 1]);
      Send(i);
    }

    LL ans = 0;
    for (int i = 1; i < nodes; i++) {
      int sender = Receive(-1);
      ans = (ans + GetLL(sender)) % MOD;
    }

    assert(totals[nodes - 1] == desiredTotals[nodes - 1]);
    printf("%lld\n", ans);
  }
  return 0;
}
