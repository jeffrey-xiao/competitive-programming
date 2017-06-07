/*
 * Dynamic programming algorithm that determines the number of ways to make change for N dollars with M coins, each with integer value. Each coin can be used an infinite number of times.
 * 
 * Time complexity: O(NM)
 */

package codebook.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CoinChange {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int n, m;
  static int[] coins;
  static int[] dp;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    m = readInt();

    dp = new int[n + 1];
    coins = new int[m];

    for (int i = 0; i < m; i++)
      coins[i] = readInt();

    dp[0] = 1;

    for (int j = 0; j < m; j++)
      for (int i = 1; i <= n; i++)
        if (coins[j] <= i)
          dp[i] += dp[i - coins[j]];

    out.println(dp[n]);
    out.close();
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

  static char readCharacter () throws IOException {
    return next().charAt(0);
  }

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
