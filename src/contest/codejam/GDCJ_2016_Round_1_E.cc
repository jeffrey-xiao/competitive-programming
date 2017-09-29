#include <message.h>
#include <bits/stdc++.h>
#include "winning_move.h"

using namespace std;

#define MASTER_NODE 0
#define INF (1L << 60)
#define DONE -1

typedef long long LL;
typedef unsigned long long ULL;

LL getHash (LL num, LL hashingNodes) {
  ULL n = (ULL)num;
  n *= 11400714819323198549ULL;
  n >>= 54;
  return (LL)(n % (hashingNodes + 1)) + hashingNodes + 1;
}

int main () {
  LL N = GetNumPlayers();
  LL nodes = NumberOfNodes();
  LL id = MyNodeId();

  LL hashingNodes = (nodes - 1) / 2;

  if (id == MASTER_NODE) {
    LL ans = INF;
    for (LL i = 0; i < hashingNodes + 1; i++) {
      LL sender = Receive(-1);
      ans = min(ans, GetLL(sender));
    }
    printf("%lld\n", ans == INF ? 0 : ans);
  } else if (1 <= id && id <= hashingNodes) {
    LL intervalSize = (N + (hashingNodes - 1)) / hashingNodes;
    LL l = intervalSize * (id - 1);
    LL r = min(N, intervalSize * id);

    if (l < N) {
      LL values[r - l];
      bool isUnique[r - l];
      for (LL i = l; i < r; i++) {
        values[i - l] = GetSubmission(i);
        isUnique[i - l] = 0;
      }
      sort(values, values + r - l);
      LL prevValue = -1;
      for (LL i = 0; i < r - l; i++) {
        if ((i == r - l - 1 || values[i] != values[i + 1]) && values[i] != prevValue)
          isUnique[i] = 1;
        prevValue = values[i];
      }
      prevValue = -1;
      for (LL i = 0; i < r - l; i++) {
        LL hashValue = getHash(values[i], hashingNodes);
        if (isUnique[i]) {
          PutLL(hashValue, values[i]);
          Send(hashValue);
        } else if (values[i] != prevValue) {
          PutLL(hashValue, values[i]);
          Send(hashValue);
          PutLL(hashValue, values[i]);
          Send(hashValue);
        }
        prevValue = values[i];
      }
    }

    for (LL i = hashingNodes + 1; i < nodes; i++) {
      PutLL(i, DONE);
      Send(i);
    }
  } else {
    vector<LL> values;
    LL finishedNodes = 0;
    while (finishedNodes < hashingNodes) {
      LL sender = Receive(-1);
      LL value = GetLL(sender);
      if (value == DONE)
        finishedNodes++;
      else
        values.push_back(value);
    }
    sort(values.begin(), values.end());

    LL prevValue = -1;
    for (LL i = 0; i < (LL)values.size(); i++) {
      if ((i == (LL)values.size() - 1 || values[i] != values[i + 1]) && values[i] != prevValue) {
        PutLL(MASTER_NODE, values[i]);
        Send(MASTER_NODE);
        return 0;
      }
      prevValue = values[i];
    } 
    PutLL(MASTER_NODE, INF);
    Send(MASTER_NODE);
  }
  return 0;
}
