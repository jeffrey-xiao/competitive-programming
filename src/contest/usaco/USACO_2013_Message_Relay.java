package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class USACO_2013_Message_Relay {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static boolean[] loopy = new boolean[0];

  public static void main(String[] args) throws IOException {
    int n = readInt();
    loopy = new boolean[n];
    int[] adjlist = new int[n];
    boolean[] visited = new boolean[n];
    for (int x = 0; x < n; x++) {
      adjlist[x] = readInt() - 1;
    }
    int counter = 0;
    for (int x = 0; x < n; x++) {

      if (!visited[x]) {
        boolean[] copy = new boolean[n];
        dfs(x, copy, adjlist);
        for (int z = 0; z < copy.length; z++)
          visited[z] = visited[z] || copy[z];
      }
      if (loopy[x])
        counter++;
    }
    System.out.println(n - counter);
  }

  private static boolean dfs(int x, boolean[] visited, int[] adjlist) {
    if (visited[x]) {
      loopy[x] = true;
      return true;
    }
    if (adjlist[x] == -1)
      return false;
    visited[x] = true;
    boolean isLoopy = dfs(adjlist[x], visited, adjlist);
    if (isLoopy)
      loopy[x] = true;
    return isLoopy;
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
