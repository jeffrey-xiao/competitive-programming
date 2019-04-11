package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2014_BOB {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  static State[] stack = new State[1001];

  public static void main(String[] args) throws IOException {
    int r = readInt();
    int c = readInt();
    int[][] grid = new int[r + 1][c + 1];
    long[][] cnt = new long[r + 1][c + 1];
    for (int i = 1; i <= r; i++) {
      for (int j = 1; j <= c; j++) {
        grid[i][j] = readInt();
        cnt[i][j] = 1;
      }
    }

    long total = 0;
    for (int i = 1; i <= r; i++) {
      int top = -1;
      for (int j = 1; j <= c; j++) {
        if (grid[i][j] == grid[i - 1][j])
          cnt[i][j] += cnt[i - 1][j];
        long count = 1;
        long temp = 0;
        while (top >= 0 && stack[top].a == grid[i][j] && stack[top].c >= cnt[i][j])
          count += stack[top--].count;
        if (top >= 0 && stack[top].a == grid[i][j])
          temp += stack[top].total;
        temp += count * cnt[i][j];
        total += temp;
        stack[++top] = new State(grid[i][j], cnt[i][j], count, temp);

      }
    }
    System.out.println(total);
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

  static class State {
    long a, c;
    long total, count;

    State(int a, long c, long count, long total) {
      this.a = a;
      this.c = c;
      this.count = count;
      this.total = total;
    }
  }
}
