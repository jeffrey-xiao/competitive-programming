package contest.usaco_other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
/*
ID: jeffrey40
LANG: JAVA
TASK: maxflow
*/
import java.util.ArrayList;
import java.util.StringTokenizer;

public class maxflow implements Runnable {
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
  static int[] depth, parent, chain, size, head, index;
  static int[] tree, lazy;
  static int n, k, chainNum, chainIndex;
  static int[] a, b;

  public static void main(String[] args) throws IOException, InterruptedException {
    //br = new BufferedReader(new FileReader("maxflow.in"));
    //out = new PrintWriter(new FileWriter("maxflow.out"));
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));

    n = readInt();
    k = readInt();

    depth = new int[n];
    parent = new int[n];
    chain = new int[n];
    size = new int[n];
    head = new int[n];
    index = new int[n];

    a = new int[k];
    b = new int[k];

    tree = new int[4 * n];
    lazy = new int[4 * n];

    for (int i = 0; i < n; i++) {
      adj.add(new ArrayList<Integer>());
      head[i] = -1;
    }

    for (int i = 0; i < n - 1; i++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      adj.get(a).add(b);
      adj.get(b).add(a);
    }

    for (int i = 0; i < k; i++) {
      a[i] = readInt() - 1;
      b[i] = readInt() - 1;
    }

    Thread t = new Thread(null, new maxflow(), "maxflow", 1 << 20);
    t.start();
    t.join();
    System.exit(0);
  }

  static int query(int n, int l, int r, int x) {
    if (l == x && r == x)
      return tree[n];
    int mid = (r + l) >> 1;
    if (lazy[n] != 0) {
      tree[n << 1] += lazy[n] * (mid - l + 1);
      tree[n << 1 | 1] += lazy[n] * (r - (mid + 1) + 1);
      lazy[n << 1] += lazy[n];
      lazy[n << 1 | 1] += lazy[n];
      lazy[n] = 0;
    }
    if (x <= mid)
      return query(n << 1, l, mid, x);
    else
      return query(n << 1 | 1, mid + 1, r, x);
  }

  static void update(int n, int l, int r, int ql, int qr, int val) {
    if (l == ql && r == qr) {
      tree[n] += val * (r - l + 1);
      lazy[n] += val;
      return;
    }
    int mid = (r + l) >> 1;
    if (lazy[n] != 0) {
      tree[n << 1] += lazy[n] * (mid - l + 1);
      tree[n << 1 | 1] += lazy[n] * (r - (mid + 1) + 1);
      lazy[n << 1] += lazy[n];
      lazy[n << 1 | 1] += lazy[n];
      lazy[n] = 0;
    }
    if (qr <= mid)
      update(n << 1, l, mid, ql, qr, val);
    else if (ql > mid)
      update(n << 1 | 1, mid + 1, r, ql, qr, val);
    else {
      update(n << 1, l, mid, ql, mid, val);
      update(n << 1 | 1, mid + 1, r, mid + 1, qr, val);
    }
    tree[n] = tree[n << 1] + tree[n << 1 | 1];
  }

  static void query(int i, int j) {
    while (chain[i] != chain[j]) {
      if (depth[head[chain[i]]] < depth[head[chain[j]]]) {
        update(1, 1, n, index[head[chain[j]]], index[j], 1);
        j = parent[head[chain[j]]];
      } else {
        update(1, 1, n, index[head[chain[i]]], index[i], 1);
        i = parent[head[chain[i]]];
      }
    }
    update(1, 1, n, Math.min(index[i], index[j]), Math.max(index[i], index[j]), 1);
  }

  static void getHld(int i, int prev) {
    if (head[chainNum] == -1) {
      head[chainNum] = i;
    }
    chain[i] = chainNum;
    index[i] = ++chainIndex;
    int maxIndex = -1;
    for (int j : adj.get(i))
      if (j != prev && (maxIndex == -1 || size[maxIndex] < size[j]))
        maxIndex = j;
    if (maxIndex != -1)
      getHld(maxIndex, i);
    for (int j : adj.get(i))
      if (j != prev && j != maxIndex) {
        chainNum++;
        getHld(j, i);
      }
  }

  static void dfs(int i, int d, int prev) {
    depth[i] = d;
    parent[i] = prev;
    size[i] = 1;
    for (int j : adj.get(i)) {
      if (j != prev) {
        dfs(j, d + 1, i);
        size[i] += size[j];
      }
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

  static String readLine() throws IOException {
    return br.readLine().trim();
  }

  @Override
  public void run() {
    dfs(0, 0, -1);
    getHld(0, -1);

    for (int i = 0; i < k; i++) {
      query(a[i], b[i]);
    }
    int ans = 0;
    for (int i = 1; i <= n; i++) {
      ans = Math.max(ans, query(1, 1, n, i));
    }
    out.println(ans);
    out.flush();
    out.close();
  }
}
