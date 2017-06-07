package contest.usaco_other;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/*
ID: jeffrey40
LANG: JAVA
TASK: fencedin
*/
import java.util.Arrays;
import java.util.StringTokenizer;

public class fencedin {
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, M, A, B;
  static int[] length, height, x, y;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new FileReader("fencedin.in"));
    out = new PrintWriter(new FileWriter("fencedin.out"));
    // br = new BufferedReader(new InputStreamReader(System.in));
    // out = new PrintWriter(new OutputStreamWriter(System.out));

    A = readInt();
    B = readInt();
    N = readInt();
    M = readInt();

    length = new int[N + 1];
    height = new int[M + 1];
    x = new int[N + 2];
    y = new int[M + 2];

    x[0] = 0;
    x[N + 1] = A;
    for (int i = 1; i <= N; i++)
      x[i] = readInt();

    y[0] = 0;
    y[M + 1] = B;
    for (int i = 1; i <= M; i++)
      y[i] = readInt();

    Arrays.sort(x);
    Arrays.sort(y);

    for (int i = 1; i <= N + 1; i++)
      length[i - 1] = x[i] - x[i - 1];

    for (int i = 1; i <= M + 1; i++)
      height[i - 1] = y[i] - y[i - 1];

    Arrays.sort(length);
    Arrays.sort(height);

    int i = 0;
    int j = 0;

    long ans = 0;

    while (i <= N || j <= M) {
      if ((j == M + 1) || (i <= N && length[i] < height[j])) {
        ans += length[i++] * (long)(i <= 1 || j <= 1 ? M : M - j + 1);
      } else {
        ans += height[j++] * (long)(i <= 1 || j <= 1 ? N : N - i + 1);
      }
    }

    out.println(ans);

    out.close();
    System.exit(0);
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

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
