#include <bits/stdc++.h>

using namespace std;

#define N 20005

int id[N], size[N];
int n, e, k;

struct edge {
  int s, d;
  edge(int s, int d) {
    this->s = s;
    this->d = d;
  }
};

vector<edge> white;
vector<edge> black;

void init() {
  for (int x = 0; x < N; x++)
    id[x] = x, size[x] = 0;
}
int find(int i) {
  while (i != id[i]) {
    id[i] = id[id[i]];
    i = id[i];
  }
  return i;
}
void merge(int x, int y) {
  int rootx = find(x);
  int rooty = find(y);
  if (size[rootx] > size[rooty]) {
    size[rootx] += size[rooty];
    id[rooty] = id[rootx];
  } else {
    size[rooty] += size[rootx];
    id[rootx] = id[rooty];
  }
}
int main() {
  scanf("%d%d%d", &n, &e, &k);

  for (int x = 0; x < e; x++) {
    int s, d, t;
    scanf("%d%d%d", &s, &d, &t);
    s--, d--;
    if (t == 0)
      black.push_back(edge(s, d));
    else
      white.push_back(edge(s, d));
  }
  init();
  for (int x = 0; x < white.size(); x++)
    if (find(white[x].s) != find(white[x].d))
      merge(white[x].s, white[x].d);
  vector<int> mark;
  for (int x = 0; x < black.size(); x++)
    if (find(black[x].s) != find(black[x].d)) {
      merge(black[x].s, black[x].d);
      mark.push_back(x);
    }
  init();
  int count = 0;
  for (int x = 0; x < black.size(); x++)
    if (find(black[x].s) != find(black[x].d)) {
      merge(black[x].s, black[x].d);
      count++;
    }
  if (count < k) {
    printf("no solution\n");
  } else {
    init();
    for (int x = 0; x < mark.size(); x++) {
      merge(black[mark[x]].s, black[mark[x]].d);
      printf("%d %d %d\n", black[mark[x]].s + 1, black[mark[x]].d + 1, 0);
    }
    int count = 0;
    for (int x = 0; x < black.size(); x++) {
      if (find(black[x].s) != find(black[x].d)) {
        merge(black[x].s, black[x].d);
        printf("%d %d %d\n", black[x].s + 1, black[x].d + 1, 0);
        count++;
        if (count + mark.size() == k)
          break;
      }
    }
    for (int x = 0; x < white.size(); x++) {
      if (find(white[x].s) != find(white[x].d)) {
        merge(white[x].s, white[x].d);
        printf("%d %d %d\n", white[x].s + 1, white[x].d + 1, 1);
      }
    }
  }
  return 0;
}
