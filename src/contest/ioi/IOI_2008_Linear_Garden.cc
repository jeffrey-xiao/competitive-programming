#include <bits/stdc++.h>

using namespace std;

int n, k;
static long long power(long long n, long long m) {
  if (m == 0)
    return 1;
  if (m == 1)
    return n;
  if (m % 2 == 0)
    return power(n * n % k, m / 2) % k;
  return n * power(n * n % k, m / 2) % k;
}
static long long compute(long long min, long long max, long long n, long long curr) {
  if (max - min > 2)
    return 0;
  if (max - min == 2)
    return power(2, n / 2) % k;
  return (power(2, n / 2) * (n % 2 == 1 ? 3 : 2) - 1) % k;
}

int main() {
  scanf("%d%ld", &n, &k);
  char input[n];
  for (int x = 0; x < n; x++)
    scanf(" %c", &input[x]);
  long long curr = 0, currMin = 0, currMax = 0;
  long long sum = 0;
  for (int x = 0; x < n; x++) {
    if (input[x] == 'P') {
      sum += compute(currMin, max(currMax, curr + 1), n - x - 1, curr + 1);
      currMin = min(currMin, --curr);
    } else {
      currMax = max(currMax, ++curr);
    }
  }
  printf("%ld", (sum + 1) % k);
  return 0;
}
