#include <message.h>
#include <bits/stdc++.h>
#include "majority.h"

using namespace std;

#define MASTER_NODE 0
#define INF (1L << 60)
#define DONE -1

typedef long long LL;

int main () {
  LL N = GetN();
  LL nodes = NumberOfNodes();
  LL id = MyNodeId();

  LL intervalSize = (N + (nodes - 1) - 1) / (nodes - 1);

  if (id != MASTER_NODE) {
    LL l = intervalSize * (id - 1);
    LL r = min(N, intervalSize * id);

    if (l >= N) {
      PutLL(MASTER_NODE, 0);
      PutLL(MASTER_NODE, 0);
      Send(MASTER_NODE);
      PutLL(MASTER_NODE, 0);
      Send(MASTER_NODE);
      return 0;
    }
    LL index = 0;
    LL amount = 0;

    for (LL i = l; i < r; i++) {
      LL vote = GetVote(i);
      if (vote == index)
        amount++;
      else if (amount == 0) {
        index = vote;
        amount = 1;
      } else {
        amount--;
      }
    }
    PutLL(MASTER_NODE, index);
    PutLL(MASTER_NODE, amount);
    Send(MASTER_NODE);

    Receive(MASTER_NODE);
    index = GetLL(MASTER_NODE);
    amount = 0;
    for (LL i = l; i < r; i++)
      amount += GetVote(i) == index;
    PutLL(MASTER_NODE, amount);
    Send(MASTER_NODE);
  } else {
    LL index = 0;
    LL amount = 0;
    for (int i = 1; i < nodes; i++) {
      Receive(i);
      LL nextIndex = GetLL(i);
      LL nextAmount = GetLL(i);

      if (nextIndex == index)
        amount += nextAmount;
      else {
        amount -= nextAmount;
        if (amount < 0) {
          amount *= -1;
          index = nextIndex;
        }
      }
    }
    amount = 0;
    for (int i = 1; i < nodes; i++) {
      PutLL(i, index);
      Send(i);
    }
    for (int i = 1; i < nodes; i++) {
      Receive(i);
      amount += GetLL(i);
    }
    if (amount > N / 2)
      printf("%lld\n", index);
    else
      printf("NO WINNER\n");
  }

  return 0;
}
