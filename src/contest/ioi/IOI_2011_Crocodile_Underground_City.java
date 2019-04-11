package contest.ioi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class IOI_2011_Crocodile_Underground_City {

  static final int INF = 1 << 30;
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    int m = readInt();
    int k = readInt();
    ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();
    for (int x = 0; x < n; x++)
      adj.add(new ArrayList<Edge>());
    for (int x = 0; x < m; x++) {
      int a = readInt();
      int b = readInt();
      int c = readInt();
      adj.get(a).add(new Edge(b, c));
      adj.get(b).add(new Edge(a, c));
    }
    int[] firstMin = new int[n];
    int[] secondMin = new int[n];
    for (int x = 0; x < n; x++) {
      firstMin[x] = INF;
      secondMin[x] = INF;
    }
    PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
    for (int x = 0; x < k; x++) {
      int a = readInt();
      firstMin[a] = 0;
      secondMin[a] = 0;
      pq.offer(new Vertex(a, 0));
    }
    while (!pq.isEmpty()) {
      Vertex curr = pq.poll();
      if (secondMin[curr.index] != curr.cost)
        continue;
      if (curr.index == 0)
        break;
      for (Edge next : adj.get(curr.index)) {
        int cost = next.cost + curr.cost;
        boolean change = false;
        if (cost < firstMin[next.dest]) {
          secondMin[next.dest] = firstMin[next.dest];
          firstMin[next.dest] = cost;
          change = true;
        } else if (cost < secondMin[next.dest]) {
          secondMin[next.dest] = cost;
          change = true;
        }

        if (secondMin[next.dest] != INF && change)
          pq.offer(new Vertex(next.dest, secondMin[next.dest]));
      }
    }
    System.out.println(secondMin[0]);
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
    int index, cost;

    Vertex(int index, int cost) {
      this.index = index;
      this.cost = cost;
    }

    @Override
    public int compareTo(Vertex o) {
      return cost - o.cost;
    }
  }

  static class Edge {
    int dest, cost;

    Edge(int dest, int cost) {
      this.dest = dest;
      this.cost = cost;
    }
  }
}
