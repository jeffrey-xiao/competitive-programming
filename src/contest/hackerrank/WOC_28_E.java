package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class WOC_28_E {

  static final int FULL_MASK = Integer.MAX_VALUE;
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;
  static int N, K;
  static HashMap<Integer, Double> dp = new HashMap<Integer, Double>();

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    K = readInt();

    int initial = 0;
    String input = next();

    for (int i = 0; i < N; i++)
      if (input.charAt(i) == 'W')
        initial |= 1 << i;

    out.println(compute(initial, 0));
    out.close();
  }

  static double compute(int mask, int k) {
    if (k == K)
      return 0.0;
    if (dp.containsKey(mask | 1 << (N - k)))
      return dp.get(mask | 1 << (N - k));
    if (dp.containsKey(reverse(mask, N - k) | 1 << (N - k)))
      return dp.get(reverse(mask, N - k) | 1 << (N - k));
    double ret = 0;
    for (int i = 0; i < (N - k + 1) / 2; i++) {
      int a = (mask & 1 << i) > 0 ? 1 : 0;
      int b = (mask & 1 << (N - k - i - 1)) > 0 ? 1 : 0;
      int maskA = remove(mask, i);
      int maskB = remove(mask, N - k - i - 1);
      double prob = ((N - k) % 2 == 1 && i == (N - k) / 2) ? 1.0 / (N - k) : 2.0 / (N - k);

      ret += Math.max(compute(maskA, k + 1) + a, compute(maskB, k + 1) + b) * prob;
    }
    dp.put(mask | 1 << (N - k), ret);
    return ret;
  }

  static int reverse(int mask, int len) {
    int ret = 0;
    for (int i = 0; i < len; i++)
      if ((mask & 1 << i) > 0)
        ret |= 1 << (len - i - 1);
    return ret;
  }

  static int remove(int bitmask, int i) {
    int upperMask = FULL_MASK ^ ((1 << (i + 1)) - 1);
    int lowerMask = (1 << i) - 1;

    return (bitmask & upperMask) >> 1 | (bitmask & lowerMask);
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

  static char readCharacter() throws IOException {
    return next().charAt(0);
  }

  static String readLine() throws IOException {
    return br.readLine().trim();
  }
}
