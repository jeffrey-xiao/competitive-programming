package contest.hackerearth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Clash_May_2016_P1 {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static long N;
  static ArrayList<Integer> primes;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readLong();

    primes = getPrimesEratosthenes((int) (Math.ceil(Math.sqrt(N))));

    long res = 0;
    for (long i = (N + 2) / 2; i <= N; i++) {
      if (isPrimePower(i)) {
        res = i;
        break;
      }
    }

    out.println(res);
    out.close();
  }

  static ArrayList<Integer> getPrimesEratosthenes(int N) {
    boolean[] prime = new boolean[N + 1];
    ArrayList<Integer> ret = new ArrayList<Integer>();

    Arrays.fill(prime, true);

    for (int i = 2; i * i <= N; i++)
      if (prime[i])
        for (int j = i * i; j <= N; j += i)
          prime[j] = false;

    for (int i = 2; i <= N; i++)
      if (prime[i])
        ret.add(i);

    return ret;
  }

  static boolean isPrimePower(long n) {
    boolean hasOtherFactor = false;
    for (int prime : primes) {
      if (n % prime == 0) {
        if (hasOtherFactor)
          return false;
        while (n % prime == 0) {
          n /= prime;
        }
        hasOtherFactor = true;
      }
    }
    if (n != 1)
      return !hasOtherFactor;
    return true;
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
