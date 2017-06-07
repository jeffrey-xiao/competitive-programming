package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2008_J2 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    int b = readInt();
    int n = readInt();
    String s = "ABCDE";
    while (b != 4 || n != 1) {
      for (int x = 0; x < n; x++) {
        if (b == 1) {
          s = s.substring(1) + s.charAt(0);
        } else if (b == 2) {
          s = "" + s.charAt(4) + s.substring(0, 4);
        } else if (b == 3) {
          s = "" + s.charAt(1) + s.charAt(0) + s.substring(2);
        }
      }
      b = readInt();
      n = readInt();
    }
    for (int x = 0; x < s.length(); x++)
      System.out.print(s.charAt(x) + " ");
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

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
