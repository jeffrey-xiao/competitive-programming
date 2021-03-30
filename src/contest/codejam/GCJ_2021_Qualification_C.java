package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2021_Qualification_C {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    int T = readInt();
    for (int t = 1; t <= T; t++) {
      int N = readInt();
      int cost = readInt();
      if (cost >= N * (N + 1) / 2 || cost < N - 1) {
        out.printf("Case #%d: IMPOSSIBLE%n", t);
        continue;
      }
      int[] A = new int[N];
      for (int i = 0; i < N; i++) {
        A[i] = i + 1;
      }

      solve(A, 0, cost - (N - 1));
      out.printf("Case #%d:", t);
      for (int i = 0; i < N; i++) {
        out.printf(" %d", A[i]);
      }
      out.println();
    }

    out.close();
  }

  static void solve(int[] A, int i, int cost) {
    int maxCost = A.length - i - 1;
    if (maxCost >= cost) {
      reverse(A, i, i + cost);
      return;
    }
    solve(A, i + 1, cost - maxCost);
    reverse(A, i, A.length - 1);
  }

  static void reverse(int[] A, int i, int j) {
    for (int k = i; k < (i + j + 1) / 2; k++) {
      int temp = A[k];
      A[k] = A[j - (k - i)];
      A[j - (k - i)] = temp;
    }
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
