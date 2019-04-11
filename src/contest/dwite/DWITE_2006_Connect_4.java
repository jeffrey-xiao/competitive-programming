package contest.dwite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DWITE_2006_Connect_4 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int[] movex = {-1, -1, -1, 0, 0, 1, 1, 1};
  static int[] movey = {-1, 0, 1, -1, 1, -1, 0, 1};

  public static void main(String[] args) throws IOException {
    main:
    for (int t = 0; t < 5; t++) {
      int[] index = new int[7];
      char[][] grid = new char[6][7];
      for (int x1 = 0; x1 < 6; x1++) {
        for (int y1 = 0; y1 < 7; y1++) {
          grid[x1][y1] = ' ';
        }
      }
      String s = next();
      boolean red = true;
      for (int x = 0; x < s.length(); x++) {
        int column = s.charAt(x) - '1';
        int row = index[column]++;
        grid[row][column] = red ? 'R' : 'B';

        for (int x1 = 0; x1 < 6; x1++) {
          for (int y1 = 0; y1 < 7; y1++) {
            for (int z = 0; z < 4; z++) {
              if (check(x1, y1, movex[z], movey[z], 3, grid[row][column], grid)) {
                System.out.println((red ? "RED" : "BLUE") + "-" + (x + 1));
                continue main;
              }
            }
          }
        }
        red = !red;
      }
    }
  }

  static boolean check(int x, int y, int dx, int dy, int moves, char c, char[][] grid) {
    if (x < 0 || x >= 6 || y < 0 || y >= 7 || grid[x][y] != c)
      return false;
    if (moves == 0)
      return true;
    return check(x + dx, y + dy, dx, dy, moves - 1, c, grid);
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

  static String readLine() throws IOException {
    return br.readLine().trim();
  }
}
