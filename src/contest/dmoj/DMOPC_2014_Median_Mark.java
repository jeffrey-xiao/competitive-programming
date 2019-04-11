package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DMOPC_2014_Median_Mark {

  static BufferedReader br;
  static PrintWriter ps;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    ps = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // ps = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    double[] a = new double[n];
    for (int i = 0; i < n; i++)
      a[i] = readDouble();
    Arrays.sort(a);
    if (n % 2 == 0)
      ps.println((int)Math.round((a[n / 2] + a[(n - 1) / 2]) / 2.0d));
    else
      ps.println((int)a[n / 2]);
    ps.close();
  }

  public static long noise(int n, int k) {
    k = k + 1;
    long x = n / k;
    long big = n % k;
    long small = k - big;
    return (x) * (x + 1) / 2 * small + (x + 1) * (x + 2) / 2 * big;
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