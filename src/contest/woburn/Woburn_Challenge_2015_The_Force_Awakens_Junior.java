package contest.woburn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Woburn_Challenge_2015_The_Force_Awakens_Junior {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, M;
  static char[][] grid;
  static boolean[][][] v;
  static boolean[][] cycle;
  static int[][] last;
  static int ans, cnt;

  static int[] dx = {-1, 1, 0, 0};
  static int[] dy = {0, 0, -1, 1};

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    M = readInt();

    grid = new char[N + 2][M + 2];
    v = new boolean[N + 2][M + 2][4];
    cycle = new boolean[N + 2][M + 2];
    last = new int[N + 2][M + 2];

    for (int i = 0; i <= N + 1; i++)
      for (int j = 0; j <= M + 1; j++)
        grid[i][j] = '#';

    for (int i = 1; i <= N; i++) {
      char[] in = next().toCharArray();
      for (int j = 1; j <= M; j++) {
        grid[i][j] = in[j - 1];
      }
    }

    for (int i = 0; i <= N + 1; i++) {
      for (int j = 0; j <= M + 1; j++) {
        if (grid[i][j] == '#') {
          for (int k = 0; k < 4; k++) {
            int nx = dx[k] + i;
            int ny = dy[k] + j;
            if (nx < 0 || nx > N + 1 || ny < 0 || ny >= M + 1 || grid[nx][ny] == '#')
              continue;
            cnt++;
            compute(i, j, dx[k], dy[k]);
          }
        }
      }
    }

    for (int i = 0; i <= N + 1; i++)
      for (int j = 0; j <= M + 1; j++)
        if (grid[i][j] == '.' && (cycle[i][j] || !(v[i][j][0] && v[i][j][1] && v[i][j][2] && v[i][j][3])))
          ans++;
    out.println(ans);
    out.close();
  }

  static void compute (int x, int y, int dx, int dy) {
    while (grid[x += dx][y += dy] != '#') {
      if (grid[x][y] == '.') {
        if (cnt == last[x][y])
          cycle[x][y] = true;
        last[x][y] = cnt;
      }
      int dir = Math.abs(dx + (dy + 1) * 2 - 1);
      if (v[x][y][dir])
        return;

      v[x][y][dir] = true;

      if (grid[x][y] == 'X') {
        dx = -dx;
        dy = -dy;
      } else if (grid[x][y] == '\\') {
        int ndx = dy;
        int ndy = dx;
        dx = ndx;
        dy = ndy;
      } else if (grid[x][y] == '/') {
        int ndx = -dy;
        int ndy = -dx;
        dx = ndx;
        dy = ndy;
      }
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

  static char readCharacter () throws IOException {
    return next().charAt(0);
  }

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
