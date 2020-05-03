package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2020_Round_1C_C {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T;
  static int N, D;
  static Long[] A;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();

    for (int t = 1; t <= T; t++) {
      N = readInt();
      D = readInt();

      HashMap<Fraction, State> candidates = new HashMap<>();

      A = new Long[N];
      for (int i = 0; i < N; i++) {
        A[i] = readLong();
      }
      Arrays.sort(A);

      for (int i = 0; i < N; i++) {
        for (int j = 1; j <= D; j++) {
          Fraction f = new Fraction(A[i], j);
          State state = candidates.get(f);
          if (state == null) {
            state = new State(0, 0);
          }
          if (j + state.served <= D) {
            state.count++;
            state.served += j;
            candidates.put(f, state);
          }
        }
      }

      ArrayList<Fraction> fs = new ArrayList<>();
      for (HashMap.Entry<Fraction, State> e : candidates.entrySet()) {
        fs.add(e.getKey());
      }
      Collections.sort(fs);

      int lo = 0;
      int hi = candidates.size() - 1;
      while (lo <= hi) {
        int mid = (lo + hi) >> 1;
        long served = 0;
        for (int i = 0; i < N; i++) {
          served += A[i] * fs.get(mid).d / fs.get(mid).n;
        }
        if (served >= D) {
          lo = mid + 1;
        } else {
          hi = mid - 1;
        }
      }

      int ans = D - 1;
      for (int i = 0; i < lo; i++) {
        ans = Math.min(ans, D - candidates.get(fs.get(i)).count);
      }
      out.printf("Case #%d: %d%n", t, ans);
    }

    out.close();
  }

  static class State {
    int count, served;
    State(int count, int served) {
      this.count = count;
      this.served = served;
    }
  }

  static class Fraction implements Comparable<Fraction> {
    long n, d;
    Fraction(long n, long d) {
      long gcd = gcd(n, d);
      this.n = n / gcd;
      this.d = d / gcd;
    }

    @Override
    public int compareTo(Fraction f) {
      return new Long(n * f.d).compareTo(f.n * d);
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof Fraction) {
        Fraction f = (Fraction)o;
        return n == f.n && d == f.d;
      }
      return false;
    }

    @Override
    public int hashCode() {
      return new Long(n).hashCode() * 31 + new Long(d).hashCode();
    }
  }

  static long gcd(long a, long b) {
    return b == 0 ? a : gcd(b, a % b);
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
