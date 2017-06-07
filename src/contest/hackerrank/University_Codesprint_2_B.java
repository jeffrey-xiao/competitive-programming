package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class University_Codesprint_2_B {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int T = readInt();

    for (int t = 1; t <= T; t++) {
      String in = readLine();
      if (in.charAt(0) == '0') {
        out.println("NO");
        continue;
      }
      long ans = 1L << 60;
      for (int i = 0; i < in.length() / 2; i++) {
        long start = Long.parseLong(in.substring(0, i + 1));
        if (isValid(start + 1, in.substring(i + 1)))
          ans = Math.min(ans, start);
      }
      if (ans != 1L << 60)
        out.println("YES " + ans);
      else
        out.println("NO");
    }

    out.close();
  }

  static boolean isValid (long val, String curr) {
    if (curr.equals(""))
      return true;
    if (curr.charAt(0) == '0')
      return false;
    for (int i = 0; i < Math.min(curr.length(), Long.toString(val).length() + 1); i++)
      if (Long.parseLong(curr.substring(0, i + 1)) == val)
        return isValid(val + 1, curr.substring(i + 1));
    return false;
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

  static char readCharacter () throws IOException {
    return next().charAt(0);
  }

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
