package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class USACO_2011_Cow_Steeplechase {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  static ArrayList<Integer> intersection = new ArrayList<Integer>();
  static boolean[][] adj;
  static boolean[] visited;
  static int[] prev;
  static int l;
  static int r;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    ArrayList<Interval> xs = new ArrayList<Interval>();
    ArrayList<Interval> ys = new ArrayList<Interval>();
    for (int x = 0; x < n; x++) {
      int x1 = readInt();
      int y1 = readInt();
      int x2 = readInt();
      int y2 = readInt();
      if (y1 == y2) {
        xs.add(new Interval(Math.min(x1, x2), y1, Math.max(x1, x2), y1));
      } else if (x1 == x2) {
        ys.add(new Interval(x1, Math.min(y1, y2), x1, Math.max(y1, y2)));
      }
    }
    l = xs.size();
    r = ys.size();
    adj = new boolean[l][r];
    prev = new int[r];
    for (int x = 0; x < l; x++) {
      for (int y = 0; y < r; y++) {
        Interval i1 = xs.get(x);
        Interval i2 = ys.get(y);
        if (i2.y1 <= i1.y1 && i1.y1 <= i2.y2 && i1.x1 <= i2.x1 && i2.x1 <= i1.x2) {

          adj[x][y] = true;
        }
      }
    }
    int count = 0;
    for (int x = 0; x < r; x++)
      prev[x] = -1;
    for (int x = 0; x < l; x++) {
      visited = new boolean[r];
      count += hungary(x) ? 1 : 0;
    }
    System.out.println(count * 2);
  }

  private static boolean hungary(int x) {
    for (int y = 0; y < r; y++) {
      if (adj[x][y] && !visited[y]) {
        visited[y] = true;
        if (prev[y] == -1 || hungary(prev[y])) {
          prev[y] = x;
          return true;
        }
      }
    }
    return false;
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

  static class Interval {
    int y1;
    int y2;
    int x1;
    int x2;

    Interval(int x1, int y1, int x2, int y2) {
      this.y1 = y1;
      this.y2 = y2;
      this.x1 = x1;
      this.x2 = x2;
    }

    @Override
    public String toString() {
      return "(" + x1 + ", " + y1 + "), (" + x2 + ", " + y2 + ")";
    }
  }
}
