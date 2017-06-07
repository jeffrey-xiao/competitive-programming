/*
 * Shortest Path Faster Algorithm solves the single-source shortestd path (SSSP) problem. It is an improvement of the Bellman-Ford algorithm.
 *
 * Time complexity:
 *  - Average: O(E)
 *  - Worst: O(VE)
 */

package codebook.graph.shortestpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SPFA {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int n, m, orig, dest;
  static int[] dist;
  static ArrayList<ArrayList<Edge>> adj;
  static Queue<Integer> q;
  static boolean[] inQ;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    m = readInt();
    orig = readInt() - 1;
    dest = readInt() - 1;

    dist = new int[n];
    inQ = new boolean[n];
    adj = new ArrayList<ArrayList<Edge>>();

    for (int x = 0; x < n; x++) {
      dist[x] = Integer.MAX_VALUE;
      adj.add(new ArrayList<Edge>());
    }
    dist[orig] = 0;

    for (int i = 0; i < m; i++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      int c = readInt();
      adj.get(a).add(new Edge(b, c));
    }

    q = new ArrayDeque<Integer>();
    q.offer(orig);
    while (!q.isEmpty()) {
      Integer curr = q.poll();
      for (Edge e : adj.get(curr)) {
        if (dist[curr] + e.cost < dist[e.dest]) {
          dist[e.dest] = dist[curr] + e.cost;
          if (!q.contains(e.dest))
            q.offer(e.dest);
        }
      }
    }

    out.println(dist[dest]);
    out.close();
  }

  static class Edge {
    int dest, cost;

    Edge (int dest, int cost) {
      this.dest = dest;
      this.cost = cost;
    }
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

  static char readCharacter () throws IOException {
    return next().charAt(0);
  }

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
