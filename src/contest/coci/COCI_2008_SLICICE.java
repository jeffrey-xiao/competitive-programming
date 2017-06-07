package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class COCI_2008_SLICICE {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();
  static int n, m, p;
  static int[][] input;

  public static void main (String[] args) throws IOException {
    n = readInt();
    m = readInt();
    input = new int[m + 1][2];
    // SOURCE IS 0
    // PURCHASES ARE FROM 1-P
    // KIDS ARE FROM P+1 - P+N
    // SINK IS P+N+1;
    int[] cards = new int[n];
    int sum = 0;
    for (int x = 0; x < n; x++)
      sum += (cards[x] = readInt());
    p = sum / 2;
    for (int x = 0; x <= p + n + 1; x++)
      adj.add(new ArrayList<Edge>());

    for (int x = 1; x <= p; x++) {
      adj.get(0).add(new Edge(x, 2));
      adj.get(x).add(new Edge(0, 0));
      if (x < 1 + m) {
        int a = readInt() + p;
        int b = readInt() + p;
        input[x][0] = a;
        input[x][1] = b;
        adj.get(x).add(new Edge(a, Integer.MAX_VALUE));
        adj.get(x).add(new Edge(b, Integer.MAX_VALUE));

        adj.get(a).add(new Edge(x, 0));
        adj.get(b).add(new Edge(x, 0));
      } else {
        for (int y = p + 1; y <= p + n; y++) {
          adj.get(x).add(new Edge(y, Integer.MAX_VALUE));
          adj.get(y).add(new Edge(x, 0));
        }
      }
    }
    for (int x = p + 1; x <= p + n; x++) {
      adj.get(x).add(new Edge(p + n + 1, cards[x - p - 1]));
      adj.get(p + n + 1).add(new Edge(x, 0));
    }
    System.out.println(p);
    maxFlow();
  }

  static class Edge {
    int dest;
    int cost;

    Edge (int dest, int cost) {
      this.dest = dest;
      this.cost = cost;
    }

    @Override
    public boolean equals (Object o) {
      if (o instanceof Edge) {
        Edge e = (Edge)o;
        return dest == e.dest;
      }
      return false;
    }

    @Override
    public String toString () {
      return "Dest: " + dest + "; Cost " + cost;
    }
  }

  static class State {
    int max;
    int index;

    State (int index, int max) {
      this.index = index;
      this.max = max;
    }
  }

  private static boolean maxFlow () {
    int augment = bfs();
    while (augment != 0) {
      augment = bfs();
    }
    int[] nums = new int[n];
    main : for (int x = 1; x <= p; x++) {
      if (x <= m) {
        int flow = Integer.MAX_VALUE - adj.get(x).get(adj.get(x).indexOf(new Edge(input[x][0], 0))).cost;
        System.out.println(input[x][0] - p + " " + (input[x][1] - p) + " " + flow);
      } else {
        boolean first = true;
        int firstFlow = 0;
        for (int y = 0; y < adj.get(x).size(); y++) {
          Edge curr = adj.get(x).get(y);
          if (curr.dest != 0) {
            int flow = Integer.MAX_VALUE - curr.cost;
            if (flow > 0 && first) {
              System.out.print((curr.dest - p) + " ");
              firstFlow = flow;
              nums[curr.dest - p - 1] += flow;
              if (flow == 2) {
                if (y - 1 >= 1)
                  System.out.println(adj.get(x).get(y - 1).dest - p + " " + flow);
                else if (y + 1 < adj.get(x).size())
                  System.out.println(adj.get(x).get(y + 1).dest - p + " " + flow);
                continue main;
              }
              first = false;
            } else if (flow > 0 && !first) {
              System.out.print((curr.dest - p) + " " + firstFlow);
              nums[curr.dest - p - 1] += flow;
            }
          }
        }
        System.out.println();
      }
    }
    return true;
  }

  private static int bfs () {
    int d = n + p + 1;
    Queue<State> moves = new LinkedList<State>();
    int[] max = new int[n + p + 2];
    int[] prev = new int[n + p + 2];
    boolean[] v = new boolean[n + p + 2];
    for (int x = 0; x < n + p + 2; x++) {
      max[x] = Integer.MAX_VALUE;
      prev[x] = -2;
    }
    prev[0] = -1;
    max[0] = 0;
    v[0] = true;
    moves.offer(new State(0, Integer.MAX_VALUE));
    while (!moves.isEmpty()) {
      State currState = moves.poll();
      if (currState.index == d)
        break;
      for (int x = 0; x < adj.get(currState.index).size(); x++) {
        Edge next = adj.get(currState.index).get(x);
        if (v[next.dest] || next.cost == 0)
          continue;
        max[next.dest] = Math.min(currState.max, next.cost);
        prev[next.dest] = currState.index;
        v[next.dest] = true;
        moves.offer(new State(next.dest, max[next.dest]));
      }
    }
    if (prev[n + p + 1] == -2)
      return 0;
    int neck = max[n + p + 1];
    int c = n + p + 1;
    while (prev[c] != -1) {
      int index = adj.get(prev[c]).indexOf(new Edge(c, 0));
      int index1 = adj.get(c).indexOf(new Edge(prev[c], 0));

      int cost = adj.get(prev[c]).get(index).cost;
      int cost1 = adj.get(c).get(index1).cost;

      adj.get(prev[c]).set(index, new Edge(c, cost - neck));
      adj.get(c).set(index1, new Edge(prev[c], cost1 + neck));
      c = prev[c];
    }
    return neck;
  }

  static String next () throws IOException {
    while (st == null || !st.hasMoreTokens())
      st = new StringTokenizer(br.readLine().trim());
    return st.nextToken();
  }

  static long readLong () throws IOException {
    return Long.parseLong(next());
  }

  static int readInt () throws IOException {
    return Integer.parseInt(next());
  }

  static double readDouble () throws IOException {
    return Double.parseDouble(next());
  }

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
