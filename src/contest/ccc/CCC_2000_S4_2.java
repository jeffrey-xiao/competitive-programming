package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2000_S4_2 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    int d = readInt();
    int n = readInt();
    int[] c = new int[n + 1];
    for (int x = 1; x <= n; x++)
      c[x] = readInt();
    int ans = knapSack(d, c, n);
    if (ans != 1000)
      System.out.printf("Roberta wins in %d %s.", ans, (ans == 1 ? "stroke" : "strokes"));
    else
      System.out.println("Roberta acknowledges defeat.");
  }

  static String next () throws IOException {
    while (st == null || !st.hasMoreTokens())
      st = new StringTokenizer(br.readLine().trim());
    return st.nextToken();
  }

  private static int knapSack (int W, int c[], int n) {
    // W = capacity of backpack; wt = weights of items; val = values of
    // items; n = # of items
    int[][] matrix = new int[n + 1][W + 1]; // build a matrix n+1 by W+1
    // everything uses one-based indexing
    for (int x = 0; x <= n; x++)
      for (int y = 1; y <= W; y++)
        matrix[x][y] = 1000;
    for (int i = 1; i <= n; i++) {
      for (int w = 0; w <= W; w++) {
        matrix[i][w] = Math.min(matrix[i][w], matrix[i - 1][w]);
        if (c[i] + w <= W)
          matrix[i][w + c[i]] = Math.min(1 + matrix[i][w], matrix[i][w + c[i]]);
      }
    }
    return matrix[n][W];
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
