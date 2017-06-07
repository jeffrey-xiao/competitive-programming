package contest.woburn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Woburn_Challenge_2001_The_Rock {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int[] movex = {1, 1, 1, 0, 0, -1, -1, -1};
  static int[] movey = {-1, 0, 1, -1, 1, -1, 0, 1};

  public static void main (String[] args) throws IOException {
    int r = readInt();
    int n = readInt();
    char[][] grid = new char[r][];
    for (int x = 0; x < r; x++)
      grid[x] = next().toCharArray();
    char[][] dir = new char[r][];
    for (int x = 0; x < r; x++)
      dir[x] = next().toCharArray();
    for (int z = 0; z < n; z++) {
      int[][] steps = new int[r][r];
      int curr = 1;
      int x = readInt() - 1;
      int y = readInt() - 1;
      String s = "";
      int repeat = -1;
      while (x >= 0 && x < r && y >= 0 && y < r) {
        if (steps[x][y] != 0) {
          repeat = steps[x][y];
          break;
        }
        steps[x][y] = curr;
        int next = dir[x][y] - 49;
        if (next >= 4)
          next--;
        s += grid[x][y];
        x += movex[next];
        y += movey[next];
        curr++;
      }
      if (repeat == -1)
        System.out.println(s);
      else {
        if (repeat - 1 == 0)
          System.out.printf("%s repeated\n", s.substring(repeat - 1));
        else
          System.out.printf("%s then %s repeated\n", s.substring(0, repeat - 1), s.substring(repeat - 1));
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

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
