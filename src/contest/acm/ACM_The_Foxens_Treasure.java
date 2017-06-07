package contest.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ACM_The_Foxens_Treasure {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    testCase : for (int t = readInt(); t > 0; t--) {
      int lcm = 1;
      int n = readInt();
      int[][] foxens = new int[n][3];
      for (int x = 0; x < n; x++) {
        foxens[x][0] = readInt();
        foxens[x][1] = readInt();
        foxens[x][2] = readInt();
        lcm = lcm(lcm, foxens[x][0] + foxens[x][1]);
      }
      main : for (int x = 0; x <= lcm; x++) {
        for (int y = 0; y < n; y++) {
          if ((x + foxens[y][2]) % (foxens[y][0] + foxens[y][1]) < foxens[y][0])
            continue main;
        }
        System.out.println(x);
        continue testCase;
      }
      System.out.println("Foxen are too powerful");
    }
  }

  private static int lcm (int a, int b) {
    return (a * b) / gcf(a, b);
  }

  private static int gcf (int a, int b) {
    return b == 0 ? a : gcf(b, a % b);
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
