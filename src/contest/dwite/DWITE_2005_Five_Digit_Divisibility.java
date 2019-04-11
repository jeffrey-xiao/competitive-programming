package contest.dwite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DWITE_2005_Five_Digit_Divisibility {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int count = 0;

  public static void main(String[] args) throws IOException {
    for (int x = 0; x < 5; x++) {
      count = 0;
      int a = readInt();
      int b = readInt();
      getPermutations(a, b, "");
      System.out.println(count);
    }
  }

  private static void getPermutations(int a, int b, String string) {
    if (string.length() == a) {
      if (Integer.parseInt(string) % b == 0)
        count++;
      return;
    }
    for (int x = 1; x <= a; x++) {
      if (string.indexOf(Integer.toString(x)) == -1)
        getPermutations(a, b, string + x);
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
