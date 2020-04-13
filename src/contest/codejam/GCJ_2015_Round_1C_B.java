package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2015_Round_1C_B {

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
    for (int t = 1; t <= T; t++) {
      int K = readInt();
      int L = readInt();
      int S = readInt();
      double[][] dp = new double[S + 1][L + 1];
      boolean[][] reachable = new boolean[S + 1][L + 1];
      int[][] max = new int[S + 1][L + 1];
      char[] keyboard = next().toCharArray();
      char[] targetWord = next().toCharArray();
      int[] nextPrefix = new int[L];
      nextPrefix[0] = -1;
      for (int i = 1; i < L; i++) {
        if (targetWord[nextPrefix[i - 1] + 1] == targetWord[i]) {
          nextPrefix[i] = nextPrefix[i - 1] + 1;
        } else if (targetWord[i] == targetWord[0]) {
          nextPrefix[i] = 0;
        } else {
          nextPrefix[i] = -1;
        }
      }
      int[] count = new int[26];
      for (int i = 0; i < K; i++) {
        count[keyboard[i] - 'A']++;
      }


      dp[0][0] = 1;
      reachable[0][0] = true;
      double totalExpected = 0;
      int totalMax = 0;
      for (int i = 0; i < S; i++) {
        for (int j = 0; j < L; j++) {
          if (!reachable[i][j]) {
            continue;
          }
          for (char k = 'A'; k < 'A' + 26; k++) {
            if (count[k - 'A'] == 0) {
              continue;
            }
            if (k == targetWord[j]) {
              if (j == L - 1) {
                max[i + 1][nextPrefix[j] + 1] = Math.max(max[i + 1][nextPrefix[j] + 1], max[i][j] + 1);
                totalMax = Math.max(totalMax, max[i + 1][nextPrefix[j] + 1]);
                totalExpected += dp[i][j] * count[k - 'A'] / K;
                dp[i + 1][nextPrefix[j] + 1] += dp[i][j] * count[k - 'A'] / K;
                reachable[i + 1][nextPrefix[j] + 1] = true;
              } else {
                max[i + 1][j + 1] = Math.max(max[i + 1][j + 1], max[i][j]);
                totalMax = Math.max(totalMax, max[i + 1][j + 1]);
                dp[i + 1][j + 1] += dp[i][j] * count[k - 'A'] / K;
                reachable[i + 1][j + 1] = true;
              }
            } else {
              int nextIndex = j - 1;
              while (nextIndex != -1 && targetWord[nextIndex + 1] != k) {
                nextIndex = nextPrefix[nextIndex];
              }
              if (targetWord[nextIndex + 1] == k) {
                nextIndex++;
              }
              max[i + 1][nextIndex + 1] = Math.max(max[i + 1][nextIndex + 1], max[i][j]);
              totalMax = Math.max(totalMax, max[i + 1][nextIndex + 1]);
              dp[i + 1][nextIndex + 1] += dp[i][j] * count[k - 'A'] / K;
              reachable[i + 1][nextIndex + 1] = true;
            }
          }
        }
      }
      out.printf("Case #%d: %.6f%n", t,  totalMax - totalExpected);
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
