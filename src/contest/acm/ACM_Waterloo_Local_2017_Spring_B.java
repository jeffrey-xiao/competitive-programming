package contest.acm;

import java.io.*;
import java.util.*;

public class ACM_Waterloo_Local_2017_Spring_B {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    int size = 65000;
    boolean[] prime = new boolean[size];
    ArrayList<Long> primes = new ArrayList<Long>();

    for (long i = 2; i < 65000; i++) {
      if (!prime[(int)i]) {
        primes.add(i);
        for (long j = i * i; j < 65000; j += i) {
          prime[(int)j] = true;
        }
      }
    }

    int N = readInt();

    for (int i = 0; i < N; i++) {
      long n = readLong();
      long j = Math.max(2, n) - 1;

    inner:
      while (true) {
        j++;
        for (long p : primes) {
          if (p * p > j) {
            break inner;
          }
          if (j % p == 0) {
            continue inner;
          }
        }
      }
      out.println(j);
    }

    out.close();
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
