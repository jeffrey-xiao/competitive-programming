package contest.hackerrank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class WOC_28_G {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static final int MOD = 998244353;

  static int N, M, K;
  static long[][] prob;
  static long[] dice;
  static int[] next;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    M = readInt();
    K = readInt();

    prob = new long[N][N];
    next = new int[N];
    dice = new long[M];

    for (int i = 0; i < N; i++)
      next[i] = readInt() - 1;

    for (int i = 0; i < M; i++)
      dice[i] = readInt();

    for (int i = 0; i < N; i++)
      prob[i] = getOccurrences(i, M);

    long[] state = new long[N];
    Arrays.fill(state, divMod(1, N, MOD));

    for (int i = 0; i < K; i++) {
      state = multiply(state, prob);
    }

    for (int i = 0; i < N; i++)
      out.println(state[i]);
    out.close();
  }

  static long[] getOccurrences (int u, int m) {
    long[] ret = new long[N];
    for (int i = 0; i < m; i++) {
      ret[u] = (ret[u] + dice[i]) % MOD;
      u = next[u];
    }
    return ret;
  }

  static long[] multiply (long[] state, long[][] transition) {
    long[] ret = new long[N];
    for (int j = 0; j < N; j++)
      for (int k = 0; k < N; k++)
        ret[j] = (ret[j] + state[k] * transition[k][j]) % MOD;
    return ret;
  }

  // O(log P)
  static long divMod (long i, long j, long p) {
    return i * pow(j, p - 2, p) % p;
  }

  // O(log power)
  static long pow (long base, long pow, long mod) {
    if (pow == 0)
      return 1;
    if (pow == 1)
      return base;
    if (pow % 2 == 0)
      return pow(base * base % mod, pow / 2, mod);
    return base * pow(base * base % mod, pow / 2, mod) % mod;
  }

  static long mod (long a, long b) {
    return ((a % b) + b) % b;
  }

  // precondition: m > 0 && gcd(a, m) = 1
  public static long modInverse (long a, long m) {
    a = mod(a, m);
    return a == 0 ? 0 : mod((1 - modInverse(m % a, a) * m) / a, m);
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
