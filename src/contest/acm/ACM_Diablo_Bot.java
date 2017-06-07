package contest.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ACM_Diablo_Bot {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    int n = readInt();
    for (int x = 0; x < n; x++) {
      String next = readLine();
      String[] s = next.split(" ");
      boolean isRare = false, isSet = false, isMagic = false, isNormal = false;
      if (s.length == 2) {

        isRare = true;
        if (!s[0].equalsIgnoreCase("of"))
          isMagic = true;
      }
      if (s.length >= 3 && s.length <= 4 && next.length() >= 2 && s[s.length - 2].equalsIgnoreCase("of")) {
        isMagic = true;
      }
      if (s.length > 0) {
        String first = s[0];
        if (first.length() >= 2 && first.substring(first.length() - 2).equalsIgnoreCase("'s"))
          isSet = true;
        if (first.equalsIgnoreCase("damaged"))
          isNormal = true;
      }
      if ((!isRare && !isSet && !isMagic))
        isNormal = true;
      if (isSet) {
        System.out.println("Set");
      } else if (isNormal) {
        System.out.println("Normal");
      } else if (isRare && isMagic) {
        System.out.println("Not sure, take anyways");
      } else if (isRare) {
        System.out.println("Rare");
      } else if (isMagic) {
        System.out.println("Magic");
      }
    }
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
