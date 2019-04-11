package contest.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ACM_NAQ_2016_C {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, M;
  static int[] items;
  static int[] A, B, C;
  static ArrayList<ArrayList<State>> adj = new ArrayList<ArrayList<State>>();

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();

    items = new int[N];

    for (int i = 0; i < N; i++) {
      items[i] = readInt();
      adj.add(new ArrayList<State>());
    }

    M = readInt();
    A = new int[M];
    B = new int[M];
    C = new int[M];
    for (int i = 0; i < M; i++) {
      A[i] = readInt() - 1;
      B[i] = readInt() - 1;
      C[i] = readInt();
      adj.get(A[i]).add(new State(B[i], C[i]));
      adj.get(B[i]).add(new State(A[i], C[i]));
    }

    int[][] start = path(0);
    int[][] end = path(N - 1);

    int shortestPath = start[0][N - 1];
    if (shortestPath == 1 << 30)
      out.println("impossible");
    else {
      int ans = 0;
      for (int i = 0; i < M; i++) {
        if (start[0][A[i]] + end[0][B[i]] + C[i] == shortestPath)
          ans = Math.max(ans, start[1][A[i]] + end[1][B[i]]);
        if (start[0][B[i]] + end[0][A[i]] + C[i] == shortestPath)
          ans = Math.max(ans, start[1][B[i]] + end[1][A[i]]);
      }

      out.println(shortestPath + " " + ans);
    }
    out.close();
  }

  static int[][] path(int u) {
    // 0: cost, 1: items
    int[][] ret = new int[2][N];
    Arrays.fill(ret[0], 1 << 30);

    PriorityQueue<State> pq = new PriorityQueue<State>();
    pq.offer(new State(u, 0, items[u]));
    ret[0][u] = 0;
    ret[1][u] = items[u];

    while (!pq.isEmpty()) {
      State curr = pq.poll();

      for (State next : adj.get(curr.index)) {
        if (ret[0][next.index] < next.cost + curr.cost || (ret[0][next.index] == next.cost + curr.cost && ret[1][next.index] >= curr.items + items[next.index]))
          continue;

        ret[0][next.index] = next.cost + curr.cost;
        ret[1][next.index] = curr.items + items[next.index];

        pq.offer(new State(next.index, ret[0][next.index], ret[1][next.index]));
      }
    }
    return ret;
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

  static class State implements Comparable<State> {
    int cost, index, items;

    State(int index, int cost) {
      this.index = index;
      this.cost = cost;
    }

    State(int index, int cost, int items) {
      this.index = index;
      this.cost = cost;
      this.items = items;
    }

    @Override
    public int compareTo(State s) {
      return cost - s.cost;
    }
  }
}