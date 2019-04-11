package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_1996_Stage_2_Quadtrees {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    for (int t = readInt(); t > 0; t--) {
      Quadtree qt = new Quadtree();
      qt.insert(0, new StringBuilder(next()));
      qt.insert(0, new StringBuilder(next()));
      System.out.printf("There are %d black pixels.\n", qt.getTotalValue(1024));
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

  static class Quadtree {
    int depth;
    char node;
    Quadtree[] child;
    int currChild;

    Quadtree() {
      node = '\u0000';
      child = new Quadtree[4];
    }

    Quadtree(int depth, char v) {
      this.node = v;
      this.depth = depth;
    }

    Quadtree(int depth) {
      node = '\u0000';
      this.depth = depth;
      child = new Quadtree[4];
    }

    private void insert(int depth, StringBuilder value) {
      if (value.charAt(0) == 'p' && node != 'f') {
        node = value.charAt(0);
        this.depth = depth;
        value.deleteCharAt(0);
      }
      if (value.length() == 1) {
        if (value.charAt(0) == 'f')
          node = 'f';
        return;
      }

      if (value.toString().equals(""))
        return;
      currChild = 0;
      for (int x = 0; x < value.length();) {
        if (value.charAt(x) == 'p') {
          int end = x + 5;
          for (int y = x + 1; y < Math.min(end, value.length()); y++) {
            if (value.charAt(y) == 'p') {
              end = end + 4;
            }
          }

          end = Math.max(end, x + 5);
          if (node != 'f') {
            if (child[currChild] == null)
              child[currChild] = new Quadtree();
            child[currChild].insert(depth + 1, new StringBuilder(value.substring(x, end)));
          }
          x = end;
          currChild++;
        } else {
          if (value.charAt(x) == 'f')
            child[currChild] = new Quadtree(depth + 1, 'f');

          currChild++;
          x++;
        }
      }
    }

    private int getTotalValue(int squareValue) {
      if (node == 'f')
        return squareValue;

      int total = 0;
      for (int x = 0; x < 4; x++)
        if (child[x] != null)
          total += child[x].getTotalValue(squareValue / 4);

      return total;
    }
  }
}
