package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Stack;
import java.util.StringTokenizer;

public class CCC_2008_J4 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static PrintStream ps = new PrintStream(System.out);

  public static void main(String[] args) throws IOException {
    String next = "";
    while (!(next = readLine()).equals("0")) {
      String[] e = new StringBuilder(next).reverse().toString().split(" ");
      Stack<String> s = new Stack<String>();
      int i = 0;
      String result = "";
      do {
        if (!isOperator(e[i])) {
          s.push(e[i]);
        } else {
          String s1 = s.pop();
          String s2 = s.pop();
          result = s1 + " " + s2 + " " + e[i];
          s.push(result);
        }
        i++;
      } while (s.size() >= 0 && i < e.length);
      System.out.println(result);
    }
  }

  private static boolean isOperator(String s) {
    if (s.equals("+") || s.equals("-"))
      return true;
    return false;
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
