package contest.ecoo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ECOO_2015_R1_P4 {

  static BufferedReader br;
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    // br = new BufferedReader(new FileReader("DATA41.txt"));
    br = new BufferedReader(new InputStreamReader(System.in));

    for (int qq = 0; qq < 10; qq++) {
      String s = next();
      int[] dp = new int[s.length() + 1];
      dp[0] = 1;
      String[] num = new String[] {"ook", "ookook", "oog", "ooga", "ug", "mook", "mookmook", "oogam", "oogum", "ugug"};
      for (int i = 0; i < s.length(); i++) {
        for (int j = 0; j < num.length; j++) {
          if (i + num[j].length() <= s.length() && s.substring(i, i + num[j].length()).equals(num[j]))
            dp[i + num[j].length()] += dp[i];
        }
      }
      System.out.println(dp[s.length()]);
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

  static char readCharacter () throws IOException {
    return next().charAt(0);
  }

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
