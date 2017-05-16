#include <message.h>
#include <stdio.h>
#include "pancakes.h"

#define MASTER_NODE 0
#define DONE -1

long long min (long long a, long long b) {
  if (a < b)
    return a;
  return b;
}
int main() {
  long long N = GetStackSize();
  long long D = GetNumDiners();
  long long nodes = NumberOfNodes();
  long long id = MyNodeId();

  long long intervalSize = (N + (nodes - 1) - 1) / (nodes - 1);

  if (id != MASTER_NODE) {
    long long currAns = 0;
    long long l = intervalSize * (id - 1);
    long long r = min(N, intervalSize * (id));
    if (l >= N) {
      PutLL(MASTER_NODE, DONE);
      Send(MASTER_NODE);
      return 0;
    }
    for (long long i = l; i < r; i++) {
      if (i == 0)
        continue;
      if (GetStackItem(i) < GetStackItem(i - 1))
        currAns++;
    }
    PutLL(MASTER_NODE, currAns);
    PutLL(MASTER_NODE, DONE);
    Send(MASTER_NODE);
  } else {
    long long ans = 1;
    for (long long i = 1; i < nodes; i++) {
      Receive(i);
      long long msg = GetLL(i);
      while (msg != DONE) {
        ans += msg;
        msg = GetLL(i);
      }
    }
    printf("%lld\n", ans);
  }

  return 0;
}
