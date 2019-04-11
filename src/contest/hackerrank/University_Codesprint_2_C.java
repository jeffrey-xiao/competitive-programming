package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class University_Codesprint_2_C {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int T = readInt();

    for (int t = 1; t <= T; t++) {
      int N = readInt();
      int M = readInt();
      long x = readLong();

      long[] stackA = new long[N + 1];
      long[] stackB = new long[M + 1];

      for (int i = 1; i <= N; i++)
        stackA[i] = readLong() + stackA[i - 1];
      for (int i = 1; i <= M; i++)
        stackB[i] = readLong() + stackB[i - 1];

      int max = 0;
      for (int i = 0; i <= N; i++) {
        int lo = 0;
        int hi = M;
        while (lo <= hi) {
          int mid = (lo + hi) >> 1;
          if (stackA[i] + stackB[mid] <= x)
            lo = mid + 1;
          else
            hi = mid - 1;
        }
        if (stackA[i] <= x)
          max = Math.max(max, i + hi);
      }
      out.println(max);
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
