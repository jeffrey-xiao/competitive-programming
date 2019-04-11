package contest.woburn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Woburn_Challenge_2016_Puzzle_Rooms {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T, N;

  static int[] patStart = {0, 4, 5};
  static String[] pat = {".#....", "...###", "###...", "....#."};

  static int[] ans = new int[101];
  static Point[] start = new Point[101];
  static Point[] end = new Point[101];
  static boolean[][][] ansVis = new boolean[101][4][101];
  static boolean[][] vis = new boolean[4][101];
  static int[] dc = {0, 0, -1, 1};
  static int[] dr = {-1, 1, 0, 0};

  static int[] precomputedAnswers = {-1, 3, 5, 8, 10, 13, 16, 19, 21, 24, 27, 29, 32, 35};

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();

    for (int t = 1; t <= T; t++) {
      N = readInt();

      int occ = Math.max((N - 8) / 6, 0);
      N -= occ * 6;

      if (ans[N] == 0) {
        for (int i = 0; i < 4; i++) {
          for (int j = 0; j < N; j++) {
            vis = new boolean[4][101];
            solve(new Point(i, j), new Point(i, j), 0);
          }
        }
      }

      out.println(ans[N] + 16 * occ);
      for (int i = 0; i < 4; i++) {
        for (int j = 0; j < N; j++) {
          if (i == start[N].r && j == start[N].c)
            out.print("S");
          else if (i == end[N].r && j == end[N].c)
            out.print("E");
          else if (ansVis[N][i][j])
            out.print(".");
          else
            out.print("#");
          if (j == patStart[N % 3]) {
            for (int k = 0; k < occ; k++) {
              out.print(pat[i]);
            }
          }
        }
        out.println();
      }
    }

    out.close();
  }

  static void solve(Point s, Point curr, int len) {
    vis[curr.r][curr.c] = true;
    if (ans[N] == precomputedAnswers[N])
      return;
    if (len > ans[N]) {
      ans[N] = len;
      start[N] = s;
      end[N] = curr;
      for (int i = 0; i < 4; i++)
        ansVis[N][i] = vis[i].clone();
    }

    for (int i = 0; i < 4; i++) {
      Point next = new Point(curr.r + dr[i], curr.c + dc[i]);
      if (next.r < 0 || next.r >= 4 || next.c < 0 || next.c >= N || !valid(next, curr))
        continue;
      solve(s, next, len + 1);
    }

    vis[curr.r][curr.c] = false;
  }

  static boolean valid(Point curr, Point prev) {
    if (vis[curr.r][curr.c])
      return false;
    for (int i = 0; i < 4; i++) {
      Point next = new Point(curr.r + dr[i], curr.c + dc[i]);
      if (next.r < 0 || next.r >= 4 || next.c < 0 || next.c >= N || (next.equals(prev)))
        continue;
      if (vis[next.r][next.c])
        return false;
    }
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

  static char readCharacter() throws IOException {
    return next().charAt(0);
  }

  static String readLine() throws IOException {
    return br.readLine().trim();
  }

  static class Point {
    int r, c;

    Point(int r, int c) {
      this.r = r;
      this.c = c;
    }

    public boolean equals(Object o) {
      if (o instanceof Point) {
        Point p = (Point)o;
        return r == p.r && c == p.c;
      }
      return false;
    }
  }
}
