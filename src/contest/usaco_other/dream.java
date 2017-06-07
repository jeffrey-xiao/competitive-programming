package contest.usaco_other;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/*
ID: jeffrey40
LANG: JAVA
TASK: dream
*/
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class dream {
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int[] mover = {0, 0, 1, -1};
  static int[] movec = {-1, 1, 0, 0};

  static int n, m;
  static int[][][] min;
  static int[][] g;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new FileReader("dream.in"));
    out = new PrintWriter(new FileWriter("dream.out"));
    //br = new BufferedReader(new InputStreamReader(System.in));
    //out = new PrintWriter(new OutputStreamWriter(System.out));

    n = readInt();
    m = readInt();

    min = new int[n][m][2];
    g = new int[n][m];

    for (int i = 0; i < n; i++)
      for (int j = 0; j < m; j++) {
        g[i][j] = readInt();
        min[i][j][0] = min[i][j][1] = 1 << 30;
      }
    Queue<State> q = new ArrayDeque<State>();
    min[0][0][0] = 0;
    q.offer(new State(0, 0, 0, 0));
    while (!q.isEmpty()) {
      State curr = q.poll();
      for (int i = 0; i < 4; i++) {
        int newr = curr.r + mover[i];
        int newc = curr.c + movec[i];
        int neworange = curr.orange;
        int moves = curr.moves + 1;
        if (!valid(newr, newc, neworange))
          continue;
        if (g[newr][newc] == 0 || (g[newr][newc] == 3 && curr.orange == 0))
          continue;
        while (g[newr][newc] == 4 && valid(newr + mover[i], newc + movec[i], neworange = 0)) {
          newr += mover[i];
          newc += movec[i];
          moves++;
        }
        if (g[newr][newc] == 2)
          neworange = 1;
        if (min[newr][newc][neworange] <= moves)
          continue;
        min[newr][newc][neworange] = moves;
        q.offer(new State(newr, newc, neworange, moves));
      }
    }
    int ans = Math.min(min[n - 1][m - 1][0], min[n - 1][m - 1][1]);
    out.println(ans == 1 << 30 ? -1 : ans);
    out.close();
    System.exit(0);
  }

  static boolean valid (int r, int c, int orange) {
    return 0 <= r && r < n && 0 <= c && c < m && g[r][c] != 0 && !(g[r][c] == 3 && orange == 0);
  }

  static class State {
    int r, c, orange, moves;

    State (int r, int c, int orange, int moves) {
      this.r = r;
      this.c = c;
      this.orange = orange;
      this.moves = moves;
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
