package contest.usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class USACO_2015_Meeting_Time {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    int m = readInt();
    boolean[][] poss1 = new boolean[n][15001];
    boolean[][] poss2 = new boolean[n][15001];

    int[][] A = new int[n][n];
    int[][] B = new int[n][n];
    poss1[0][0] = poss2[0][0] = true;
    for (int x = 0; x < m; x++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      int c = readInt();
      int d = readInt();
      A[a][b] = c;
      B[a][b] = d;
    }
    for (int x = 0; x < n; x++)
      for (int y = 0; y <= 15000; y++)
        for (int z = 0; z < n; z++)
          if (A[x][z] > 0) {
            if (poss1[x][y])
              poss1[z][A[x][z] + y] = true;
            if (poss2[x][y])
              poss2[z][B[x][z] + y] = true;
          }
    for (int y = 1; y <= 15000; y++) {
      if (poss1[n - 1][y] && poss2[n - 1][y]) {
        System.out.println(y);
        return;
      }
    }

    System.out.println("IMPOSSIBLE");
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

  static class Edge {
    int dest, a, b;

    Edge(int dest, int a, int b) {
      this.dest = dest;
      this.a = a;
      this.b = b;
    }
  }
}
