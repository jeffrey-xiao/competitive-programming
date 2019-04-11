package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CCC_2011_Stage_2_Spies_Like_Us {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    int m = readInt();
    ArrayList<ArrayList<Integer>> a = new ArrayList<ArrayList<Integer>>();
    ArrayList<ArrayList<Integer>> b = new ArrayList<ArrayList<Integer>>();
    for (int x = 0; x < n; x++)
      a.add(new ArrayList<Integer>());
    for (int x = 0; x < m; x++)
      b.add(new ArrayList<Integer>());
    for (int q = readInt(); q > 0; q--) {
      int x = readInt() - 1;
      int y = readInt() - 1;
      a.get(x).add(y);
      b.get(y).add(x);
    }
    for (int x = 0; x < n; x++) {
      boolean[] found = new boolean[n];
      for (Integer next : a.get(x)) {
        for (Integer con : b.get(next)) {
          if (!found[con] && con != x)
            found[con] = true;
          else if (found[con]) {
            System.out.println("NO");
            return;
          }
        }
      }
    }
    System.out.println("YES");
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
}
