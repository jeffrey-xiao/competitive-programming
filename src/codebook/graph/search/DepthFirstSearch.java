/*
 * Implementation of depth-first search (DFS)
 *
 * Time complexity: O(V + E)
 */

package codebook.graph.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class DepthFirstSearch {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int n, m, orig, dest;
  static ArrayList<ArrayList<Integer>> adj;
  static Queue<Integer> q;
  static boolean[] v;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    m = readInt();

    adj = new ArrayList<ArrayList<Integer>>();

    v = new boolean[n];

    for (int i = 0; i < n; i++)
      adj.add(new ArrayList<Integer>());

    for (int i = 0; i < m; i++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      adj.get(a).add(b);
      adj.get(b).add(a);
    }
    for (int i = 0; i < n; i++)
      if (!v[i])
        dfs(i);
    out.close();
  }

  static void dfs(int i) {
    v[i] = true;
    for (Integer j : adj.get(i))
      if (!v[j])
        dfs(j);
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
