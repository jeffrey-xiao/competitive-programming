// set of k elements from a total of n elements where order doesn't matter

package codebook.math;

import static codebook.math.Combinatorics.choose;

public class Combinations {

  static boolean nextCombination(int n, int k, int[] a) {
    for (int i = k - 1; i >= 0; i--) {
      if (a[i] < n - k + i) {
        for (++a[i]; ++i < k; )
          a[i] = a[i - 1] + 1;
        return true;
      }
    }
    return false;
  }

  static boolean nextCombinationRepeat(int n, int k, int[] a) {
    for (int i = k - 1; i >= 0; i--) {
      if (a[i] < n - 1) {
        for (++a[i]; ++i < k; )
          a[i] = a[i - 1];
        return true;
      }
    }
    return false;
  }

  // next binary combination
  static long nextCombination(long x) {
    long s = x & -x, r = x + s;
    return r | (((x ^ r) >> 2) / s);
  }

  static int[] combinationByRank(int n, int k, long x) {
    int[] ret = new int[k];
    int cnt = n;
    for (int i = 0; i < k; i++) {
      int j = 1;
      for (; ; j++) {
        long num = choose(cnt - j, k - 1 - i);
        if (x < num)
          break;
        x -= num;
      }
      ret[i] = i > 0 ? (ret[i - 1] + j) : (j - 1);
      cnt -= j;
    }
    return ret;
  }

  static long rankByCombination(int n, int k, int[] a) {
    long ret = 0;
    int prev = -1;
    for (int i = 0; i < k; i++) {
      for (int j = prev + 1; j < a[i]; j++)
        ret += choose(n - 1 - j, k - 1 - i);
      prev = a[i];
    }
    return ret;
  }
}
