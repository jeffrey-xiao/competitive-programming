package contest.usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class USACO_2011_Above_The_Median_2 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;
  static int[] bit;
  static int[] h;
  static int n;

  public static void main(String[] args) throws IOException {
    n = readInt();
    h = new int[n];
    bit = new int[n * 2];
    int k = readInt();
    for (int x = 0; x < n; x++)
      h[x] = readInt() >= k ? 1 : -1;
    update(0);
    int sum = 0;
    long count = 0;
    for (int x = 0; x < n; x++) {
      sum += h[x];
      count += freq(sum);
      update(sum);
    }
    System.out.println(count);
  }

  private static void update(int idxx) {
    for (int x = n + idxx; x < bit.length; x += (x & -x))
      bit[x]++;
  }

  private static long freq(int idxx) {
    int sum = 0;
    for (int x = n + idxx; x > 0; x -= (x & -x))
      sum += bit[x];
    return sum;
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
