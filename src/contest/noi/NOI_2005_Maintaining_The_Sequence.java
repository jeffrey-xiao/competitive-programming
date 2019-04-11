package contest.noi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Random;
import java.util.StringTokenizer;

public class NOI_2005_Maintaining_The_Sequence {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, M;
  static Node root = null;
  static Random rand = new Random();

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    M = readInt();

    for (int i = 0; i < N; i++)
      root = merge(root, new Node(readInt()));

    for (int i = 0; i < M; i++) {
      String command = next();
      if (command.equals("INSERT")) {
        Node add = null;
        int pos = readInt();
        int tot = readInt();
        for (int j = 0; j < tot; j++)
          add = merge(add, new Node(readInt()));
        Nodepair res = split(root, pos, 0);
        root = merge(res.left, merge(add, res.right));
      } else if (command.equals("DELETE")) {
        int pos = readInt() - 1;
        int tot = readInt();
        Nodepair res2 = split(root, pos + tot, 0);
        Nodepair res1 = split(res2.left, pos, 0);
        root = merge(res1.left, res2.right);
      } else if (command.equals("MAKE-SAME")) {
        int pos = readInt() - 1;
        int tot = readInt();
        int c = readInt();
        Nodepair res2 = split(root, pos + tot, 0);
        Nodepair res1 = split(res2.left, pos, 0);
        res1.right = makeSame(res1.right, c);
        root = merge(res1.left, merge(res1.right, res2.right));
      } else if (command.equals("REVERSE")) {
        int pos = readInt() - 1;
        int tot = readInt();
        Nodepair res2 = split(root, pos + tot, 0);
        Nodepair res1 = split(res2.left, pos, 0);
        res1.right = reverse(res1.right);
        root = merge(res1.left, merge(res1.right, res2.right));
      } else if (command.equals("GET-SUM")) {
        int pos = readInt() - 1;
        int tot = readInt();
        if (tot == 0) {
          out.println(0);
        } else {
          Nodepair res2 = split(root, pos + tot, 0);
          Nodepair res1 = split(res2.left, pos, 0);
          out.println(res1.right.sum);
          root = merge(res1.left, merge(res1.right, res2.right));
        }
      } else if (command.equals("MAX-SUM")) {
        out.println(root.maxSubarray);
      }
    }

    out.close();
  }

  static Nodepair split(Node n, int k, int lower) {
    if (n == null)
      return new Nodepair(null, null);

    int key = lower + getSize(n.left) + 1;

    n = update(n);

    if (k >= key) {
      Nodepair res = split(n.right, k, lower + getSize(n.left) + 1);
      n.right = res.left;
      res.left = n;
      res.right = update(res.right);
      res.left = update(res.left);
      return res;
    } else {
      Nodepair res = split(n.left, k, lower);
      n.left = res.right;
      res.right = n;
      res.right = update(res.right);
      res.left = update(res.left);
      return res;
    }
  }

  static Node merge(Node a, Node b) {
    if (a == null)
      return b;
    if (b == null)
      return a;

    a = update(a);
    b = update(b);

    if (a.p > b.p) {
      a.right = merge(a.right, b);
      a = update(a);
      return a;
    } else {
      b.left = merge(a, b.left);
      b = update(b);
      return b;
    }
  }

  static int getSize(Node n) {
    return n == null ? 0 : n.size;
  }

  static int getSum(Node n) {
    return n == null ? 0 : n.sum;
  }

  static Node update(Node n) {
    if (n == null)
      return n;

    n.size = getSize(n.left) + getSize(n.right) + 1;

    if (n.same != 1 << 30) {
      n.left = makeSame(n.left, n.same);
      n.right = makeSame(n.right, n.same);
      n.same = 1 << 30;
    }

    if (n.reverse) {
      n.left = reverse(n.left);
      n.right = reverse(n.right);
      n.reverse = false;
    }

    n.sum = n.value + getSum(n.left) + getSum(n.right);
    n.maxSubarray = n.value;
    n.maxPrefix = n.sum;
    n.maxSuffix = n.sum;

    n.maxPrefix = Math.max(n.maxPrefix, getSum(n.left) + n.value);
    n.maxSuffix = Math.max(n.maxSuffix, getSum(n.right) + n.value);

    if (n.left != null) {
      n.maxPrefix = Math.max(n.maxPrefix, n.left.maxPrefix);
      n.maxSuffix = Math.max(n.maxSuffix, getSum(n.right) + n.value + Math.max(0, n.left.maxSuffix));
      n.maxSubarray = Math.max(n.maxSubarray, n.left.maxSubarray);
      n.maxSubarray = Math.max(n.maxSubarray, n.value + n.left.maxSuffix);
    }

    if (n.right != null) {
      n.maxSuffix = Math.max(n.maxSuffix, n.right.maxSuffix);
      n.maxPrefix = Math.max(n.maxPrefix, getSum(n.left) + n.value + Math.max(0, n.right.maxPrefix));
      n.maxSubarray = Math.max(n.maxSubarray, n.right.maxSubarray);
      n.maxSubarray = Math.max(n.maxSubarray, n.value + n.right.maxPrefix);
    }

    if (n.left != null && n.right != null) {
      n.maxSubarray = Math.max(n.maxSubarray, Math.max(Math.max(0, n.left.maxSuffix) + n.right.maxPrefix + n.value, n.value + n.left.maxSuffix + Math.max(0, n.right.maxPrefix)));
    }

    if (n.left == null && n.right == null)
      n.maxPrefix = n.maxSuffix = n.value;

    return n;
  }

  static Node reverse(Node n) {
    if (n == null)
      return n;
    n.reverse = !n.reverse;
    int maxPrefix = n.maxPrefix;
    int maxSuffix = n.maxSuffix;
    Node left = n.left;
    Node right = n.right;
    n.maxPrefix = maxSuffix;
    n.maxSuffix = maxPrefix;
    n.left = right;
    n.right = left;
    return n;
  }

  static Node makeSame(Node n, int c) {
    if (n == null)
      return n;
    n.value = c;
    n.same = c;
    n.maxPrefix = Math.max(getSize(n) * c, c);
    n.maxSuffix = Math.max(getSize(n) * c, c);
    n.maxSubarray = Math.max(getSize(n) * c, c);
    n.sum = getSize(n) * c;
    return n;
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

  static class Node {
    Node left, right;
    double p;
    int size, value, same;
    int maxPrefix, maxSuffix, maxSubarray, sum;
    boolean reverse;

    Node(int value) {
      this.value = value;
      this.p = rand.nextDouble();
      this.size = 1;
      this.same = 1 << 30;
      this.reverse = false;
    }
  }

  static class Nodepair {
    Node left, right;

    Nodepair(Node left, Node right) {
      this.left = left;
      this.right = right;
    }
  }
}
