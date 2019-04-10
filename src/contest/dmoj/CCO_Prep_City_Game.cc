#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 1010
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

static int N, M, K;
static int a[SIZE][SIZE], height[SIZE][SIZE];
static stack<int> s;

int compute() {
  while (!s.empty())
    s.pop();
  int ret = 0;
  int rows = N;
  int cols = M;

  for (int j = 0; j < cols; j++) {
    for (int i = rows - 1; i >= 0; i--) {
      if (a[i][j] == 1)
        height[i][j] = 0;
      else
        height[i][j] = 1 + (i == rows - 1 ? 0 : height[i + 1][j]);
    }
  }
  for (int i = 0; i < rows; i++) {
    for (int j = 0; j < cols; j++) {
      int minIndex = j;
      while (!s.empty() && height[i][s.top()] >= height[i][j]) {
        ret = max(ret, (j - s.top()) * (height[i][s.top()]));
        minIndex = s.top();
        height[i][minIndex] = height[i][j];
        s.pop();
      }
      s.push(minIndex);
    }
    while (!s.empty()) {
      ret = max(ret, (cols - s.top()) * height[i][s.top()]);
      s.pop();
    }
  }
  return ret;
}

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  rint(K);

  for (int t = 1; t <= K; t++) {
    scanf(" %d %d", &N, &M);

    memset(a, 0, sizeof a);
    memset(height, 0, sizeof height);

    for (int i = 0; i < N; i++)
      for (int j = 0; j < M; j++) {
        char c;
        scanf(" %c", &c);
        if (c == 'R')
          a[i][j] = 1;
        else
          a[i][j] = 0;
      }
    printf("%d\n", compute() * 3);
  }

  return 0;
}
