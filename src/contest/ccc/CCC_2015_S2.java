package contest.ccc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CCC_2015_S2 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    int j = readInt();
    int a = readInt();

    char[] J = new char[j];

    for (int x = 0; x < j; x++) {
      J[x] = readCharacter();
    }
    int count = 0;
    for (int x = 0; x < a; x++) {
      char c = readCharacter();
      int i = readInt() - 1;
      if (match(c, J[i])) {
        count++;
        J[i] = 'X';
      }
    }
    ps.println(count);

    ps.close();
  }

  private static boolean match (char c, char d) {
    if (c == 'S' && (d == 'S' || d == 'M' || d == 'L'))
      return true;
    if (c == 'M' && (d == 'M' || d == 'L'))
      return true;
    if (c == 'L' && d == 'L')
      return true;
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
