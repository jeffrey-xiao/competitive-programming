package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class WOC_20_C {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, M, K;
  static int[][] adj, dp, dist;
  static ArrayList<ArrayList<Integer>> nodes;
  static ArrayList<ArrayList<Pair>> cost;
  static int[] bit;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    M = readInt();
    K = readInt();

    cost = new ArrayList<ArrayList<Pair>>(N);
    nodes = new ArrayList<ArrayList<Integer>>(K);
    adj = new int[N][N];
    bit = new int[N];
    dp = new int[1 << K][N];
    dist = new int[N][N];

    for (int i = 0; i < K; i++)
      nodes.add(new ArrayList<Integer>());

    for (int i = 0; i < N; i++) {
      int sz = readInt();
      for (int j = 0; j < sz; j++) {
        int val = readInt() - 1;
        nodes.get(val).add(i);
        bit[i] |= 1 << val;
      }
    }

    for (int i = 0; i < N; i++) {
      cost.add(new ArrayList<Pair>());
      for (int j = 0; j < N; j++)
        adj[i][j] = 1 << 25;
      adj[i][i] = 0;
    }

    for (int i = 0; i < M; i++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      int c = readInt();
      adj[a][b] = adj[b][a] = c;
      cost.get(a).add(new Pair(b, c));
      cost.get(b).add(new Pair(a, c));
    }

    for (int i = 0; i < N; i++)
      getShortestPath(i);

    for (int i = 0; i < 1 << K; i++)
      for (int j = 0; j < N; j++)
        dp[i][j] = 1 << 25;

    PriorityQueue<State> pq = new PriorityQueue<State>();
    pq.offer(new State(bit[0], 0, 0));
    dp[bit[0]][0] = 0;

    while (!pq.isEmpty()) {
      State curr = pq.poll();
      for (int i = 0; i < N; i++) {
        int newBits = curr.bits | bit[i];
        if (dp[newBits][i] <= curr.cost + dist[curr.curr][i] || curr.bits == newBits)
          continue;
        dp[newBits][i] = curr.cost + dist[curr.curr][i];
        pq.offer(new State(newBits, i, dp[newBits][i]));
      }
    }

    int ans = 1 << 30;

    int[] bestDp = new int[1 << K];
    Arrays.fill(bestDp, 1 << 30);

    for (int i = 0; i < 1 << K; i++)
      for (int j = 0; j < N; j++)
        bestDp[i] = Math.min(bestDp[i], dp[i][j] + dist[j][N - 1]);

    for (int i = 0; i < 1 << K; i++)
      for (int j = 0; j < 1 << K; j++)
        if ((i | j) == (1 << K) - 1)
          ans = Math.min(ans, Math.max(bestDp[i], bestDp[j]));

    out.println(ans);
    out.close();
  }

  static void getShortestPath(int u) {
    dist[u] = new int[N];
    Arrays.fill(dist[u], 1 << 30);
    PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
    pq.offer(new Pair(u, 0));
    dist[u][u] = 0;

    while (!pq.isEmpty()) {
      Pair curr = pq.poll();

      for (Pair next : cost.get(curr.dest)) {
        if (dist[u][next.dest] <= curr.cost + next.cost)
          continue;
        dist[u][next.dest] = curr.cost + next.cost;
        pq.offer(new Pair(next.dest, dist[u][next.dest]));
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

  static class Pair implements Comparable<Pair> {
    int dest, cost;

    Pair(int dest, int cost) {
      this.dest = dest;
      this.cost = cost;
    }

    @Override
    public int compareTo(Pair o) {
      return cost - o.cost;
    }
  }

  static class State implements Comparable<State> {
    int bits, curr, cost;

    State(int bits, int curr, int cost) {
      this.bits = bits;
      this.curr = curr;
      this.cost = cost;
    }

    @Override
    public int compareTo(State s) {
      return cost - s.cost;
    }
  }
}
