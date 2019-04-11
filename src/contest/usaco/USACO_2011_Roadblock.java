package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class USACO_2011_Roadblock {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int n = 0;
  static ArrayList<Integer> shortestPath = null;

  public static void main(String[] args) throws IOException {
    ArrayList<ArrayList<Edge>> adjlist = new ArrayList<ArrayList<Edge>>();
    n = readInt();
    for (int x = 0; x < n; x++)
      adjlist.add(new ArrayList<Edge>());
    int m = readInt();
    for (int x = 0; x < m; x++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      int c = readInt();
      adjlist.get(a).add(new Edge(b, c));
      adjlist.get(b).add(new Edge(a, c));
    }
    int ori = shortestPath(0, n - 1, adjlist, true);
    int max = 0;
    for (int x = 0; x < shortestPath.size() - 1; x++) {
      int a = shortestPath.get(x);
      int b = shortestPath.get(x + 1);
      adjlist.get(a).get(adjlist.get(a).indexOf(new Edge(b, 0))).cost *= 2;
      adjlist.get(b).get(adjlist.get(b).indexOf(new Edge(a, 0))).cost *= 2;
      max = Math.max(max, shortestPath(0, n - 1, adjlist, false));
      adjlist.get(a).get(adjlist.get(a).indexOf(new Edge(b, 0))).cost /= 2;
      adjlist.get(b).get(adjlist.get(b).indexOf(new Edge(a, 0))).cost /= 2;
    }
    System.out.println(max - ori);
  }

  private static int shortestPath(int i, int d, ArrayList<ArrayList<Edge>> adjlist, boolean getShortestPath) {
    boolean[] visited = new boolean[n];
    int[] min = new int[n];
    int[] prevDest = new int[n];
    PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
    pq.add(new Vertex(i, 0, -1));
    while (!pq.isEmpty()) {
      Vertex curr = pq.poll();
      visited[curr.index] = true;
      min[curr.index] = curr.cost;
      prevDest[curr.index] = curr.prev;
      for (int x = 0; x < adjlist.get(curr.index).size(); x++) {
        Edge e = adjlist.get(curr.index).get(x);
        Vertex next = new Vertex(e.dest, e.cost + curr.cost, curr.index);
        if (visited[next.index])
          continue;
        if (min[next.index] == 0 || min[next.index] > next.cost) {
          min[next.index] = next.cost;
          pq.remove(next);
          pq.add(next);
        }
      }
    }
    if (getShortestPath) {
      shortestPath = new ArrayList<Integer>();
      int curr = n - 1;
      while (curr != -1) {
        shortestPath.add(curr);
        curr = prevDest[curr];
      }
      return min[d];
    }
    return min[d];
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

  static class Vertex implements Comparable<Vertex> {
    int index;
    int cost;
    int prev;

    Vertex(int index, int cost, int prev) {
      this.index = index;
      this.cost = cost;
      this.prev = prev;
    }

    @Override
    public int compareTo(Vertex o) {
      return cost - o.cost;
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof Vertex) {
        Vertex v = (Vertex)o;
        return index == v.index;
      }
      return false;
    }
  }

  static class Edge {
    int dest;
    int cost;

    Edge(int dest, int cost) {
      this.dest = dest;
      this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof Edge) {
        Edge v = (Edge)o;
        return dest == v.dest;
      }
      return false;
    }
  }
}
