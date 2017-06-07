package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2009_J2 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    int a = readInt();
    int b = readInt();
    int c = readInt();
    int n = readInt();
    int count = 0;
    for (int x = 0; x <= n / a; x++) {
      for (int y = 0; y <= n / b; y++) {
        for (int z = 0; z <= n / c; z++) {
          if (x * a + y * b + z * c <= n && x + y + z > 0) {
            System.out.printf("%d Brown Trout, %d Northern Pike, %d Yellow Pickerel\n", x, y, z);
            count++;
          }
        }
      }
    }
    System.out.println("Number of ways to catch fish: " + count);
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
