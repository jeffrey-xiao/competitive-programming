#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 500100
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

unordered_map<int, int> toX, toIndex;

int N, L, S;

struct Event {
  int x, cost;
  Event(int x, int cost) {
    this->x = x;
    this->cost = cost;
  }

  bool operator<(const Event &e) const {
    return x >= e.x;
  }
};

int diff[SIZE];

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  scanf("%d%d%d", &N, &L, &S);

  priority_queue<Event> moves;

  int count = 0;

  set<int> m;

  for (int x = 0; x < N; x++) {
    int a, b, c;
    scanf("%d%d%d", &a, &b, &c);
    m.insert(a);
    m.insert(b + 1);
    moves.push(Event(a, c));
    moves.push(Event(b + 1, -c));
  }

  m.insert(0);
  moves.push(Event(0, 0));

  for (int i : m) {
    if (!toX.count(i)) {
      toX[count] = i;
      toIndex[i] = count++;
    }
  }

  int curr = 0;

  while (!moves.empty()) {
    Event next = moves.top();
    moves.pop();

    curr += next.cost;

    while (!moves.empty() && moves.top().x == next.x) {
      next = moves.top();
      moves.pop();
      curr += next.cost;
    }

    diff[toIndex[next.x]] = curr;
  }

  int c = 0;

  for (int x = 0; x < m.size() - 1; x++) {
    if (diff[x] >= S) {
      c += toX[x + 1] - toX[x];
    }
  }

  printf("%d\n", L - c);
  return 0;
}
