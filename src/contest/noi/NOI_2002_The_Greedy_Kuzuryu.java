package contest.noi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class NOI_2002_The_Greedy_Kuzuryu {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  static int n, m, k;
  static int[][][] dp;
  static boolean[] v;
  static int[] size;
  static ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // pr = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    m = readInt();
    k = readInt();

    dp = new int[n][k + 1][2];
    v = new boolean[n];
    size = new int[n];
    for (int i = 0; i < n; i++) {
      adj.add(new ArrayList<Edge>());
      for (int j = 0; j <= k; j++)
        dp[i][j][0] = dp[i][j][1] = 1 << 29;

    }
    for (int i = 0; i < n - 1; i++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      int c = readInt();
      adj.get(a).add(new Edge(b, c));
    }
    if (k > n - m) {
      System.out.println(-1);
      return;
    }
    solve(0);
    System.out.println(dp[0][k][0]);
    pr.close();
  }

  // node, fruit left, id of head
  static void solve(int curr) {
    v[curr] = true;
    dp[curr][0][1] = 0;
    dp[curr][1][0] = 0;
    size[curr] = 1;
    // leaf base case
    if (adj.get(curr).size() == 0)
      return;

    for (Edge next : adj.get(curr)) {
      if (!v[next.dest])
        solve(next.dest);
      size[curr] += size[next.dest];
      int[][] temp = new int[k + 1][2];
      for (int i = 0; i <= k; i++) {
        temp[i][0] = dp[curr][i][0];
        temp[i][1] = dp[curr][i][1];
      }
      for (int i = 0; i <= k; i++)
        dp[curr][i][0] = dp[curr][i][1] = 1 << 29;
      for (int i = k; i >= 0; i--) {
        // when the main head is eating
        for (int j = i - 1; j >= 0; j--) {
          dp[curr][i][0] = Math.min(dp[curr][i][0], temp[i - j][0] + Math.min(dp[next.dest][j][1], dp[next.dest][j][0] + next.cost));
        }
        // when other head is eating
        for (int j = i; j >= 0; j--) {
          dp[curr][i][1] = Math.min(dp[curr][i][1], temp[i - j][1] + Math.min(dp[next.dest][j][1] + (m == 2 ? next.cost : 0), dp[next.dest][j][0]));
        }
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

  static class Edge {
    int dest, cost;

    Edge(int dest, int cost) {
      this.dest = dest;
      this.cost = cost;
    }
  }
}
