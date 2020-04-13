package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2020_Qualification_B {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    for (int t = 1; t <= T; t++) {
      out.printf("Case #%d: ", t);
      char[] s = readLine().toCharArray();
      int curr = 0, ans = 0;
      for (int i = 0; i < s.length; i++) {
        int target = s[i] - '0';
        while (curr != target) {
          if (curr > target) {
            out.printf(")");
            curr--;
          } else {
            out.printf("(");
            curr++;
          }
          ans++;
        }
        out.print(s[i]);
      }
      while (curr > 0) {
        curr--;
        ans++;
        out.printf(")");
      }
      ans += curr;
      out.println();
    }

    out.close();
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

  static char readCharacter() throws IOException {
    return next().charAt(0);
  }

  static String readLine() throws IOException {
    return br.readLine().trim();
  }
}
