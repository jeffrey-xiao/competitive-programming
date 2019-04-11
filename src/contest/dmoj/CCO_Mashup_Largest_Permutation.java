package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CCO_Mashup_Largest_Permutation {

  static final int SIZE = 100005;
  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;
  static Node[] seg = new Node[SIZE * 3];
  static int[] a = new int[SIZE];

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // pr = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    int k = readInt();
    for (int i = 1; i <= n; i++)
      a[i] = readInt();
    if (k >= n - 1) {
      for (int i = n; i >= 1; i--) {
        System.out.print(i + " ");
      }
    } else {
      build(1, n, 1);
      int swaps = 0;
      for (int i = 1; i <= n && swaps < k; i++) {
        State s = query(i + 1, n, 1);
        if (s.max > a[i]) {
          int temp = a[i];
          a[i] = s.max;
          a[s.index] = temp;
          update(i, 1, a[i]);
          update(s.index, 1, a[s.index]);
          swaps++;
        }
      }
      for (int i = 1; i <= n; i++)
        System.out.print(a[i] + " ");
      System.out.println();
    }

    pr.close();
  }

  static void update(int x, int n, int v) {
    if (seg[n].l == x && x == seg[n].r) {
      seg[n].max = v;
      return;
    }
    int mid = (seg[n].l + seg[n].r) / 2;
    if (x <= mid)
      update(x, 2 * n, v);
    else
      update(x, 2 * n + 1, v);
    if (seg[2 * n].max > seg[2 * n + 1].max) {
      seg[n].max = seg[2 * n].max;
      seg[n].index = seg[2 * n].index;
    } else {
      seg[n].max = seg[2 * n + 1].max;
      seg[n].index = seg[2 * n + 1].index;
    }
  }

  static State query(int l, int r, int n) {
    if (seg[n].l == l && seg[n].r == r) {
      return new State(seg[n].index, seg[n].max);
    }
    int mid = (seg[n].l + seg[n].r) / 2;
    if (r <= mid)
      return query(l, r, 2 * n);
    else if (l > mid)
      return query(l, r, 2 * n + 1);
    State s1 = query(l, mid, 2 * n);
    State s2 = query(mid + 1, r, 2 * n + 1);
    if (s1.max > s2.max)
      return s1;
    return s2;
  }

  static void build(int l, int r, int n) {
    seg[n] = new Node(l, r);
    if (l == r) {
      seg[n].max = a[l];
      seg[n].index = l;
      return;
    }
    int mid = (r + l) / 2;
    build(l, mid, 2 * n);
    build(mid + 1, r, 2 * n + 1);
    if (seg[2 * n].max > seg[2 * n + 1].max) {
      seg[n].max = seg[2 * n].max;
      seg[n].index = seg[2 * n].index;
    } else {
      seg[n].max = seg[2 * n + 1].max;
      seg[n].index = seg[2 * n + 1].index;
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

  static class State {
    int index, max;

    State(int index, int min) {
      this.index = index;
      this.max = min;
    }
  }

  static class Node {
    int l, r, max, index;

    Node(int l, int r) {
      this.l = l;
      this.r = r;
    }
  }
}
