package contest.ccc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class MockCCC_2015_S1 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int P = readInt();
    int U = readInt();
    int R1 = readInt();
    int R2 = readInt();
    HashSet<Ratio> hs = new HashSet<Ratio>();
    for (int x = 1; x * (R1 + R2) <= U; x++) {
      // 2 : 1
      int L = (U - (R1 + R2) * x);
      if (R1 * x * 2 + R2 * x * 1 + L * -1 == P)
        hs.add(new Ratio(R1 * x, R2 * x, L));
      if (R1 * x * 1 + R2 * x * 2 + L * -1 == P)
        hs.add(new Ratio(R2 * x, R1 * x, L));

      // 1 : -1
      if (R1 * x * 1 + R2 * x * -1 + L * 2 == P)
        hs.add(new Ratio(L, R1 * x, R2 * x));
      if (R1 * x * -1 + R2 * x * 1 + L * 2 == P)
        hs.add(new Ratio(L, R2 * x, R1 * x));

      // 2 : -1
      if (R1 * x * 2 + R2 * x * -1 + L * 1 == P)
        hs.add(new Ratio(R1 * x, L, R2 * x));
      if (R1 * x * -1 + R2 * x * 2 + L * 1 == P)
        hs.add(new Ratio(R2 * x, L, R1 * x));
    }
    System.out.println(hs.size());
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

  static class Ratio {
    int DU, U, D;

    Ratio(int DU, int U, int D) {
      this.DU = DU;
      this.U = U;
      this.D = D;
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof Ratio) {
        Ratio r = (Ratio)o;
        return DU == r.DU && U == r.U && D == r.D;
      }
      return false;
    }

    @Override
    public int hashCode() {
      return new Integer(DU).hashCode() * 31 * 31 + new Integer(D).hashCode() * 31 + new Integer(U).hashCode();
    }
  }
}
