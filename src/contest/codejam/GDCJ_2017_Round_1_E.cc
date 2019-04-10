#include "query_of_death.h"
#include <bits/stdc++.h>
#include <message.h>

using namespace std;

#define MASTER_NODE 0
#define DONE -1

typedef long long LL;

int main() {
  LL N = GetLength();
  LL nodes = NumberOfNodes();
  LL id = MyNodeId();

  if (id == 0) {
    vector<int> availableNodes, nextAvailableNodes;
    for (int i = 1; i < nodes; i++)
      availableNodes.push_back(i);
    LL l = 0, r = N;
    LL total = 0;
    LL brokenNode = 0;
    do {
      // dividing interval to availableNodes
      int currSz = availableNodes.size();
      int intervalSize = r - l;
      int workerSize = (intervalSize + currSz - 1) / currSz;
      int nextL = l, nextR = r;

      for (int i = 0; i < (int)availableNodes.size(); i++) {
        LL workerL = workerSize * i + l;
        LL workerR = min(r, (LL)workerSize * (i + 1) + l);
        if (workerL >= r)
          continue;
        PutLL(availableNodes[i], workerL);
        PutLL(availableNodes[i], workerR);
        Send(availableNodes[i]);
      }

      // checking for broken worker
      for (int i = 0; i < (int)availableNodes.size(); i++) {
        LL workerL = workerSize * i + l;
        LL workerR = min(r, (LL)workerSize * (i + 1) + l);
        if (workerL >= r)
          continue;
        Receive(availableNodes[i]);
        LL currTotal = GetLL(availableNodes[i]);
        LL isWorking = GetLL(availableNodes[i]);

        if (isWorking) {
          total += currTotal;
          nextAvailableNodes.push_back(availableNodes[i]);
        } else {
          brokenNode = currTotal;
          nextL = workerL;
          nextR = workerR;
        }
      }

      availableNodes = nextAvailableNodes;
      nextAvailableNodes.clear();
      l = nextL;
      r = nextR;
    } while (r - l > 1);

    for (int i = 1; i < nodes; i++) {
      PutLL(i, DONE);
      PutLL(i, DONE);
      Send(i);
    }

    printf("%lld\n", total + brokenNode);

  } else if (id != MASTER_NODE) {
    while (true) {
      Receive(MASTER_NODE);
      LL l = GetLL(MASTER_NODE), r = GetLL(MASTER_NODE);
      if (l == DONE || r == DONE)
        return 0;
      bool isWorking = 1;
      LL ans = 0;
      for (LL i = l; i < r; i++) {
        LL initialValue = GetValue(i);
        ans += initialValue;
        for (int j = 0; j < 20; j++)
          if (initialValue != GetValue(i))
            isWorking = 0;
      }
      PutLL(MASTER_NODE, ans);
      PutLL(MASTER_NODE, isWorking);
      Send(MASTER_NODE);
    }
  }
  return 0;
}
