package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2015_Qualification_D {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    int T = readInt();
    for (int t = 1; t <= T; t++) {
      int X = readInt();
      int R = readInt();
      int C = readInt();

      int S = Math.min(R, C);
      int L = Math.max(R, C);
      if (R * C % X != 0 || (X == 3 && S == 1) || (X == 4 && S <= 2) ||
          (X == 5 && (S <= 2 || (S == 3 && L == 5))) || (X == 6 && S <= 3) ||
          X >= 7) {
        out.printf("Case #%d: RICHARD%n", t);
      } else {
        out.printf("Case #%d: GABRIEL%n", t);
      }
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
