package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class COCI_2009_RIMSKI {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    String next = next();
    int[] d = new int[100];
    for (int x = 0; x < next.length(); x++)
      d[next.charAt(x)]++;
    if (d['C'] == 1) {
      System.out.print("XC");
      d['X']--;
      d['C']--;
    }
    if (d['L'] == 1) {
      if (d['X'] == 1 || (d['X'] == 2 && d['I'] == 1 && d['V'] == 0)) {
        System.out.print("X");
        d['X']--;
      }
      System.out.print("L");
      d['L']--;
    }
    while (d['X'] > 1) {
      System.out.print("X");
      d['X']--;
    }
    if (d['X'] == 1 && d['V'] == 0 && d['I'] == 1) {
      System.out.print("IX");
      d['I']--;
      d['X']--;
    }
    if (d['X'] == 1) {
      System.out.print("X");
      d['X']--;
    }
    if (d['I'] == 1 && d['V'] == 1) {
      d['I']--;
      System.out.print("I");
    }
    if (d['V'] == 1) {
      System.out.print("V");
      d['V']--;
    }
    while (d['I'] > 0) {
      System.out.print("I");
      d['I']--;
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
