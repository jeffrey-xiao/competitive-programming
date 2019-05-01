package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2018_Round_1A_A {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();

  outer:
    for (int t = 1; t <= T; t++) {
      int R = readInt();
      int C = readInt();
      int H = readInt();
      int V = readInt();

      int[][] sum = new int[R + 1][C + 1];
      int total = 0;

      for (int i = 1; i <= R; i++) {
        String s = readLine();
        for (int j = 0; j < s.length(); j++) {
          sum[i][j + 1] = sum[i - 1][j + 1] + sum[i][j] - sum[i - 1][j] + (s.charAt(j) == '@' ? 1 : 0);
          if (s.charAt(j) == '@') {
            total += 1;
          }
        }
      }

      if (total == 0) {
        out.printf("Case #%d: POSSIBLE%n", t);
        continue outer;
      }

      if (total % ((H + 1) * (V + 1)) != 0) {
        out.printf("Case #%d: IMPOSSIBLE%n", t);
        continue outer;
      }

      int rowCount = total / (H + 1);
      int colCount = total / (V + 1);
      int cellCount = total / ((H + 1) * (V + 1));
      int prev = 0;
      ArrayList<Integer> horizontalCuts = new ArrayList<Integer>(), verticalCuts = new ArrayList<Integer>();
      horizontalCuts.add(0);
      verticalCuts.add(0);
      for (int i = 1; i <= R; i++) {
        if (sum[i][C] - sum[prev][C] == rowCount) {
          prev = i;
          horizontalCuts.add(i);
        }
      }

      prev = 0;
      for (int i = 1; i <= C; i++) {
        if (sum[R][i] - sum[R][prev] == colCount) {
          prev = i;
          verticalCuts.add(i);
        }
      }

      if (horizontalCuts.size() != H + 2 || verticalCuts.size() != V + 2) {
        out.printf("Case #%d: IMPOSSIBLE%n", t);
        continue outer;
      }

      for (int i = 1; i < horizontalCuts.size(); i++) {
        for (int j = 1; j < verticalCuts.size(); j++) {
          int curr = sum[horizontalCuts.get(i)][verticalCuts.get(j)] - sum[horizontalCuts.get(i - 1)][verticalCuts.get(j)] - sum[horizontalCuts.get(i)][verticalCuts.get(j - 1)] + sum[horizontalCuts.get(i - 1)][verticalCuts.get(j - 1)];
          if (curr != cellCount) {
            out.printf("Case #%d: IMPOSSIBLE%n", t);
            continue outer;
          }
        }
      }

      out.printf("Case #%d: POSSIBLE%n", t);
    }

    out.close();
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
