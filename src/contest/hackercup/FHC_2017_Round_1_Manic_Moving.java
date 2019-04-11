package contest.hackercup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class FHC_2017_Round_1_Manic_Moving {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T, N, M, K;
  static int[] S, D;
  static ArrayList<ArrayList<State>> adj = new ArrayList<ArrayList<State>>();
  static int[] ret;
  static int[][] dist;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    br = new BufferedReader(new FileReader("in.txt"));
    out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();

    for (int t = 1; t <= T; t++) {
      N = readInt();
      M = readInt();
      K = readInt();

      adj.clear();
      S = new int[K + 1];
      D = new int[K + 1];

      for (int i = 0; i < N; i++)
        adj.add(new ArrayList<State>());

      for (int i = 0; i < M; i++) {
        int u = readInt() - 1;
        int v = readInt() - 1;
        int c = readInt();
        adj.get(u).add(new State(v, c));
        adj.get(v).add(new State(u, c));
      }

      for (int i = 1; i <= K; i++) {
        S[i] = readInt() - 1;
        D[i] = readInt() - 1;
      }

      dist = new int[N][];
      for (int i = 0; i < N; i++)
        dist[i] = computePath(i);

      // always start at the destination of the previous
      long[][] dp = new long[K + 1][2]; // 0 = did not reach next start, 1 = did reach next start
      dp[0][1] = 1 << 30;
      for (int i = 1; i <= K; i++) {
        dp[i][0] = dp[i][1] = 1 << 30;

        // going from D[i - 1] - S[i] - D[i]
        long currCost = 0;
        ret = getPath(D[i - 1]);
        currCost += ret[S[i]];
        ret = getPath(S[i]);
        currCost += ret[D[i]];

        if (currCost + dp[i - 1][0] < 1 << 30)
          dp[i][0] = Math.min(dp[i][0], currCost + dp[i - 1][0]);

        // going from D[i - 1] - D[i]
        currCost = 0;
        ret = getPath(D[i - 1]);
        currCost += ret[D[i]];
        if (currCost + dp[i - 1][1] < 1 << 30)
          dp[i][0] = Math.min(dp[i][0], currCost + dp[i - 1][1]);

        if (i < K) {
          // going from D[i - 1] - S[i] - S[i + 1] - D[i]
          currCost = 0;
          ret = getPath(D[i - 1]);
          currCost += ret[S[i]];
          ret = getPath(S[i]);
          currCost += ret[S[i + 1]];
          ret = getPath(S[i + 1]);
          currCost += ret[D[i]];

          if (currCost + dp[i - 1][0] < 1 << 30)
            dp[i][1] = Math.min(dp[i][1], currCost + dp[i - 1][0]);

          // going from D[i - 1] - S[i + 1] - D[i]
          currCost = 0;
          ret = getPath(D[i - 1]);
          currCost += ret[S[i + 1]];
          ret = getPath(S[i + 1]);
          currCost += ret[D[i]];

          if (currCost + dp[i - 1][1] < 1 << 30)
            dp[i][1] = Math.min(dp[i][1], currCost + dp[i - 1][1]);
        }
      }

      out.printf("Case #%d: %d\n", t, dp[K][0] >= 1 << 30 ? -1 : dp[K][0]);
    }

    out.close();
  }

  static int[] getPath(int u) {
    return dist[u];
  }

  static int[] computePath(int u) {
    int[] dist = new int[N];
    Arrays.fill(dist, 1 << 30);
    dist[u] = 0;
    PriorityQueue<State> pq = new PriorityQueue<State>();
    pq.offer(new State(u, 0));

    while (!pq.isEmpty()) {
      State curr = pq.poll();

      for (State next : adj.get(curr.dest)) {
        if (curr.cost + next.cost >= dist[next.dest])
          continue;
        dist[next.dest] = curr.cost + next.cost;
        pq.offer(new State(next.dest, dist[next.dest]));
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

  static class State implements Comparable<State> {
    int dest, cost;

    State(int dest, int cost) {
      this.dest = dest;
      this.cost = cost;
    }

    @Override
    public int compareTo(State o) {
      return cost - o.cost;
    }
  }
}
