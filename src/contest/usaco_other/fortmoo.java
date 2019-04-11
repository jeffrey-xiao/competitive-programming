package contest.usaco_other;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/*
ID: jeffrey40
LANG: JAVA
TASK: fortmoo
*/
import java.util.StringTokenizer;

public class fortmoo {
  static final int SIZE = 202;
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;
  static int n, m;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new FileReader("fortmoo.in"));
    out = new PrintWriter(new FileWriter("fortmoo.out"));
    //br = new BufferedReader(new InputStreamReader(System.in));
    //out = new PrintWriter(new OutputStreamWriter(System.out));

    n = readInt();
    m = readInt();

    int[][] left = new int[SIZE][SIZE];
    int[][] down = new int[SIZE][SIZE];
    int[][] right = new int[SIZE][SIZE];
    int[][] up = new int[SIZE][SIZE];
    int[][] grid = new int[SIZE][SIZE];

    for (int i = 1; i <= n; i++) {
      String in = (" " + readLine());
      for (int j = 1; j <= m; j++) {
        grid[i][j] = in.charAt(j) == '.' ? 1 : 0;
        if (grid[i][j] == 1) {
          left[i][j] = 1 + left[i][j - 1];
          up[i][j] = 1 + up[i - 1][j];
        } else {
          left[i][j] = up[i][j] = 0;
        }
      }
    }

    for (int i = n; i >= 1; i--) {
      for (int j = m; j >= 1; j--) {
        if (grid[i][j] == 1) {
          right[i][j] = 1 + right[i][j + 1];
          down[i][j] = 1 + down[i + 1][j];
        } else {
          right[i][j] = down[i][j] = 0;
        }
      }
    }

    int ans = 0;
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        for (int k = 1; k <= up[i][j]; k++) {
          for (int l = Math.max(1, ans / k); l <= left[i][j]; l++) {
            if (left[i - k + 1][j] >= l && up[i][j - l + 1] >= k) {
              ans = Math.max(ans, k * l);
            }
          }
        }
      }
    }

    out.println(ans);
    out.close();
    System.exit(0);
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
