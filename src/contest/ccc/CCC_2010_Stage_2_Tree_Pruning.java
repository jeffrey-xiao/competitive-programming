package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2010_Stage_2_Tree_Pruning {

  static final boolean WHITE = true;
  static final boolean BLACK = false;
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static Node[] nodes;
  static int mod;
  static int[][] minPrune;

  public static void main(String[] args) throws IOException {
    // INPUT
    int n = readInt();
    int d = readInt();
    mod = d > 0 ? -1 : 1;
    nodes = new Node[n];
    for (int x = 0; x < n; x++) {
      int id = readInt();
      int color = readInt();
      int children = readInt();
      nodes[id] = new Node(color, id);
      if (children == 1)
        nodes[id].left = readInt();
      else if (children == 2) {
        nodes[id].left = readInt();
        nodes[id].right = readInt();
      }
    }
    // GETTING COLORS AT EACH TREE
    computeColors(nodes[0]);
    int curr = nodes[0].numWhite - nodes[0].numBlack;
    int diff = d - curr;
    minPrune = new int[n][Math.abs(diff) + 1];
    for (int x = 0; x < n; x++)
      for (int y = 0; y < Math.abs(diff) + 1; y++)
        minPrune[x][y] = -2;
    System.out.println(search(nodes[0], diff));
  }

  private static int search(Node node, int diff) {
    int total = -node.numWhite + node.numBlack;
    if (diff == 0)
      return 0;
    if (total == diff) {
      return 1;
    }
    if (minPrune[node.index][Math.abs(diff)] != -2)
      return minPrune[node.index][Math.abs(diff)];

    int min = Integer.MAX_VALUE;
    if (node.right != -1 && node.left != -1) {
      for (int x = 0; x <= Math.abs(diff); x++) {
        int a = search(nodes[node.left], diff < 0 ? -x : x);
        int b = search(nodes[node.right], diff < 0 ? -(-diff - x) : diff - x);
        if (a != -1 && b != -1)
          min = Math.min(min, a + b);
      }
    } else if (node.right != -1) {
      int a = search(nodes[node.right], diff);
      if (a != -1)
        min = Math.min(min, a);
    } else if (node.left != -1) {
      int a = search(nodes[node.left], diff);
      if (a != -1)
        min = Math.min(min, a);
    }
    minPrune[node.index][Math.abs(diff)] = min == Integer.MAX_VALUE ? -1 : min;
    return minPrune[node.index][Math.abs(diff)];
  }

  private static void computeColors(Node n) {
    if (n.color == WHITE)
      n.numWhite++;
    else if (n.color == BLACK)
      n.numBlack++;
    if (n.left != -1) {
      computeColors(nodes[n.left]);
      n.numWhite += nodes[n.left].numWhite;
      n.numBlack += nodes[n.left].numBlack;
    }
    if (n.right != -1) {
      computeColors(nodes[n.right]);
      n.numWhite += nodes[n.right].numWhite;
      n.numBlack += nodes[n.right].numBlack;
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

  static class Node {
    boolean color;
    int index;
    int left = -1;
    int right = -1;
    int numWhite = 0;
    int numBlack = 0;

    Node(int color, int index) {
      this.color = color == 1;
      this.index = index;
    }
  }
}
