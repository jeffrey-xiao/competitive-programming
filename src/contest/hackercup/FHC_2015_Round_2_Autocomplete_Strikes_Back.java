package contest.hackercup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class FHC_2015_Round_2_Autocomplete_Strikes_Back {

  static BufferedReader br;
  static PrintWriter out;

  static StringTokenizer st;
  static double p;
  static Node root;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int tt = readInt();
    for (int qq = 1; qq <= tt; qq++) {
      root = new Node(false, 0);
      int n = readInt();
      int k = readInt();
      for (int i = 0; i < n; i++)
        addWord(next());
      Stack<Node> order = new Stack<Node>();
      Queue<Node> q = new LinkedList<Node>();
      order.push(root);
      q.offer(root);
      while (!q.isEmpty()) {
        Node curr = q.poll();
        for (int i = 0; i < 26; i++) {
          if (curr.child[i] != null) {
            q.offer(curr.child[i]);
            order.push(curr.child[i]);
          }
        }
      }
      while (!order.isEmpty()) {
        Node curr = order.pop();
        curr.min[0] = 0;
        if (curr.isEnd)
          curr.min[1] = curr.depth;
        for (Node i : curr.list) {
          for (int j = 100; j >= 0; j--) {
            for (int l = 0; l <= 100; l++) {
              if (j - l >= 0 && curr.min[j - l] + i.min[l] < curr.min[j]) {
                curr.min[j] = curr.min[j - l] + i.min[l];
              }
            }
          }
        }
        curr.min[1] = curr.depth;
      }
      out.printf("Case #%d: %d\n", qq, root.min[k]);
    }
    out.close();
  }

  static void addWord (String s) {
    Node curr = root;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (curr.child[c - 'a'] == null) {
        Node add = new Node(i == s.length() - 1, curr.depth + 1);
        curr.child[c - 'a'] = add;
        curr.list.add(add);
      }
      curr = curr.child[c - 'a'];
    }
  }

  static class Node {
    boolean isEnd;
    int depth;
    Node[] child;
    ArrayList<Node> list = new ArrayList<Node>();
    int[] min = new int[101];

    Node (boolean isEnd, int depth) {
      this.isEnd = isEnd;
      this.depth = depth;
      child = new Node[26];
      for (int i = 0; i <= 100; i++)
        min[i] = 1 << 29;
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
