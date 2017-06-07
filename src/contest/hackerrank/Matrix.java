package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Matrix {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();
  static boolean[] isMachine;

  static int[][] dp;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    int m = readInt();
    dp = new int[n][2];
    // 1 = something, 0 = nothing
    isMachine = new boolean[n];
    for (int i = 0; i < n; i++)
      adj.add(new ArrayList<Edge>());
    for (int i = 0; i < n - 1; i++) {
      int a = readInt();
      int b = readInt();
      int c = readInt();
      adj.get(a).add(new Edge(b, c));
      adj.get(b).add(new Edge(a, c));
    }
    for (int i = 0; i < m; i++)
      isMachine[readInt()] = true;
    dfs(0, -1);
    out.println(Math.min(dp[0][0], dp[0][1]));
    out.close();
  }

  static void dfs (int curr, int prev) {
    dp[curr][0] = 0;
    dp[curr][1] = isMachine[curr] ? 1 << 29 : 0;
    for (Edge e : adj.get(curr)) {
      if (e.dest == prev)
        continue;
      dfs(e.dest, curr);
      dp[curr][0] = dp[curr][0] + Math.min(dp[e.dest][1], dp[e.dest][0] + e.cost);
      if (!isMachine[curr]) {
        dp[curr][0] = Math.min(dp[curr][0], dp[curr][1] + dp[e.dest][0]);
        dp[curr][1] += Math.min(dp[e.dest][1], dp[e.dest][0] + e.cost);
      }
    }
  }

  static class Edge {
    int dest, cost;

    Edge (int dest, int cost) {
      this.dest = dest;
      this.cost = cost;
    }
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

  static char readCharacter () throws IOException {
    return next().charAt(0);
  }

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
