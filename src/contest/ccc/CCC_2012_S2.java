package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2012_S2 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    char[] input = next().toCharArray();
    int total = 0;
    for (int x = 0; x < input.length; x += 2) {
      int curr = toNum(input[x + 1]);
      if (x + 2 >= input.length || (x + 2 < input.length && curr >= toNum(input[x + 3])))
        total += (input[x] - 48) * curr;
      else
        total -= (input[x] - 48) * curr;

    }
    System.out.println(total);
  }

  public static int toNum(char c) {
    if (c == 'I')
      return 1;
    else if (c == 'V')
      return 5;
    else if (c == 'X')
      return 10;
    else if (c == 'L')
      return 50;
    else if (c == 'C')
      return 100;
    else if (c == 'D')
      return 500;
    return 1000;
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
