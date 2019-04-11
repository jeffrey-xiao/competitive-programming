package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class IOI_2012_Jousting_Tournament {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int n, c, r;
  static int[] k;

  static int[] bit;
  static Tournament[] nodes;
  static int[] dp;
  static Queue<Node> delete = new ArrayDeque<Node>();
  static Node root;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    c = readInt();
    r = readInt();

    dp = new int[n + c];
    nodes = new Tournament[n + c];
    k = new int[n];
    bit = new int[n + 1];
    for (int i = 1; i < n; i++) {
      k[i] = (readInt() > r ? 1 : 0) + k[i - 1];
    }
    TreeSet<Integer> left = new TreeSet<Integer>();
    for (int i = 0; i < n; i++) {
      nodes[i] = new Tournament(i + 1, i + 1, i, -1);
      root = add(root, new Node(i));
      left.add(i + 1);
      update(i + 1, 1);
    }
    for (int i = 0; i < c; i++) {
      int l = readInt() + 1;
      int r = readInt() + 1;

      int lo = 1;
      int hi = n;
      while (lo <= hi) {
        int mid = (hi + lo) / 2;
        if (query(mid) < l)
          lo = mid + 1;
        else
          hi = mid - 1;
      }
      l = lo;
      lo = 1;
      hi = n;
      while (lo < hi) {
        int mid = (hi + lo + 1) / 2;
        if (query(mid) <= r)
          lo = mid;
        else
          hi = mid - 1;
      }
      r = hi;
      for (Integer j : left.subSet(l + 1, true, r, true))
        update(j, -1);
      left.subSet(l + 1, true, r, true).clear();
      nodes[i + n] = new Tournament(l, r);
      search(root, new Node(i + n));
      while (!delete.isEmpty())
        root = remove(root, delete.poll());
      root = add(root, new Node(i + n));
    }
    int max = 0;
    int maxIndex = 0;
    for (int i = n + c - 1; i >= 0; i--) {
      if (k[nodes[i].r - 1] - k[nodes[i].l - 1] == 0) {
        dp[i] = 1 + (nodes[i].advance != -1 ? dp[nodes[i].advance] : 0);
      }
      if (nodes[i].indexLeft != -1) {
        if (dp[i] > max) {
          max = dp[i];
          maxIndex = nodes[i].indexLeft;
        } else if (dp[i] == max)
          maxIndex = Math.min(maxIndex, nodes[i].indexLeft);
      }
    }
    out.println(maxIndex);
    out.close();
  }

  static void update(int i, int val) {
    for (int x = i; x <= n; x += (x & -x))
      bit[x] += val;
  }

  static int query(int i) {
    int sum = 0;
    for (int x = i; x > 0; x -= (x & -x))
      sum += bit[x];
    return sum;
  }

  private static Node remove(Node n, Node remove) {
    if (n == null)
      return n;
    int cmp = nodes[n.index].l - nodes[remove.index].l;
    if (cmp > 0)
      n.left = remove(n.left, remove);
    else if (cmp < 0)
      n.right = remove(n.right, remove);
    else {
      if (n.left == null) {
        n = n.right;
        return n;
      } else if (n.right == null) {
        n = n.left;
        return n;
      } else {
        Node replace = minV(n.right);
        n.index = replace.index;
        n.right = remove(n.right, n);
        return n;
      }
    }
    return balance(n);
  }

  private static void search(Node n, Node insert) {
    if (n == null)
      return;
    if (nodes[insert.index].l <= nodes[n.index].l && nodes[n.index].r <= nodes[insert.index].r) {
      nodes[n.index].advance = insert.index;
      if (nodes[n.index].l == nodes[n.index].r && (nodes[insert.index].indexLeft == -1 || nodes[n.index].l < nodes[insert.index].indexLeft))
        nodes[insert.index].indexLeft = nodes[n.index].l;

      delete.offer(n);
    }
    if (nodes[n.index].r < nodes[insert.index].r) {
      search(n.right, insert);
    }
    if (nodes[n.index].l > nodes[insert.index].l) {
      search(n.left, insert);
    }
  }

  private static Node minV(Node n) {
    while (n.left != null)
      n = n.left;
    return n;
  }

  private static Node add(Node n, Node insert) {
    if (n == null)
      return insert;
    int cmp = nodes[n.index].l - nodes[insert.index].l;
    if (cmp > 0)
      n.left = add(n.left, insert);
    else if (cmp < 0)
      n.right = add(n.right, insert);
    return balance(n);
  }

  private static Node balance(Node n) {
    resetHeight(n);
    int diff1 = getHeight(n.left) - getHeight(n.right);
    if (diff1 >= 2) {
      int diff2 = getHeight(n.left.right) - getHeight(n.left.left);
      if (diff2 > 0)
        n.left = rotateLeft(n.left);
      n = rotateRight(n);
    } else if (diff1 <= -2) {
      int diff2 = getHeight(n.right.left) - getHeight(n.right.right);
      if (diff2 > 0)
        n.right = rotateRight(n.right);
      n = rotateLeft(n);
    }
    return n;
  }

  private static Node rotateLeft(Node n) {
    Node x = n.right;
    n.right = x.left;
    x.left = n;
    resetHeight(n);
    resetHeight(x);
    return x;
  }

  private static Node rotateRight(Node n) {
    Node x = n.left;
    n.left = x.right;
    x.right = n;
    resetHeight(n);
    resetHeight(x);
    return x;
  }

  private static void resetHeight(Node n) {
    n.height = Math.max(getHeight(n.left), getHeight(n.right)) + 1;
  }

  private static int getHeight(Node n) {
    return n == null ? -1 : n.height;
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

  static class Tournament {
    int l, r;
    int indexLeft, advance;

    Tournament(int l, int r) {
      this(l, r, -1, -1);
    }

    Tournament(int l, int r, int indexLeft, int advance) {
      this.l = l;
      this.r = r;
      this.advance = advance;
      this.indexLeft = indexLeft;
    }
  }

  static class Node {
    int index, height;
    Node left, right;

    Node(int index) {
      this.index = index;
      this.height = 0;
    }
  }
}
