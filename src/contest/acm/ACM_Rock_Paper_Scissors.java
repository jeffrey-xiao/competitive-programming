package contest.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ACM_Rock_Paper_Scissors {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    int n = readInt();
    for (int x = 0; x < n; x++) {
      String s = next();
      if (s.equals("Scissors"))
        System.out.println("Rock");
      else if (s.equals("Paper"))
        System.out.println("Scissors");
      else if (s.equals("Rock"))
        System.out.println("Paper");
      else if (s.equals("Fox"))
        System.out.println("Foxen");
      else
        break;
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
