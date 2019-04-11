package contest.ccc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CCC_2008_Stage_2_Mobile {

  static final int NAN = -10000;
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;
  static Node r1 = new Node(NAN), r2 = new Node(NAN);
  static int[] a, b;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    a = new int[n];
    b = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = readInt();
      b[i] = readInt();
    }
    add(0, r1);

    n = readInt();
    for (int i = 0; i < n; i++) {
      a[i] = readInt();
      b[i] = readInt();
    }
    add(0, r2);

    if (compare(r1, r2))
      System.out.println("Fred and Mary might have the same mobile.");
    else
      System.out.println("Fred and Mary have different mobiles.");
  }

  private static boolean compare(Node n1, Node n2) {
    if (n1.value != n2.value)
      return false;
    if (n1.value != NAN && n2.value != NAN)
      return n1.value == n2.value;

    return (compare(n1.left, n2.left) && compare(n1.right, n2.right));
  }

  private static void add(int i, Node n) {
    if (a[i] < 0) {
      n.left = new Node(a[i]);
      n.left.v = a[i] * 1337 + 13371337;
    } else {
      n.left = new Node(NAN);
      add(a[i] - 1, n.left);
    }
    if (b[i] < 0) {
      n.right = new Node(b[i]);
      n.right.v = b[i] * 1337 + 13371337;
    } else {
      n.right = new Node(NAN);
      add(b[i] - 1, n.right);
    }
    n.v = (n.left.v ^ n.right.v) + n.left.v + n.right.v + n.left.v * n.right.v;
    if (n.left.v > n.right.v) {
      Node temp = n.left;
      n.left = n.right;
      n.right = temp;
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

  static class Node {
    int value;
    int v;
    Node left, right;

    Node(int value) {
      this.value = value;
    }
  }
}
