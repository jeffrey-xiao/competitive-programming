package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2010_Stage_2_Shuffle {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int[] cycles = new int[27];
  static boolean[] visited = new boolean[27];

  public static void main (String[] args) throws IOException {
    char[] c = new char[27];

    for (int x = 0; x < 27; x++)
      c[x] = next().charAt(0);

    for (int x = 0; x < 27; x++)
      findCycles(c, toChar(x), 0);

    int n = readInt();
    char[] s = next().toCharArray();

    int lcm = cycles[0];
    for (int x = 1; x < 27; x++)
      lcm = lcm(lcm, cycles[x]);
    n %= lcm;

    for (int x = 0; x < n; x++)
      s = nextState(c, s);
    System.out.println(new String(s));
  }

  private static int lcm (int a, int b) {
    return a * b / (gcf(a, b));
  }

  private static char[] nextState (char[] table, char[] s) {
    char[] c = s;
    for (int x = 0; x < s.length; x++) {
      c[x] = table[toCode(c[x])];
    }
    return c;
  }

  private static int gcf (int a, int b) {
    return b == 0 ? a : gcf(b, a % b);
  }

  public static int findCycles (char[] table, char curr, int count) {
    if (visited[toCode(curr)])
      return count;
    visited[toCode(curr)] = true;
    cycles[toCode(curr)] = findCycles(table, table[toCode(curr)], count + 1);

    return cycles[toCode(curr)];
  }

  public static char toChar (int x) {
    if (x == 26)
      x = 30;
    return (char)(x + 65);
  }

  public static int toCode (char c) {
    int x = c - 65;
    if (x == 30)
      x = 26;
    return x;
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

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
