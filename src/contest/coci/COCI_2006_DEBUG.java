package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2006_DEBUG {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int R, C;
  static int[][] g;
  static long[][][] pre;
  static final int SIZE = 18;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    R = readInt();
    C = readInt();

    g = new int[R][C];
    pre = new long[4][R][C];

    for (int i = 0; i < R; i++) {
      char[] in = readLine().toCharArray();
      for (int j = 0; j < C; j++) {
        g[i][j] = in[j] == '0' ? 0 : 1;
      }
    }

    // computing top and left
    for (int i = 0; i < R; i++) {
      for (int j = 0; j < C; j++) {
        if (i > 0) {
          pre[0][i][j] = (pre[0][i - 1][j] << 1) | g[i - 1][j];
          pre[0][i][j] &= (1L << SIZE) - 1;
        }

        if (j > 0) {
          pre[1][i][j] = (pre[1][i][j - 1] << 1) | g[i][j - 1];
          pre[1][i][j] &= (1L << SIZE) - 1;
        }
      }
    }

    // computing bottom and right
    for (int i = R - 1; i >= 0; i--) {
      for (int j = C - 1; j >= 0; j--) {
        if (i <= R - 2) {
          pre[2][i][j] = (pre[2][i + 1][j] << 1) | g[i + 1][j];
          pre[2][i][j] &= (1L << SIZE) - 1;
        }

        if (j <= C - 2) {
          pre[3][i][j] = (pre[3][i][j + 1] << 1) | g[i][j + 1];
          pre[3][i][j] &= (1L << SIZE) - 1;
        }
      }
    }

    int ans = -1;
    // attempting to expand every 1x1 square
    for (int i = 0; i < R; i++) {
      for (int j = 0; j < C; j++) {
        int currSize = 0;

        while (i - currSize >= SIZE && j - currSize >= SIZE && i + currSize + SIZE < R && j + currSize + SIZE < C && canExpandBig1(i, j, currSize))
          currSize += SIZE;

        while (i - currSize >= 1 && j - currSize >= 1 && i + currSize + 1 < R && j + currSize + 1 < C && canExpandSmall1(i, j, currSize))
          currSize++;

        if (currSize > 0)
          ans = Math.max(ans, currSize * 2 + 1);
      }
    }

    // attempting to expand every 2x2 square
    for (int i = 0; i < R - 1; i++) {
      for (int j = 0; j < C - 1; j++) {
        int currSize = 0;
        if (g[i][j] != g[i + 1][j + 1] || g[i + 1][j] != g[i][j + 1])
          continue;

        while (i - currSize >= SIZE && j - currSize >= SIZE && i + currSize + SIZE + 1 < R && j + currSize + SIZE + 1 < C && canExpandBig2(i, j, currSize))
          currSize += SIZE;

        while (i - currSize >= 1 && j - currSize >= 1 && i + currSize + 2 < R && j + currSize + 2 < C && canExpandSmall2(i, j, currSize))
          currSize++;

        ans = Math.max(ans, currSize * 2 + 2);
      }
    }

    out.println(ans);
    out.close();
  }

  static boolean canExpandBig2 (int i, int j, int currSize) {
    for (int k = -(currSize + SIZE); k <= currSize + SIZE + 1; k++) {
      if (pre[1][i + k][j - currSize] != pre[3][i - k + 1][j + currSize + 1])
        return false;
      if (pre[0][i - currSize][j + k] != pre[2][i + currSize + 1][j - k + 1])
        return false;
    }
    return true;
  }

  static boolean canExpandBig1 (int i, int j, int currSize) {
    for (int k = -(currSize + SIZE); k <= currSize + SIZE; k++) {
      if (pre[1][i + k][j - currSize] != pre[3][i - k][j + currSize])
        return false;
      if (pre[0][i - currSize][j + k] != pre[2][i + currSize][j - k])
        return false;
    }
    return true;
  }

  static boolean canExpandSmall2 (int i, int j, int currSize) {
    for (int k = -(currSize + 1); k <= currSize + 2; k++) {
      if (g[i + k][j - currSize - 1] != g[i + 1 - k][j + currSize + 2])
        return false;
      if (g[i - currSize - 1][j + k] != g[i + currSize + 2][j + 1 - k])
        return false;
    }
    return true;
  }

  static boolean canExpandSmall1 (int i, int j, int currSize) {
    for (int k = -(currSize + 1); k <= currSize + 1; k++) {
      if (g[i + k][j - currSize - 1] != g[i - k][j + currSize + 1])
        return false;
      if (g[i - currSize - 1][j + k] != g[i + currSize + 1][j - k])
        return false;
    }
    return true;
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
