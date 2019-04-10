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

int movex[6] = {-1, 1, 0, 0, 0, 0};
int movey[6] = {0, 0, -1, 1, 0, 0};
int movez[6] = {0, 0, 0, 0, -1, 1};

int X, Y, Z, sz, sy, sx;
char grid[150][150][150];
int cost[150][150][150];

struct point {
  int x, y, z, cost;
  point(int x, int y, int z, int cost) {
    this->x = x;
    this->y = y;
    this->z = z;
    this->cost = cost;
  }
  bool operator<(const point &p) const {
    return p.cost < cost;
  }
};

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  rint(X), rint(Y), rint(Z);
  for (int i = 0; i < Z; i++) {
    for (int j = 0; j < Y; j++) {
      for (int k = 0; k < X; k++) {
        scanf(" %c", &grid[i][j][k]);
        if (grid[i][j][k] == 'J') {
          sz = i;
          sy = j;
          sx = k;
        }
        cost[i][j][k] = 1 << 30;
      }
    }
  }
  cost[sz][sy][sx] = 0;

  priority_queue<point> pq;
  pq.push(point(sx, sy, sz, 0));

  int ans = 1 << 30;

  while (!pq.empty()) {
    point curr = pq.top();
    pq.pop();

    if (curr.z == 0 || curr.z == Z - 1 || curr.y == 0 || curr.y == Y - 1 || curr.x == 0 ||
        curr.x == X - 1)
      ans = min(ans, cost[curr.z][curr.y][curr.x]);

    for (int i = 0; i < 6; i++) {
      int nx = curr.x + movex[i];
      int ny = curr.y + movey[i];
      int nz = curr.z + movez[i];

      if (nx < 0 || ny < 0 || nz < 0 || nx >= X || ny >= Y || nz >= Z ||
          cost[nz][ny][nx] <= curr.cost + grid[nz][ny][nx] - '0')
        continue;

      cost[nz][ny][nx] = curr.cost + grid[nz][ny][nx] - '0';
      pq.push(point(nx, ny, nz, cost[nz][ny][nx]));
    }
  }
  printf("%d\n", ans);
  return 0;
}
