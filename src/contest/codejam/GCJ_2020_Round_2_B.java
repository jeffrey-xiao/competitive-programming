package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2020_Round_2_B {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T;
  static int C, D;

  static ArrayList<ArrayList<Edge>> adj;
  static PriorityQueue<State> numBeforePQ, costPQ;
  static State[] states;
  static int[] dist;

  static int[] edgeCosts;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    for (int t = 1; t <= T; t++) {
      C = readInt();
      D = readInt();

      adj = new ArrayList<>();
      numBeforePQ = new PriorityQueue<>();
      costPQ = new PriorityQueue<>();
      dist = new int[C];
      edgeCosts = new int[D];

      for (int i = 0; i < C; i++) {
        adj.add(new ArrayList<>());
      }
      Arrays.fill(dist, Integer.MAX_VALUE);
      dist[0] = 0;

      for (int i = 1; i < C; i++) {
        int val = readInt();
        if (val < 0) {
          numBeforePQ.offer(new State(i, -val));
        } else {
          costPQ.offer(new State(i, val));
        }
      }

      Arrays.fill(edgeCosts, (int)1e6);
      for (int i = 0; i < D; i++) {
        int u = readInt() - 1;
        int v = readInt() - 1;
        adj.get(u).add(new Edge(v, i));
        adj.get(v).add(new Edge(u, i));
      }

      int nodesFixed = 1;
      int prevMaxDist = 0;
      while (!numBeforePQ.isEmpty()) {
        State numBeforeNode = numBeforePQ.peek();
        while (nodesFixed != numBeforeNode.val) {
          State costNode = costPQ.poll();
          fixCostNode(costNode.index, costNode.val);
          prevMaxDist = Math.max(prevMaxDist,  dist[costNode.index]);
          nodesFixed++;
        }

        int nextMaxDist = prevMaxDist;
        while (!numBeforePQ.isEmpty() && numBeforePQ.peek().val == numBeforeNode.val) {
          numBeforeNode = numBeforePQ.poll();
          fixNumBeforeNode(numBeforeNode.index, prevMaxDist);
          nextMaxDist = Math.max(nextMaxDist, dist[numBeforeNode.index]);
          nodesFixed++;
        }
        prevMaxDist = nextMaxDist;
      }

      while (!costPQ.isEmpty()) {
        State costNode = costPQ.poll();
        fixCostNode(costNode.index, costNode.val);
      }

      out.printf("Case #%d:", t);
      for (int i = 0; i < D; i++) {
        out.print(" " + edgeCosts[i]);
      }
      out.println();
    }

    out.close();
  }

  static void fixCostNode(int index, int currDist) {
    Edge minEdge = null;
    for (Edge edge : adj.get(index)) {
      if (minEdge == null || (dist[minEdge.dest] > dist[edge.dest])) {
        minEdge = edge;
      }
    }
    dist[index] = currDist;
    edgeCosts[minEdge.index] = currDist - dist[minEdge.dest];
  }

  static void fixNumBeforeNode(int index, int prevMaxDist) {
    Edge minEdge = null;
    for (Edge edge : adj.get(index)) {
      if (minEdge == null || (dist[minEdge.dest] > dist[edge.dest])) {
        minEdge = edge;
      }
    }
    dist[index] = prevMaxDist + 1;
    edgeCosts[minEdge.index] = prevMaxDist + 1 - dist[minEdge.dest];
  }

  static class State implements Comparable<State> {
    int index, val;
    State(int index, int val) {
      this.index = index;
      this.val = val;
    }

    public int compareTo(State s) {
      return val - s.val;
    }
  }

  static class Edge {
    int dest, index;
    Edge(int dest, int index) {
      this.dest = dest;
      this.index = index;
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
}
