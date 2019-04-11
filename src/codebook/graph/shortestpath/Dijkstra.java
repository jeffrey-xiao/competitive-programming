/*
 * Dijkstra's algorithm solves the single-source shortest path (SSSP) problem. It does not work with negative edge weights.
 *
 * Time complexity: O(V^2)
 */

package codebook.graph.shortestpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Dijkstra {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int n, m, orig, dest;
  static ArrayList<ArrayList<Edge>> adj;
  static int[] dist;
  static boolean[] v;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    m = readInt();

    orig = readInt() - 1;
    dest = readInt() - 1;

    adj = new ArrayList<ArrayList<Edge>>();

    dist = new int[n];
    v = new boolean[n];

    for (int i = 0; i < n; i++) {
      adj.add(new ArrayList<Edge>());
      dist[i] = 1 << 30;
    }

    for (int i = 0; i < m; i++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      int c = readInt();
      adj.get(a).add(new Edge(b, c));
    }
    dist[orig] = 0;

    for (int i = 0; i < n - 1; i++) {
      int minIndex = -1;
      for (int j = 0; j < n; j++)
        if (!v[j] && (minIndex == -1 || dist[minIndex] > dist[j]))
          minIndex = j;
      v[minIndex] = true;
      for (Edge e : adj.get(minIndex))
        dist[e.dest] = Math.min(dist[e.dest], dist[minIndex] + e.cost);
    }

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
    int dest, cost;

    Edge(int dest, int cost) {
      this.dest = dest;
      this.cost = cost;
    }
  }
}
