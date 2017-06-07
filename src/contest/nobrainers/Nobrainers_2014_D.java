package contest.nobrainers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Nobrainers_2014_D {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static byte[] movex = {-1, 0, 1, 0};
  static byte[] movey = {0, 1, 0, -1};
  static byte[] change1 = {3, 2, 1, 0};
  static byte[] change2 = {1, 0, 3, 2};

  public static void main (String[] args) throws IOException {
    int r = readInt();
    int c = readInt();
    char[][] grid = new char[r][];
    for (int x = 0; x < r; x++)
      grid[x] = next().toCharArray();
    byte direction = 1;
    byte x = 0;
    byte y = 0;
    int second = 1;
    while (0 <= x && x < r && 0 <= y && y < c) {
      if (grid[x][y] != '.') {
        if (second % 2 == 0) {
          if (grid[x][y] == '/')
            direction = change1[direction];
          else
            direction = change2[direction];
        } else {
          if (grid[x][y] == '/')
            direction = change2[direction];
          else
            direction = change1[direction];
        }
      }
      x += movex[direction];
      y += movey[direction];
      second++;
    }
    System.out.println(second - 1);
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
