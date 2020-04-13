package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2015_Qualification_A {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    for (int t = 1; t <= T; t++) {
      int S = readInt();
      int[] shy = new int[S + 1];
      char[] in = next().toCharArray();
      for (int i = 0; i <= S; i++) {
        shy[i] = in[i] - '0';
      }
      int ans = 0;
      for (int i = 1; i <= S; i++) {
        if (shy[i - 1] < i) {
          shy[i] += shy[i - 1] + (i - shy[i - 1]);
          ans += i - shy[i - 1];
        } else {
          shy[i] += shy[i - 1];
        }
      }
      out.printf("Case #%d: %d%n", t, ans);
    }

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
