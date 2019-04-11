// ordered multiset of positive integers that has a total sum equal to n

package codebook.math;

import java.util.ArrayList;
import java.util.Arrays;

public class Partitions {

  static long[][] dp = new long[101][101];

  static boolean nextPartition(ArrayList<Integer> p) {
    int n = p.size();
    if (n <= 1)
      return false;
    int s = p.get(n - 1) - 1, i = n - 2;
    p.remove(p.size() - 1);
    for (; i > 0 && p.get(i) == p.get(i - 1); i--) {
      s += p.get(i);
      p.remove(p.size() - 1);
    }
    for (p.set(i, p.get(i) + 1); s-- > 0;)
      p.add(1);
    return true;
  }

  static long countPartitions(int n) {
    long[] dp = new long[n + 1];
    dp[0] = 1;
    for (int i = 1; i <= n; i++)
      for (int j = 1; j <= n; j++)
        dp[j] += dp[j - 1];
    return dp[n];
  }

  static long partition(int i, int j) {
    if (i == 0 || j == 1 || i == j)
      return 1;
    if (j > i)
      return 0;
    if (dp[i][j] > 0)
      return dp[i][j];
    for (int k = 1; k <= j; k++)
      dp[i][j] += partition(i - j, k);
    return dp[i][j];
  }

  // one indexed
  static ArrayList<Integer> partitionByRank(int n, long x) {
    ArrayList<Integer> ret = new ArrayList<Integer>();
    while (n > 0) {
      for (int i = 1; i <= n; i++) {
        long next = partition(n, i);
        if (next >= x) {
          n -= i;
          ret.add(i);
          break;
        }
        x -= next;
      }
    }
    return ret;
  }

  static long rankByPartition(ArrayList<Integer> p) {
    long ret = 0;
    int sum = 0;
    for (int i = 0; i < p.size(); i++)
      sum += p.get(i);
    for (int i = 0; i < p.size(); i++) {
      for (int j = 1; j < p.get(i); j++)
        ret += partition(sum, j);
      sum -= p.get(i);
    }
    return ret + 1;
  }

  public static void main(String[] args) {
    System.out.println(partitionByRank(10, 14).toString());
    ArrayList<Integer> a = new ArrayList<Integer>(Arrays.asList(3, 3, 3, 1));
    System.out.println(rankByPartition(a));
  }
}
