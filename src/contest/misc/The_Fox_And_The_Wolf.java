package contest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class The_Fox_And_The_Wolf {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter pr = new PrintWriter(new OutputStreamWriter(System.out));
  static StringTokenizer st;
  static int n, m, c, b;
  static int sx1, sy1, sx2, sy2, ex1, ey1, ex2, ey2;
  static char[][] g;
  static int[] movex = {0, 0, 0, -1, 1};
  static int[] movey = {0, -1, 1, 0, 0};
  static boolean[][][][][][][] v;

  public static void main (String[] args) throws IOException {
    n = readInt();
    m = readInt();
    c = readInt();
    b = readInt();
    g = new char[n][m];
    v = new boolean[n][m][n][m][b + 1][b + 1][c + 1];
    for (int i = 0; i < n; i++) {
      g[i] = next().toCharArray();
      for (int j = 0; j < m; j++) {
        if (g[i][j] == 'f') {
          g[i][j] = '.';
          sx1 = i;
          sy1 = j;
        } else if (g[i][j] == 'F') {
          ex1 = i;
          ey1 = j;
        } else if (g[i][j] == 'w') {
          g[i][j] = '.';
          sx2 = i;
          sy2 = j;
        } else if (g[i][j] == 'W') {
          ex2 = i;
          ey2 = j;
        }
      }
    }
    Queue<State> q = new ArrayDeque<State>();
    q.offer(new State(sx1, sy1, sx2, sy2, 0, 0, 0, 0));
    v[sx1][sy1][sx2][sy2][0][0][0] = true;
    while (!q.isEmpty()) {
      State curr = q.poll();
      if (curr.x1 == ex1 && curr.y1 == ey1 && curr.x2 == ex2 && curr.y2 == ey2) {
        System.out.println(curr.time);
        return;
      }
      for (int i = 0; i < 5; i++) {
        if (i > 0 && (curr.c > 0 || curr.b1 > 0))
          continue;
        for (int j = 0; j < 5; j++) {
          if (j > 0 && (curr.c > 0 || curr.b2 > 0))
            continue;
          int nx1 = curr.x1 + movex[i];
          int ny1 = curr.y1 + movey[i];
          int nx2 = curr.x2 + movex[j];
          int ny2 = curr.y2 + movey[j];
          if (nx1 < 0 || nx1 >= n || ny1 < 0 || ny1 >= m || nx2 < 0 || nx2 >= n || ny2 < 0 || ny2 >= m || g[nx1][ny1] == 'T' || g[nx2][ny2] == 'T' || g[nx1][ny1] == 'W' || g[nx2][ny2] == 'F')
            continue;

          int nb1 = curr.b1 > 0 ? curr.b1 - 1 : 0;
          int nb2 = curr.b2 > 0 ? curr.b2 - 1 : 0;
          int nc = curr.c > 0 ? curr.c - 1 : 0;

          if (curr.b1 == 0 && g[curr.x1][curr.y1] == 'B' && g[nx1][ny1] == '.')
            nb1 = b;
          if (curr.b2 == 0 && g[curr.x2][curr.y2] == 'B' && g[nx2][ny2] == '.')
            nb2 = b;
          if (curr.c == 0 && ((nx1 == nx2 && ny1 == ny2) || (curr.x1 == nx2 && curr.y1 == ny2 && curr.x2 == nx1 && curr.y2 == ny1)))
            nc = c;
          if (v[nx1][ny1][nx2][ny2][nb1][nb2][nc])
            continue;
          v[nx1][ny1][nx2][ny2][nb1][nb2][nc] = true;
          q.offer(new State(nx1, ny1, nx2, ny2, nb1, nb2, nc, curr.time + 1));
        }
      }
    }
    System.out.println(-1);
  }

  static class State {
    int x1, y1, x2, y2, time, b1, b2, c;

    State (int x1, int y1, int x2, int y2, int b1, int b2, int c, int time) {
      this.x1 = x1;
      this.y1 = y1;
      this.x2 = x2;
      this.y2 = y2;
      this.time = time;
      this.b1 = b1;
      this.b2 = b2;
      this.c = c;
    }

  }

  static String next () throws IOException {
    while (st == null || !st.hasMoreTokens())
      st = new StringTokenizer(br.readLine().trim());
    return st.nextToken();
  }

  static long readLong () throws IOException {
    return Long.parseLong(next());
  }

  static int readInt () throws IOException {
    return Integer.parseInt(next());
  }

  static double readDouble () throws IOException {
    return Double.parseDouble(next());
  }

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}