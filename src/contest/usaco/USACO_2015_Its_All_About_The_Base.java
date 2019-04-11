package contest.usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class USACO_2015_Its_All_About_The_Base {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    for (int t = readInt(); t > 0; t--) {
      int a = readInt();
      int b = readInt();
      int x = 10;
      int y = 10;
      int nx = -1;
      int ny = 0;
      while (nx != ny) {
        nx = toNum(a, x);
        ny = toNum(b, y);
        if (nx < ny)
          x++;
        else if (nx > ny)
          y++;
        else
          System.out.println(x + " " + y);
      }
    }
  }

  private static int toNum(int a, int x) {
    return a / 100 * x * x + (a % 100) / 10 * x + a % 10;
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
