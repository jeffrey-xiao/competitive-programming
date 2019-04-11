package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class CCC_2009_S2 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int r = readInt();
    String[] lights = new String[r];
    for (int x = 0; x < r; x++) {
      lights[x] = readLine().replaceAll("\\s", "");

    }
    LinkedHashSet<String> poss = new LinkedHashSet<String>();
    poss.add(lights[0]);
    for (int x = 1; x < r; x++) {
      LinkedHashSet<String> newPoss = new LinkedHashSet<String>();
      for (String s : poss) {
        newPoss.add(getPoss(lights[x], s));
      }
      newPoss.add(lights[x]);
      poss = new LinkedHashSet<String>(newPoss);

    }
    System.out.println(poss.size());
  }

  public static String getPoss(String a, String b) {
    String s = "";
    for (int x = 0; x < a.length(); x++)
      if (a.charAt(x) == b.charAt(x))
        s += "0";
      else
        s += "1";
    return s;
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
