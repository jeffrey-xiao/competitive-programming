package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2015_Round_1A_A {

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
      int N = readInt();
      int[] m = new int[N];
      for (int i = 0; i < N; i++) {
        m[i] = readInt();
      }
      int ans1 = 0, ans2 = 0;
      int maxRate = 0;
      for (int i = 1; i < N; i++) {
        if (m[i] < m[i - 1]) {
          ans1 += m[i - 1] - m[i];
          maxRate = Math.max(maxRate, m[i - 1] - m[i]);
        }
      }
      for (int i = 0; i < N - 1; i++) {
        ans2 += Math.min(maxRate, m[i]);
      }
      out.printf("Case #%d: %d %d%n", t, ans1, ans2);
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
