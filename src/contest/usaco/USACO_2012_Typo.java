package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class USACO_2012_Typo {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int netPara = 0;
    String s = next();
    int open = 0;
    int close = 0;
    for (int x = 0; x < s.length(); x++) {
      if (s.charAt(x) == '(') {
        netPara++;
        open++;
      } else {
        netPara--;
        close++;
      }
      if (netPara <= 1)
        open = 0;
      if (netPara == -1) {
        System.out.print(close);
        return;
      }
    }
    System.out.println(open);
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
