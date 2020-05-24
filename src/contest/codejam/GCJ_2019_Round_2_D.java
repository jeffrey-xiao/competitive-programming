package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2019_Round_2_D {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int INF = -1;
  static long MOD = (long)(1e9 + 7);

  static int T;
  static int M;
  static int[][] adj;
  static ArrayList<HashSet<Integer>> components;
  static int index, componentIndex;
  static int[] disc, lo, id;
  static long[] count, componentCount, componentMultiplier;
  static boolean[] inStack;
  static ArrayDeque<Integer> s;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    for (int t = 1; t <= T; t++) {
      M = readInt();

      index = 0;
      componentIndex = 0;
      adj = new int[M][2];
      components = new ArrayList<>();
      disc = new int[M];
      lo = new int[M];
      id = new int[M];
      count = new long[M];
      inStack = new boolean[M];
      s = new ArrayDeque<>();

      for (int i = 0; i < M; i++) {
        adj[i][0] = readInt() - 1;
        adj[i][1] = readInt() - 1;
      }

      for (int i = 0; i < M; i++) {
        count[i] = readInt();
      }

      for (int i = 0; i < M; i++) {
        if (disc[i] == 0) {
          computeScc(i);
        }
      }

      componentCount = new long[componentIndex];
      componentMultiplier = new long[componentIndex];

      for (int i = 0; i < M; i++) {
        componentCount[id[i]] += count[i];
      }

      Arrays.fill(disc, 0);
      for (int i = 0; i < componentIndex; i++) {
        if (disc[i] == 0) {
          computeTopological(i);
        }
      }

      componentMultiplier[id[0]] = 1;
      boolean unbounded = false;
      while (!s.isEmpty()) {
        int u = s.pollLast();
        if (componentMultiplier[u] == INF) {
          continue;
        }
        for (int n : components.get(u)) {
          int v1 = id[adj[n][0]];
          int v2 = id[adj[n][1]];
          if (v1 == v2) {
            // Both going into itself. If component contains lead, then answer
            // will be unbounded if we can get any lead.
            if (v1 == u && u == id[0]) {
              unbounded = true;
            }
            // Both going into different component and current component doesn't
            // contain lead.
            if (v1 != u && u != id[0]) {
              if (componentMultiplier[v1] == INF) {
                componentMultiplier[u] = INF;
              } else {
                componentMultiplier[u] = (componentMultiplier[v1] * 2) % MOD;
              }
            }
          } else {
            // Going to different components.
            if (v1 == u) {
              if (componentMultiplier[v2] != 0) {
                componentMultiplier[u] = INF;
              }
            } else if (v2 == u) {
              if (componentMultiplier[v1] != 0) {
                componentMultiplier[u] = INF;
              }
            } else if (u != id[0]) {
              if (componentMultiplier[v1] == INF || componentMultiplier[v2] == INF) {
                componentMultiplier[u] = INF;
              } else {
                componentMultiplier[u] = (componentMultiplier[v1] + componentMultiplier[v2]) % MOD;
              }
            }
          }
        }
      }

      long ans = 0;
      for (int i = 0; i < componentIndex; i++) {
        if (componentMultiplier[i] == INF && componentCount[i] > 0) {
          ans = INF;
        } else if (ans != INF) {
          ans = (ans + componentCount[i] * componentMultiplier[i]) % MOD;
        }
      }
      if (ans == INF || (unbounded && ans > 0)) {
        out.printf("Case #%d: UNBOUNDED%n", t);
      } else {
        out.printf("Case #%d: %d%n", t, ans);
      }
    }

    out.close();
  }

  static void computeScc(int u) {
    disc[u] = lo[u] = ++index;

    inStack[u] = true;
    s.push(u);
    for (int i = 0; i < 2; i++) {
      int v = adj[u][i];
      if (disc[v] == 0) {
        computeScc(v);
        lo[u] = Math.min(lo[u], lo[v]);
      } else if (inStack[v]) {
        lo[u] = Math.min(lo[u], disc[v]);
      }
    }

    if (disc[u] == lo[u]) {
      int curr;
      components.add(new HashSet<>());
      do {
        curr = s.pop();
        inStack[curr] = false;
        id[curr] = componentIndex;
        components.get(componentIndex).add(curr);
      } while (curr != u);
      componentIndex++;
    }
  }

  static void computeTopological(int u) {
    for (int n : components.get(u)) {
      for (int i = 0; i < 2; i++) {
        int v = id[adj[n][i]];
        if (disc[v] == 0 && v != u) {
          computeTopological(v);
        }
      }
    }
    disc[u] = 1;
    s.push(u);
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
