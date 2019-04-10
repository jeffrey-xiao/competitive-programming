#include <bits/stdc++.h>

using namespace std;

bool primes[1000001];
int nums[1000001];

int main() {
  int a, b;
  scanf("%d%d", &a, &b);
  primes[1] = true;
  for (int x = 2; x < b + 1; x++) {
    if (!primes[x]) {
      for (int y = x; y < b + 1 && y >= 0; y += x) {
        primes[y] = true;
        nums[y]++;
      }
    }
    if (x >= a && x <= b)
      printf("%d\n", nums[x] == 0 ? nums[x] + 1 : nums[x]);
  }
  return 0;
}
