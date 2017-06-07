package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2008_CIJEVI {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  static int r, c;
  static char[][] g;

  public static void main (String[] args) throws IOException {
    r = readInt();
    c = readInt();
    g = new char[r][c];
    for (int i = 0; i < r; i++)
      g[i] = next().toCharArray();
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        if (g[i][j] != '.')
          continue;
        if (up(i, j) && left(i, j) && down(i, j) && right(i, j))
          System.out.printf("%d %d +\n", i + 1, j + 1);
        else if (up(i, j) && down(i, j))
          System.out.printf("%d %d |\n", i + 1, j + 1);
        else if (left(i, j) && right(i, j))
          System.out.printf("%d %d -\n", i + 1, j + 1);
        else if (down(i, j) && right(i, j))
          System.out.printf("%d %d 1\n", i + 1, j + 1);
        else if (up(i, j) && right(i, j))
          System.out.printf("%d %d 2\n", i + 1, j + 1);
        else if (up(i, j) && left(i, j))
          System.out.printf("%d %d 3\n", i + 1, j + 1);
        else if (down(i, j) && left(i, j))
          System.out.printf("%d %d 4\n", i + 1, j + 1);
      }
    }
  }

  static boolean up (int x, int y) {
    return x - 1 >= 0 && (g[x - 1][y] == '|' || g[x - 1][y] == '+' || g[x - 1][y] == '1' || g[x - 1][y] == '4');
  }

  static boolean down (int x, int y) {
    return x + 1 < r && (g[x + 1][y] == '|' || g[x + 1][y] == '+' || g[x + 1][y] == '2' || g[x + 1][y] == '3');
  }

  static boolean left (int x, int y) {
    return y - 1 >= 0 && (g[x][y - 1] == '-' || g[x][y - 1] == '+' || g[x][y - 1] == '1' || g[x][y - 1] == '2');
  }

  static boolean right (int x, int y) {
    return y + 1 < c && (g[x][y + 1] == '-' || g[x][y + 1] == '+' || g[x][y + 1] == '3' || g[x][y + 1] == '4');
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
