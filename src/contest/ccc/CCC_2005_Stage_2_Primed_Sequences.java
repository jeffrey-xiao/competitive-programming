package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2005_Stage_2_Primed_Sequences {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    main : for (int t = readInt(); t > 0; t--) {
      int n = readInt();
      int[] sum = new int[n + 1];
      for (int x = 1; x <= n; x++) {
        int i = readInt();
        sum[x] = i + sum[x - 1];
      }
      for (int x = 2; x <= n; x++) {
        for (int y = 0, z = y + x; z <= n; y++, z++) {
          if (isPrime(sum[z] - sum[y])) {
            System.out.print("Shortest primed subsequence is length " + x + ":");
            for (int y1 = y; y1 < z; y1++) {
              System.out.print(" " + (sum[y1 + 1] - sum[y1]));
            }
            System.out.println();
            continue main;
          }
        }
      }
      System.out.println("This sequence is anti-primed.");
    }
  }

  private static boolean isPrime (int n) {
    if (n <= 1)
      return false;
    if (n == 2)
      return true;
    if (n % 2 == 0)
      return false;
    if (n < 9)
      return true;
    if (n % 3 == 0)
      return false;

    long counter = 5;
    while ((counter * counter) <= n) {
      if (n % counter == 0)
        return false;
      if (n % (counter + 2) == 0)
        return false;
      counter += 6;
    }

    return true;
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
