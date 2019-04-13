package codejam;

import java.io.*;
import java.util.*;

public class GCJ_2019_Round_1A_C {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T, N;
  static Node root;
  static final int SHIFT = 'A';

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    for (int t = 1; t <= T; t++) {
      N = readInt();
      root = new Node();
      for (int i = 0; i < N; i++) {
        String word = next();
        addWord(word);
      }

      out.printf("Case #%d: %d\n", t, solve(root));
    }

    out.close();
  }

  static int solve(Node curr) {
    int total = 0;
    int children = 0;
    for (int i = 0; i < 26; i++) {
      if (curr.child[i] != null) {
        children += solve(curr.child[i]);
      }
    }
    if (curr.count == 0) {
      return children;
    } else if (curr.count - children >= 2) {
      return children + 2;
    } else {
      return children;
    }
  }

  static void addWord(String word) {
    Node curr = root;
    for (int i = word.length() - 1; i >= 0; i--) {
      if (curr.child[word.charAt(i) - SHIFT] == null)
        curr.child[word.charAt(i) - SHIFT] = new Node();
      curr = curr.child[word.charAt(i) - SHIFT];
      curr.count++;
    }
  }


  static class Node {
    private Node[] child;
    private int count;

    Node() {
      child = new Node[26];
      count = 0;
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
}
