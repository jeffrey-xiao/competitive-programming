package contest.codeforces;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Round_301D {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    // br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    br = new BufferedReader(new FileReader("in.txt"));
    // pr = new PrintWriter(new FileWriter("out.txt"));

    int r = readInt();
    int s = readInt();
    int p = readInt();
    double[][][] prob = new double[r + 1][s + 1][p + 1];
    prob[r][s][p] = 1;
    for (int i = r; i >= 0; i--) {
      for (int j = s; j >= 0; j--) {
        for (int k = p; k >= 0; k--) {
          double rs = i * j;
          double sp = j * k;
          double pr = k * i;
          double total = rs + sp + pr;
          // rock
          if (i > 0 && k > 0) {
            prob[i - 1][j][k] += prob[i][j][k] * (pr / total);
          }
          // scissors
          if (j > 0 && i > 0) {
            prob[i][j - 1][k] += prob[i][j][k] * (rs / total);
          }
          // paper
          if (k > 0 && j > 0) {
            prob[i][j][k - 1] += prob[i][j][k] * (sp / total);
          }
        }
      }
    }
    double ar = 0;
    double as = 0;
    double ap = 0;
    for (int i = 1; i <= r; i++)
      ar += prob[i][0][0];
    for (int i = 1; i <= s; i++)
      as += prob[0][i][0];
    for (int i = 1; i <= p; i++)
      ap += prob[0][0][i];
    System.out.println(ar + " " + as + " " + ap);
    pr.close();
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
