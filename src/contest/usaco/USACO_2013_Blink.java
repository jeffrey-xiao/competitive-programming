package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class USACO_2013_Blink {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    long b = readLong();
    boolean[] start = new boolean[n];
    boolean[] turtle = null;
    boolean[] rabbit = null;
    for (int x = 0; x < n; x++) {
      start[x] = readInt() == 1 ? true : false;
    }
    turtle = Arrays.copyOf(start, start.length);
    rabbit = Arrays.copyOf(start, start.length);
    do {
      nextState(turtle);
      b--;
      nextState(rabbit);
      nextState(rabbit);
    } while (!equals(turtle, rabbit));

    int cycle = 0;

    do {
      cycle++;
      nextState(rabbit);
    } while (!equals(turtle, rabbit));

    b %= cycle;
    if (b < 0)
      b += cycle;
    for (long x = 0; x < b; x++) {
      nextState(rabbit);
    }

    for (int x = 0; x < n; x++)
      System.out.println(rabbit[x] ? "1" : "0");
  }

  private static void nextState(boolean[] curr) {
    boolean last = curr[curr.length - 1];
    for (int x = curr.length - 1; x >= 1; x--) {
      if (curr[x - 1])
        curr[x] = !curr[x];
    }
    if (last)
      curr[0] = !curr[0];
  }

  private static boolean equals(boolean[] a, boolean[] b) {
    for (int x = 0; x < a.length; x++)
      if (a[x] != b[x])
        return false;
    return true;
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

  static String readLine() throws IOException {
    return br.readLine().trim();
  }
}
