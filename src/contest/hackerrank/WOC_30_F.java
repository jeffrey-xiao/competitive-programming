package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class WOC_30_F {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N;
  static int[][] adj;
  static boolean[] dfsVis;
  static long curr;
  static ArrayList<HashSet<Integer>> triangles;

  static double best = -1;
  static long ans = 1;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();

    adj = new int[N][N];

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        adj[i][j] = readInt();
      }
    }

    if (N < 3) {
      out.println(N);
      for (int i = 1; i <= N; i++)
        out.print(i + " ");
      out.println();
      out.close();
      return;
    } else if (N < 15) {
      bruteforce();
    } else {

      HashSet<Long> vis = new HashSet<Long>();
      triangles = new ArrayList<HashSet<Integer>>();
      for (int i = 0; i < N; i++)
        triangles.add(new HashSet<Integer>());
      Queue<Long> q = new ArrayDeque<Long>();

      for (int i = 0; i < N; i++)
        for (int j = i + 1; j < N; j++)
          for (int k = j + 1; k < N; k++)
            if (adj[i][j] == 1 && adj[j][k] == 1 && adj[k][i] == 1) {
              long node = (1L << i) | (1L << j) | (1L << k);
              triangles.get(i).add(k * 100 + j);
              triangles.get(j).add(k * 100 + i);
              triangles.get(k).add(j * 100 + i);
              q.add(node);
              vis.add(node);
            }

      while (!q.isEmpty()) {
        curr = q.poll();

        // greedily add nodes that connect to current subgraph
        dfsVis = new boolean[N];
        for (int i = 0; i < N; i++)
          if ((curr & 1L << i) == 0 && !dfsVis[i])
            dfs(i);

        double currBest = 1.0 * countTriangles(curr) / bitCount(curr);
        if (currBest > best) {
          best = currBest;
          ans = curr;
        }

        // bfs by adding two nodes
        for (int i = 0; i < N; i++) {
          if ((curr & 1L << i) > 0) {
            for (int j = 0; j < N; j++) {
              if ((curr & 1L << j) == 0) {
                for (int k = j + 1; k < N; k++) {
                  if ((curr & 1L << k) == 0) {
                    if (triangles.get(i).contains(k * 100 + j)) {
                      long next = curr | (1L << k) | (1L << j);
                      if (!vis.contains(next)) {
                        q.offer(next);
                        vis.add(next);
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }

    out.println(bitCount(ans));
    for (int i = 0; i < N; i++)
      if ((ans & 1L << i) > 0)
        out.print(i + 1L + " ");
    out.println();

    out.close();
  }

  static double bruteforce() {
    double best = -1;
    for (long i = 1; i < 1L << N; i++) {
      double currBest = 1.0 * countTriangles(i) / bitCount(i);
      if (currBest > best) {
        best = currBest;
        ans = i;
      }
    }
    return best;
  }

  static boolean dfs(int i) {
    if (dfsVis[i])
      return (curr & 1L << i) > 0;

    dfsVis[i] = true;

    if ((curr & 1L << i) > 0)
      return true;

    for (int triangle : triangles.get(i)) {
      int n = triangle % 100;
      int m = triangle / 100;
      if (dfs(n) && dfs(m)) {
        curr |= 1L << i;
        return true;
      }
    }

    return false;
  }

  static int bitCount(long state) {
    int ret = 0;
    while (state > 0) {
      ret += (state & 1);
      state >>= 1;
    }
    return ret;
  }

  static int countTriangles(long state) {
    int ret = 0;
    for (int i = 0; i < N; i++)
      for (int j = i + 1; j < N; j++)
        for (int k = j + 1; k < N; k++)
          if ((state & (1L << i)) > 0 && (state & (1L << j)) > 0 && (state & (1L << k)) > 0 && adj[i][j] == 1 && adj[i][k] == 1 && adj[j][k] == 1) {
            ret++;
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
}
