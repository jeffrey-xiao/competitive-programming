package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2009_KABOOM {

  static final int SIZE = 500;
  static final int MOD = 10301;
  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;
  static int[][][] dp1 = new int[SIZE][SIZE][2];
  static int[][][] dp2 = new int[SIZE][SIZE][2];
  static int n, a, b;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // pr = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    a = readInt();
    b = readInt();
    for (int i = 0; i < SIZE; i++)
      for (int j = 0; j < SIZE; j++)
        for (int k = 0; k < 2; k++)
          dp1[i][j][k] = dp2[i][j][k] = -1;
    System.out.println(solve1(n - a - b, a, 1));
    pr.close();
  }

  // left
  static int solve1(int left, int size, int change) {
    if (dp1[left][size][change] != -1)
      return dp1[left][size][change];
    int res = 0;
    if (change != 0)
      res = (res + solve2(left, b, 1)) % MOD;
    if (left > 0)
      res = (res + solve1(left - 1, size + 1, 0)) % MOD;
    if (left >= size)
      res = (res + solve1(left - size, size, 1)) % MOD;
    return dp1[left][size][change] = res;
  }

  // right
  static int solve2(int left, int size, int change) {
    if (dp2[left][size][change] != -1)
      return dp2[left][size][change];
    int res = change;
    if (left > 0)
      res = (res + solve2(left - 1, size + 1, 0)) % MOD;
    if (left >= size)
      res = (res + solve2(left - size, size, 1)) % MOD;
    return dp2[left][size][change] = res;
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
