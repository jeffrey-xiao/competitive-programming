package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class VMSS_Tomb_Robbing {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int r, c;
  static char[][] g;
  static boolean[][] v;

  static int[] mover = {0, 0, -1, 1};
  static int[] movec = {-1, 1, 0, 0};

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    r = readInt();
    c = readInt();

    g = new char[r][c];
    v = new boolean[r][c];

    for (int i = 0; i < r; i++)
      g[i] = readLine().toCharArray();

    int cnt = 0;
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        if (!v[i][j] && g[i][j] == '.') {
          cnt++;
          dfs(i, j);
        }

      }
    }
    out.println(cnt);
    out.close();
  }

  static void dfs(int i, int j) {
    v[i][j] = true;
    for (int k = 0; k < 4; k++) {
      int newi = i + mover[k];
      int newj = j + movec[k];

      if (newi < 0 || newi >= r || newj < 0 || newj >= c || v[newi][newj] || g[newi][newj] == 'X')
        continue;
      dfs(newi, newj);
    }
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
