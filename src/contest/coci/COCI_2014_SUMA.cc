#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define rfloat(x) scanf("%f", &(x))
#define SIZE 701
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

struct edge {
  int a, b;
  long long h, v;
  edge() {}
  edge(int a, int b, long long h, long long v) {
    this->a = a;
    this->b = b;
    this->h = h;
    this->v = v;
  }
};

int N;
vector<edge> e;
vector<edge> constant;
long long H[SIZE][SIZE];
long long V[SIZE][SIZE];

int id[SIZE * SIZE];
int size[SIZE * SIZE];

void init() {
  for (int i = 0; i < SIZE * SIZE; i++) {
    id[i] = i;
    size[i] = 1;
  }
}

bool cmp(const edge &e1, const edge &e2) {
  return (long long)e1.h * e2.v < (long long)e2.h * e1.v;
}
int find(int x) {
  return x == id[x] ? x : find(id[x]);
}
bool merge(int rootx, int rooty) {
  if (rootx == rooty)
    return false;
  if (size[rootx] > size[rooty]) {
    size[rootx] += size[rooty];
    id[rooty] = rootx;
  } else {
    size[rooty] += size[rootx];
    id[rootx] = rooty;
  }
  return true;
}

void remove(int a, int b) {
  if (id[a] == a) {
    size[a] -= size[b];
    id[b] = b;
  } else if (id[b] == b) {
    size[b] -= size[a];
    id[a] = a;
  }
}

stack<pi> prevAdded;
int main() {
  rint(N);
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
      scanf("%lld", &H[i][j]);
    }
  }
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
      scanf("%lld", &V[i][j]);
    }
  }
  int maxV = 1;
  init();
  for (int i = 0; i < N; i++)
    for (int j = 0; j < N; j++) {
      if (i + 1 < N) {
        if (V[i + 1][j] == V[i][j] && H[i][j] == H[i + 1][j]) {
          merge(find(i * N + j), find((i + 1) * N + j));
        } else if (V[i][j] != V[i + 1][j]) {
          long long diffH = -H[i][j] + H[i + 1][j];
          long long diffV = +V[i][j] - V[i + 1][j];
          if (diffV < 0) {
            diffH = -diffH;
            diffV = -diffV;
          }
          if (diffH >= 0) {
            e.pb(edge(i * N + j, (i + 1) * N + j, diffH, diffV));
          }
        }
      }
      if (j + 1 < N) {
        if (V[i][j + 1] == V[i][j] && H[i][j] == H[i][j + 1]) {
          merge(find(i * N + j), find(i * N + j + 1));
        } else if (V[i][j] != V[i][j + 1]) {
          long long diffH = -H[i][j] + H[i][j + 1];
          long long diffV = +V[i][j] - V[i][j + 1];
          if (diffV < 0) {
            diffH = -diffH;
            diffV = -diffV;
          }
          if (diffH >= 0) {
            e.pb(edge(i * N + j, i * N + j + 1, diffH, diffV));
          }
        }
      }
    }
  sort(e.begin(), e.end(), cmp);
  for (int i = 0; i < e.size(); i++) {

    edge x = e[i];
    int rootx = find(x.a);
    int rooty = find(x.b);
    if (merge(rootx, rooty)) {
      prevAdded.push(mp(rootx, rooty));
    }
    maxV = max(maxV, size[find(x.a)]);
    if (i == e.size() || cmp(e[i], e[i + 1])) {
      while (!prevAdded.empty()) {
        pi last = prevAdded.top();
        prevAdded.pop();
        remove(last.first, last.second);
      }
    }
  }
  printf("%d\n", maxV);
}
