package contest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

public class Distinct_Prime_Factors {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static PrintStream ps = new PrintStream(System.out);

  public static void main (String[] args) throws IOException {
    int a = readInt();
    int b = readInt();
    boolean[] primes = new boolean[b + 1];
    int[] nums = new int[b + 1];
    primes[1] = true;
    for (int x = 2; x < primes.length; x++) {
      if (primes[x] == false)
        for (int y = x; y < primes.length && y >= 0; y += x) {
          primes[y] = true;
          nums[y]++;
        }
      if (x >= a && x <= b)
        ps.print(nums[x] == 0 ? nums[x] + 1 : nums[x] + "\n");
    }
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
