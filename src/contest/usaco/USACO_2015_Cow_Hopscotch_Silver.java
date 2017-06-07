package contest.usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class USACO_2015_Cow_Hopscotch_Silver {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  static final int MOD = 1000000007;

  public static void main (String[] args) throws IOException {
    int r = readInt();
    int c = readInt();
    int k = readInt();

    int[][][] prefix = new int[r + 1][c + 1][k];
    int[][] g = new int[r + 1][c + 1];
    for (int i = 1; i <= r; i++)
      for (int j = 1; j <= c; j++)
        g[i][j] = readInt() - 1;
    prefix[1][1][g[1][1]] = 1;
    for (int i = 1; i <= r; i++) {
      for (int j = 1; j <= c; j++) {
        if (i == 1 && j == 1)
          continue;
        int sum = 0;
        int id = g[i][j];
        for (int x = 0; x < k; x++) {
          prefix[i][j][x] = (((prefix[i][j][x] + prefix[i - 1][j][x]) % MOD + prefix[i][j - 1][x]) % MOD - prefix[i - 1][j - 1][x]) % MOD;
          if (x == id)
            continue;
          sum = (sum + prefix[i - 1][j - 1][x]) % MOD;
        }
        prefix[i][j][id] = (prefix[i][j][id] + sum) % MOD;
      }
    }
    System.out.println(((((prefix[r][c][g[r][c]] - prefix[r - 1][c][g[r][c]]) % MOD - prefix[r][c - 1][g[r][c]]) % MOD + prefix[r - 1][c - 1][g[r][c]]) % MOD + MOD) % MOD);
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
