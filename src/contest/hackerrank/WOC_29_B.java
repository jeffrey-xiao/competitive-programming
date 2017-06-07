package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class WOC_29_B {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int N = readInt();

    String[] s = new String[N];

    for (int i = 0; i < N; i++)
      s[i] = next();

    Arrays.sort(s, new Comparator<String>() {
      @Override
      public int compare (String s1, String s2) {
        if (s1.length() != s2.length())
          return s1.length() - s2.length();
        for (int i = 0; i < s1.length(); i++)
          if (s1.charAt(i) != s2.charAt(i))
            return s1.charAt(i) - s2.charAt(i);
        return 0;
      }
    });

    for (int i = 0; i < N; i++)
      out.println(s[i]);

    out.close();
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
