package contest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Rock_Climbing {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main (String[] args) {
    int n = nextInt();
    int m = nextInt();
    int e = nextInt();
    int[] pos = new int[n + 1];
    for (int x = 1; x < n + 1; x++)
      pos[x] = nextInt();
    int[] reach = new int[n + 1];
    boolean[] visited = new boolean[n + 1];
    visited[0] = true;
    reach[0] = e;
    for (int x = 0; x < pos.length - 1; x++) {
      if (!visited[x])
        break;
      for (int y = x + 1; y < pos.length; y++) {
        if (pos[y] - pos[x] > m * 2)
          break;
        if (pos[y] - pos[x] > m && reach[x] == 0)
          break;
        if (pos[y] - pos[x] <= m) {
          visited[y] = true;
          reach[y] = reach[x];
        }
        if (reach[x] != 0 && pos[y] - pos[x] > m) {
          visited[y] = true;
          reach[y] = reach[x] - 1;
        }
      }
    }
    System.out.println(visited[n] ? "Too easy!" : "Unfair!");
  }

  static String next () {
    while (st == null || !st.hasMoreTokens()) {
      try {
        st = new StringTokenizer(br.readLine().trim());
      } catch (IOException e) {
      }
    }
    return st.nextToken();
  }

  static int nextInt () {
    return Integer.parseInt(next());
  }

  static String nextLine () {
    String s = "";
    try {
      s = br.readLine().trim();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return s;
  }
}
