package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Bad_Paths {

  static final int MOD = (int) (1e9 + 7);
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;
  static long[] fact = new long[1000001];
  static long[] prefix = new long[1000001];

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    fact[0] = 1;
    prefix[0] = 1;
    for (int i = 1; i <= 1000000; i++) {
      fact[i] = fact[i - 1] * i % MOD;
      prefix[i] = (prefix[i - 1] + divMod(1, fact[i], MOD)) % MOD;
    }

    int N = readInt();

    for (int i = 0; i < N; i++) {
      int val = readInt();
      long total = (fact[val] * prefix[val] - (val + 1)) % MOD;
      long tree = (long) val * (val - 1) % MOD;
      long ans = ((total - tree) % MOD + MOD) % MOD;
      ans = divMod(ans, 2, MOD);
      out.printf("%d\n", ans);
    }

    out.close();
  }

  // O(log P)
  static long divMod(long i, long j, long p) {
    return i * pow(j, p - 2, p) % p;
  }

  static long pow(long base, long pow, long mod) {
    if (pow == 0)
      return 1;
    if (pow == 1)
      return base;
    if (pow % 2 == 0)
      return pow(base * base % mod, pow / 2, mod);
    return base * pow(base * base % mod, pow / 2, mod) % mod;
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
