package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class VMSS_Frank_And_Roads {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int t = readInt();
    int n = readInt() + 1;
    int m = readInt();
    int g = readInt();
    ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();
    boolean[] isBasic = new boolean[n];
    int[] min = new int[n];
    for (int i = 0; i < n; i++) {
      adj.add(new ArrayList<Edge>());
      min[i] = 1 << 30;
    }
    for (int i = 0; i < g; i++)
      isBasic[readInt()] = true;
    for (int i = 0; i < m; i++) {
      int a = readInt();
      int b = readInt();
      int c = readInt();
      adj.get(a).add(new Edge(b, c));
    }
    PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
    pq.offer(new Vertex(0, 0));
    min[0] = 0;
    while (!pq.isEmpty()) {
      Vertex curr = pq.poll();
      for (Edge next : adj.get(curr.index)) {
        if (min[next.dest] <= curr.cost + next.cost)
          continue;
        min[next.dest] = curr.cost + next.cost;
        pq.offer(new Vertex(next.dest, min[next.dest]));
      }
    }
    int cnt = 0;
    for (int i = 0; i < n; i++)
      if (isBasic[i] && min[i] <= t)
        cnt++;
    out.println(cnt);
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
    int cost, dest;

    Edge(int dest, int cost) {
      this.cost = cost;
      this.dest = dest;
    }
  }

  static class Vertex implements Comparable<Vertex> {
    int index, cost;

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
