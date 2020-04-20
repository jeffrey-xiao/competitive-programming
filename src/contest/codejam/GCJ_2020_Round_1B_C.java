package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2020_Round_1B_C {

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
      int R = readInt();
      int S = readInt();
      out.printf("Case #%d: %d%n", t, (R * S - R + 1) / 2);
      ArrayList<Integer> a = new ArrayList<Integer>();
      int[] fixedSizes = new int[R + 1];
      for (int i = 1; i < R; i++) {
        fixedSizes[i] = 1;
      }
      for (int i = 1; i < S; i++) {
        for (int j = 1; j <= R; j++) {
          if (i != S - 1 || j != R) {
            a.add(j);
          }
        }
      }
      int lastSize = 1;
      for (int i = 0; i < a.size(); i += 2) {
        if (i == a.size() - 1) {
          out.printf("%d %d%n", 1 + lastSize, getPrefixSum(fixedSizes, a.get(i)));
          lastSize = 0;
        } else if (a.get(i) + 1 == a.get(i + 1)) {
          int remaining = a.size() - i - 2 + lastSize;
          out.printf("2 %d%n", remaining + getPrefixSum(fixedSizes, a.get(i)));
          fixedSizes[a.get(i)]++;
          fixedSizes[a.get(i + 1)]++;
        } else {
          int remaining = a.size() - i - 2 + lastSize;
          out.printf("2 %d%n", remaining);
          fixedSizes[a.get(i + 1)]++;
          lastSize++;
        }
      }
      if (lastSize != 0) {
        out.printf("%d %d%n", lastSize, R * S - lastSize);
      }
    }

    out.close();
  }

  static int getPrefixSum(int[] sum, int n) {
    int ret = 0;
    for (int i = 1; i <= n; i++) {
      ret += sum[i];
    }
    return ret;
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
