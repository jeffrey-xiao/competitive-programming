#include <bits/stdc++.h>
#define EPS 1e-14
using namespace std;

bool isPrime(int N) {
  if (N <= 1)
    return false;
  for (int i = 2; i * i <= N; i++)
    if (N % i == 0)
      return false;
  return true;
}

double hypot(int x, int y, int lx, int ly) {
  int dx = x - lx;
  int dy = y - ly;
  return dx * dx + dy * dy;
}

int main() {
  int T;
  cin >> T;
  for (int i = 0; i < T; i++) {
    int N, D;
    cin >> N >> D;
    int best = 0;
    double dist = 0.;
    int lx = 0, ly = 0;
    bool done = false;
    for (int j = 1; j <= N; j++) {
      int x, y;
      cin >> x >> y;
      dist += sqrt(hypot(x, y, lx, ly));
      lx = x;
      ly = y;
      if (dist - 1e-5 > D || done) {
        done = true;
        continue;
      }
      if (isPrime(j)) {
        best = j;
      }
    }
    cout << best << endl;
  }
}
