package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class COCI_2014_NEO {

  static BufferedReader br;
  static PrintWriter ps;
  static StringTokenizer st;

  static int[][] a;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    // br = new BufferedReader(new FileReader("in.txt"));
    // ps = new PrintWriter("out.txt");

    int r = readInt();
    int c = readInt();
    a = new int[r][c];
    int[][] dp = new int[r][c];
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        a[i][j] = readInt();
        if (i > 0 && j > 0)
          if (a[i - 1][j - 1] + a[i][j] <= a[i - 1][j] + a[i][j - 1])
            dp[i][j] = 1;
      }
    }
    int ans = 0;
    for (int i = 1; i < r; i++) {
      for (int j = 1; j < c; j++)
        if (dp[i][j] != 0)
          dp[i][j] += dp[i][j - 1];
    }
    for (int j = 1; j < c; j++) {
      Stack<State> curr = new Stack<State>();
      for (int i = 1; i < r; i++) {
        int size = 1;
        int nsize = 0;
        int nheight = 0;
        while (!curr.isEmpty() && curr.peek().height >= dp[i][j]) {
          nsize += curr.peek().size;
          nheight = curr.pop().height;
          ans = Math.max(ans, (nsize + 1) * (nheight + 1));
        }
        size += nsize;
        curr.push(new State(dp[i][j], size));
        ans = Math.max(ans, (size + 1) * (dp[i][j] + 1));
      }
      int size = 0;
      int height = 0;
      while (!curr.isEmpty()) {
        size += curr.peek().size;
        height = curr.pop().height;
        ans = Math.max(ans, (size + 1) * (height + 1));
      }
    }
    ps.println(ans);
    ps.close();
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
    int height;
    int size;

    State(int height, int size) {
      this.height = height;
      this.size = size;
    }
  }
}