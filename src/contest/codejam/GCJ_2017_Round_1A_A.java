package contest.codejam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class GCJ_2017_Round_1A_A {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T, R, C;
  static char[][] grid;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();

    for (int t = 1; t <= T; t++) {
      R = readInt();
      C = readInt();

      grid = new char[R][C];

      for (int i = 0; i < R; i++)
        grid[i] = next().toCharArray();

      for (int i = 0; i < R; i++) {
        for (int j = 0; j < C - 1; j++)
          if (grid[i][j] != '?' && grid[i][j + 1] == '?')
            grid[i][j + 1] = grid[i][j];
        for (int j = C - 1; j > 0; j--)
          if (grid[i][j] != '?' & grid[i][j - 1] == '?')
            grid[i][j - 1] = grid[i][j];
      }
      for (int i = 0; i < R - 1; i++)
        if (grid[i][0] != '?' && grid[i + 1][0] == '?')
          for (int j = 0; j < C; j++)
            grid[i + 1][j] = grid[i][j];
      for (int i = R - 1; i > 0; i--)
        if (grid[i][0] != '?' && grid[i - 1][0] == '?')
          for (int j = 0; j < C; j++)
            grid[i - 1][j] = grid[i][j];

      out.printf("Case #%d:\n", t);
      for (int i = 0; i < R; i++, out.println())
        for (int j = 0; j < C; j++)
          out.print(grid[i][j]);
    }

    out.close();
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
