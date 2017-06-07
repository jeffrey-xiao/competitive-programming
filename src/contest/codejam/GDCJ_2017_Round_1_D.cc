#include <message.h>
#include <bits/stdc++.h>
#include "todd_and_steven.h"

using namespace std;

#define MASTER_NODE 0
#define DONE -1
#define MOD (LL)(1e9 + 7)
#define MAX_VAL (LL)(5e9)

typedef long long LL;

LL N, M, T;

LL getValue (LL i) {
  LL lo1 = 0, hi1 = MAX_VAL;
  while (lo1 <= hi1) {
    LL mid1 = (hi1 + lo1) / 2LL;
    LL total = 0;
    LL lo2, hi2;

    lo2 = 0, hi2 = N - 1;
    while (lo2 <= hi2) {
      LL mid2 = (hi2 + lo2) / 2LL;
      if (GetToddValue(mid2) <= mid1)
        lo2 = mid2 + 1;
      else
        hi2 = mid2 - 1;
    }
    total += lo2;

    lo2 = 0, hi2 = M - 1;
    while (lo2 <= hi2) {
      LL mid2 = (hi2 + lo2) / 2LL;
      if (GetStevenValue(mid2) <= mid1)
        lo2 = mid2 + 1;
      else
        hi2 = mid2 - 1;
    }
    total += lo2;

    if (total <= i)
      lo1 = mid1 + 1;
    else
      hi1 = mid1 - 1;
  }
  return lo1;
}

int main() {
  N = GetToddLength();
  M = GetStevenLength();
  T = N + M;
  LL nodes = NumberOfNodes();
  LL id = MyNodeId();

  LL intervalSize = (T + (nodes - 1) - 1) / (nodes - 1);

  if (id != MASTER_NODE) {
    LL currAns = 0;
    LL l = intervalSize * (id - 1);
    LL r = min(T, intervalSize * id) - 1;

    if (l < T && l <= r) {
      LL targetL = getValue(l) - 1, targetR = getValue(r);
      LL toddL, toddR, stevenL, stevenR, lo, hi;

      lo = 0, hi = N - 1;
      while (lo <= hi) {
        LL mid = (lo + hi) / 2LL;
        if (GetToddValue(mid) <= targetL)
          lo = mid + 1;
        else
          hi = mid - 1;
      }
      toddL = lo;

      lo = 0, hi = N - 1;
      while (lo <= hi) {
        LL mid = (lo + hi) / 2LL;
        if (GetToddValue(mid) <= targetR)
          lo = mid + 1;
        else
          hi = mid - 1;
      }
      toddR = lo;

      lo = 0, hi = M - 1;
      while (lo <= hi) {
        LL mid = (lo + hi) / 2LL;
        if (GetStevenValue(mid) <= targetL)
          lo = mid + 1;
        else
          hi = mid - 1;
      }
      stevenL = lo;

      lo = 0, hi = M - 1;
      while (lo <= hi) {
        LL mid = (lo + hi) / 2LL;
        if (GetStevenValue(mid) <= targetR)
          lo = mid + 1;
        else
          hi = mid - 1;
      }
      stevenR = lo;

      LL i = toddL, j = stevenL;
      while (i < toddR || j < stevenR) {
        if (i == toddR || (j != stevenR && GetStevenValue(j) < GetToddValue(i))) {
          currAns = (currAns + (GetStevenValue(j) ^ (i + j))) % MOD;
          j++;
        } else {
          currAns = (currAns + (GetToddValue(i) ^ (i + j))) % MOD;
          i++;
        }
      }
    }

    PutLL(MASTER_NODE, currAns);
    Send(MASTER_NODE);
  } else {
    LL ans = 0;
    for (LL i = 1; i < nodes; i++) {
      LL src = Receive(i);
      ans = (ans + GetLL(src)) % MOD;
    }
    printf("%lld\n", ans);
  }

  return 0;
}
