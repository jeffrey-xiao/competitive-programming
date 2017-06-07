package contest.ccc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CCC_2010_S5 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    Node root = new Node(-1);
    Node curr = root;
    char[] line = readLine().trim().toCharArray();
    for (int x = 0; x < line.length; x++) {
      if (line[x] == ' ')
        continue;
      if (line[x] == '(') {
        if (curr.left == null) {
          curr.left = new Node(-1);
          curr.left.parent = curr;
          curr = curr.left;
        } else {
          curr.right = new Node(-1);
        }
      }
    }
  }

  static class Node {
    Node parent, left, right;
    int value;
    int[] dp = new int[2500];

    Node (int value) {
      this.value = value;
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
