#include "almost_sorted.h"
#include <bits/stdc++.h>
#include <message.h>

using namespace std;

#define MASTER_NODE 0
#define INF (1L << 60)
#define DONE -1
#define MOD (1L << 20)

typedef long long LL;

int main() {
  LL N = NumberOfFiles();
  LL maxDist = MaxDistance();
  LL nodes = NumberOfNodes();
  LL id = MyNodeId();

  if (id == MASTER_NODE) {
    LL ans = 0;
    for (LL i = 1; i < nodes; i++) {
      LL sender = Receive(-1);
      ans = (ans + GetLL(sender)) % MOD;
    }
    printf("%lld\n", ans);
  } else {
    LL intervalSize = (N + (nodes - 1) - 1) / (nodes - 1);
    LL l = intervalSize * (id - 1);
    LL r = min(N, intervalSize * id);

    if (l >= N) {
      PutLL(MASTER_NODE, 0);
      Send(MASTER_NODE);
      return 0;
    }

    LL sl = max(0LL, l - maxDist);
    LL sr = min(N, r + maxDist);

    LL sorted[sr - sl];

    LL ans = 0;
    for (LL i = sl; i < sr; i++)
      sorted[i - sl] = Identifier(i);
    sort(sorted, sorted + sr - sl);
    for (LL i = l; i < r; i++)
      ans = (ans + sorted[i - sl] % MOD * i) % MOD;
    PutLL(MASTER_NODE, ans);
    Send(MASTER_NODE);
  }
  return 0;
}
