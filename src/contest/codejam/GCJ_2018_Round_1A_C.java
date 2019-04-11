package codejam;

import java.io.*;
import java.util.*;

public class GCJ_2018_Round_1A_C {

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
      int P = readInt();
      int perimeter = 0;
      int[] W = new int[N], H = new int[N];

      for (int i = 0; i < N; i++) {
        W[i] = readInt();
        H[i] = readInt();
        perimeter += (W[i] + H[i]) * 2;
      }

      HashMap<Integer, Double> hm = new HashMap<Integer, Double>();
      hm.put(perimeter, (double) perimeter);

      for (int i = 0; i < N; i++) {
        HashMap<Integer, Double> curr = new HashMap<Integer, Double>();
        for (Map.Entry<Integer, Double> e : hm.entrySet()) {
          curr.put(e.getKey(), e.getValue());
        }

        for (Map.Entry<Integer, Double> e : curr.entrySet()) {
          int lo = e.getKey() + Math.min(W[i], H[i]) * 2;
          double hi = e.getValue() + Math.sqrt(1.0 * W[i] * W[i] + H[i] * H[i]) * 2;

          if (lo > P) {
            continue;
          }

          if (!hm.containsKey(lo)) {
            hm.put(lo, hi);
          } else {
            hm.put(lo, Math.max(hi, hm.get(lo)));
          }
        }
      }

      double ans = 0;
      for (Map.Entry<Integer, Double> e : hm.entrySet()) {
        double currAns = Math.min(P, e.getValue());
        ans = Math.max(currAns, ans);
      }

      out.printf("Case #%d: %.9f\n", t, ans);
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
