package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Dijkstra_Shortest_Reach_2 {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //pr = new PrintWriter(new FileWriter("out.txt"));

    int t = readInt();
    for (int qq = 0; qq < t; qq++) {
      int n = readInt();
      int m = readInt();
      ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();

      int[] dist = new int[n];
      for (int i = 0; i < n; i++) {
        adj.add(new ArrayList<Edge>());
        dist[i] = 1 << 29;
      }
      for (int i = 0; i < m; i++) {
        int a = readInt() - 1;
        int b = readInt() - 1;
        int c = readInt();
        adj.get(a).add(new Edge(b, c));
        adj.get(b).add(new Edge(a, c));
      }
      int s = readInt() - 1;
      PriorityQueue<Vertex> q = new PriorityQueue<Vertex>();
      dist[s] = 0;
      q.offer(new Vertex(s, 0));
      while (!q.isEmpty()) {
        Vertex curr = q.poll();
        for (Edge next : adj.get(curr.index)) {
          if (dist[next.dest] > curr.cost + next.cost) {
            dist[next.dest] = curr.cost + next.cost;
            q.offer(new Vertex(next.dest, dist[next.dest]));
          }
        }
      }
      for (int i = 0; i < n; i++) {
        if (i == s)
          continue;
        System.out.print(dist[i] == 1 << 29 ? "-1 " : dist[i] + " ");
      }
      System.out.println();
    }

    pr.close();
  }

  static class Edge {
    int dest, cost;

    Edge (int dest, int cost) {
      this.dest = dest;
      this.cost = cost;
    }
  }

  static class Vertex implements Comparable<Vertex> {
    int index, cost;

    Vertex (int index, int cost) {
      this.index = index;
      this.cost = cost;
    }

    @Override
    public int compareTo (Vertex o) {
      return cost - o.cost;
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
