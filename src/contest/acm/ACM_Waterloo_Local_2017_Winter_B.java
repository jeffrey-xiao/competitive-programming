package contest.acm;

import java.io.*;
import java.util.*;

public class ACM_Waterloo_Local_2017_Winter_B {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N;
  static int[] X;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

  main:
    while (br.ready()) {
      N = readInt();
      X = new int[N];
      for (int i = 0; i < N; i++) {
        X[i] = readInt();
      }

      if (N == 1) {
        out.println(1);
        continue;
      }

      int diff = X[N - 1] - X[N - 2];
      for (int i = N - 2; i >= 0; i--) {
        if (X[i + 1] - X[i] != diff) {
          out.println(i + 2);
          continue main;
        }
      }
      out.println(1);
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
