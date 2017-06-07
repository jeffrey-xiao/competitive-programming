package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CCC_2001_Stage_2_Partitions_DP {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int[][] dp = new int[101][101];

  public static void main (String[] args) throws IOException {
    main : for (int q = readInt(); q > 0; q--) {
      int n = readInt();
      int a = readInt();
      ArrayList<Integer> nums = new ArrayList<Integer>();
      while (n > 0) {
        int curr = -1;
        for (int x = 1; x <= n; x++) {
          int next = compute(n, x);
          if (next >= a) {
            curr = x;
            break;
          }
          a -= next;
        }
        if (curr == -1) {
          System.out.println("Too big");
          continue main;
        } else {
          n -= curr;
          nums.add(curr);
        }
      }
      System.out.print("(" + nums.get(0));
      for (int x = 1; x < nums.size(); x++) {
        System.out.print("," + nums.get(x));
      }
      System.out.println(")");
    }
  }

  static int compute (int n, int a) {
    if (a == 1 || n == 0 || n == a)
      return 1;
    if (a > n)
      return 0;
    if (dp[n][a] > 0)
      return dp[n][a];
    for (int x = 1; x <= a; x++)
      dp[n][a] += compute(n - a, x);
    return dp[n][a];
  }

  static String next () throws IOException {
    while (st == null || !st.hasMoreTokens())
      st = new StringTokenizer(br.readLine().trim());
    return st.nextToken();
  }

  static long readLong () throws IOException {
    return Long.parseLong(next());
  }

  static int readInt () throws IOException {
    return Integer.parseInt(next());
  }

  static double readDouble () throws IOException {
    return Double.parseDouble(next());
  }

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
