package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2006_S2 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    char[] alpha = new char[27];
    String s1 = readLine();
    String s2 = readLine();
    for (int x = 0; x < s1.length(); x++) {
      int index = s2.charAt(x) - 'A';
      if (index < 0)
        index += 59;
      alpha[index] = s1.charAt(x);
    }
    String s3 = readLine();
    for (int x = 0; x < s3.length(); x++) {
      int index = s3.charAt(x) - 'A';
      if (index < 0)
        index += 59;
      if (alpha[index] == '\u0000')
        System.out.print(".");
      else
        System.out.print(alpha[index]);
    }
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
