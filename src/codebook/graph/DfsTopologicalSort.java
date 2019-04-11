/*
 * An application of depth-first search to topologically sort the graph. If the graph is not a directed acyclic graph (DAG), the program will exit.
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

public class DfsTopologicalSort {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int n, m;
  static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();

  static boolean[] v;
  static boolean[] done;
  static Stack<Integer> order = new Stack<Integer>();

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    m = readInt();
    for (int i = 0; i < n; i++)
      adj.add(new ArrayList<Integer>());
    v = new boolean[n];
    done = new boolean[n];
    for (int i = 0; i < m; i++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      adj.get(a).add(b);
    }
    for (int i = 0; i < n; i++)
      if (!done[i])
        dfs(i);
    while (!order.isEmpty())
      out.println(order.pop() + 1);
    out.close();
  }

  static void dfs(int i) {
    if (v[i]) {
      out.println("NOT A DIRECTED ACYCLIC GRAPH");
      out.close();
      System.exit(0);
    }
    v[i] = true;
    for (int j : adj.get(i))
      if (!done[j])
        dfs(j);
    v[i] = false;
    done[i] = true;
    order.push(i);
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
