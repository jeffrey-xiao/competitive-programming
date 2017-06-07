package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2011_Stage_2_The_Monkey_Dance {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {

    int n = readInt();
    while (n != 0) {
      int[] to = new int[n];
      boolean[] visited = new boolean[n];
      for (int x = 0; x < n; x++) {
        int a = readInt() - 1;
        int b = readInt() - 1;
        to[a] = b;
      }
      int maxCycle = 1;
      for (int x = 0; x < n; x++) {
        int curr = x;
        int cycleLength = 0;
        while (!visited[curr]) {
          visited[curr] = true;
          curr = to[curr];
          cycleLength++;
        }
        if (cycleLength != 0)
          maxCycle = lcm(maxCycle, cycleLength);
      }
      System.out.println(maxCycle);
      n = readInt();
    }
  }

  private static int lcm (int a, int b) {
    return (a * b) / gcf(a, b);
  }

  private static int gcf (int a, int b) {
    if (b == 0)
      return a;
    return gcf(b, a % b);
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
