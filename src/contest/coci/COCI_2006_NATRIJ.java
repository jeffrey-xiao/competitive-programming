package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class COCI_2006_NATRIJ {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    String[] s1 = readLine().split(":");
    String[] s2 = readLine().split(":");
    int a = Integer.parseInt(s2[0]) - Integer.parseInt(s1[0]);
    int b = Integer.parseInt(s2[1]) - Integer.parseInt(s1[1]);
    int c = Integer.parseInt(s2[2]) - Integer.parseInt(s1[2]);
    if (c < 0) {
      c += 60;
      b--;
    }
    if (b < 0) {
      b += 60;
      a--;
    }
    if (a < 0) {
      a += 24;
    }
    if (a == 0 && b == 0 && c == 0)
      a = 24;
    System.out.printf("%02d:%02d:%02d", a, b, c);
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
