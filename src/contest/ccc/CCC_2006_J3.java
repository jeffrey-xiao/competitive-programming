package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2006_J3 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    String alpha = "abc def ghi jkl mno pqrstuv wxyz";
    String next = "";
    while (!(next = next()).equals("halt")) {
      int total = alpha.indexOf(next.charAt(0)) % 4 + 1;
      for (int x = 1; x < next.length(); x++) {
        if (next.charAt(x) / 4 == next.charAt(x - 1) / 4)
          total += 2;
        total += alpha.indexOf(next.charAt(x)) % 4 + 1;
      }
      System.out.println(total);
    }
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

  static String readLine() throws IOException {
    return br.readLine().trim();
  }
}
