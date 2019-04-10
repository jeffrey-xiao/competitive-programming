#include <bits/stdc++.h>
using namespace std;

typedef long long ll;

int sorted[300001];
int sz;
int bsearch(int i) {
  int lo = 0;
  int hi = sz - 1;
  while (lo <= hi) {
    int mid = lo + (hi - lo) / 2;
    if (sorted[mid] > i) {
      hi = mid - 1;
    } else if (sorted[mid] < i) {
      lo = mid + 1;
    } else {
      return mid;
    }
  }
  return -1;
}
int main() {
  int n, q;
  scanf("%d%d", &n, &q);
  set<int> ts;
  int a[n];
  int b[n];
  int c[q];
  for (int i = 0; i < n; i++) {
    scanf("%d%d", &a[i], &b[i]);
    ts.insert(a[i]);
    ts.insert(b[i]);
  }
  for (int i = 0; i < q; i++) {
    scanf("%d", &c[i]);
    ts.insert(c[i]);
  }
  sz = ts.size();
  int cnt = 0;
  for (int i : ts) {
    sorted[cnt++] = i;
  }
  for (int i = 0; i < n; i++) {
    a[i] = bsearch(a[i]);
    b[i] = bsearch(b[i]);
  }
  for (int i = 0; i < q; i++)
    c[i] = bsearch(c[i]);
  int diff[sz + 1];
  memset(diff, 0, sizeof diff);
  for (int i = 0; i < n; i++) {
    diff[a[i]]++;
    diff[b[i] + 1]--;
  }
  cnt = 0;
  for (int i = 0; i <= sz; i++)
    diff[i] = (cnt += diff[i]);
  for (int i = 0; i < q; i++)
    printf("%d\n", diff[c[i]]);
  return 0;
}
