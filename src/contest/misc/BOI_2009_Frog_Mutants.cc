#include <bits/stdc++.h>

using namespace std;

#define N 1000005

int h[N], j[N];
vector<int> heights;
int n;
int main() {
  scanf("%d", &n);
  for (int x = 0; x < n; x++)
    scanf("%d", &h[x]);
  for (int x = 0; x < n; x++)
    scanf("%d", &j[x]);
  int size = 0;
  for (int x = n - 1; x >= 0; x--) {
    while (size != 0 && h[x] >= heights[size - 1])
      heights.pop_back(), size--;
    if (size == 0 || size - j[x] < 0)
      j[x] = -1;
    else
      j[x] = heights[size - j[x]];
    heights.push_back(h[x]), size++;
  }
  for (int x = 0; x < n; x++)
    printf("%d ", j[x]);
  return 0;
}
