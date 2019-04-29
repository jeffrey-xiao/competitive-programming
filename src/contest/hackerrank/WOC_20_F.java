package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class WOC_20_F {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, D, K;
  static int[] L, R;
  static Fenwick[] seg;
  static ArrayList<ArrayList<Integer>> occ;
  static int[] ans;
  static ArrayList<ArrayList<Integer>> start;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    D = readInt();
    K = readInt();

    occ = new ArrayList<ArrayList<Integer>>(D + 1);
    seg = new Fenwick[4 * D];
    ans = new int[D + 1];
    L = new int[N];
    R = new int[N];
    start = new ArrayList<ArrayList<Integer>>(D + 1);

    for (int i = 0; i <= D; i++) {
      occ.add(new ArrayList<Integer>());
      start.add(new ArrayList<Integer>());
    }

    for (int i = 0; i < 4 * D; i++)
      seg[i] = new Fenwick();

    for (int i = 0; i < N; i++) {
      L[i] = readInt();
      R[i] = readInt();
      start.get(L[i]).add(R[i]);
      occ.get(R[i] - L[i] + 1).add(i);
    }

    init(1, 1, D);

    for (int i = D; i >= 1; i--) {
      for (int j : occ.get(i))
        update(1, 1, D, L[j], j);
      ans[i] = compute(1, D);
    }

    for (int i = 0; i < K; i++)
      out.printf("%d%n", ans[readInt()]);
    out.close();
  }

  static int query(int n, int l, int r, int ql, int qr, int val) {
    if (l == ql && r == qr)
      return seg[n].query(val);

    int mid = (l + r) >> 1;

    if (qr <= mid)
      return query(n << 1, l, mid, ql, qr, val);
    else if (ql > mid)
      return query(n << 1 | 1, mid + 1, r, ql, qr, val);
    return Math.min(query(n << 1, l, mid, ql, mid, val), query(n << 1 | 1, mid + 1, r, mid + 1, qr, val));
  }

  static int compute(int l, int r) {
    if (l > r)
      return 0;

    int index = query(1, 1, D, l, r, r);

    if (index == 1 << 30)
      return 0;

    return compute(l, L[index] - 1) + compute(R[index] + 1, r) + (R[index] - L[index] + 1);
  }

  static void init(int n, int l, int r) {
    if (l == r) {
      seg[n].init(l);
      return;
    }

    int mid = (l + r) >> 1;

    init(n << 1, l, mid);
    init(n << 1 | 1, mid + 1, r);
    seg[n].init(seg[n << 1].values, seg[n << 1 | 1].values);
  }

  static void update(int n, int l, int r, int x, int val) {
    seg[n].update(R[val], val);
    if (l == x && r == x)
      return;

    int mid = (l + r) >> 1;

    if (x <= mid)
      update(n << 1, l, mid, x, val);
    else
      update(n << 1 | 1, mid + 1, r, x, val);
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

  static class Fenwick {
    ArrayList<Integer> values;
    int sz;
    int[] tree;

    Fenwick() {
      values = new ArrayList<Integer>();
      values.add(-1);
    }

    void init(int x) {
      values.addAll(start.get(x));
      Collections.sort(values);

      ArrayList<Integer> newValues = new ArrayList<Integer>();
      newValues.add(values.get(0));

      for (int i = 1; i < values.size(); i++)
        if (values.get(i) != values.get(i - 1))
          newValues.add(values.get(i));

      values = newValues;

      sz = values.size();

      tree = new int[sz];
      Arrays.fill(tree, 1 << 30);
    }

    void init(ArrayList<Integer> a, ArrayList<Integer> b) {
      int i = 0, j = 0;
      while (i < a.size() || j < b.size()) {
        int valToAdd = 0;
        if (i == a.size() || (j != b.size() && a.get(i) > b.get(j))) {
          valToAdd = b.get(j++);
        } else {
          valToAdd = a.get(i++);
        }
        if (valToAdd != values.get(values.size() - 1))
          values.add(valToAdd);
      }

      sz = values.size();

      tree = new int[sz];
      Arrays.fill(tree, 1 << 30);
    }

    void insert(int val) {
      values.add(val);
    }

    int getIndex(int x) {
      int lo = 0;
      int hi = values.size() - 1;
      while (lo <= hi) {
        int mid = (lo + hi) >> 1;
        if (values.get(mid) == x)
          return mid;
        else if (values.get(mid) < x)
          lo = mid + 1;
        else
          hi = mid - 1;
      }
      return hi;
    }

    int query(int x) {
      x = getIndex(x);

      int ret = 1 << 30;

      for (int i = x; i > 0; i -= (i & -i))
        ret = Math.min(ret, tree[i]);

      return ret;
    }

    void update(int x, int val) {
      x = getIndex(x);

      if (x == 0)
        return;

      for (int i = x; i < sz; i += (i & -i))
        tree[i] = Math.min(tree[i], val);
    }
  }
}
