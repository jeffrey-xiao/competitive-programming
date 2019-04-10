#include <bits/stdc++.h>

using namespace std;

#define SIZE 10005

int N, S;
int T[SIZE], F[SIZE];
int dp[SIZE], sumT[SIZE], sumF[SIZE];

deque<int> q;

inline double g(int k, int i) {
  return ((double)dp[k] - (double)dp[i]) / ((double)sumT[k] - (double)sumT[i]);
}
int main() {
  scanf("%d%d", &N, &S);
  for (int x = 0; x < N; x++)
    scanf("%d%d", &T[x], &F[x]);
  for (int i = N - 1; i >= 0; i--) {
    sumT[i] = sumT[i + 1] + T[i];
    sumF[i] = sumF[i + 1] + F[i];
  }
  q.push_back(N);
  for (int i = N - 1; i >= 0; i--) {
    while (q.size() >= 2 && g(q[0], q[1]) < (double)sumF[i])
      q.pop_front();

    int j = q.front();
    dp[i] = (dp[j] + (S + sumT[i] - sumT[j]) * sumF[i]);
    while (q.size() >= 2 && g(q[q.size() - 2], q[q.size() - 1]) > g(q[q.size() - 1], i)) {
      q.pop_back();
    }
    q.push_back(i);
  }
  printf("%d\n", dp[0]);
  return 0;
}
