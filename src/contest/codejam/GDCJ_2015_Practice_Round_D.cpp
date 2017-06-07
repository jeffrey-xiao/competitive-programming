#include <message.h>
#include <bits/stdc++.h>
#include "load_balance-0.h"

using namespace std;

#define MASTER_NODE 0
#define INF (1L << 60)
#define DONE -1

typedef long long LL;

// NODE 0: master node
// EVENT
// NODE 1: handles subsets of N / 2
// Node 2: handles subsets of (N + 1) / 2

int main () {
  LL N = GetN();
  LL nodes = NumberOfNodes();
  LL id = MyNodeId();
  LL weights[N];
  for (int i = 0; i < N; i++)
    weights[i] = GetWeight(i);

  return 0;
}

