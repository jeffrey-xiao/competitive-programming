package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class New_Year_Cake_Balancing {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int l, r, w;
  static int[][][] dp;
  static int[] wl, wr;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    l = readInt();
    r = readInt();
    w = readInt();

    wl = new int[l];
    wr = new int[r];

    for (int i = 0; i < l; i++)
      wl[i] = readInt();

    for (int i = 0; i < r; i++)
      wr[i] = readInt();

    dp = new int[1 << l][1 << r][2];

    for (int i = 0; i < 1 << l; i++)
      for (int j = 0; j < 1 << r; j++)
        dp[i][j][0] = dp[i][j][1] = -1;

    out.println(Math.min(solve((1 << l) - 1, (1 << r) - 1, 0), solve((1 << l) - 1, (1 << r) - 1, 1)) + 1);
    out.close();
  }

  static int solve(int bl, int br, int flag) {
    // 0 = remove left, 1 = remove right
    if (bl == 0 && br == 0)
      return 0;

    if (dp[bl][br][flag] != -1)
      return dp[bl][br][flag];

    int totalL = 0;
    int totalR = 0;
    int min = 1 << 30;

    for (int i = 0; i < l; i++)
      if ((bl & 1 << i) > 0)
        totalL += wl[i];

    for (int i = 0; i < r; i++)
      if ((br & 1 << i) > 0)
        totalR += wr[i];

    for (int i = 0; i < l; i++)
      if ((bl & 1 << i) > 0)
        if (Math.abs((totalL - wl[i]) - totalR) <= w)
          min = Math.min(min, flag + solve(bl ^ (1 << i), br, 0));

    for (int i = 0; i < r; i++)
      if ((br & 1 << i) > 0)
        if (Math.abs(totalL - (totalR - wr[i])) <= w)
          min = Math.min(min, 1 - flag + solve(bl, br ^ (1 << i), 1));

    return dp[bl][br][flag] = min;
  }

  static String next() throws IOException {
    while (st == null || !st.hasMoreTokens())
      st = new StringTokenizer(br.readLine().trim());
    return st.nextToken();
  }

  static long readLong() throws IOException {
    return Long.parseLong(next());
  }

  static int readInt() throws IOException {
    return Integer.parseInt(next());
  }

  static double readDouble() throws IOException {
    return Double.parseDouble(next());
  }

  static char readCharacter() throws IOException {
    return next().charAt(0);
  }

  static String readLine() throws IOException {
    return br.readLine().trim();
  }
}
