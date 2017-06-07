package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2014_NIKO {

  static BufferedReader br;
  static PrintWriter ps;
  static StringTokenizer st;

  static int[][] p;
  static boolean[][] poss;
  static int m;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    // br = new BufferedReader(new FileReader("in.txt"));
    // ps = new PrintWriter("out.txt");

    int N = readInt();
    p = new int[N][3];
    for (int i = 0; i < N; i++) {
      String[] in = next().split("-");
      p[i][0] = Integer.parseInt(in[0]);
      p[i][1] = Integer.parseInt(in[1]);
      p[i][2] = Integer.parseInt(in[2]);
    }
    m = readInt();
    poss = new boolean[m][3];
    for (int i = 0; i < m; i++) {
      String in = next();
      for (int j = 0; j < in.length(); j++) {
        if (in.charAt(j) == 'O')
          poss[i][0] = true;
        else if (in.charAt(j) == 'V')
          poss[i][1] = true;
        else
          poss[i][2] = true;
      }
    }
    for (int i = 0; i < N; i++) {
      if (compute(p[i], 0))
        System.out.println("DA");
      else
        System.out.println("NE");
    }
    ps.close();
  }

  static boolean compute (int[] p, int i) {
    if (p[0] == 0 && p[1] == 0 && p[2] == 0)
      return true;
    if (i == m)
      return false;
    for (int j = 0; j < 3; j++) {
      if (poss[i][j] && p[j] > 0) {
        p[j]--;
        if (compute(p, i + 1))
          return true;
        p[j]++;
      }
    }
    if (compute(p, i + 1))
      return true;
    return false;
  }

  static String next () throws IOException {
    while (st == null || !st.hasMoreTokens())
      st = new StringTokenizer(br.readLine().trim());
    return st.nextToken();
  }

  static long readLong () throws IOException {
    return Long.parseLong(next());
  }

  static int readInt () throws IOException {
    return Integer.parseInt(next());
  }

  static double readDouble () throws IOException {
    return Double.parseDouble(next());
  }

  static char readCharacter () throws IOException {
    return next().charAt(0);
  }

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}