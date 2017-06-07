package contest.usaco_other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/*
ID: jeffrey40
LANG: JAVA
TASK: combo
 */
import java.util.StringTokenizer;

public class combo {
  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new FileReader("combo.in"));
    pr = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));

    int n = readInt();
    int a1 = readInt();
    int a2 = readInt();
    int a3 = readInt();
    int b1 = readInt();
    int b2 = readInt();
    int b3 = readInt();
    int count = 0;
    for (int x = 1; x <= n; x++) {
      for (int y = 1; y <= n; y++) {
        for (int z = 1; z <= n; z++) {
          if (close(x, y, z, a1, a2, a3, n) || close(x, y, z, b1, b2, b3, n))
            count++;
        }
      }
    }
    pr.println(count);

    pr.close();
    System.exit(0);
  }

  private static boolean close (int x, int y, int z, int a1, int a2, int a3, int n) {
    if (valid(x, a1, n) && valid(y, a2, n) && valid(z, a3, n))
      return true;
    return false;
  }

  private static boolean valid (int x, int a1, int n) {
    int abs = Math.abs(x - a1);
    if (abs <= 2)
      return true;
    if (abs >= n - 2)
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

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
