package contest.woburn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Woburn_Challenge_2015_Stakeout {

  static final int MOD = (int) (1e9 + 7);
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;
  static int N, M, Q;
  static int[] l, r;
  static TreeSet<Building> ts = new TreeSet<Building>();
  static int[] min, lazy, pow;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));
    N = readInt();
    M = readInt();
    Q = readInt();

    min = new int[4 * N];
    lazy = new int[4 * N];
    pow = new int[M + 1];
    l = new int[M];
    r = new int[M];

    pow[0] = 1;
    for (int i = 1; i <= M; i++)
      pow[i] = pow[i - 1] * 2 % MOD;

    for (int i = 0; i < N; i++)
      ts.add(new Building(readInt(), 0));

    int cnt = 0;
    for (Building b : ts)
      b.index = cnt++;

    if (ts.size() != N)
      throw new IOException();

    for (int j = 0; j < M; j++) {
      int agent = readInt();
      int range = readInt();

      Building left = ts.ceiling(new Building(agent - range, 0));
      Building right = ts.floor(new Building(agent + range, 0));

      if (left == null || right == null || left.index > right.index) {
        l[j] = r[j] = -1;
      } else {
        l[j] = left.index;
        r[j] = right.index;
      }
    }

    for (int i = 0; i < Q; i++) {
      int val = readInt();

      for (int j = 0; j < M; j++)
        if (l[j] != -1)
          update(1, 0, N - 1, l[j], r[j], 1);

      if (min[1] < val) {
        for (int j = 0; j < M; j++)
          if (l[j] != -1)
            update(1, 0, N - 1, l[j], r[j], -1);
        out.println(-1);
        continue;
      }

      long cost = 0;

      ArrayList<Integer> sub = new ArrayList<Integer>();

      for (int j = M - 1; j >= 0; j--) {
        if (l[j] != -1)
          update(1, 0, N - 1, l[j], r[j], -1);

        if (min[1] < val) {
          cost = (cost + pow[j + 1]) % MOD;
          update(1, 0, N - 1, l[j], r[j], 1);
          sub.add(j);
        }
      }

      for (int j : sub)
        update(1, 0, N - 1, l[j], r[j], -1);

      out.println(cost);
    }
    out.close();
  }

  static void update(int n, int l, int r, int ql, int qr, int add) throws IOException {
    if (ql == l && qr == r) {
      min[n] += add;
      lazy[n] += add;
      return;
    }

    pushDown(n);

    int mid = (l + r) >> 1;

    if (qr <= mid)
      update(n << 1, l, mid, ql, qr, add);

    else if (ql > mid)
      update(n << 1 | 1, mid + 1, r, ql, qr, add);

    else {
      update(n << 1, l, mid, ql, mid, add);
      update(n << 1 | 1, mid + 1, r, mid + 1, qr, add);
    }

    min[n] = Math.min(min[n << 1], min[n << 1 | 1]);
  }

  static void pushDown(int n) {
    if (lazy[n] != 0) {
      min[n << 1] += lazy[n];
      min[n << 1 | 1] += lazy[n];
      lazy[n << 1] += lazy[n];
      lazy[n << 1 | 1] += lazy[n];
      lazy[n] = 0;
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

  static class Building implements Comparable<Building> {
    int pos, index;

    Building(int pos, int index) {
      this.pos = pos;
      this.index = index;
    }

    @Override
    public int compareTo(Building o) {
      if (pos < o.pos)
        return -1;
      else if (pos > o.pos)
        return 1;
      else
        return 0;
    }
  }
}
