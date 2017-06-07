package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class USACO_2012_Grazing_Patterns {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static boolean[][] visited = new boolean[5][5];
  static int numVisited = 0;

  public static void main (String[] args) throws IOException {
    numVisited = readInt();
    for (int x = 0; x < numVisited; x++)
      visited[readInt() - 1][readInt() - 1] = true;
    System.out.println(compute(0, 0));
  }

  private static int compute (int x, int y) {
    if (x < 0 || y < 0 || x >= 5 || y >= 5 || visited[x][y])
      return 0;
    visited[x][y] = true;
    numVisited++;
    int total = 0;
    if (numVisited == 25 && x == 4 && y == 4)
      total++;
    else
      total = compute(x - 1, y) + compute(x + 1, y) + compute(x, y - 1) + compute(x, y + 1);
    visited[x][y] = false;
    numVisited--;
    return total;
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
