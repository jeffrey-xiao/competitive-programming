package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class DMOPC_2014_Attack_On_Anti_Spiral {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  static ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();
  static ArrayList<ArrayList<Integer>> g = new ArrayList<ArrayList<Integer>>();
  static int[] low = new int[100001], disc = new int[100001], id = new int[100001];
  static long[] sum = new long[100001];
  static int cnt = 0;
  static int num = 0;
  static boolean[] bridge = new boolean[100001];
  static Stack<Integer> s = new Stack<Integer>();

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // pr = new PrintWriter(new FileWriter("out.txt"));
    int n = readInt();
    int m = readInt();
    for (int i = 0; i < n; i++) {
      adj.add(new ArrayList<Edge>());
      low[i] = disc[i] = -1;
    }
    for (int i = 0; i < m; i++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      int c = readInt();
      adj.get(a).add(new Edge(b, c));
      adj.get(b).add(new Edge(a, c));
    }
    dfs(0, -1);
    for (int i = 1; i < 100001; i++)
      g.add(new ArrayList<Integer>());
    for (int i = 1; i < n; i++) {
      for (Edge j : adj.get(i)) {
        if (j.dest == 0)
          continue;
        g.get(id[i]).add(id[j.dest]);
        g.get(id[j.dest]).add(id[i]);
      }
    }
    long[] min = new long[100001];
    for (int i = 0; i <= 100000; i++)
      min[i] = 1 << 30;
    Queue<Integer> q = new LinkedList<Integer>();
    min[0] = 0;
    q.offer(0);

    while (!q.isEmpty()) {
      Integer curr = q.poll();
      for (Edge next : adj.get(curr)) {
        if (min[next.dest] == 1 << 30) {
          min[next.dest] = min[curr] + 1;
          bridge[next.dest] |= bridge[curr];
          q.offer(next.dest);
        }
      }
    }

    for (int i = 0; i < n; i++) {
      for (Edge j : adj.get(i)) {
        if (id[i] == id[j.dest]) {
          sum[id[i]] += j.cost;
        } else {
          if (min[j.dest] > min[i]) {
            sum[id[j.dest]] += j.cost;
          } else {
            sum[id[i]] += j.cost;
          }
        }
      }
    }
    boolean[] v = new boolean[100001];
    q = new LinkedList<Integer>();
    v[id[0]] = true;
    q.offer(id[0]);
    while (!q.isEmpty()) {
      int i = q.poll();
      for (int j : g.get(i)) {
        if (!v[j]) {
          v[j] = true;
          sum[j] += sum[i];
          q.offer(j);
        }
      }
    }
    for (int i = 0; i <= 100000; i++)
      min[i] = Long.MAX_VALUE;
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
    v[0] = false;
    for (int i = 1; i < n; i++) {
      if (bridge[i]) {
        System.out.println(1 + " " + min[i]);
      } else {
        System.out.println(2 + " " + sum[id[i]] / 2);
      }
    }
    pr.close();
  }

  private static void dfs(int i, int prev) {
    low[i] = disc[i] = cnt++;
    s.push(i);
    for (Edge j : adj.get(i)) {
      if (j.dest == prev)
        continue;
      if (disc[j.dest] == -1) {
        dfs(j.dest, i);
        if (low[j.dest] > disc[i]) {
          bridge[j.dest] = true;
        }
        if (low[j.dest] >= disc[i]) {
          while (s.peek() != j.dest)
            id[s.pop()] = num;
          id[s.pop()] = num;
          if (!s.isEmpty() && disc[i] == 0)
            id[s.pop()] = num;
          num++;
        } else
          low[i] = Math.min(low[i], low[j.dest]);
      } else {
        low[i] = Math.min(low[i], disc[j.dest]);
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

  static char readCharacter() throws IOException {
    return next().charAt(0);
  }

  static String readLine() throws IOException {
    return br.readLine().trim();
  }

  static class Vertex implements Comparable<Vertex> {
    int index;
    Long cost;

    Vertex(int index, long cost) {
      this.index = index;
      this.cost = cost;
    }

    @Override
    public int compareTo(Vertex o) {
      return cost.compareTo(o.cost);
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
