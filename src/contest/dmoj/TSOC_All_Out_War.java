package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TSOC_All_Out_War {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  static Node[] tree = new Node[30001 * 3];
  static int[] a = new int[30001];

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    // br = new BufferedReader(new FileReader("in.txt"));
    // pr = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    int q = readInt();

    for (int i = 1; i <= n; i++)
      a[i] = readInt();
    build(1, n, 1);
    for (int i = 0; i < q; i++) {
      int a = readInt();
      int b = readInt();
      int c = readInt();
      update(a, b, 1, c);
      System.out.printf("%d %d\n", query(a, b, 1), query(1, n, 1));
    }
    pr.close();
  }

  static void build (int l, int r, int n) {
    tree[n] = new Node(l, r);
    if (l == r) {
      tree[n].min = a[l];
      return;
    }
    int mid = (l + r) / 2;
    build(l, mid, 2 * n);
    build(mid + 1, r, 2 * n + 1);
    pushUp(n);
  }

  static void update (int l, int r, int n, int a) {
    if (tree[n].l == l && tree[n].r == r) {
      tree[n].p += a;
      tree[n].min = Math.max(0, tree[n].min - a);
      return;
    }
    if (tree[n].p != 0)
      pushDown(n);
    int mid = (tree[n].l + tree[n].r) / 2;
    if (r <= mid)
      update(l, r, 2 * n, a);
    else if (l > mid)
      update(l, r, 2 * n + 1, a);
    else {
      update(l, mid, 2 * n, a);
      update(mid + 1, r, 2 * n + 1, a);
    }
    pushUp(n);
  }

  static int query (int l, int r, int n) {
    if (tree[n].r == r && tree[n].l == l)
      return tree[n].min;
    if (tree[n].p != 0)
      pushDown(n);
    int mid = (tree[n].l + tree[n].r) / 2;
    if (r <= mid)
      return query(l, r, 2 * n);
    if (l > mid)
      return query(l, r, 2 * n + 1);
    return Math.min(query(l, mid, 2 * n), query(mid + 1, r, 2 * n + 1));
  }

  static void pushUp (int n) {
    tree[n].min = Math.min(tree[2 * n].min, tree[2 * n + 1].min);
  }

  static void pushDown (int n) {
    tree[2 * n].min = Math.max(0, tree[2 * n].min - tree[n].p);
    tree[2 * n + 1].min = Math.max(0, tree[2 * n + 1].min - tree[n].p);
    tree[2 * n].p += tree[n].p;
    tree[2 * n + 1].p += tree[n].p;
    tree[n].p = 0;
  }

  static class Node {
    int l, r, min;
    int p;

    Node (int l, int r) {
      this.l = l;
      this.r = r;
    }
  }

  static String next () throws IOException {
    while (st == null || !st.hasMoreTokens())
      st = new StringTokenizer(br.readLine().trim());
    return st.nextToken();
  }

  static long readLong () throws IOException {
    return Long.parseLong(next());
  }

  static int readInt () throws IOException {
    return Integer.parseInt(next());
  }

  static double readDouble () throws IOException {
    return Double.parseDouble(next());
  }

  static char readCharacter () throws IOException {
    return next().charAt(0);
  }

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
