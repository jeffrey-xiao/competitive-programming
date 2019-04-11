package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Alex_And_Animal_Rights {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int r, c;
  static char[][] g;

  static int[] movex = {-1, 1, 0, 0};
  static int[] movey = {0, 0, -1, 1};

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    r = readInt();
    c = readInt();
    g = new char[r][c];
    for (int i = 0; i < r; i++)
      g[i] = next().toCharArray();

    int cnt = 0;
    for (int i = 0; i < r; i++)
      for (int j = 0; j < c; j++)
        if (g[i][j] != '-' && dfs(i, j))
          cnt++;

    out.println(cnt);
    out.close();
  }

  static boolean dfs(int i, int j) {
    boolean res = false;
    if (g[i][j] == 'M')
      res = true;
    g[i][j] = '-';
    for (int k = 0; k < 4; k++) {
      int nx = movex[k] + i;
      int ny = movey[k] + j;
      if (nx < 0 || nx >= r || ny < 0 || ny >= c || g[nx][ny] == '-' || g[nx][ny] == '#')
        continue;
      res |= dfs(nx, ny);
    }
    return res;
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
}
