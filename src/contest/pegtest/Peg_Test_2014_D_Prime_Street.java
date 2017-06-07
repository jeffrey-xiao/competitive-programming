package contest.pegtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Peg_Test_2014_D_Prime_Street {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static final int K = 1500000;

  public static void main (String[] args) throws IOException {
    long n = readLong();
    int l = readInt();
    boolean[] sieve = new boolean[K];
    long[] primes = new long[110001];
    long[] compos = new long[110001];
    primes[1] = 2;
    int primeCounter = 2;
    int compoCounter = 1;
    for (int x = 4; x < K; x += 2)
      sieve[x] = true;
    for (int x = 3; x < K; x++) {
      if (!sieve[x] && primeCounter <= 110000) {
        primes[primeCounter] = x + primes[primeCounter - 1];
        primeCounter++;
        for (long y = x; y < K; y += x)
          sieve[(int)y] = true;
      } else if (sieve[x] && compoCounter <= 110000) {
        compos[compoCounter] = x + compos[compoCounter - 1];
        compoCounter++;
      }
    }
    long min = Integer.MAX_VALUE;
    int index = -1;
    for (int x = 0; x < 110000 - l; x++) {
      long sum = Math.abs(primes[x + l] - primes[x] - n - (compos[x + l] - compos[x]));
      if (sum < min) {
        min = sum;
        index = x;
      }
    }
    System.out.println(index + 1);
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
