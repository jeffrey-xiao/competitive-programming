#include <bits/stdc++.h>

#define mp make_pair

using namespace std;

typedef pair<int, int> pi;

bool cmp(pi i, pi j) {
  if (i.first == j.first)
    return i.second < j.second;
  return i.first < j.first;
}
int n, m, q;
pi cc[200000];
int in[200000];
bool done[200001];

int lower(int v, int i) {
  int lo = 0;
  int hi = n - 1;
  while (lo <= hi) {
    int mid = lo + (hi - lo) / 2;
    if (cc[mid].first < v || (cc[mid].first == v && cc[mid].second <= i))
      lo = mid + 1;
    else
      hi = mid - 1;
  }
  return hi;
}
int higher(int v, int i) {
  int lo = 0;
  int hi = n - 1;
  while (lo <= hi) {
    int mid = lo + (hi - lo) / 2;

    if (cc[mid].first < v || (cc[mid].first == v && cc[mid].second < i))
      lo = mid + 1;
    else
      hi = mid - 1;
  }
  return lo;
}
int main() {
  srand((unsigned)time(0));
  scanf("%d", &n);
  scanf("%d", &m);
  for (int i = 0; i < n; i++) {
    scanf("%d", &in[i]);
    cc[i] = mp(in[i], i);
  }
  sort(cc, cc + n, cmp);
  scanf("%d", &q);
  unordered_set<int> hs;
  for (int i = 0; i < q; i++) {
    int l, r;
    scanf("%d%d", &l, &r);
    l--;
    r--;
    bool valid = false;
    int res = 0;
    hs.clear();
    for (int j = 0; j < 30; j++) {
      int ran = (long long)(r - l + 1) * rand() / RAND_MAX + l;
      int cnt = lower(in[ran], r) - max(0, higher(in[ran], l)) + 1;
      if (cnt * 3 >= (r - l + 1) * 2) {
        valid = true;
        break;
      } else if (cnt * 3 >= (r - l + 1))
        hs.insert(in[ran]);
    }
    if (hs.size() >= 2 || valid)
      printf("YES\n");
    else
      printf("NO\n");
  }
}
