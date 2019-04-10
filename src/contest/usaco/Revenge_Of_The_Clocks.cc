#include <bits/stdc++.h>

using namespace std;

int main() {
  int start[9];
  int moves[9];
  memset(moves, 0, sizeof moves);
  for (int i = 0; i < 9; i++)
    scanf("%d", &start[i]);
  int states[9][9] = {{7, 3, 7, 3, 7, 2, 7, 2, 8}, {10, 7, 10, 7, 10, 7, 9, 8, 9},
      {7, 3, 7, 2, 7, 3, 8, 2, 7}, {10, 7, 9, 7, 10, 8, 10, 7, 9}, {10, 7, 10, 7, 9, 7, 10, 7, 10},
      {9, 7, 10, 8, 10, 7, 9, 7, 10}, {7, 2, 8, 3, 7, 2, 7, 3, 7}, {9, 8, 9, 7, 10, 7, 10, 7, 10},
      {8, 2, 7, 2, 7, 3, 7, 3, 7}};
  for (int i = 0; i < 9; i++) {
    int shift = (12 - start[i]) % 12;
    for (int j = 0; j < 9; j++) {
      moves[j] += states[i][j] * shift;
    }
  }
  for (int i = 0; i < 9; i++) {
    moves[i] %= 12;
    printf("%d\n", moves[i]);
  }
}
