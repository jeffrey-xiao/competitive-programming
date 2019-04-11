/*
 * Dinic's algorithm finds the maximal flow in a flow network using the concepts of level graph and blocking flow.
 * 
 * Time complexity: O(EV^2)
 */

package codebook.graph.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class MaxFlowDinic {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static Edge[] e;
  static int[] last, dist;
  static int N, M, cnt, src, sink;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    M = readInt();

    src = 0;
    sink = N - 1;

    last = new int[N];
    e = new Edge[2 * M];

    Arrays.fill(last, -1);

    for (int i = 0; i < M; i++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      int c = readInt();
      addEdge(a, b, c, 0);
    }

    out.println(getFlow());
    out.close();
  }

  static long getFlow() {
    long res = 0;

    while (getPath())
      res += dfs(src, 1 << 30);

    return res;
  }

  static boolean getPath() {
    dist = new int[N];
    Arrays.fill(dist, -1);

    Queue<Integer> q = new ArrayDeque<Integer>();
    q.offer(src);
    dist[src] = 0;

    while (!q.isEmpty()) {
      int curr = q.poll();
      for (int i = last[curr]; i != -1; i = e[i].next) {
        if (e[i].cost > 0 && dist[e[i].dest] == -1) {
          dist[e[i].dest] = dist[curr] + 1;
          q.offer(e[i].dest);
        }
      }
    }

    return dist[sink] != -1;
  }

  static long dfs(int curr, long flow) {
    if (curr == sink)
      return flow;

    long ret = 0;

    for (int i = last[curr]; i != -1; i = e[i].next) {
      if (e[i].cost > 0 && dist[e[i].dest] == dist[curr] + 1) {
        long res = dfs(e[i].dest, Math.min(flow, e[i].cost));
        ret += res;
        e[i].cost -= res;
        e[i ^ 1].cost += res;
        flow -= res;

        if (flow == 0)
          break;
      }
    }
    return ret;
  }

  static void addEdge(int x, int y, int xy, int yx) {
    e[cnt] = new Edge(y, xy, last[x]);
    last[x] = cnt++;
    e[cnt] = new Edge(x, yx, last[y]);
    last[y] = cnt++;
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
    int dest, next;
    long cost;

    Edge(int dest, long cost, int next) {
      this.dest = dest;
      this.cost = cost;
      this.next = next;
    }
  }
}
