package contest.woburn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Woburn_Challenge_2015_Contest_Sites {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;
  static int n, m, k;
  static ArrayList<ArrayList<Edge>> adj;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    m = readInt();
    k = readInt();
    long[] live = new long[n];
    adj = new ArrayList<ArrayList<Edge>>();
    for (int i = 0; i < n; i++) {
      live[i] = readInt();
      adj.add(new ArrayList<Edge>());
    }
    for (int i = 0; i < m; i++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      int c = readInt();
      adj.get(b).add(new Edge(a, c));
    }
    int[] path1 = path(0);
    int[] path2 = path(1);
    int cnt = 0;
    for (int i = 0; i < n; i++) {
      if (live[i] > 0 && path1[i] == 1 << 30 && path2[i] == 1l << 30) {
        System.out.println(-1);
        return;
      } else if (path1[i] == 1 << 30)
        cnt += live[i];
    }
    if (cnt > k) {
      System.out.println(-1);
      return;
    }
    ArrayList<Vertex> diff = new ArrayList<Vertex>();
    for (int i = 0; i < n; i++)
      diff.add(new Vertex(i, path2[i] - path1[i]));
    Collections.sort(diff);
    long res = 0;
    for (int i = 0; i < n; i++) {
      if (diff.get(i).cost >= 0)
        res += live[diff.get(i).index] * (long)path1[diff.get(i).index];
      else {
        int index = diff.get(i).index;
        if (live[index] > k) {
          res += k * (long)path2[index];
          res += (live[index] - k) * (long)path1[index];
          k = 0;
        } else {
          res += live[index] * (long)path2[index];
          k -= live[index];
        }
      }
    }
    out.println(res);
    out.close();
  }

  public static int[] path(int s) {
    int[] dist = new int[n];
    for (int i = 0; i < n; i++)
      dist[i] = 1 << 30;
    dist[s] = 0;
    PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
    pq.offer(new Vertex(s, 0));
    while (!pq.isEmpty()) {
      Vertex curr = pq.poll();
      for (Edge next : adj.get(curr.index)) {
        if (next.cost + curr.cost < dist[next.dest]) {
          dist[next.dest] = next.cost + curr.cost;
          pq.offer(new Vertex(next.dest, dist[next.dest]));
        }
      }
    }
    return dist;
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

  static class Vertex implements Comparable<Vertex> {
    int index;
    Integer cost;

    Vertex(int index, int cost) {
      this.index = index;
      this.cost = cost;
    }

    @Override
    public int compareTo(Vertex v) {
      return cost.compareTo(v.cost);
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
