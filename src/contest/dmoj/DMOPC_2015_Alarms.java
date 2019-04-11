package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DMOPC_2015_Alarms {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int n, m;
  static int[][] g;
  static int[] a;

  static int best = 0;
  static boolean[] rowUsed, colUsed;
  static int[][] covered;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    g = new int[n + 1][n + 1];
    covered = new int[n + 1][n + 1];
    rowUsed = new boolean[n + 1];
    colUsed = new boolean[n + 1];

    for (int i = 1; i <= n; i++)
      for (int j = 1; j <= n; j++)
        g[i][j] = readInt();

    int m = readInt();
    a = new int[m + 1];
    for (int i = 1; i <= m; i++)
      a[i] = readInt();

    solve(m, 0);
    out.println(best);
    out.close();
  }

  static void solve(int i, int curr) {
    best = Math.max(best, curr);
    if (i == 0)
      return;
    int max = 0;
    for (int j = 1; j <= i; j++) {
      max += (a[j] * 2 - 1) * (a[j] * 2 - 1);
    }
    if (max + curr <= best)
      return;
    int radius = a[i];
    for (int j = radius; j <= n - radius + 1; j++) {
      for (int k = radius; k <= n - radius + 1; k++) {
        if (rowUsed[j] || colUsed[k] || g[j][k] == 0)
          continue;
        rowUsed[j] = colUsed[k] = true;
        int sum = 0;
        for (int x = j - (radius - 1); x <= j + (radius - 1); x++) {
          for (int y = k - (radius - 1); y <= k + (radius - 1); y++) {
            if (covered[x][y] == 0)
              sum += g[x][y];
            covered[x][y]++;
          }
        }
        solve(i - 1, curr + sum);
        for (int x = j - (radius - 1); x <= j + (radius - 1); x++) {
          for (int y = k - (radius - 1); y <= k + (radius - 1); y++) {
            covered[x][y]--;
          }
        }
        rowUsed[j] = colUsed[k] = false;
      }
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
