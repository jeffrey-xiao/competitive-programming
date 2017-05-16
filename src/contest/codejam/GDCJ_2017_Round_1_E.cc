#include <message.h>
#include <stdio.h>
#include "query_of_death.h" 

#define MASTER_NODE 0
#define DONE -1

long long min (long long a, long long b) {
  if (a < b)
    return a;
  return b;
}
int main() {
  long long N = GetLength();
  long long id = MyNodeId();

  if (id == 0) {
    for (int i = 0; i < N; i++) {
      int value = GetValue(i);
      for (int j = 0; j < 64; j++)
        if (GetValue(i) != value) {
          PutLL(1, i);
          Send(1);
          return 0;
        }
    }
  } else if (id == 1) {
    long long ans = 0;
    Receive(0);
    long long qod = GetLL(0);
    for (int i = 0; i < N; i++)
      if (i != qod)
        ans += GetValue(i);
    ans += GetValue(qod);
    printf("%lld\n", ans);
  } 
  return 0;
}
