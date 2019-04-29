/*
 * Modification of Tarjan's Strongly Connected Components (SCC) algorithm to work with biconnected components
 *
 * Time complexity: O(V + E)
 */

package codebook.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class DfsBiconnectedComponents {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static Stack<Edge> s = new Stack<Edge>();
  static ArrayList<Edge> bridges = new ArrayList<Edge>();
  static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
  static int[] low, disc;
  static boolean[] v, cutVertex;
  static int count = 0;
  static int n, m;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    m = readInt();
    v = new boolean[n];
    disc = new int[n];
    low = new int[n];
    cutVertex = new boolean[n];
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
        dfs(i, -1);
    while (!s.isEmpty()) {
      out.printf("%d-%d ", s.peek().a + 1, s.peek().b + 1);
      s.pop();
    }
    out.printf("%d-%d%n", s.peek().a + 1, s.peek().b + 1);
    s.pop();
    for (int i = 0; i < n; i++)
      if (cutVertex[i])
        out.printf("%d is a cut vertex%n", i + 1);
    for (Edge e : bridges)
      out.printf("%d-%d is a bridge%n", e.a, e.b);
    out.close();
  }

  static void dfs(int i, int prev) {
    disc[i] = low[i] = count++;
    v[i] = true;
    int children = 0;
    for (Integer j : adj.get(i)) {
      if (!v[j]) {
        children++;
        s.push(new Edge(i, j));
        dfs(j, i);
        low[i] = Math.min(low[i], low[j]);
        if ((disc[i] == 0 && children > 1) || (disc[i] > 0 && low[j] >= disc[i])) {
          cutVertex[i] = true;
          while (s.peek().a != i || s.peek().b != j) {
            out.printf("%d-%d ", s.peek().a + 1, s.peek().b + 1);
            s.pop();
          }
          out.printf("%d-%d%n", s.peek().a + 1, s.peek().b + 1);
          s.pop();
        }
        if (low[j] > disc[i])
          bridges.add(new Edge(i + 1, j + 1));
      } else if (j != prev && disc[j] < low[i]) {
        low[i] = disc[j];
        s.push(new Edge(i, j));
      }
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

  static class Edge {
    int a, b;

    Edge(int a, int b) {
      this.a = a;
      this.b = b;
    }
  }
}
