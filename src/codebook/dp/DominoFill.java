package codebook.dp;

public class DominoFill {

  /**
   * .....
   * ..234
   * 01
   */
  public static int method1 (int R, int C) {
    int[] prev = new int[1 << C];
    prev[(1 << C) - 1] = 1;

    for (int r = 0; r < R; r++) {
      for (int c = 0; c < C; c++) {
        int[] cur = new int[1 << C];
        for (int mask = 0; mask < 1 << C; mask++) {
          if ((mask & (1 << c)) != 0) {
            cur[mask ^ (1 << c)] += prev[mask]; // do nothing

            if (c > 0 && (mask & (1 << (c - 1))) == 0) {
              cur[mask | (1 << (c - 1))] += prev[mask]; // horizontal
            }
          } else {
            cur[mask | (1 << c)] += prev[mask]; // vertical
          }
        }
        prev = cur;
      }
    }

    return prev[(1 << C) - 1];
  }

  /**
   * .....
   * ..012
   * 34
   */
  public static int method2 (int R, int C) {
    int[] prev = new int[1 << C];
    prev[(1 << C) - 1] = 1;

    for (int r = 0; r < R; r++) {
      for (int c = 0; c < C; c++) {
        int[] cur = new int[1 << C];
        for (int mask = 0; mask < 1 << C; mask++) {
          int nmask = (mask << 1) & ((1 << C) - 1);
          if ((mask & (1 << (C - 1))) != 0) {
            // do nothing
            cur[nmask] += prev[mask];

            if (c > 0 && ((mask & 1) == 0)) {
              // horizontal
              cur[nmask | 3] += prev[mask];
            }
          } else {
            // vertical
            cur[nmask | 1] += prev[mask];
          }
        }
        prev = cur;
      }
    }

    return prev[(1 << C) - 1];
  }
}
