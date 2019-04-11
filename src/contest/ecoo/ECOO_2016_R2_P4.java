package contest.ecoo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class ECOO_2016_R2_P4 {

  static final int TEST_CASES = 10;
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;
  static int[] BR = {0, 0, -1, 1};
  static int[] BC = {-1, 1, 0, 0};

  static int[] HR = {-1, -1, -1};
  static int[] HC = {-1, 0, 1};

  static int R, C;
  static char[][] g;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));

    // br = new BufferedReader(new FileReader("in.txt"));
    // br = new BufferedReader(new FileReader("DATA42.txt"));

    for (int T = 1; T <= TEST_CASES; T++) {
      C = readInt();
      R = readInt();
      g = new char[R][C];

      Point p1 = null, p2 = null, p3 = null;
      Point c = null, t = null, f = null;
      for (int i = 0; i < R; i++) {
        g[i] = readLine().toCharArray();
        for (int j = 0; j < C; j++) {
          if (g[i][j] == 'h') {
            p1 = new Point(i, j, 0);
            g[i][j] = '.';
          } else if (g[i][j] == 's') {
            p2 = new Point(i, j, 1);
            g[i][j] = '.';
          } else if (g[i][j] == 'j') {
            p3 = new Point(i, j, 2);
            g[i][j] = '.';
          } else if (g[i][j] == 'C') {
            c = new Point(i, j, 0);
            g[i][j] = '.';
          } else if (g[i][j] == 'T') {
            t = new Point(i, j, 0);
            g[i][j] = '.';
          } else if (g[i][j] == 'F') {
            f = new Point(i, j, 0);
            g[i][j] = '.';
          }
        }
      }

      boolean[][][] vis = new boolean[3][R][C];
      vis[0][p1.r][p1.c] = true;
      vis[1][p2.r][p2.c] = true;
      vis[2][p3.r][p3.c] = true;

      Queue<Point> q = new ArrayDeque<Point>();

      q.offer(p1);
      q.offer(p2);
      q.offer(p3);

      while (!q.isEmpty()) {
        Point curr = q.poll();

        while (curr.r + 1 < R && g[curr.r + 1][curr.c] == '.' && g[curr.r][curr.c] != '#') {
          curr.r++;
          vis[curr.type][curr.r][curr.c] = true;
        }

        for (int k = 0; k < 4; k++) {
          int nr = curr.r + BR[k];
          int nc = curr.c + BC[k];
          if (nr < 0 || nr >= R || nc < 0 || nc >= C || vis[curr.type][nr][nc] || g[nr][nc] == '=')
            continue;
          if (k == 2 && g[curr.r][curr.c] != '#')
            continue;
          if (k == 3 && g[nr][nc] != '#')
            continue;

          while (nr + 1 < R && g[nr + 1][nc] == '.' && g[nr][nc] != '#') {
            vis[curr.type][nr][nc] = true;
            nr++;
          }

          if (vis[curr.type][nr][nc])
            continue;

          vis[curr.type][nr][nc] = true;

          if (nr + 1 >= R && g[nr][nc] == '.')
            continue;

          q.offer(new Point(nr, nc, curr.type));
        }

        if (curr.type == 0 || curr.type == 2) {
          if (isValid(curr.r - 1, curr.c)) {
            for (int k = 0; k < 3; k++) {
              int nr = curr.r + HR[k];
              int nc = curr.c + HC[k];

              if (!isValid(nr, nc))
                continue;

              while (nr + 1 < R && g[nr + 1][nc] == '.' && g[nr][nc] != '#') {
                vis[curr.type][nr][nc] = true;
                nr++;
              }

              if (vis[curr.type][nr][nc])
                continue;

              vis[curr.type][nr][nc] = true;

              if (nr + 1 >= R && g[nr][nc] == '.')
                continue;

              q.offer(new Point(nr, nc, curr.type));

            }
          }
        }

        if (curr.type == 1 || curr.type == 2) {
          if (isValid(curr.r, curr.c - 1)) {
            int nr = curr.r;
            int nc = curr.c - 2;

            if (isValid(nr, nc)) {

              while (nr + 1 < R && g[nr + 1][nc] == '.' && g[nr][nc] != '#') {
                vis[curr.type][nr][nc] = true;
                nr++;
              }

              if (!vis[curr.type][nr][nc]) {
                vis[curr.type][nr][nc] = true;

                if (nr + 1 < R || g[nr][nc] != '.') {
                  q.offer(new Point(nr, nc, curr.type));
                }
              }
            }
          }

          if (isValid(curr.r, curr.c + 1)) {
            int nr = curr.r;
            int nc = curr.c + 2;

            if (isValid(nr, nc)) {

              while (nr + 1 < R && g[nr + 1][nc] == '.' && g[nr][nc] != '#') {
                vis[curr.type][nr][nc] = true;
                nr++;
              }

              if (!vis[curr.type][nr][nc]) {
                vis[curr.type][nr][nc] = true;

                if (nr + 1 < R || g[nr][nc] != '.') {
                  q.offer(new Point(nr, nc, curr.type));
                }
              }
            }
          }
        }

        if (curr.type == 2) {
          if (isValid(curr.r, curr.c - 1) && isValid(curr.r, curr.c - 2) && isValid(curr.r, curr.c - 3)) {
            int nr = curr.r;
            int nc = curr.c - 3;

            while (nr + 1 < R && g[nr + 1][nc] == '.' && g[nr][nc] != '#') {
              vis[curr.type][nr][nc] = true;
              nr++;
            }

            if (!vis[curr.type][nr][nc]) {
              vis[curr.type][nr][nc] = true;
              if (nr + 1 < R || g[nr][nc] != '.') {
                q.offer(new Point(nr, nc, curr.type));
              }
            }
          }

          if (isValid(curr.r, curr.c + 1) && isValid(curr.r, curr.c + 2) && isValid(curr.r, curr.c + 3)) {
            int nr = curr.r;
            int nc = curr.c + 3;

            while (nr + 1 < R && g[nr + 1][nc] == '.' && g[nr][nc] != '#') {
              vis[curr.type][nr][nc] = true;
              nr++;
            }

            if (!vis[curr.type][nr][nc]) {
              vis[curr.type][nr][nc] = true;

              if (nr + 1 < R || g[nr][nc] != '.') {
                q.offer(new Point(nr, nc, curr.type));
              }
            }
          }

          if (isValid(curr.r - 1, curr.c) && isValid(curr.r - 1, curr.c + 1) && isValid(curr.r - 1, curr.c + 2)) {
            int nr = curr.r - 1;
            int nc = curr.c + 2;

            while (nr + 1 < R && g[nr + 1][nc] == '.' && g[nr][nc] != '#') {
              vis[curr.type][nr][nc] = true;
              nr++;
            }

            if (!vis[curr.type][nr][nc]) {
              vis[curr.type][nr][nc] = true;

              if (nr + 1 < R || g[nr][nc] != '.') {
                q.offer(new Point(nr, nc, curr.type));
              }
            }
          }

          if (isValid(curr.r - 1, curr.c) && isValid(curr.r - 1, curr.c - 1) && isValid(curr.r - 1, curr.c - 2)) {
            int nr = curr.r - 1;
            int nc = curr.c - 2;

            while (nr + 1 < R && g[nr + 1][nc] == '.' && g[nr][nc] != '#') {
              vis[curr.type][nr][nc] = true;
              nr++;
            }

            if (!vis[curr.type][nr][nc]) {
              vis[curr.type][nr][nc] = true;

              if (nr + 1 < R || g[nr][nc] != '.') {
                q.offer(new Point(nr, nc, curr.type));
              }
            }
          }

          if (isValid(curr.r - 1, curr.c) && isValid(curr.r - 2, curr.c)) {
            int nr = curr.r - 2;
            int nc = curr.c;

            while (nr + 1 < R && g[nr + 1][nc] == '.' && g[nr][nc] != '#') {
              vis[curr.type][nr][nc] = true;
              nr++;
            }

            if (!vis[curr.type][nr][nc]) {
              vis[curr.type][nr][nc] = true;

              if (nr + 1 < R || g[nr][nc] != '.') {
                q.offer(new Point(nr, nc, curr.type));
              }
            }
          }
        }
      }

      for (int i = 0; i < 3; i++) {
        if (i == 0)
          out.print("HOP ");
        else if (i == 1)
          out.print("SKIP ");
        else if (i == 2)
          out.print("JUMP ");
        if (vis[i][c.r][c.c])
          out.print("C");
        if (vis[i][f.r][f.c])
          out.print("F");
        if (vis[i][t.r][t.c])
          out.print("T");
        out.println();
      }
      out.println();

    }

    out.close();
  }

  static boolean isValid(int r, int c) {
    if (r < 0 || c < 0 || r >= R || c >= C)
      return false;
    if (g[r][c] == '=')
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

  static char readCharacter() throws IOException {
    return next().charAt(0);
  }

  static String readLine() throws IOException {
    return br.readLine().trim();
  }

  static class Point {
    int r, c, type;

    Point(int r, int c, int type) {
      this.r = r;
      this.c = c;
      this.type = type;
    }
  }
}
