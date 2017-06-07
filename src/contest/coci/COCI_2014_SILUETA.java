package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2014_SILUETA {

  static BufferedReader br;
  static PrintWriter ps;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    // br = new BufferedReader(new FileReader("in.txt"));
    // ps = new PrintWriter("out.txt");

    int n = readInt();
    int[] l = new int[n], r = new int[n], h = new int[n];
    char[][] grid = new char[1001][1002];
    for (int i = 0; i < 1000; i++)
      for (int j = 0; j < 1001; j++)
        grid[i][j] = '.';
    int min = 1 << 30;
    int max = 0;
    for (int i = 0; i < n; i++) {
      l[i] = readInt();
      r[i] = readInt() - 1;
      h[i] = readInt();
      min = Math.min(min, l[i]);
      max = Math.max(max, r[i]);
    }
    int[] maxh = new int[1001];
    int ans = 0;
    int maxheight = 0;
    for (int i = 1; i <= 1000; i++) {

      for (int j = 0; j < n; j++)
        if (l[j] <= i && i <= r[j])
          maxh[i] = Math.max(maxh[i], h[j]);
      ans += Math.abs(maxh[i] - maxh[i - 1]);
      maxheight = Math.max(maxheight, maxh[i]);
      if (maxh[i] != 0)
        ans++;
      for (int j = Math.min(maxh[i], maxh[i - 1]); j <= Math.max(maxh[i], maxh[i - 1]); j++) {
        if (maxh[i] >= maxh[i - 1])
          grid[i][j] = '#';
        else
          grid[i - 1][j] = '#';
      }
      grid[i][maxh[i]] = '#';
      grid[i][0] = grid[i - 1][0] = '*';
    }
    System.out.println(ans);
    for (int i = maxheight; i >= 0; i--) {
      for (int j = min; j <= max; j++)
        ps.print(grid[j][i]);
      ps.println();
    }
    ps.close();
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