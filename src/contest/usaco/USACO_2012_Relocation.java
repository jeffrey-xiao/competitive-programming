package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class USACO_2012_Relocation {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();
  static ArrayList<int[]> min = new ArrayList<int[]>();
  static int n;
  static int[][] paths;
  static int[] markets;

  public static void main(String[] args) throws IOException {
    n = readInt();
    int m = readInt();
    int k = readInt();
    markets = new int[k];
    paths = new int[k][k];
    for (int x = 0; x < n; x++)
      adj.add(new ArrayList<Edge>());
    for (int x = 0; x < k; x++) {
      min.add(new int[n]);
      markets[x] = readInt() - 1;
    }
    for (int x = 0; x < m; x++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      int c = readInt();
      adj.get(a).add(new Edge(b, c));
      adj.get(b).add(new Edge(a, c));
    }

    for (int x = 0; x < k; x++)
      getPath(markets[x], x);
    permute(k, k, new ArrayList<Integer>());
    int minValue = Integer.MAX_VALUE;
    main:
    for (int x = 0; x < n; x++) {
      for (int y = 0; y < k; y++) {
        if (x == markets[y]) {
          continue main;
        }
      }
      for (int y = 0; y < k; y++) {
        for (int z = 0; z < k; z++) {
          if (y != z) {
            minValue = Math.min(minValue, min.get(z)[x] + min.get(y)[x] + paths[y][z]);
          }
        }
      }
    }
    System.out.println(minValue);
  }

  private static void permute(int i, int c, ArrayList<Integer> a) {
    if (c == 0) {
      int sum = 0;
      for (int x = 0; x < a.size() - 1; x++)
        sum += min.get(a.get(x))[markets[a.get(x + 1)]];
      paths[a.get(0)][a.get(a.size() - 1)] = sum;
      return;
    }
    for (int x = 0; x < i; x++) {
      if (!a.contains(x)) {
        a.add(x);
        permute(i, c - 1, a);
        a.remove((Integer) x);
      }
    }
  }

  private static void getPath(int s, int i) {
    int[] m = min.get(i);
    for (int x = 0; x < n; x++)
      if (x != s)
        m[x] = Integer.MAX_VALUE;
    PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
    pq.offer(new Vertex(s, 0));
    while (!pq.isEmpty()) {
      Vertex curr = pq.poll();
      for (int x = 0; x < adj.get(curr.index).size(); x++) {
        Edge next = adj.get(curr.index).get(x);
        if (curr.cost + next.cost < m[next.dest]) {
          m[next.dest] = curr.cost + next.cost;
          pq.offer(new Vertex(next.dest, m[next.dest]));
        }
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

  static String readLine() throws IOException {
    return br.readLine().trim();
  }

  static class Edge {
    int dest;
    int cost;

    Edge(int dest, int cost) {
      this.dest = dest;
      this.cost = cost;
    }
  }

  static class Vertex implements Comparable<Vertex> {
    int index;
    int cost;

    Vertex(int index, int cost) {
      this.index = index;
      this.cost = cost;
    }

    @Override
    public int compareTo(Vertex v) {
      return cost - v.cost;
    }
  }
}
