package contest.woburn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Woburn_Challenge_2015_LexCorp_Infiltration {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int R, C, N;
  static int[][] grid;
  static int[] bit = new int[1000001];
  static int rooms = 0;
  static boolean[] gassed = new boolean[1000000];
  static int[] size = new int[1000000];
  static int[] dr = {-1, 1, 0, 0};
  static int[] dc = {0, 0, -1, 1};

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    R = readInt();
    C = readInt();
    N = readInt();

    grid = new int[R][C];
    for (int i = 0; i < R; i++) {
      char[] in = next().toCharArray();
      for (int j = 0; j < C; j++) {
        if (in[j] == '#')
          grid[i][j] = -1;
        else
          grid[i][j] = 0;
      }
    }

    for (int i = 0; i < R; i++)
      for (int j = 0; j < C; j++) {
        if (grid[i][j] == 0) {
          rooms++;
          size[rooms] = bfs(i, j);
          update(size[rooms], 1);
        }
      }
    for (int i = 0; i < N; i++) {
      int t = readInt();
      int a = readInt() - 1;
      int b = readInt() - 1;

      int index = grid[a][b];
      if (gassed[index])
        out.println("-1");
      else
        out.println(rooms - query(size[index]));
      if (t == 1) {
      } else {
        if (!gassed[index]) {
          gassed[index] = true;
          update(size[index], -1);
          rooms--;
        }
      }
    }
    out.close();
  }

  static void update(int x, int val) {
    for (int i = x; i <= 1000000; i += (i & -i)) {
      bit[i] += val;
    }
  }

  static int query(int x) {
    int sum = 0;
    for (int i = x; i > 0; i -= (i & -i))
      sum += bit[i];
    return sum;
  }

  static int bfs(int r, int c) {
    Queue<Point> q = new ArrayDeque<Point>();
    q.offer(new Point(r, c));
    int size = 1;
    grid[r][c] = rooms;
    while (!q.isEmpty()) {
      Point curr = q.poll();
      for (int i = 0; i < 4; i++) {
        int nr = dr[i] + curr.r;
        int nc = dc[i] + curr.c;
        if (nr < 0 || nr >= R || nc < 0 || nc >= C || grid[nr][nc] != 0)
          continue;
        grid[nr][nc] = rooms;
        size++;
        q.offer(new Point(nr, nc));
      }
    }
    return size;
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
