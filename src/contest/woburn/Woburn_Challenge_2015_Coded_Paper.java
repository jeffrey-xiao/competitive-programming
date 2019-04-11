package contest.woburn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Woburn_Challenge_2015_Coded_Paper {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, R;
  static int[][] a;
  static int[] best;
  static int[] dp;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    R = readInt();

    a = new int[2][N];
    for (int j = 0; j < 2; j++) {
      for (int i = 0; i < N; i++) {
        a[j][i] = readInt();
      }
    }

    dp = new int[5];

    for (int i = N - 1; i >= 0; i--) {
      int[] curr = new int[] {R, R + R, R + a[1][i], a[0][i] + R, a[0][i] + a[1][i]};
      if (i == N - 1) {
        dp = Arrays.copyOf(curr, curr.length);
      } else {
        int[] newDp = new int[5];
        Arrays.fill(newDp, -1 << 30);

        for (int j = 0; j < 5; j++) {
          for (int k = 0; k < 5; k++) {
            if (j == k) {
              if (j == 0 || j == 2 || j == 3) {
                newDp[j] = Math.max(newDp[j], curr[j] + dp[k] - R);
              } else if (j == 1) {
                newDp[j] = Math.max(newDp[j], curr[j] + dp[k] - 2 * R);
                newDp[j] = Math.max(newDp[j], curr[j] + dp[k] - R);
              }
            }
            if (j == 1 && k == 2 || j == 2 && k == 1) {
              newDp[j] = Math.max(newDp[j], curr[j] + dp[k] - R);
            }
            if (j == 1 && k == 3 || j == 3 && k == 1) {
              newDp[j] = Math.max(newDp[j], curr[j] + dp[k] - R);
            }
            newDp[j] = Math.max(newDp[j], curr[j] + dp[k]);
          }
        }
        dp = Arrays.copyOf(newDp, newDp.length);
      }
    }
    int ans = -1 << 30;
    for (int i = 0; i < 5; i++)
      ans = Math.max(ans, dp[i]);
    out.println(ans);

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
