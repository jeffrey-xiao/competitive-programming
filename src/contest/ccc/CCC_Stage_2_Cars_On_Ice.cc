#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 2000
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

static int r, c;
char g[SIZE][SIZE];
int in[SIZE][SIZE][4];
bool car[SIZE][SIZE][4];
string dir = "NESW";
int movex[4] = {-1, 0, 1, 0};
int movey[4] = {0, 1, 0, -1};

struct State {
  int r, c, d;
  State(int r, int c, int d) {
    this->r = r;
    this->c = c;
    this->d = d;
  }
};

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  scanf("%d%d", &r, &c);

  for (int i = 0; i < r; i++)
    for (int j = 0; j < c; j++)
      scanf(" %c", &g[i][j]);

  for (int i = 0; i < r; i++)
    for (int j = 0; j < c; j++) {
      for (int z = 0; z < 4; z++)
        if (0 <= i + movex[z] && i + movex[z] < r && 0 <= j + movey[z] && j + movey[z] < c)
          in[i][j][z]++;
      if (g[i][j] != '.') {
        int currDir = 0;
        for (int k = 0; k < 4; k++)
          if (dir[k] == g[i][j])
            currDir = k;
        for (int z = 0; z < 4; z++)
          if (z != currDir)
            in[i][j][z]++;
        car[i][j][currDir] = true;
      }
    }
  queue<State> q;
  for (int i = 0; i < r; i++)
    for (int j = 0; j < c; j++)
      for (int k = 0; k < 4; k++)
        if (in[i][j][k] == 0)
          q.push(State(i, j, k));

  while (!q.empty()) {
    State curr = q.front();
    q.pop();
    if (car[curr.r][curr.c][curr.d]) {
      printf("(%d,%d)\n", curr.r, curr.c);
      for (int z = 0; z < 4; z++)
        if (z != curr.d) {
          in[curr.r][curr.c][z]--;
          if (in[curr.r][curr.c][z] == 0)
            q.push(State(curr.r, curr.c, z));
        }
    }

    // depends on direction
    int nr = curr.r + movex[(curr.d + 2) % 4];
    int nc = curr.c + movey[(curr.d + 2) % 4];
    if (0 <= nr && nr < r && 0 <= nc && nc < c) {
      in[nr][nc][curr.d]--;
      if (in[nr][nc][curr.d] == 0)
        q.push(State(nr, nc, curr.d));
    }
  }
  return 0;
}
