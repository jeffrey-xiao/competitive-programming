package contest.usaco_other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/*
ID: jeffrey40
LANG: JAVA
TASK: grass
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class grass {
  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  static ArrayList<HashSet<Integer>> adj = new ArrayList<HashSet<Integer>>();
  static ArrayList<HashSet<Integer>> adjG = new ArrayList<HashSet<Integer>>();
  static ArrayList<HashSet<Integer>> revG = new ArrayList<HashSet<Integer>>();
  static int[] disc, low, id, size, dp;
  static boolean[] reachable;
  static int n, index, max;
  static Stack<Integer> curr;
  static int count = 0;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new FileReader("grass.in"));
    // br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new BufferedWriter(new FileWriter("grass.out")));

    int n = readInt();
    int m = readInt();
    disc = new int[n];
    low = new int[n];
    size = new int[n];
    id = new int[n];

    curr = new Stack<Integer>();
    for (int x = 0; x < n; x++) {
      adj.add(new HashSet<Integer>());
    }
    for (int x = 0; x < m; x++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      adj.get(a).add(b);
    }
    for (int x = 0; x < n; x++) {
      disc[x] = -1;
      low[x] = -1;
    }
    for (int x = 0; x < n; x++)
      if (disc[x] == -1)
        dfs(x);

    reachable = new boolean[count];
    dp = new int[count];

    for (int x = 0; x < count; x++) {
      adjG.add(new HashSet<Integer>());
      revG.add(new HashSet<Integer>());
    }
    int[] ingoing = new int[count];
    for (int x = 0; x < n; x++)
      for (Integer y : adj.get(x)) {
        if (!adjG.get(id[x]).contains(id[y]) && id[x] != id[y]) {
          adjG.get(id[x]).add(id[y]);
          revG.get(id[y]).add(id[x]);
          ingoing[id[y]]++;
        }
      }
    Queue<Integer> process = new LinkedList<Integer>();
    ArrayList<Integer> order = new ArrayList<Integer>();
    for (int x = 0; x < count; x++)
      if (ingoing[x] == 0) {
        process.offer(x);
        order.add(x);
      }
    while (!process.isEmpty()) {
      int next = process.poll();
      for (Integer i : adjG.get(next)) {
        ingoing[i]--;
        if (ingoing[i] == 0) {
          process.offer(i);
          order.add(i);
        }
      }
    }

    getReachable(id[0], revG);
    precompute(id[0], 0);
    solve(id[0], 0);
    pr.println(max);
    pr.close();
    System.exit(0);
  }

  private static void solve(int i, int s) {
    dp[i] = s + size[i];
    for (int x : revG.get(i)) {
      if (reachable[x]) {
        max = Math.max(max, dp[i] + dp[x] - dp[id[0]]);
      }
    }
    for (int x : adjG.get(i)) {
      if (dp[x] < dp[i] + size[x]) {
        solve(x, dp[i]);
      }
    }
  }

  private static void precompute(int i, int s) {
    dp[i] = s + size[i];
    for (Integer x : revG.get(i)) {
      if (dp[x] < dp[i] + size[x])
        precompute(x, dp[i]);
    }
  }

  private static void getReachable(int i, ArrayList<HashSet<Integer>> g) {
    reachable[i] = true;
    for (Integer x : g.get(i)) {
      if (!reachable[x]) {
        getReachable(x, g);
      }
    }
  }

  private static void dfs(int x) {
    // labeling current vertex
    disc[x] = low[x] = index++;
    curr.push(x);
    // setting the low value and traversing graph
    for (Integer next : adj.get(x)) {
      if (disc[next] == -1) {
        dfs(next);
        low[x] = Math.min(low[x], low[next]);
      } else if (curr.contains(next))
        low[x] = Math.min(low[x], disc[next]);
    }
    // outputting SCCs if it is a root vertex
    if (disc[x] == low[x]) {

      while (curr.peek() != x) {
        int a = curr.pop();
        id[a] = count;
        size[count]++;
      }
      int a = curr.pop();
      id[a] = count;
      size[count]++;
      count++;
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

  static String readLine() throws IOException {
    return br.readLine().trim();
  }

  static class Edge {
    int a, b;

    Edge(int a, int b) {
      this.a = a;
      this.b = b;
    }
  }
}