#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 1000100
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

static int N;
static vector<int> toIndex(SIZE);

int compute(vector<int> index) {
  set<int> peaks;
  int maxPeak = N / 2;
  for (int i = 0; i < N; i++) {
    int curr = min(abs(i - index[i]), abs(N + i - index[i]));
    if (N % 2 == 0) {
      if ((i + curr) % N == index[i]) {
        int p = (maxPeak - curr + N) % N;
        peaks.insert(p);
      }
      if ((i - curr + N) % N == index[i]) {
        int p = ((N - maxPeak + curr) + N) % N;
        peaks.insert(p);
      }
    } else {
      if ((i + curr) % N == index[i]) {
        int p = (maxPeak - curr + N) % N;
        peaks.insert(p);
        peaks.insert((p + 1) % N);
      }
      if ((i - curr + N) % N == index[i]) {
        int p = ((N - maxPeak + curr) + N) % N;
        peaks.insert(p);
        peaks.insert((p - 1 + N) % N);
      }
    }
  }

  int prev = 0;
  int ret = 1 << 30;

  peaks.insert(*(peaks.begin()) + N);

  for (int p : peaks) {
    ret = min(ret, maxPeak - (p - prev) / 2);
    prev = p;
  }
  return ret;
}

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  scanf("%d", &N);

  for (int i = 0; i < N; i++) {
    int val;
    scanf("%d", &val);

    toIndex[val - 1] = i;
  }

  int ans = compute(toIndex);

  for (int i = 0; i < N; i++)
    toIndex[i] = N - toIndex[i] - 1;

  ans = min(ans, compute(toIndex));

  printf("%d\n", ans);

  return 0;
}
