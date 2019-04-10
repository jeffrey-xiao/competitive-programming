#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 100100
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int N;
int seq[25];
queue<pi> q;
unordered_set<int> v;

int getState(int seq[25]) {
  int state = 0;
  for (int i = 0; i < N; i++)
    state = 2 * state + seq[i];
  return state;
}
int adjust(int state, int pos) {
  int l = pos;
  int r = pos;
  state ^= 1 << pos;
  while (l >= 0 && state & (1 << l))
    l--;
  while (r < N && state & (1 << r))
    r++;
  l++;
  r--;
  if (r - l + 1 > 3)
    for (int i = l; i <= r; i++)
      state ^= 1 << i;
  return state;
}
int main() {
  rint(N);
  for (int i = 0; i < N; i++)
    rint(seq[i]);
  int state = getState(seq);
  v.insert(state);
  q.push(mp(state, 0));
  while (!q.empty()) {
    int s = q.front().first;
    int moves = q.front().second;
    q.pop();
    if (s == 0 || s == (1 << N) - 1) {
      printf("%d\n", moves);
      break;
    }
    for (int i = 0; i < N; i++) {
      if (!(s & (1 << i))) {
        int nstate = adjust(s, i);
        if (v.insert(nstate).second)
          q.push(mp(nstate, moves + 1));
      }
    }
  }
}
