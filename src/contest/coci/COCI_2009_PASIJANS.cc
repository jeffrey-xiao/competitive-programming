#include <algorithm>
#include <bits/stdc++.h>
#include <cstdio>
#include <cstring>
#include <utility>
#include <vector>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1 << 31 + 7
#define rlong(x) scanf("%lld", &(x))
#define SIZE 1002
#define BASE 31
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2
char _inp[16777217], *_pinp = _inp, _;
using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;
#define getchar()                                                                                  \
  (*_pinp ? *_pinp++ : (_inp[fread(_pinp = _inp, 1, 16777216, stdin)] = '\0', *_pinp++))
#define rint(x)                                                                                    \
  do {                                                                                             \
    while ((x = getchar()) < '0')                                                                  \
      ;                                                                                            \
    for (x -= '0'; '0' <= (_ = getchar()); x = (x << 3) + (x << 1) + _ - '0')                      \
      ;                                                                                            \
  } while (0)

int n;

int currIndex[SIZE], a[SIZE][SIZE], len[SIZE];
ll hashValue[SIZE][SIZE], power[SIZE];

int minIndex[4 * SIZE];
int minValue[4 * SIZE];

ll getHash(int i, int l, int r) {
  return ((hashValue[i][r] - hashValue[i][l - 1] * power[r - l + 1]) % MOD + MOD) % MOD;
}

void pushUp(int n) {
  if (minValue[n << 1] < minValue[n << 1 | 1]) {
    minValue[n] = minValue[n << 1];
    minIndex[n] = minIndex[n << 1];
  } else if (minValue[n << 1] > minValue[n << 1 | 1]) {
    minValue[n] = minValue[n << 1 | 1];
    minIndex[n] = minIndex[n << 1 | 1];
  } else {
    int leftIndex = minIndex[n << 1];
    int rightIndex = minIndex[n << 1 | 1];
    int sz = min(len[leftIndex] - currIndex[leftIndex], len[rightIndex] - currIndex[rightIndex]);
    int lo = 0;
    int hi = sz;
    while (lo <= hi) {
      int mid = (hi + lo) / 2;
      if (getHash(leftIndex, currIndex[leftIndex], currIndex[leftIndex] + mid) ==
          getHash(rightIndex, currIndex[rightIndex], currIndex[rightIndex] + mid))
        lo = mid + 1;
      else
        hi = mid - 1;
    }
    if (lo > sz) {
      if (len[leftIndex] - currIndex[leftIndex] < len[rightIndex] - currIndex[rightIndex]) {
        minValue[n] = minValue[n << 1 | 1];
        minIndex[n] = minIndex[n << 1 | 1];
      } else {
        minValue[n] = minValue[n << 1];
        minIndex[n] = minIndex[n << 1];
      }
      return;
    }
    int cmp = a[leftIndex][currIndex[leftIndex] + lo] - a[rightIndex][currIndex[rightIndex] + lo];
    if (cmp < 0) {
      minValue[n] = minValue[n << 1];
      minIndex[n] = minIndex[n << 1];
    } else if (cmp > 0) {
      minValue[n] = minValue[n << 1 | 1];
      minIndex[n] = minIndex[n << 1 | 1];
    } else {
      if (len[leftIndex] - currIndex[leftIndex] < len[rightIndex] - currIndex[rightIndex]) {
        minValue[n] = minValue[n << 1 | 1];
        minIndex[n] = minIndex[n << 1 | 1];
      } else {
        minValue[n] = minValue[n << 1];
        minIndex[n] = minIndex[n << 1];
      }
    }
  }
}

void build(int n, int l, int r) {
  if (l == r) {
    minIndex[n] = l;
    minValue[n] = a[l][currIndex[l]];
    return;
  }
  int mid = (l + r) / 2;
  build(n << 1, l, mid);
  build(n << 1 | 1, mid + 1, r);
  pushUp(n);
}

void update(int n, int l, int r, int x) {
  if (l == x && x == r) {
    minIndex[n] = l;
    minValue[n] = a[l][currIndex[l]];
    return;
  }
  int mid = (l + r) / 2;
  if (x <= mid)
    update(n << 1, l, mid, x);
  else
    update(n << 1 | 1, mid + 1, r, x);
  pushUp(n);
}

int main() {
  // freopen("../../../in.txt", "r", stdin);
  // freopen("../../../out.txt", "w", stdout);

  rint(n);
  power[0] = 1;
  for (int i = 1; i <= 1001; i++)
    power[i] = power[i - 1] * BASE % MOD;

  int total = 0;
  for (int i = 1; i <= n; i++) {
    rint(len[i]);
    total += len[i];

    currIndex[i] = 1;

    for (int j = 1; j <= len[i]; j++) {
      rint(a[i][j]);
      hashValue[i][j] = (a[i][j] + hashValue[i][j - 1] * BASE) % MOD;
    }
    a[i][len[i] + 1] = 1 << 30;
  }

  build(1, 1, n);

  for (int i = 0; i < total; i++) {
    printf("%d ", minValue[1]);
    currIndex[minIndex[1]]++;
    update(1, 1, n, minIndex[1]);
  }

  return 0;
}
