package contest.usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class USACO_2013_Cow_Crossing {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    Cow[] c = new Cow[n];

    for (int x = 0; x < n; x++)
      c[x] = new Cow(readInt(), readInt());
    Arrays.sort(c);
    int[] left = new int[n];
    int[] right = new int[n];
    left[0] = c[0].y;
    right[n - 1] = c[n - 1].y;

    for (int x = 1; x < n; x++)
      left[x] = Math.max(left[x - 1], c[x].y);

    for (int x = n - 2; x >= 0; x--)
      right[x] = Math.min(right[x + 1], c[x].y);

    int count = n;

    for (int x = 0; x < n; x++) {
      if ((x != 0 && left[x - 1] > c[x].y) || (x != n - 1 && right[x + 1] < c[x].y))
        count--;
    }
    System.out.println(count);
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

  static class Cow implements Comparable<Cow> {
    int x, y;

    Cow(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public int compareTo(Cow o) {
      return x - o.x;
    }
  }
}
