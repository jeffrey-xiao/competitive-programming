package contest.codejam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class GCJ_2017_Round_1B_C {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T;
  static int N, Q;
  static int[] E, S;
  static long[][] distAdj;
  static double[][] timeAdj;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();

    for (int t = 1; t <= T; t++) {
      N = readInt();
      Q = readInt();

      E = new int[N];
      S = new int[N];
      distAdj = new long[N][N];
      timeAdj = new double[N][N];

      for (int i = 0; i < N; i++) {
        E[i] = readInt();
        S[i] = readInt();
      }

      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          long dist = readLong();
          distAdj[i][j] = dist == -1 ? 1L << 60 : dist;
          timeAdj[i][j] = 1L << 60;
        }
      }

      // Floyd Warshall on distance graph
      for (int k = 0; k < N; k++)
        for (int i = 0; i < N; i++)
          for (int j = 0; j < N; j++)
            distAdj[i][j] = Math.min(distAdj[i][j], distAdj[i][k] + distAdj[k][j]);

      // Populating time graph
      for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
          if (distAdj[i][j] <= E[i])
            timeAdj[i][j] = Math.min(timeAdj[i][j], 1.0 * distAdj[i][j] / S[i]);

      // Floyd Warshall on time graph
      for (int k = 0; k < N; k++)
        for (int i = 0; i < N; i++)
          for (int j = 0; j < N; j++)
            timeAdj[i][j] = Math.min(timeAdj[i][j], timeAdj[i][k] + timeAdj[k][j]);

      out.printf("Case #%d: ", t);
      for (int i = 0; i < Q; i++)
        out.print(timeAdj[readInt() - 1][readInt() - 1] + " ");
      out.println();
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
