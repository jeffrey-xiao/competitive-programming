package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class COCI_2009_IKS {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
  static HashMap<Integer, Integer> revMap = new HashMap<Integer, Integer>();
  static int count;
  static int[] total = new int[168];

  public static void main(String[] args) throws IOException {
    int n = readInt();
    int[][] primes = new int[n][168];
    for (int x = 0; x < n; x++) {
      int i = readInt();
      primeFactor(i, primes[x]);
    }
    int gcf = 1;
    int moves = 0;
    for (int y = 0; y < 168; y++) {
      total[y] /= n;
      if (total[y] != 0)
        gcf *= (int) (Math.pow(revMap.get(y), total[y]));
    }
    for (int x = 0; x < n; x++) {
      for (int y = 0; y < 168; y++) {
        int increase = total[y];
        if (increase > primes[x][y]) {
          moves += increase - primes[x][y];
        }
      }
    }
    System.out.println(gcf + " " + moves);
  }

  private static void primeFactor(int i, int[] primes) {
    for (int x = 2; x * x <= i; x++)
      while (i % x == 0) {
        if (map.get(x) == null) {
          map.put(x, count);
          revMap.put(count, x);
          count++;
        }
        primes[map.get(x)]++;
        total[map.get(x)]++;
        i /= x;
      }
    if (i > 1) {
      if (map.get(i) == null) {
        map.put(i, count);
        revMap.put(count, i);
        count++;
      }
      primes[map.get(i)]++;
      total[map.get(i)]++;
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
