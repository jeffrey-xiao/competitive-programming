package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2018_Round_1B_C {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T, M;
  static int[] R1, R2;
  static long[] G;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    for (int t = 1; t <= T; t++) {
      M = readInt();
      R1 = new int[M];
      R2 = new int[M];
      G = new long[M];
      long sum = 0;

      for (int i = 0; i < M; i++) {
        R1[i] = readInt() - 1;
        R2[i] = readInt() - 1;
      }

      for (int i = 0; i < M; i++) {
        G[i] = readInt();
        sum += G[i];
      }

      long lo = 0;
      long hi = (long)1e11;
      while (lo <= hi) {
        long mid = (lo + hi) >> 1;
        long[] curr = Arrays.copyOf(G, G.length);
        long currSum = sum;
        curr[0] -= mid;
        currSum -= mid;

        boolean success = false;
        for (int k = 0; k < 2 * M && !success && currSum >= 0; k++) {
          success = true;
          for (int i = 0; i < M; i++) {
            if (curr[i] < 0) {
              success = false;
              if (R1[i] == i || R2[i] == i) {
                currSum = -1;
                break;
              }
              if (R1[i] == 0 || R2[i] == 0) {
                currSum = -1;
                break;
              }
              long change = curr[i];
              curr[R1[i]] += change;
              curr[R2[i]] += change;
              currSum += curr[i];
              curr[i] -= change;
            }
          }
        }

        if (success) {
          lo = mid + 1;
        } else {
          hi = mid - 1;
        }
      }
      out.printf("Case #%d: %d%n", t, hi);
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
