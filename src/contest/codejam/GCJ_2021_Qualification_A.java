package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2021_Qualification_A {

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
      int[] A = new int[N];
      for (int i = 0; i < N; i++) {
        A[i] = readInt();
      }

      int cost = 0;
      for (int i = 0; i < N - 1; i++) {
        int minIndex = i;
        for (int j = i + 1; j < N; j++) {
          if (A[j] < A[minIndex]) {
            minIndex = j;
          }
        }

        cost += reverse(A, i, minIndex);
      }

      out.printf("Case #%d: %d%n", t, cost);
    }

    out.close();
  }

  static int reverse(int[] A, int i, int j) {
    for (int k = i; k < (i + j + 1) / 2; k++) {
      int temp = A[k];
      A[k] = A[j - (k - i)];
      A[j - (k - i)] = temp;
    }
    return j - i + 1;
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

