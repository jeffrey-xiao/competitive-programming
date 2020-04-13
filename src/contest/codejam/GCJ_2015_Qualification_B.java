package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2015_Qualification_B {

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
      int D = readInt();
      int[] pancakes = new int[D];
      for (int i = 0; i < D; i++) {
        pancakes[i] = readInt();
      }
      int ans = Integer.MAX_VALUE;
      for (int i = 1; i <= 1000; i++) {
        int curr = 0;
        for (int j = 0; j < D; j++) {
          if (pancakes[j] > i) {
            curr += (pancakes[j] + i - 1) / i - 1;
          }
        }
        curr += i;
        if (curr < ans) {
          ans = curr;
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
