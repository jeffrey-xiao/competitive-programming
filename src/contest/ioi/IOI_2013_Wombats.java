package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class IOI_2013_Wombats {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int R, C, E;
  static int[][] H, V;
  static int[][][] seg;

  static final int COMPRESSION_SIZE = 20;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    R = readInt();
    C = readInt();

    H = new int[R][C - 1];
    V = new int[R - 1][C];
    seg = new int[4 * Math.max(1, ((R - 2) / COMPRESSION_SIZE))][C][C];

    for (int i = 0; i < R; i++)
      for (int j = 0; j < C - 1; j++)
        H[i][j] = readInt();

    for (int i = 0; i < R - 1; i++)
      for (int j = 0; j < C; j++)
        V[i][j] = readInt();

    build(1, 0, (R - 2) / COMPRESSION_SIZE);

    E = readInt();

    for (int i = 0; i < E; i++) {
      int command = readInt();

      // change horizontal road
      if (command == 1) {
        int r = readInt();
        int c = readInt();
        H[r][c] = readInt();
        if (0 <= r - 1 && r - 1 <= R - 2)
          update(1, 0, (R - 2) / COMPRESSION_SIZE, (r - 1) / COMPRESSION_SIZE);
        if (0 <= r && r <= R - 2)
          update(1, 0, (R - 2) / COMPRESSION_SIZE, (r) / COMPRESSION_SIZE);
      } else if (command == 2) {
        int r = readInt();
        int c = readInt();
        V[r][c] = readInt();
        update(1, 0, (R - 2) / COMPRESSION_SIZE, (r) / COMPRESSION_SIZE);
      } else {
        int x1 = readInt();
        int x2 = readInt();
        int[][] ret = seg[1];
        out.println(ret[x1][x2]);
      }
    }

    out.close();
  }

  static void update (int n, int l, int r, int x) {
    if (l == x && x == r) {
      int a = x * COMPRESSION_SIZE;
      int b = Math.min(R - 1, (x + 1) * COMPRESSION_SIZE);
      seg[n] = computeNextRow(a);
      for (int i = a + 1; i < b; i++)
        seg[n] = combine(seg[n], computeNextRow(i));
      return;
    }

    int mid = (l + r) >> 1;
    if (x <= mid)
      update(n << 1, l, mid, x);
    else
      update(n << 1 | 1, mid + 1, r, x);

    seg[n] = combine(seg[n << 1], seg[n << 1 | 1]);
  }

  static void build (int n, int l, int r) {
    if (l == r) {
      int a = l * COMPRESSION_SIZE;
      int b = Math.min(R - 1, (l + 1) * COMPRESSION_SIZE);
      seg[n] = computeNextRow(a);
      for (int i = a + 1; i < b; i++)
        seg[n] = combine(seg[n], computeNextRow(i));
      return;
    }

    int mid = (l + r) >> 1;
    build(n << 1, l, mid);
    build(n << 1 | 1, mid + 1, r);

    seg[n] = combine(seg[n << 1], seg[n << 1 | 1]);
  }

  static int[][] computeNextRow (int row) {
    int[][] ret = new int[C][C];

    // computing current row first	

    // going right
    for (int i = 0; i < C; i++)
      for (int j = 0; j < i; j++)
        ret[j][i] = ret[j][i - 1] + H[row][i - 1];

    // going left
    for (int i = C - 1; i >= 0; i--)
      for (int j = C - 1; j > i; j--)
        ret[j][i] = ret[j][i + 1] + H[row][i];

    for (int i = 0; i < C; i++)
      for (int j = 0; j < C; j++)
        ret[i][j] += V[row][j];

    // computing next row

    // going right
    for (int i = 1; i < C; i++)
      for (int j = 0; j < C; j++)
        ret[j][i] = Math.min(ret[j][i], ret[j][i - 1] + H[row + 1][i - 1]);

    // going left
    for (int i = C - 2; i >= 0; i--)
      for (int j = C - 1; j >= 0; j--)
        ret[j][i] = Math.min(ret[j][i], ret[j][i + 1] + H[row + 1][i]);

    return ret;

  }

  // [X, Y], [Y, Z]
  static int[][] combine (int[][] a, int[][] b) {
    int[][] ret = new int[C][C];
    int[][] best = new int[C][C];

    // i <= j
    for (int d = 0; d < C; d++) {
      for (int i = 0; i + d < C; i++) {
        int j = i + d;
        ret[i][j] = 1 << 30;
        if (d <= 1) {
          for (int k = 0; k < C; k++) {
            int next = a[i][k] + b[k][j];
            if (next < ret[i][j]) {
              ret[i][j] = next;
              best[i][j] = k;
            }
          }
        } else {
          for (int k = best[i][j - 1]; k <= best[i + 1][j]; k++) {
            int next = a[i][k] + b[k][j];
            if (next < ret[i][j]) {
              ret[i][j] = next;
              best[i][j] = k;
            }
          }
        }
      }
    }

    // i > j
    for (int d = 1; d < C; d++) {
      for (int i = C - 1; i - d >= 0; i--) {
        int j = i - d;
        ret[i][j] = 1 << 30;
        if (d <= 1) {
          for (int k = 0; k < C; k++) {
            int next = a[i][k] + b[k][j];
            if (next < ret[i][j]) {
              ret[i][j] = next;
              best[i][j] = k;
            }
          }
        } else {
          for (int k = best[i - 1][j]; k <= best[i][j + 1]; k++) {
            int next = a[i][k] + b[k][j];
            if (next < ret[i][j]) {
              ret[i][j] = next;
              best[i][j] = k;
            }
          }
        }
      }
    }

    return ret;
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
