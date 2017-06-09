#include <message.h>
#include <bits/stdc++.h>
#include "shhhh.h"

using namespace std;

#define MASTER_NODE 0
#define INF (1L << 60)
#define DONE -1

typedef long long LL;

LL randLong () {
  LL a = rand();
  LL b = rand();
  return (a << 0) + (b << 31);
}

int main () {
  srand(time(NULL));
  LL N = GetN();
  LL nodes = NumberOfNodes();
  LL id = MyNodeId();

  if (id == MASTER_NODE) {
    vector<LL> points;
    queue<LL> availableNodes;
    map<LL, pair<LL, LL>> distTo;
    for (LL i = 0; i < (LL)(nodes * log2(nodes)); i++)
      points.push_back(randLong() % N);
    points.push_back(0LL);
    points.push_back(1LL);
    sort(points.begin(), points.end());
    points.erase(unique(points.begin(), points.end()), points.end());

    for (LL i = 1; i < nodes; i++) {
      PutLL(i, points.size());
      for (LL point : points)
        PutLL(i, point);
      Send(i);
      availableNodes.push(i);
    }

    for (LL i = 0; i < (LL)points.size(); i++) {
      if (availableNodes.size() == 0) {
        LL sender = Receive(-1);
        LL start = GetLL(sender);
        LL end = GetLL(sender);
        LL dist = GetLL(sender);
        distTo[start] = {end, dist};
        availableNodes.push(sender);
      }
      LL nextNode = availableNodes.front();
      availableNodes.pop();
      PutLL(nextNode, points[i]);
      Send(nextNode);
    }

    while ((LL)availableNodes.size() != nodes - 1) {
      LL sender = Receive(-1);
      LL start = GetLL(sender);
      LL end = GetLL(sender);
      LL dist = GetLL(sender);
      distTo[start] = {end, dist};
      availableNodes.push(sender);
    }

    for (LL i = 1; i < nodes; i++) {
      PutLL(i, DONE);
      Send(i);
    }

    LL totalDist = 0;
    LL oneWay = 0;
    LL curr = 0;
    while (curr != 1) {
      totalDist += distTo[curr].second;
      oneWay += distTo[curr].second;
      curr = distTo[curr].first;
    }
    while (curr != 0) {
      totalDist += distTo[curr].second;
      curr = distTo[curr].first;
    }

    if (totalDist - oneWay == oneWay)
      printf("WHATEVER %lld\n", oneWay);
    else if (totalDist - oneWay < oneWay)
      printf("LEFT %lld\n", totalDist - oneWay);
    else
      printf("RIGHT %lld\n", oneWay);
  } else {
    Receive(MASTER_NODE);

    LL size = GetLL(MASTER_NODE);
    vector<int> points;
    for (LL i = 0; i < size; i++)
      points.push_back(GetLL(MASTER_NODE));

    Receive(MASTER_NODE);
    LL input = GetLL(MASTER_NODE);
    while (input != DONE) {
      LL start = input;
      LL end = start;
      LL dist = 0;

      do {
        end = GetRightNeighbour(end);
        dist++;
      } while (!binary_search(points.begin(), points.end(), end));

      PutLL(MASTER_NODE, start);
      PutLL(MASTER_NODE, end);
      PutLL(MASTER_NODE, dist);
      Send(MASTER_NODE);

      Receive(MASTER_NODE);
      input = GetLL(MASTER_NODE);
    }
  }
  return 0;
}
