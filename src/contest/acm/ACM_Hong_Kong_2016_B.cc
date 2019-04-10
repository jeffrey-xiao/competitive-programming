#include <bits/stdc++.h>

using namespace std;
int main() {
  long long N;
  cin >> N;
  long long *A = new long long[100001];

  for (long long i = 0; i < 100001; ++i) {
    A[i] = 0;
  }

  for (long long i = 0; i < N; i++) {
    long long n;
    cin >> n;
    A[n + 50000] += 1;
  }

  long long amt = 0;

  // case 1: i = j = k = 0
  amt += A[50000] * (A[50000] - 1) * (A[50000] - 2);

  // case 2a: i = j < 0, 2b: i = j > 0
  for (long long i = -25000; i < 0; ++i) {
    amt += A[i + 50000] * (A[i + 50000] - 1) * A[i + i + 50000];
  }

  for (long long i = 1; i <= 25000; ++i) {
    amt += A[i + 50000] * (A[i + 50000] - 1) * A[i + i + 50000];
  }

  // case 3a: i = 0 and j = k, 3b: j = 0 and i = k
  for (long long i = -50000; i < 0; ++i) {
    amt += A[50000] * A[i + 50000] * (A[i + 50000] - 1) * 2;
  }

  for (long long i = 1; i <= 50000; ++i) {
    amt += A[50000] * A[i + 50000] * (A[i + 50000] - 1) * 2;
  }

  // case 4a: i < j < 0, 4b: i < 0 < j
  for (long long i = -50000; i < 0; ++i) {
    long long count = 0;
    for (long long j = max(50001 + i, -i); j < 50000; ++j) {
      count += A[i + j] * A[j];
    }
    for (long long j = 50001; j < 100001; ++j) {
      count += A[i + j] * A[j];
    }
    amt += count * A[i + 50000] * 2;
  }

  // case 5: 0 < i < j
  for (long long i = 1; i <= 25000; ++i) {
    long long count = 0;
    for (long long j = i + 50001; j < 100001 - i; ++j) {
      count += A[i + j] * A[j];
    }
    amt += count * A[i + 50000] * 2;
  }

  cout << amt << endl;
  return 0;
}
