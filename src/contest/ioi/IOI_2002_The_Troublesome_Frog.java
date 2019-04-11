package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class IOI_2002_The_Troublesome_Frog {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // pr = new PrintWriter(new FileWriter("out.txt"));

    int r = readInt();
    int c = readInt();
    boolean[][] xy = new boolean[c + 1][r + 1];
    int n = readInt();
    int[] x = new int[n];
    int[] y = new int[n];
    for (int i = 0; i < n; i++) {
      y[i] = readInt();
      x[i] = readInt();
      xy[x[i]][y[i]] = true;
    }
    int ans = 2;
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        int lx = x[i];
        int rx = x[j];
        int ly = y[i];
        int ry = y[j];
        int curr = 2;
        if (lx > rx || (lx == rx && ly > ry)) {
          int t = lx;
          lx = rx;
          rx = t;
          t = ly;
          ly = ry;
          ry = t;
        }
        int dx = rx - lx;
        int dy = ry - ly;
        while (rx + dx <= c && ry + dy <= r && ry + dy >= 1 && xy[rx + dx][ry + dy]) {
          rx += dx;
          ry += dy;
          curr++;
        }
        while (lx - dx >= 1 && ly - dy <= r && ly - dy >= 1 && xy[lx - dx][ly - dy]) {
          lx -= dx;
          ly -= dy;
          curr++;
        }
        if ((lx - dx < 1 || (ly - dy < 1 || ly - dy > r)) && (rx + dx > c || (ry + dy < 1 || ry + dy > r)))
          ans = Math.max(ans, curr);
      }
    }
    pr.println(ans == 2 ? 0 : ans);
    pr.close();
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

  static class State {
    Integer dx, dy;
    Double inter;

    State(int dx, int dy, int x, int y) {
      this.dx = dx;
      this.dy = dy;
      inter = y - (double) (dy) / dx * x;
    }

    public boolean equals(Object o) {
      if (o instanceof State) {
        State s = (State) o;
        return dx == s.dx && dy == s.dy && inter == s.inter;
      }
      return false;
    }

    public int hashCode() {
      return dx.hashCode() * 31 + dy.hashCode();
    }
  }
}
