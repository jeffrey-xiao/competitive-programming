package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class COCI_2006_OKVIRI {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int l;

  public static void main (String[] args) throws IOException {
    String n = readLine();
    l = n.length();
    print1();
    print2();
    for (int x = 0; x < l; x++) {
      System.out.printf("%1$s.%2$s.", (x + 1) % 3 == 0 || (x) % 3 == 0 && x != 0 ? "*" : "#", n.charAt(x));
    }
    System.out.println((l) % 3 == 0 ? "*" : "#");
    print2();
    print1();
  }

  private static void print1 () {
    for (int x = 0; x < l; x++)
      System.out.printf("..%1$s.", (x + 1) % 3 == 0 ? "*" : "#");
    System.out.println(".");
  }

  private static void print2 () {
    for (int x = 0; x < l; x++)
      System.out.printf(".%1$s.%1$s", (x + 1) % 3 == 0 ? "*" : "#");
    System.out.println(".");
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
