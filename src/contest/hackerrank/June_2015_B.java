package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class June_2015_B {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // pr = new PrintWriter(new FileWriter("out.txt"));

    int t = readInt();
    for (int qq = 0; qq < t; qq++) {
      int r = readInt();
      int s = readInt();
      double firstS = (double)(r) / (double)(s);
      if (firstS - (int)(firstS) > 1.0 / 4.0) {
        System.out.println((int)((Math.ceil(firstS)) * s) + " 0/1");
      } else {
        long top = r % s;
        long bot = s;
        long gcf = gcf(top, bot);
        top /= gcf;
        bot /= gcf;
        System.out.println(r + " " + top + "/" + bot);
      }
    }

    pr.close();
  }

  static long lca(long a, long b) {
    return a * b / gcf(a, b);
  }

  static long gcf(long a, long b) {
    return b == 0 ? a : gcf(b, a % b);
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
