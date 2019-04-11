package contest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Traffic_Lights {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int m;

  public static void main(String[] args) throws IOException {
    ArrayList<ArrayList<Edge>> adjlist = new ArrayList<ArrayList<Edge>>();
    int n = readInt();
    m = readInt();
    for (int x = 0; x < m; x++)
      adjlist.add(new ArrayList<Edge>());
    for (int x = 0; x < n; x++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      int c = readInt();
      adjlist.get(a).add(new Edge(b, c));
      adjlist.get(b).add(new Edge(a, c));
    }
    ArrayList<int[]> cycles = new ArrayList<int[]>();
    for (int x = 0; x < m; x++) {
      cycles.add(new int[] {readInt(), readInt()});
    }
    System.out.println(shortestPath(adjlist, cycles));
  }

  private static int shortestPath(ArrayList<ArrayList<Edge>> adjlist, ArrayList<int[]> cycles) {
    int[] min = new int[m];
    for (int x = 1; x < m; x++)
      min[x] = Integer.MAX_VALUE;
    PriorityQueue<Vertex> moves = new PriorityQueue<Vertex>();
    moves.offer(new Vertex(0, 0));
    while (!moves.isEmpty()) {
      Vertex curr = moves.poll();
      if (curr.index == m - 1)
        return curr.time;
      for (int x = 0; x < adjlist.get(curr.index).size(); x++) {
        Edge next = adjlist.get(curr.index).get(x);
        int nextTime = curr.time + next.cost;
        int greenCycle = cycles.get(next.dest)[0];
        int redCycle = cycles.get(next.dest)[1];
        int totalCycle = greenCycle + redCycle;
        if (totalCycle != 0 && nextTime % (totalCycle)-greenCycle >= 0)
          nextTime += redCycle - (nextTime % (totalCycle)-greenCycle);

        if (nextTime >= min[next.dest])
          continue;

        moves.offer(new Vertex(next.dest, nextTime));
        min[next.dest] = nextTime;
      }
    }
    return -1;
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
    int time;

    Vertex(int index, int time) {
      this.index = index;
      this.time = time;
    }

    @Override
    public int compareTo(Vertex o) {
      return time - o.time;
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
