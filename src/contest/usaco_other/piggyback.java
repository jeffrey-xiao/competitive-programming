package contest.usaco_other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/*
ID: jeffrey40
LANG: JAVA
TASK: piggyback
 */
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class piggyback {
  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;
  static int b, e, p, n;
  static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new FileReader("piggyback.in"));
    // br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new BufferedWriter(new FileWriter("piggyback.out")));

    b = readInt();
    e = readInt();
    p = readInt();
    n = readInt();
    int m = readInt();
    int[] bessie = new int[n];
    int[] elsie = new int[n];
    int[] total = new int[n];
    for (int x = 0; x < n; x++) {
      adj.add(new ArrayList<Integer>());
      bessie[x] = elsie[x] = total[x] = Integer.MAX_VALUE;
    }
    for (int x = 0; x < m; x++) {
      int i = readInt() - 1;
      int j = readInt() - 1;
      adj.get(i).add(j);
      adj.get(j).add(i);
    }
    shortestPath(0, adj, bessie, b);
    shortestPath(1, adj, elsie, e);
    shortestPath(n - 1, adj, total, p);
    int min = Integer.MAX_VALUE;
    for (int x = 0; x < n; x++) {
      min = Math.min(min, bessie[x] + elsie[x] + total[x]);
    }
    pr.println(min);
    pr.close();
    System.exit(0);
  }

  private static void shortestPath(int i, ArrayList<ArrayList<Integer>> list, int[] min, int cost) {
    PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
    pq.offer(new Vertex(i, 0));
    min[i] = 0;
    while (!pq.isEmpty()) {
      Vertex curr = pq.poll();
      for (Integer x : adj.get(curr.index)) {
        if (min[x] <= curr.cost + cost)
          continue;
        min[x] = curr.cost + cost;
        pq.offer(new Vertex(x, min[x]));
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
}
