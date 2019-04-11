package contest.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ACM_NAQ_2016_D {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static char[] in;
  static int[][] seg, max, min;
  static int N;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    in = readLine().toCharArray();
    N = in.length;

    seg = new int[N][N];
    max = new int[N][N];
    min = new int[N][N];

    for (int i = 0; i < N; i++) {
      for (int j = i; j < N; j++) {
        if (i == j)
          seg[i][j] = min[i][j] = max[i][j] = in[j] == '(' ? 1 : -1;
        else {
          int val = in[j] == '(' ? 1 : -1;
          seg[i][j] = seg[i][j - 1] + val;
          min[i][j] = Math.min(seg[i][j], min[i][j - 1]);
          max[i][j] = Math.max(seg[i][j], max[i][j - 1]);
        }
      }
    }

    boolean done = false;

    if (seg[0][N - 1] == 0 && min[0][N - 1] >= 0) {
      out.println("possible");
      done = true;
    }

    for (int i = 0; i < N; i++) {
      for (int j = i; j < N; j++) {
        boolean valid = true;
        int totalSeg = -seg[i][j];
        int currMin = -max[i][j];
        if (i > 0)
          totalSeg += seg[0][i - 1];
        if (j < N - 1)
          totalSeg += seg[j + 1][N - 1];

        if (totalSeg != 0) {
          valid = false;
        }

        int totalMin = 1 << 30;
        int firstTwoSeg = -seg[i][j];
        if (i > 0) {
          firstTwoSeg += seg[0][i - 1];
          totalMin = Math.min(totalMin, min[0][i - 1]);
          totalMin = Math.min(totalMin, seg[0][i - 1] + currMin);
        }
        if (i == 0) {
          totalMin = Math.min(totalMin, currMin);
        }
        if (j < N - 1)
          totalMin = Math.min(totalMin, firstTwoSeg + min[j + 1][N - 1]);

        if (totalMin < 0) {
          valid = false;
        }

        if (valid && !done) {
          done = true;
          out.println("possible");
        }
      }
    }
    if (!done)
      out.println("impossible");
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