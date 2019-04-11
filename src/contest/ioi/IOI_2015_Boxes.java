package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class IOI_2015_Boxes {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int n, k, l;
  static long[] dp1, dp2, pos;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    k = readInt();
    l = readInt();
    dp1 = new long[n + 2];
    dp2 = new long[n + 2];
    pos = new long[n + 1];
    for (int i = 1; i <= n; i++)
      pos[i] = readInt();
    for (int i = 1; i <= n; i++) {
      if (i >= k) {
        dp1[i] = dp1[i - k] + Math.min(2 * pos[i], l);

      } else {
        dp1[i] = Math.min(2 * pos[i], l);
      }
    }
    for (int i = n; i >= 1; i--) {
      if (i + k <= n) {
        dp2[i] = dp2[i + k] + Math.min(2 * (l - pos[i]), l);
      } else {
        dp2[i] = Math.min(2 * (l - pos[i]), l);
      }
    }
    long ans = 1l << 60;
    for (int i = 0; i <= n; i++)
      ans = Math.min(ans, dp1[i] + dp2[i + 1]);
    out.println(ans);
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
