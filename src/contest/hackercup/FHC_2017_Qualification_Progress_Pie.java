package contest.hackercup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FHC_2017_Qualification_Progress_Pie {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T;
  static double P, x, y;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    br = new BufferedReader(new FileReader("in.txt"));
    out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();

    for (int t = 1; t <= T; t++) {
      P = readDouble() / 100.0 * Math.PI * 2;
      x = readInt() - 50;
      y = readInt() - 50;

      double angle = Math.PI / 2 - Math.atan2(y, x);
      if (angle < 0)
        angle += 2 * Math.PI;
      if (P == 0)
        out.printf("Case #%d: white\n", t);
      else if (x == 0 && y == 0)
        out.printf("Case #%d: black\n", t);
      else if (x * x + y * y > 50 * 50 || angle > P)
        out.printf("Case #%d: white\n", t);
      else
        out.printf("Case #%d: black\n", t);
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
