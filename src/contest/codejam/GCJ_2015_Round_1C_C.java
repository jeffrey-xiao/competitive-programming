package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2015_Round_1C_C {

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
      int C = readInt();
      int D = readInt();
      long V = readInt();

      Queue<Long> q = new ArrayDeque<Long>();
      for (int i = 0; i < D; i++) {
        q.add(readLong());
      }

      long N = 0;
      int ans = 0;
      while (N < V) {
        if (q.isEmpty() || q.peek() > N + 1) {
          ans++;
          N = (N + 1) * C + N;
        } else {
          N = Math.max(N, q.poll() * C + N);
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
