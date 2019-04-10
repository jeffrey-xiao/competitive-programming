#include <bits/stdc++.h>

using namespace std;

int order[20000];
unordered_map<int, int> pos[5];
int n;

bool cmp(int o1, int o2) {
  int total = 0;
  for (int x = 0; x < 5; x++)
    if (pos[x][o1] < pos[x][o2])
      total++;
  return total > 2;
}

int main() {
  scanf("%d", &n);
  for (int x = 0; x < 5; x++) {
    for (int y = 0; y < n; y++) {
      int cow;
      scanf("%d", &cow);
      pos[x][cow] = y;
      order[y] = cow;
    }
  }
  sort(order, order + n, cmp);
  for (int x = 0; x < n; x++)
    printf("%d\n", order[x]);
}
