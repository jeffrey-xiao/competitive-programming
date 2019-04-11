/*
 * Bellman-Ford algorithm solves the single-source shortest path (SSSP) problem. It supports negative edge weights and it will report when a negative cycle has been found.
 *
 * Time complexity: O(VE)
 */

package codebook.graph.shortestpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BellmanFord {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int n, m, orig, dest;
  static Edge[] e;
  static int[] dist;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    m = readInt();
    orig = readInt() - 1;
    dest = readInt() - 1;

    dist = new int[n];
    e = new Edge[m];

    for (int i = 0; i < n; i++)
      dist[i] = 1 << 29;

    for (int i = 0; i < m; i++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      int c = readInt();
      e[i] = new Edge(a, b, c);
    }

    dist[orig] = 0;
    for (int i = 0; i < n - 1; i++)
      for (Edge edge : e)
        dist[edge.dest] = Math.min(dist[edge.dest], dist[edge.orig] + edge.cost);

    out.println(dist[dest]);
    out.close();
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
    int orig, dest, cost;

    Edge(int orig, int dest, int cost) {
      this.orig = orig;
      this.dest = dest;
      this.cost = cost;
    }
  }
}
