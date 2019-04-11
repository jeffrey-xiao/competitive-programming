package contest.hackercup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FHC_2017_Round_1_Beach_Umbreallas {

  static final long MOD = (long) (1e9 + 7);
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;
  static long[] fact = new long[2001];

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    fact[0] = 1;
    for (int i = 1; i <= 2000; i++)
      fact[i] = fact[i - 1] * i % MOD;

    int T = readInt();

    for (int t = 1; t <= T; t++) {
      int N = readInt();
      int M = readInt();

      int maxR = 0;
      int sumR = 0;

      int[] R = new int[N];

      for (int i = 0; i < N; i++) {
        maxR = Math.max(maxR, R[i] = readInt());
        sumR += 2 * R[i];
      }

      if (N == 1) {
        out.printf("Case #%d: %d\n", t, M);
        continue;
      }

      int[] occ = new int[maxR * 2 + 1];

      for (int i = 0; i < N; i++)
        for (int j = i + 1; j < N; j++)
          occ[R[i] + R[j]]++;

      long ans = 0;

      // fixing two umbrella
      for (int i = 2; i <= maxR * 2; i++) {
        int spaces = M + i - sumR;
        if (spaces >= 1)
          ans = (ans + occ[i] * choose(spaces + N - 1, N)) % MOD;
      }
      ans = (ans * fact[N - 2] * 2) % MOD;

      out.printf("Case #%d: %d\n", t, ans);
    }

    out.close();
  }

  static long choose(int n, int k) {
    if (k > n - k)
      k = n - k;
    long ret = 1;
    for (int i = 0; i < k; i++)
      ret = ret * (n - i) % MOD;
    return divMod(ret, fact[k]);
  }

  static long divMod(long i, long j) {
    return i * pow(j, MOD - 2) % MOD;
  }

  static long pow(long a, long b) {
    if (b == 0)
      return 1;
    if (b == 1)
      return a;
    if (b % 2 == 0)
      return pow(a * a % MOD, b / 2);
    return a * pow(a * a % MOD, b / 2) % MOD;
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
