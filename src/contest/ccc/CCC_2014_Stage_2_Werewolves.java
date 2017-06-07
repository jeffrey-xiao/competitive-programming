package contest.ccc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class CCC_2014_Stage_2_Werewolves {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  static final int SIZE = 250;
  static final int INF = 1 << 30;
  static final int MOD = 1000000007;

  public static void main (String[] args) throws IOException {
    ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();
    int[] ingoing = new int[SIZE];
    long[][][] dp = new long[SIZE][SIZE][2];
    int n = readInt();
    int w = readInt();
    int m = readInt();
    for (int x = 0; x < SIZE; x++) {
      adj.add(new ArrayList<Edge>());
    }
    for (int x = 0; x < m; x++) {
      char c = next().trim().charAt(0);
      int a = readInt();
      int b = readInt();
      adj.get(a).add(new Edge(c == 'A', b));
      ingoing[b]++;
    }
    Stack<Integer> sorted = new Stack<Integer>();
    Queue<Integer> curr = new LinkedList<Integer>();
    sorted.push(0);

    for (int x = 1; x <= n; x++) {
      if (ingoing[x] == 0) {
        adj.get(0).add(new Edge(false, x));
        curr.offer(x);
        sorted.push(x);
      }
    }
    while (!curr.isEmpty()) {
      int c = curr.poll();
      for (int x = 0; x < adj.get(c).size(); x++) {
        Edge next = adj.get(c).get(x);
        ingoing[next.dest]--;
        if (ingoing[next.dest] == 0) {
          curr.offer(next.dest);
          sorted.push(next.dest);
        }
      }
    }
    while (!sorted.isEmpty()) {
      int c = sorted.pop();
      dp[c][0][0] = dp[c][1][1] = 1;
      for (Edge e : adj.get(c)) {
        for (int y = w; y >= 0; y--) {
          long a = 0;
          long b = 0;
          for (int z = 0; z <= y; z++) {
            a = (a + dp[c][y - z][0] * (dp[e.dest][z][0] + dp[e.dest][z][1])) % MOD;
            b = (b + dp[c][y - z][1] * (dp[e.dest][z][e.accuse ? 0 : 1])) % MOD;
          }
          dp[c][y][0] = a;
          dp[c][y][1] = b;
        }
      }
    }
    System.out.println(dp[0][w][0]);
  }

  static class Edge {
    boolean accuse;
    int dest;

    Edge (boolean accuse, int dest) {
      this.accuse = accuse;
      this.dest = dest;
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
