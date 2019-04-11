package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Maximize_Sum {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    int t = readInt();
    for (int qq = 1; qq <= t; qq++) {
      int n = readInt();
      long m = readLong();
      long[] a = new long[n + 1];
      long[] sum = new long[n + 1];
      for (int i = 1; i <= n; i++)
        a[i] = readLong();
      for (int i = 1; i <= n; i++)
        sum[i] = (sum[i - 1] + a[i] % m) % m;
      TreeSet<Long> ts = new TreeSet<Long>();
      ts.add(m);
      long max = 0;
      for (int i = 1; i <= n; i++) {
        max = Math.max((sum[i] - ts.ceiling(sum[i]) + m) % m, max);
        ts.add(sum[i]);
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