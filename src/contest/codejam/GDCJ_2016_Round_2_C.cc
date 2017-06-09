#include <message.h>
#include <bits/stdc++.h>
#include "lisp_plus_plus.h"

using namespace std;

#define MASTER_NODE 0
#define INF (1L << 60)
#define DONE -1

typedef long long LL;

int main () {
  LL N = GetLength();
  LL nodes = NumberOfNodes();
  LL id = MyNodeId();

  if (id == MASTER_NODE) {
    LL prevTotal = 0;
    for (LL i = 1; i < nodes; i++) {
      PutLL(i, prevTotal);
      Send(i);
      Receive(i);
      prevTotal += GetLL(i);
    }

    LL ans = -1;
    bool isGood = 1;
    for (LL i = 1; i < nodes; i++) {
      Receive(i);
      LL currMax = GetLL(i);
      bool isCurrGood = GetLL(i);

      if (isGood)
        ans = max(currMax, ans);
      isGood &= isCurrGood;
    }

    ans++;
    if (ans == N && prevTotal == 0)
      printf("-1\n");
    else
      printf("%lld\n", ans);
  } else {
    LL intervalSize = (N + (nodes - 1) - 1) / (nodes - 1);
    LL l = intervalSize * (id - 1);
    LL r = min(N, intervalSize * id);

    LL total = 0;

    if (l < N) {
      for (LL i = l; i < r; i++) {
        if (GetCharacter(i) == '(')
          total++;
        else
          total--;
      }
    }

    PutLL(MASTER_NODE, total);
    Send(MASTER_NODE);

    Receive(MASTER_NODE);
    total = GetLL(MASTER_NODE);

    bool isGood = 1;
    LL maxVal = -1;
    if (l < N) {
      if (total < 0)
        isGood = 0;
      for (LL i = l; i < r; i++) {
        if (GetCharacter(i) == '(')
          total++;
        else
          total--;
        if (total >= 0 && isGood)
          maxVal = i;
        if (total < 0)
          isGood = 0;
      }
    }
    PutLL(MASTER_NODE, maxVal);
    PutLL(MASTER_NODE, isGood);
    Send(MASTER_NODE);
  }

  return 0;
}
