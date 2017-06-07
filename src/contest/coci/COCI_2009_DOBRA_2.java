package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class COCI_2009_DOBRA_2 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder s;
  static ArrayList<Integer> blank;
  static String vowel = "AEIOU";
  static String alpha = "BCDFGHJKLMNPQRSTVWXYZ";
  static long sum;

  public static void main (String[] args) throws IOException {
    s = new StringBuilder(next());
    blank = new ArrayList<Integer>();
    for (int x = 0; x < s.length(); x++) {
      if (s.charAt(x) == '_') {
        blank.add(x);
      }
    }
    compute(0, blank.size(), 1);
    System.out.println(sum);
  }

  private static void compute (int x, int size, long m) {
    if (x == size) {
      if (s.indexOf("L") == -1)
        return;
      for (int y = 1; y < s.length() - 1; y++) {
        if (vowel.indexOf(s.charAt(y - 1)) != -1 && vowel.indexOf(s.charAt(y + 1)) != -1 && vowel.indexOf(s.charAt(y)) != -1)
          return;
        if (alpha.indexOf(s.charAt(y - 1)) != -1 && alpha.indexOf(s.charAt(y + 1)) != -1 && alpha.indexOf(s.charAt(y)) != -1)
          return;
      }
      sum += m;
      return;
    }
    int i = blank.get(x);

    s.setCharAt(i, 'B');
    compute(x + 1, size, m * 20);
    s.setCharAt(i, '_');
    s.setCharAt(i, 'L');
    compute(x + 1, size, m);
    s.setCharAt(i, '_');

    s.setCharAt(i, 'A');
    compute(x + 1, size, 5 * m);
    s.setCharAt(i, '_');

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