package contest.ecoo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class ECOO_2016_R1_P2 {

  static final int TEST_CASES = 10;
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("DATA21.txt"));

    for (int T = 1; T <= TEST_CASES; T++) {
      int N = readInt();
      int[] occ = new int[101];
      for (int i = 0; i < N; i++) {
        occ[readInt()]++;
      }
      HashSet<Integer> hm = new HashSet<Integer>();
      for (int i = 1; i <= 100; i++) {
        for (int j = 1; j <= 100; j++) {
          for (int k = 1; k <= 100; k++) {
            if (occ[i] > 0 && occ[j] > 0 && occ[k] > 0) {
              hm.add((i * j) * k);
              hm.add((i + j) * k);
              hm.add((i * j) + k);
              hm.add((i + j) + k);
            }
          }
        }
      }
      for (int i = 0; i < 5; i++) {
        int v = readInt();
        out.print(hm.contains(v) ? "T" : "F");
      }
      out.println();
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
