package contest.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CCPC_2017_H {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int R, C;
  static int[][] height;
  static boolean[][] vis, sink;
  static int[] mover = {0, 0, -1, 1};
  static int[] movec = {-1, 1, 0, 0};

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    C = readInt();
    R = readInt();

    height = new int[R][C];
    vis = new boolean[R][C];
    sink = new boolean[R][C];

    for (int i = 0; i < R; i++)
      for (int j = 0; j < C; j++)
        height[i][j] = readInt();

    for (int i = 0; i < R; i++)
      for (int j = 0; j < C; j++)
        if (!vis[i][j])
          bfs(i, j);

    int ans = 0;

    for (int i = 0; i < R; i++)
      for (int j = 0; j < C; j++)
        ans += sink[i][j] ? 1 : 0;

    out.println(ans);
    out.close();
  }

  static void bfs(int r, int c) {
    Queue<Point> q = new ArrayDeque<Point>();

    q.offer(new Point(r, c));
    vis[r][c] = true;
    sink[r][c] = true;

    ArrayList<Point> all = new ArrayList<Point>();
    all.add(new Point(r, c));

    while (!q.isEmpty()) {
      Point curr = q.poll();
      for (int i = 0; i < 4; i++) {
        int nr = curr.r + mover[i];
        int nc = curr.c + movec[i];
        if (nr < 0 || nr >= R || nc < 0 || nc >= C)
          continue;
        if (height[nr][nc] < height[curr.r][curr.c])
          sink[curr.r][curr.c] = false;
        if (height[curr.r][curr.c] == height[nr][nc] && !vis[nr][nc]) {
          vis[nr][nc] = true;
          sink[nr][nc] = true;
          q.offer(new Point(nr, nc));
          all.add(new Point(nr, nc));
        }
      }
    }

    boolean isSink = true;

    for (Point p : all)
      isSink &= sink[p.r][p.c];

    for (Point p : all)
      sink[p.r][p.c] = isSink;
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
  }
}
