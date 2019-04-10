#include <bits/stdc++.h>

using namespace std;
static void solve(int n, char start, char temp, char dest) {
  if (n == 0)
    return;
  solve(n - 1, start, dest, temp);
  printf("%c%c\n", start, dest);
  solve(n - 1, temp, start, dest);
}
int main() {
  int n;
  scanf("%d", &n);
  solve(n, 'A', 'B', 'C');
  return 0;
}
