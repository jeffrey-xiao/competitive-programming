package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CCO_Prep_Kth_Rank_Student {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    int n = readInt();
    int m = readInt();
    root = new Node[n];
    id = new int[n];
    size = new int[n];

    for (int i = 0; i < n; i++) {
      root[i] = add(root[i], readInt(), i);
      id[i] = i;
      size[i] = 1;
    }
    for (int i = 0; i < m; i++)
      build(readInt() - 1, readInt() - 1);

    int q = readInt();
    for (int i = 0; i < q; i++) {
      char c = readCharacter();
      int a = readInt();
      int b = readInt();
      if (c == 'B') {
        build(a - 1, b - 1);
      } else {
        ps.println(query(root[find(a - 1)], b) + 1);
      }
    }
    ps.close();
  }

  private static int query (Node n, int k) {
    if (k > getSize(n))
      return -2;
    if (getSize(n.left) + 1 == k)
      return n.id;
    if (getSize(n.left) + 1 > k)
      return query(n.left, k);
    return query(n.right, k - getSize(n.left) - 1);
  }

  static Node[] root;
  static int[] id;
  static int[] size;

  private static void build (int a, int b) {
    int prevA = find(a);
    int prevB = find(b);
    merge(prevA, prevB);
    if (find(a) == prevA)
      root[prevA] = traverse(root[prevB], root[prevA]);
    else
      root[prevB] = traverse(root[prevA], root[prevB]);
  }

  private static int find (int x) {
    return x == id[x] ? x : (id[x] = find(id[x]));
  }

  private static void merge (int rootx, int rooty) {
    if (size[rootx] > size[rooty]) {
      size[rootx] += size[rooty];
      id[rooty] = rootx;
    } else {
      size[rooty] += size[rootx];
      id[rootx] = rooty;
    }
  }

  static class Node {
    Integer value, size, id;
    Double priority;
    Node left = null, right = null;

    Node (int value, int id) {
      this.value = value;
      priority = Math.random();
      this.size = 1;
      this.id = id;
    }
  }

  public static void traverse (Node n) {
    if (n == null)
      return;
    traverse(n.left);
    System.out.print(n.id + " ");
    traverse(n.right);
  }

  public static Node traverse (Node n, Node root) {
    if (n == null)
      return root;
    root = traverse(n.left, root);
    root = add(root, n.value, n.id);
    root = traverse(n.right, root);
    return root;
  }

  private static int getSize (Node n) {
    return n == null ? 0 : n.size;
  }

  // auxiliary function to insert
  private static Node add (Node n, Integer v, Integer id) {
    if (n == null) {
      return new Node(v, id);
    }
    int cmp = v.compareTo(n.value);
    // going left
    if (cmp < 0) {
      n.left = add(n.left, v, id);
      if (n.priority < n.left.priority) {
        n = rotateRight(n);
      }
    }
    // going right
    else if (cmp > 0) {
      n.right = add(n.right, v, id);
      if (n.priority < n.right.priority) {
        n = rotateLeft(n);
      }
    }
    // else the value already exists
    n.size = 1 + getSize(n.left) + getSize(n.right);
    return n;
  }

  // rotate left
  private static Node rotateLeft (Node n) {
    Node x = n.right;
    n.right = x.left;
    x.left = n;
    n.size = 1 + getSize(n.left) + getSize(n.right);
    return x;
  }

  // rotate right
  private static Node rotateRight (Node n) {
    Node x = n.left;
    n.left = x.right;
    x.right = n;
    n.size = 1 + getSize(n.left) + getSize(n.right);
    return x;
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
