package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class CCO_Prep_Double_Cross {

  static final int SIZE = 10001;
  static final int MOD = 1000000009;
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;
  static int r, c;
  static long[] cnt = new long[SIZE];
  static long[] addSum = new long[SIZE];
  static long[] subSum = new long[SIZE];
  static Queue<Pair> added = new ArrayDeque<Pair>();
  static int[][] left;
  static int[][] right;
  static int[][] up;
  static int[][] down;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    r = readInt();
    c = readInt();

    left = new int[r + 2][c + 2];
    right = new int[r + 2][c + 2];
    up = new int[r + 2][c + 2];
    down = new int[r + 2][c + 2];
    boolean[][] grid = new boolean[r + 2][c + 2];
    for (int i = 0; i < r + 2; i++)
      for (int j = 0; j < c + 2; j++)
        grid[i][j] = true;
    int n = readInt();
    for (int i = 0; i < n; i++)
      grid[readInt()][readInt()] = false;

    for (int i = 1; i <= r; i++) {
      for (int j = 1; j <= c; j++)
        left[i][j] = !grid[i][j] ? 0 : 1 + left[i][j - 1];
      for (int j = c; j >= 1; j--)
        right[i][j] = !grid[i][j] ? 0 : 1 + right[i][j + 1];
    }
    for (int j = 1; j <= c; j++) {
      for (int i = 1; i <= r; i++)
        up[i][j] = !grid[i][j] ? 0 : 1 + up[i - 1][j];
      for (int i = r; i >= 1; i--)
        down[i][j] = !grid[i][j] ? 0 : 1 + down[i + 1][j];
    }
    long res = 0;
    for (int j = 1; j <= c; j++) {
      int prev = 0;
      int prevMul = 0;
      int curr = 0;

      for (int i = 1; i <= r; i++) {
        if (!grid[i][j]) {
          prev = 0;
          prevMul = 0;
          curr = 0;
          while (!added.isEmpty())
            remove(added.poll());
        } else {
          curr = Math.min(left[i][j], right[i][j]) - 1;
          if (curr > 1) {
            long totalCurr = 0;
            totalCurr = (totalCurr + (query(cnt, SIZE - 1) - query(cnt, curr - 2)) * (curr * (curr - 1) / 2));
            totalCurr = (totalCurr + query(addSum, curr - 2) * curr - query(subSum, curr - 2));
            res = (res + totalCurr * down[i + 1][j] % MOD) % MOD;

          }
        }
        add(prev, prevMul);
        prev = curr;
        prevMul = up[i - 1][j];
      }

      while (!added.isEmpty())
        remove(added.poll());
    }
    out.println(res);
    out.close();
  }

  static void add(int prev, int upVal) {
    if (prev == 0 || upVal == 0)
      return;
    added.offer(new Pair(prev, upVal));
    update(cnt, prev, upVal);
    update(addSum, prev, prev * upVal);
    update(subSum, prev, prev * (prev + 1) / 2 * upVal);
  }

  static void remove(Pair p) {
    update(cnt, p.curr, -p.mul);
    update(addSum, p.curr, -p.curr * p.mul);
    update(subSum, p.curr, -p.curr * (p.curr + 1) / 2 * p.mul);
  }

  static void update(long[] tree, int x, int val) {
    for (int i = x; i < SIZE; i += (i & -i))
      tree[i] = (tree[i] + val) % MOD;
  }

  static long query(long[] tree, int x) {
    long sum = 0;
    for (int i = x; i > 0; i -= (i & -i))
      sum = (sum + tree[i]) % MOD;
    return sum;
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

  static class Pair {
    int curr, mul;

    Pair(int curr, int mul) {
      this.curr = curr;
      this.mul = mul;
    }
  }
}
