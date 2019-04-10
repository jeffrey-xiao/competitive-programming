#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 1000
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int N;
int g[SIZE][SIZE];
int occ[SIZE][SIZE];

struct State {
  int height, index;
  State(int index, int height) {
    this->index = index;
    this->height = height;
  }

  bool operator<(const State &s) const {
    return height < s.height;
  }
};

ll getSubarrays(int val) {
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
      if ((val & g[i][j]) == 0) {
        occ[i][j] = 1;
        if (i > 0)
          occ[i][j] += occ[i - 1][j];
      } else {
        occ[i][j] = 0;
      }
    }
  }

  ll ret = 0;

  stack<State> s;
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
      State add = State(j, occ[i][j]);

      while (!s.empty() && s.top().height >= add.height) {
        State state = s.top();
        s.pop();
        ll diffWidth = j - state.index;
        ll diffHeight = state.height;
        if (s.empty() || s.top().height < add.height)
          diffHeight -= occ[i][j];
        else
          diffHeight -= s.top().height;
        add.index = state.index;
        ret += diffWidth * (diffWidth + 1) / 2 * diffHeight;
      }

      s.push(add);
    }
    while (!s.empty()) {
      State state = s.top();
      s.pop();
      ll diffWidth = N - state.index;
      ll diffHeight = state.height;
      if (s.empty())
        diffHeight -= 0;
      else
        diffHeight -= s.top().height;
      ret += diffWidth * (diffWidth + 1) / 2 * diffHeight;
    }
  }
  return ret;
}

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  rint(N);

  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
      char c;
      scanf(" %c", &c);
      if (c == '.')
        g[i][j] = 0;
      else
        g[i][j] = 1 << (c - 'A');
    }
  }

  ll ans = 0;
  for (int i = 0; i < 1 << 5; i++) {
    int sz = 0;
    for (int j = 0; j < 5; j++)
      if ((i & 1 << j) > 0)
        sz++;

    if (sz % 2 == 1)
      ans -= getSubarrays(i);
    else
      ans += getSubarrays(i);
  }

  printf("%lld\n", ans);
  return 0;
}
