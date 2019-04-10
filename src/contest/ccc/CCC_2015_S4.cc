#include <algorithm>
#include <functional>
#include <iostream>
#include <queue>
#include <stdio.h>
#include <vector>
#define pp pair<int, pair<int, int>>
using namespace std;
int hull, islands, routeCnt, a, b;
int min1[2001][201];
struct route {
  int to, dist, wear;
};
vector<route> routes[2001];
void dijkstra1(int start) {
  priority_queue<pp, vector<pp>, greater<pp>> Q;
  Q.push(make_pair(0, make_pair(0, start)));
  for (int i = 0; i <= 200; i++) {
    min1[1][i] = 0;
  }
  while (!Q.empty()) {
    pp cur = Q.top();
    Q.pop();
    int curId = cur.second.second;
    for (int i = 0; i < routes[curId].size(); i++) {
      route ti = routes[curId][i];
      int nextH = cur.second.first + ti.wear;
      int nextT = cur.first + ti.dist;
      if (min1[ti.to][nextH] >= nextT) {
        if (nextH < hull) {
          min1[ti.to][nextH] = nextT;
          Q.push(make_pair(nextT, make_pair(nextH, ti.to)));
        }
      }
    }
  }
}
int main() {
  scanf("%d %d %d", &hull, &islands, &routeCnt);
  for (int i = 1; i <= islands; i++) {
    for (int j = 0; j <= 200; j++) {
      min1[i][j] = 1 << 30;
    }
  }
  for (int i = 0; i < routeCnt; i++) {
    int from, to, dist, wear;
    scanf("%d %d %d %d", &from, &to, &dist, &wear);
    route tmp1, tmp2;
    tmp1.to = to;
    tmp1.dist = dist;
    tmp1.wear = wear;
    tmp2.to = from;
    tmp2.dist = dist;
    tmp2.wear = wear;
    routes[from].push_back(tmp1);
    routes[to].push_back(tmp2);
  }
  scanf("%d %d", &a, &b);
  dijkstra1(a);
  int theMin = 1 << 30;
  for (int i = 0; i <= 200; i++) {
    if (min1[b][i] < theMin) {
      theMin = min1[b][i];
    }
  }
  if (theMin == 1 << 30) {
    theMin = -1;
  }
  printf("%d\n", theMin);
}
