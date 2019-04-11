package contest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class King_Graffs_Trip {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int source;
  static int dest;
  static long maxLength;
  static int n;
  static HashSet<Integer> shrines = new HashSet<Integer>();

  public static void main(String[] args) throws IOException {
    ArrayList<ArrayList<Edge>> adjlist = new ArrayList<ArrayList<Edge>>();
    n = readInt();
    int m = readInt();
    source = readInt() - 1;
    dest = readInt() - 1;
    maxLength = readLong();

    for (int x = 0; x < n; x++)
      adjlist.add(new ArrayList<Edge>());

    for (int x = 0; x < m; x++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      int c = readInt();
      adjlist.get(a).add(new Edge(b, c));
    }
    int s = readInt();
    for (int x = 0; x < s; x++)
      shrines.add(readInt() - 1);

    long shortest = shortestPath(adjlist, source, dest);
    System.out.println(shortest == Long.MAX_VALUE ? -1 : shortest);
  }

  private static long shortestPath(ArrayList<ArrayList<Edge>> adjlist, int s, int d) {
    long[] min = new long[n];
    for (int x = 0; x < n; x++) {
      if (x != s)
        min[x] = Long.MAX_VALUE;
    }
    PriorityQueue<Vertex> moves = new PriorityQueue<Vertex>();
    moves.offer(new Vertex(s, 0, 0, 0));

    while (!moves.isEmpty()) {
      Vertex curr = moves.poll();
      for (int x = 0; x < adjlist.get(curr.index).size(); x++) {
        Edge next = adjlist.get(curr.index).get(x);

        long shrineTime = curr.currStretch + next.cost;
        long nextTime = curr.time + next.cost;
        long maxShrineTime = Math.max(curr.maxStretch, shrineTime);
        if (nextTime > maxLength)
          continue;

        if (maxShrineTime >= min[next.dest])
          continue;

        min[next.dest] = maxShrineTime;

        if (shrines.contains(next.dest))
          moves.offer(new Vertex(next.dest, nextTime, maxShrineTime, 0));
        else
          moves.offer(new Vertex(next.dest, nextTime, maxShrineTime, shrineTime));
      }
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
    long time;
    long maxStretch;
    long currStretch;

    Vertex(int index, long time, long maxStretch, long currStretch) {
      this.index = index;
      this.time = time;
      this.maxStretch = maxStretch;
      this.currStretch = currStretch;
    }

    @Override
    public int compareTo(Vertex o) {
      if (maxStretch < o.maxStretch)
        return -1;
      else if (maxStretch > o.maxStretch)
        return 1;
      return 0;
    }
  }

  static class Edge {
    int dest;
    int cost;

    Edge(int dest, int cost) {
      this.dest = dest;
      this.cost = cost;
    }
  }
}
