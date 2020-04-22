package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2020_Qualification_E {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T, N, K;
  static boolean[][] adj;
  static int[] prev;
  static boolean[] v;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    for (int t = 1; t <= T; t++) {
      N = readInt();
      K = readInt();
      if (K == N + 1 || K == N * N - 1) {
        out.printf("Case #%d: IMPOSSIBLE%n", t);
        continue;
      }
      adj = new boolean[N][N];
      prev = new int[N];
      v = new boolean[N];
      int a = 0, b = 0, c = 0;
    main:
      for (a = 1; a <= N; a++) {
        for (b = 1; b <= N; b++) {
          for (c = 1; c <= N; c++) {
            if (a * (N - 2) + b + c != K) {
              continue;
            }
            if ((a == b && b != c) || (a == c && c != b)) {
              continue;
            }
            break main;
          }
        }
      }
      int[][] ans = new int[N][N];
      boolean[][] columnVis = new boolean[N][N];
      ans[0][0] = c;
      columnVis[0][c - 1] = true;
      ans[1][1] = b;
      columnVis[1][b - 1] = true;
      for (int i = 2; i < N; i++) {
        ans[i][i] = a;
        columnVis[i][a - 1] = true;
      }

      boolean valid = true;
      for (int i = 0; i < N; i++) {
        Arrays.fill(prev, -1);
        for (int j = 0; j < N; j++) {
          Arrays.fill(adj[j], false);
          if (ans[i][j] != 0) {
            continue;
          }
          for (int k = 0; k < N; k++) {
            if (columnVis[j][k]) {
              continue;
            }
            if ((i == 0 && k + 1 == c) || (i == 1 && k + 1 == b) || (i > 1 && k + 1 == a)) {
              continue;
            }
            adj[j][k] = true;
          }
        }
        int matched = 0;
        for (int j = 0; j < N; j++) {
          Arrays.fill(v, false);
          matched += match(j) ? 1 : 0;
        }
        if (matched != N - 1) {
          valid = false;
          break;
        }
        for (int j = 0; j < N; j++) {
          if (prev[j] == -1) {
            continue;
          }
          ans[i][prev[j]] = j + 1;
          columnVis[prev[j]][j] = true;
        }
      }
      if (valid) {
        out.printf("Case #%d: POSSIBLE%n", t);
        for (int i = 0; i < N; i++) {
          for (int j = 0; j < N; j++) {
            out.print(ans[i][j] + " ");
          }
          out.println();
        }
      } else {
        out.printf("Case #%d: IMPOSSIBLE%n", t);
      }
   }

    out.close();
  }

  static boolean match(int i) {
    for (int j = 0; j < N; j++) {
      if (adj[i][j] && !v[j]) {
        v[j] = true;
        if (prev[j] == -1 || match(prev[j])) {
          prev[j] = i;
          return true;
        }
      }
    }
    return false;
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
