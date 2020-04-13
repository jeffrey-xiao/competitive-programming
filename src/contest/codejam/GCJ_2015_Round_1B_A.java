package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2015_Round_1B_A {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    for (int t = 1; t <= T; t++) {
      long N = readLong();
      out.printf("Case #%d: %d%n", t, solve(N) + 1);
    }

    out.close();
  }

  static long solve(long N) {
    ArrayList<Integer> n = new ArrayList<Integer>();
    long temp = N;
    while (temp > 0) {
      n.add((int)(temp % 10));
      temp /= 10;
    }
    long digitAns = 0;
    long a = 0, b = 9;
    long base = 1;
    for (int i = 1; i < n.size(); i++) {
      digitAns += a + b;
      if (i % 2 == 1) {
        a = a * 10 + 9;
      } else {
        b = b * 10 + 9;
      }
      if (i > 1) {
        digitAns++;
      }
      base *= 10;
    }
    long flipAns = 1;
    long countAns = N - base;
    boolean allZeroes = true;
    for (int i = 0; i < (n.size() + 1) / 2; i++) {
      flipAns += (n.get(i) - (i == 0 ? 1 : 0)) * (long)Math.pow(10, i);
      if (n.get(i) != 0) {
        allZeroes = false;
      }
    }
    if (allZeroes) {
      return Math.min(countAns + digitAns, solve(N - 1) + 1);
    }
    for (int i = (n.size() + 1) / 2; i < n.size(); i++) {
      flipAns += n.get(i) * (long)Math.pow(10, (n.size() - i - 1));
    }
    return Math.min(countAns + digitAns, digitAns + flipAns);
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
