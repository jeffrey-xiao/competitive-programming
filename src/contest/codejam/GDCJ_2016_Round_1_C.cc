#include "rps.h"
#include <bits/stdc++.h>
#include <message.h>

using namespace std;

#define MASTER_NODE 0
#define INF (1L << 60)
#define DONE -1

typedef long long LL;

LL getWinner(LL a, LL b) {
  char x = GetFavoriteMove(a);
  char y = GetFavoriteMove(b);
  if (x == y)
    return a;
  if (x == 'R')
    return y == 'S' ? a : b;
  if (x == 'P')
    return y == 'R' ? a : b;
  if (x == 'S')
    return y == 'P' ? a : b;
}

int main() {
  int N = GetN();
  LL nodes = NumberOfNodes();
  LL id = MyNodeId();
  LL totalSize = 1LL << N;
  LL nodesToUse = 8;

  if (nodes == 100)
    nodesToUse = 64;

  nodesToUse = min(nodesToUse, totalSize);

  LL intervalSize = totalSize / nodesToUse;

  if (id != MASTER_NODE) {
    LL l = intervalSize * (id - 1);
    LL r = min(totalSize, intervalSize * id);

    if (l >= totalSize)
      return 0;

    if (intervalSize == 1) {
      PutLL(MASTER_NODE, l);
      Send(MASTER_NODE);
      return 0;
    }

    vector<LL> winners;
    vector<LL> nextWinners;

    for (LL i = l; i < r; i += 2)
      winners.push_back(getWinner(i, i + 1));

    while (winners.size() != 1) {
      for (int i = 0; i < (int)winners.size(); i += 2)
        nextWinners.push_back(getWinner(winners[i], winners[i + 1]));
      winners = nextWinners;
      nextWinners.clear();
    }
    PutLL(MASTER_NODE, winners[0]);
    Send(MASTER_NODE);
  } else {
    vector<LL> winners;
    vector<LL> nextWinners;

    for (int i = 1; i <= nodesToUse; i++) {
      Receive(i);
      winners.push_back(GetLL(i));
    }

    while (winners.size() != 1) {
      for (int i = 0; i < (int)winners.size(); i += 2)
        nextWinners.push_back(getWinner(winners[i], winners[i + 1]));
      winners = nextWinners;
      nextWinners.clear();
    }

    printf("%lld\n", winners[0]);
  }
  return 0;
}
