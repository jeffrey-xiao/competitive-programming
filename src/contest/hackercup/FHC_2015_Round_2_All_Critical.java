package contest.hackercup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FHC_2015_Round_2_All_Critical {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static double p;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int[][] C = new int[21][21];
    for (int i = 0; i <= 20; i++) {
      for (int j = 0; j <= 20; j++) {
        if (i == j || j == 0)
          C[i][j] = 1;
        else if (j > i)
          C[i][j] = 0;
        else
          C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
      }
    }

    int tt = readInt();
    for (int qq = 1; qq <= tt; qq++) {
      p = readDouble();
      double[][] prob = new double[5001][21];
      for (int j = 0; j <= 20; j++) {
        for (int i = 0; i <= 5000; i++) {
          if (i == 0) {
            if (j == 0) {
              prob[i][j] = 1;
            } else {
              prob[i][j] = 0;
            }
          } else {
            for (int k = 0; k <= j; k++) {
              prob[i][j] += prob[i - 1][k] * C[20 - k][j - k] * pow(p, j - k) * pow(1 - p, 20 - j);
            }
          }
        }
      }
      double ans = 0;
      for (int i = 1; i <= 5000; i++)
        ans += i * (prob[i][20] - prob[i - 1][20]);
      out.printf("Case #%d: %.5f\n", qq, ans);
    }
    out.close();
  }

  static double pow(double p, int i) {
    double res = 1;
    for (int j = 0; j < i; j++)
      res *= p;
    return res;
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
