package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class VMSS_Jayden_Eats_Chocolate {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int n, x, y, z;
  static int[] dp;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    x = readInt();
    y = readInt();
    z = readInt();

    dp = new int[n + 1];

    for (int i = 0; i <= n; i++) {
      if (dp[i] == 0 && i != 0)
        continue;
      if (i + x <= n)
        dp[i + x] = Math.max(dp[i + x], dp[i] + 1);
      if (i + y <= n)
        dp[i + y] = Math.max(dp[i + y], dp[i] + 1);
      if (i + z <= n)
        dp[i + z] = Math.max(dp[i + z], dp[i] + 1);
    }

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
