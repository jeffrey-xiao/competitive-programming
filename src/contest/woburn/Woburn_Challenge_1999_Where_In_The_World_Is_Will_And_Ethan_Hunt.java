package contest.woburn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Woburn_Challenge_1999_Where_In_The_World_Is_Will_And_Ethan_Hunt {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int l;
  static int r;
  static boolean[] visited;
  static int[] prev;
  static boolean[][] adj;

  public static void main (String[] args) throws IOException {
    for (int t = readInt(); t > 0; t--) {
      l = readInt();
      r = readInt();

      prev = new int[l + r];
      for (int x = 0; x < l + r; x++)
        prev[x] = -1;
      adj = new boolean[l][r];
      String s1 = next();
      String s2 = next();
      while (!s1.equals("-1")) {
        int a = s1.charAt(0) - 'a';
        int b = s2.charAt(0) - 'A';
        adj[a][b] = true;
        s1 = next();
        s2 = next();
      }
      int count = 0;
      for (int x = 0; x < l; x++) {
        visited = new boolean[l];
        count += hungary(x) ? 1 : 0;
      }
      System.out.println(count);
      System.out.println(l == count ? "Will" : "Ethan");
    }
  }

  private static boolean hungary (int x) {
    for (int y = 0; y < l; y++) {
      if (adj[x][y] && !visited[y]) {
        visited[y] = true;
        if (prev[y] == -1 || hungary(prev[y])) {
          prev[y] = x;
          return true;
        }
      }
    }
    return false;
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

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
