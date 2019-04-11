package contest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class APIO_2007_Zoo {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    int c = readInt();
    int[][] dp = new int[n][1 << 5];
    int[][] value = new int[n][1 << 5];
    for (int i = 0; i < c; i++) {
      int e = readInt() - 1;
      int f = readInt();
      int l = readInt();
      int fear = 0;
      int love = 0;
      for (int j = 0; j < f; j++)
        fear |= 1 << ((readInt() - e - 1 + n) % n);
      for (int j = 0; j < l; j++)
        love |= 1 << ((readInt() - e - 1 + n) % n);
      for (int j = 0; j < 1 << 5; j++)
        if ((j & fear) != fear || (j & love) != 0)
          value[e][j]++;
    }
    int res = 0;
    for (int i = 0; i < 1 << 5; i++) {
      for (int j = 0; j < n; j++) {
        for (int k = 0; k < 1 << 5; k++) {
          boolean valid = true;
          for (int l = 0; l < 5; l++) {
            int index = (j + l) % n;
            if (0 <= index && index <= 4 && (((k & (1 << l)) > 0) ^ ((i & (1 << index)) > 0)))
              valid = false;
          }
          if (!valid) {
            dp[j][k] = -c;
          } else if (j == 0) {
            dp[j][k] = value[j][k];
          } else {
            int prevState = (k & ((1 << 4) - 1)) << 1;
            dp[j][k] = value[j][k] + Math.max(dp[j - 1][prevState], dp[j - 1][prevState + 1]);
            res = Math.max(dp[j][k], res);
          }
        }
      }
    }
    out.println(res);
    out.close();
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
