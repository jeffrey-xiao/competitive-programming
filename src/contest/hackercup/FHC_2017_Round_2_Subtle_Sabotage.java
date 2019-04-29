package contest.hackercup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FHC_2017_Round_2_Subtle_Sabotage {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T;
  static int N, M, K;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();

    for (int t = 1; t <= T; t++) {
      N = readInt();
      M = readInt();
      K = readInt();

      if (K >= N || K >= M)
        out.printf("Case #%d: %d%n", t, -1);
      else {
        int min = 1 << 30;
        if (2 * K + 3 <= M && K < N)
          min = Math.min(min, (N + K - 1) / K);
        if (2 * K + 3 <= N && K < M)
          min = Math.min(min, (M + K - 1) / K);
        if (2 * K + 1 <= N && M >= 1 + K + 1 + K + 1)
          min = Math.min(min, 2 + ((K == 1) ? 3 : 2));
        if (2 * K + 1 <= M && N >= 1 + K + 1 + K + 1)
          min = Math.min(min, 2 + ((K == 1) ? 3 : 2));
        out.printf("Case #%d: %d%n", t, min == 1 << 30 ? -1 : min);
      }
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
