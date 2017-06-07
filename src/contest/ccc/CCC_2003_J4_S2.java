package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2003_J4_S2 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static String vowels = "aeiouAEIOU ";

  public static void main (String[] args) throws IOException {
    for (int t = readInt(); t > 0; t--) {
      String s1 = getLast(readLine());
      String s2 = getLast(readLine());
      String s3 = getLast(readLine());
      String s4 = getLast(readLine());
      if (s1.equals(s2) && s2.equals(s3) && s3.equals(s4))
        System.out.println("perfect");
      else if (s1.equals(s2) && s3.equals(s4))
        System.out.println("even");
      else if (s1.equals(s3) && s2.equals(s4))
        System.out.println("cross");
      else if (s1.equals(s4) && s3.equals(s2))
        System.out.println("shell");
      else
        System.out.println("free");
    }
  }

  private static String getLast (String s) {
    String result = "";
    for (int x = s.length() - 1; x >= 0; x--) {
      char c = s.charAt(x);
      if (vowels.indexOf(c) >= 0)
        return (result + c).toLowerCase();
      result += c;
    }
    return result.toLowerCase();
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
