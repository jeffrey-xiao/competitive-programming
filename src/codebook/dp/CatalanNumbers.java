/*
 * Dynamic programming and sieve computation of Catalan numbers.
 * 
 * Time complexity:
 *  - Dynamic programming: O(N^2)
 *  - Sieve: O(N log N)
 */

package codebook.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class CatalanNumbers {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int n, m;
  static long[] dp;
  static ArrayList<HashMap<Integer, Integer>> factors = new ArrayList<HashMap<Integer, Integer>>();

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    m = readInt();
    dp = new long[n + 1];

    compute(n, m);

    out.println(dp[n] + " " + computeIth(n, m));
    out.close();
  }

  static void compute (int n, int m) {
    dp[0] = dp[1] = 1;
    for (int i = 2; i <= n; i++) {
      for (int j = 0; j <= i - 1; j++)
        dp[i] = (dp[i] + (dp[j] * dp[i - j - 1]) % m) % m;
      dp[i] %= m;
    }
  }

  static long computeIth (int n, int m) {
    fillFactors(n);
    HashMap<Integer, Integer> res = new HashMap<Integer, Integer>();
    for (int i = 2; i <= n; i++) {
      factor(i + n, 1, res);
      factor(i, -1, res);
    }
    long ans = 1;
    for (Map.Entry<Integer, Integer> entry : res.entrySet())
      ans = (ans * modpow(entry.getKey(), entry.getValue(), m)) % m;
    return ans;
  }

  static long modpow (long base, long pow, long m) {
    if (pow == 0)
      return 1;
    if (pow == 1)
      return base;
    if (pow % 2 == 0)
      return modpow(base * base, pow / 2, m) % m;
    return base * modpow(base * base, pow / 2, m) % m;
  }

  static void factor (int n, int type, HashMap<Integer, Integer> res) {
    for (Map.Entry<Integer, Integer> entry : factors.get(n).entrySet()) {
      if (!res.containsKey(entry.getKey()))
        res.put(entry.getKey(), 0);
      res.put(entry.getKey(), res.get(entry.getKey()) + entry.getValue() * type);
    }
  }

  static void fillFactors (int n) {
    for (int i = 0; i <= 2 * n; i++)
      factors.add(new HashMap<Integer, Integer>());
    boolean[] isComposite = new boolean[2 * n + 1];
    for (int i = 2; i <= 2 * n; i++) {
      if (!isComposite[i]) {
        for (int j = i; j <= 2 * n; j += i) {
          isComposite[j] = true;
          if (!factors.get(j).containsKey(i))
            factors.get(j).put(i, 0);
          int curr = j, cnt = 0;
          while (curr % i == 0) {
            cnt++;
            curr /= i;
          }
          factors.get(j).put(i, factors.get(j).get(i) + cnt);
        }
      }
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

  static char readCharacter () throws IOException {
    return next().charAt(0);
  }

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
