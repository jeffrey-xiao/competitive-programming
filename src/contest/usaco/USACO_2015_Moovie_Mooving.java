package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class USACO_2015_Moovie_Mooving {
  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;
  static int n;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));

    n = readInt();
    int t = readInt();
    int[] times = new int[n];
    int[] dp = new int[1 << 20];
    ArrayList<TreeSet<Integer>> ts = new ArrayList<TreeSet<Integer>>();
    for (int x = 0; x < n; x++)
      ts.add(new TreeSet<Integer>());
    for (int x = 0; x < n; x++) {
      times[x] = readInt();
      for (int y = readInt(); y > 0; y--) {
        int a = readInt();
        ts.get(x).add(a);
        if (a == 0) {
          dp[1 << x] = times[x];
        }
      }
    }
    int min = Integer.MAX_VALUE;
    for (int x = 0; x < 1 << n; x++) {
      System.out.println("HERE " + x + " " + dp[x]);
      if (dp[x] == 0)
        continue;
      if (dp[x] >= t) {
        min = Math.min(min, count(x));
      }
      for (int y = 0; y < n; y++) {
        if ((x & (1 << y)) == 0) {
          Integer nextMovie = ts.get(y).floor(dp[x]);
          if (nextMovie == null || nextMovie + times[y] < dp[x])
            continue;
          System.out.println(dp[x] + " " + nextMovie + " " + dp[x | (1 << y)]);
          dp[x | (1 << y)] = Math.max(dp[x | (1 << y)], nextMovie + times[y]);
          System.out.println(dp[x | (1 << y)]);
        }
      }
    }
    if (min == Integer.MAX_VALUE)
      System.out.println(-1);
    else
      System.out.println(min);
  }

  private static int count(int nv) {
    int c = 0;
    for (int x = 0; x < n; x++) {
      if ((nv & 1 << x) != 0)
        c++;
    }
    return c;
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

  static class State {
    int v, index, movie;

    State(int movie, int index, int v) {
      this.movie = movie;
      this.index = index;
      this.v = v;
    }
  }
}
