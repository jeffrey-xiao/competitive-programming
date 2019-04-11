package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DMOPC_2015_Steins_Number {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int Q;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    Q = readInt();

    for (int i = 0; i < Q; i++) {
      long l = readLong();
      long r = readLong();
      out.println(compute(r) - compute(l - 1));
    }

    out.close();
  }

  static long compute(long n) {
    long base = 1;
    for (int i = 0; i < 37; i++)
      base *= 3;
    ArrayList<Integer> baseThree = new ArrayList<Integer>();
    while (base != 0) {
      baseThree.add((int)(n / base));
      n %= base;
      base /= 3;
    }
    boolean toOne = false;
    for (int i = 0; i < baseThree.size(); i++) {
      if (baseThree.get(i) == 2) {
        toOne = true;
        baseThree.set(i, 1);
      }
      if (toOne) {
        baseThree.set(i, 1);
      }
    }
    long res = 0;
    base = 1;
    for (int i = baseThree.size() - 1; i >= 0; i--) {
      res += baseThree.get(i) * base;
      base *= 2;
    }
    return res;
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
