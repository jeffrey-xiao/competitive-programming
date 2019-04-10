#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 100000
using namespace std;
typedef long long ll;
typedef pair<int, int> pi;
typedef pair<double, double> pd;
typedef pair<float, float> pf;
typedef pair<ll, ll> pll;

int N, M;

int sz[SIZE], id[SIZE];

void init() {
  for (int i = 0; i < SIZE; i++) {
    sz[i] = 1;
    id[i] = i;
  }
}
int find(int x) {
  return x == id[x] ? x : id[x] = find(id[x]);
}
bool merge(int x, int y) {
  x = find(x);
  y = find(y);
  if (x == y)
    return false;
  if (sz[y] > sz[x])
    swap(x, y);
  sz[x] += sz[y];
  id[y] = x;
  return true;
}
int main() {
  rint(N), rint(M);
  vector<int> e;
  init();
  for (int i = 0; i < M; i++) {
    int a, b;
    rint(a);
    rint(b);
    a--;
    b--;
    if (merge(a, b))
      e.pb(i);
  }
  if (e.size() != N - 1)
    printf("Disconnected Graph");
  else
    for (int i : e)
      printf("%d\n", i + 1);
  return 0;
}
