package contest.misc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class APIO_2009_Digging_For_Oil {

  static BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;
  static int[][] tl;
  static int[][] tr;
  static int[][] bl;
  static int[][] br;
  static int k;
  static int[][] g;

  public static void main (String[] args) throws IOException {
    int r = readInt();
    int c = readInt();
    k = readInt();
    g = new int[r + 1][c + 1];
    tl = new int[r + 1][c + 1];
    tr = new int[r + 1][c + 1];
    bl = new int[r + 1][c + 1];
    br = new int[r + 1][c + 1];
    for (int x = 1; x <= r; x++)
      for (int y = 1; y <= c; y++)
        g[x][y] = readInt() + g[x - 1][y] + g[x][y - 1] - g[x - 1][y - 1];
    int max = 0;
    // Top calculation
    for (int x = k; x <= r; x++) {
      // x-k, y-k, x, y
      for (int y = k; y <= c; y++) {
        int sum = g[x][y] - g[x - k][y] - g[x][y - k] + g[x - k][y - k];
        int m = Math.max(tl[x][y - 1], tl[x - 1][y]);
        tl[x][y] = Math.max(m, sum);
      }
      // x-k,y;x, y+k
      for (int y = c - k; y >= 0; y--) {
        int sum = g[x][y + k] - g[x - k][y + k] - g[x][y] + g[x - k][y];
        int m = Math.max(tr[x][y + 1], tr[x - 1][y]);
        tr[x][y] = Math.max(m, sum);
      }
    }
    // Bottom calculation
    for (int x = r - k; x >= 0; x--) {
      // x, y-k, x+k, y
      for (int y = k; y <= c; y++) {
        int sum = g[x + k][y] - g[x][y] - g[x + k][y - k] + g[x][y - k];
        int m = Math.max(bl[x][y - 1], bl[x + 1][y]);
        bl[x][y] = Math.max(m, sum);
      }
      // x,y; x+k, y+k
      for (int y = c - k; y >= 0; y--) {
        int sum = g[x + k][y + k] - g[x][y + k] - g[x + k][y] + g[x][y];
        int m = Math.max(br[x][y + 1], br[x + 1][y]);
        br[x][y] = Math.max(m, sum);
      }
    }
    for (int x = k; x <= r - k; x++) {
      for (int y = k; y <= c - k; y++) {
        int TL = tl[x][y];
        int BL = bl[x][y];
        int TR = tr[x][y];
        int BR = br[x][y];

        int top = tl[x][c];
        int left = tl[r][y];
        int right = br[0][y];
        int bottom = br[x][0];
        max = Math.max(max, top + BL + BR);
        max = Math.max(max, left + TR + BR);
        max = Math.max(max, bottom + TL + TR);
        max = Math.max(max, right + TL + BL);
      }
    }
    // PRECOMPUTE ROWS
    int[] rows = new int[r + 1];
    int[] columns = new int[c + 1];
    for (int x = k; x <= r; x++) {
      int maxRow = 0;
      for (int z = k; z <= c; z++) {
        int middle = g[x][z] - g[x - k][z] - g[x][z - k] + g[x - k][z - k];
        maxRow = Math.max(maxRow, middle);
      }
      rows[x] = maxRow;
    }
    // PRECOMPUTE COLS
    for (int z = k; z <= c; z++) {
      int maxCol = 0;
      for (int x = k; x <= r; x++) {
        int middle = g[x][z] - g[x - k][z] - g[x][z - k] + g[x - k][z - k];
        maxCol = Math.max(maxCol, middle);
      }
      columns[z] = maxCol;
    }
    for (int x = k; x <= r - k; x++) {
      int y = x + k;
      int top = tl[x][c];
      int bottom = br[y][0];
      int middle = rows[y];
      max = Math.max(max, top + bottom + middle);
    }
    for (int x = k; x <= c - k; x++) {
      int y = x + k;
      int left = tl[r][x];
      int right = br[0][y];
      int middle = columns[y];
      max = Math.max(max, left + middle + right);
    }
    System.out.println(max);
  }

  static String next () throws IOException {
    while (st == null || !st.hasMoreTokens())
      st = new StringTokenizer(b.readLine().trim());
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
    return b.readLine().trim();
  }
}
