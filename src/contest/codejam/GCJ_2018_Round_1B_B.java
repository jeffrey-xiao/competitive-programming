import java.io.*;
import java.util.*;

public class GCJ_2018_Round_1B_B {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T, S;
  static int[] W, E;
  static int max, count;
  static HashSet<Pair> pairs;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    for (int t = 1; t <= T; t++) {
      S = readInt();
      W = new int[S];
      E = new int[S];
      pairs = new HashSet<Pair>();
      max = 0;
      count = 0;
      for (int i = 0; i < S; i++) {
        int D = readInt();
        int A = readInt();
        int B = readInt();
        W[i] = D + A;
        E[i] = D - B;
      }
      solve(0, S - 1);
      out.printf("Case #%d: %d %d%n", t, max, count);
    }

    out.close();
  }

  static void solve(int l, int r) {
    if (l == r) {
      setMax(l, r);
      return;
    }
    int mid = (l + r) >> 1;
    solve(l, mid);
    solve(mid + 1, r);

    State s;
    s = computeLeft(l, mid, W[mid], null);
    setMax(s.x, computeRight(mid + 1, r, s.w, s.e).x);

    s = computeRight(mid + 1, r, W[mid], null);
    setMax(computeLeft(l, mid, s.w, s.e).x, s.x);

    s = computeLeft(l, mid, W[mid + 1], null);
    setMax(s.x, computeRight(mid + 1, r, s.w, s.e).x);

    s = computeRight(mid + 1, r, W[mid + 1], null);
    setMax(computeLeft(l, mid, s.w, s.e).x, s.x);

    s = computeLeft(l, mid, null, E[mid]);
    setMax(s.x, computeRight(mid + 1, r, s.w, s.e).x);

    s = computeRight(mid + 1, r, null, E[mid]);
    setMax(computeLeft(l, mid, s.w, s.e).x, s.x);

    s = computeLeft(l, mid, null, E[mid + 1]);
    setMax(s.x, computeRight(mid + 1, r, s.w, s.e).x);

    s = computeRight(mid + 1, r, null, E[mid + 1]);
    setMax(computeLeft(l, mid, s.w, s.e).x, s.x);
  }

  static void setMax(int l, int r) {
    if (pairs.contains(new Pair(l, r))) {
      return;
    }
    int sum = r - l + 1;
    if (sum > max) {
      max = sum;
      count = 0;
      pairs.clear();
    }
    if (sum == max) {
      count++;
      pairs.add(new Pair(l, r));
    }
  }

  static State computeRight(int l, int r, Integer w, Integer e) {
    for (int i = l; i <= r; i++) {
      if ((w != null && W[i] == w) || (e != null && E[i] == e)) {
        continue;
      } else if (w == null) {
        w = W[i];
        continue;
      } else if (e == null) {
        e = E[i];
        continue;
      }
      return new State(i - 1, w, e);
    }
    return new State(r, w, e);
  }

  static State computeLeft(int l, int r, Integer w, Integer e) {
    for (int i = r; i >= l; i--) {
      if ((w != null && W[i] == w) || (e != null && E[i] == e)) {
        continue;
      } else if (w == null) {
        w = W[i];
        continue;
      } else if (e == null) {
        e = E[i];
        continue;
      }
      return new State(i + 1, w, e);
    }
    return new State(l, w, e);
  }

  static class State {
    int x;
    Integer w, e;
    State(int x, Integer w, Integer e) {
      this.x = x;
      this.w = w;
      this.e = e;
    }
  }

  static class Pair {
    int x, y;
    Pair(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public int hashCode() {
      return x * 31 + y;
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof Pair) {
        Pair p = (Pair)o;
        return p.x == x && p.y == y;
      }
      return false;
    }
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
