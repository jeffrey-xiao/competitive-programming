package contest.codejam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;
import java.util.StringTokenizer;

public class GCJ_2016_Round_1A_C {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T, N;
  static ArrayList<ArrayList<Integer>> adj, rev;
  static int cnt, idCnt;
  static int[] disc, lo, id, sz;
  static boolean[] inStack;
  static Stack<Integer> s = new Stack<Integer>();
  static ArrayList<HashSet<Integer>> g;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();

    for (int t = 1; t <= T; t++) {
      N = readInt();

      adj = new ArrayList<ArrayList<Integer>>();
      rev = new ArrayList<ArrayList<Integer>>();
      g = new ArrayList<HashSet<Integer>>();

      for (int i = 0; i < N; i++) {
        adj.add(new ArrayList<Integer>());
        rev.add(new ArrayList<Integer>());
      }

      disc = new int[N];
      id = new int[N];
      lo = new int[N];
      inStack = new boolean[N];
      sz = new int[N];
      cnt = 0;
      idCnt = 0;

      for (int i = 0; i < N; i++) {
        int val = readInt() - 1;
        adj.get(i).add(val);
        rev.get(val).add(i);
      }

      for (int i = 0; i < N; i++) {
        if (disc[i] == 0) {
          dfs(i);
        }
      }

      for (int i = 0; i < idCnt; i++) {
        g.add(new HashSet<Integer>());
      }

      for (int i = 0; i < N; i++)
        for (int j : adj.get(i))
          if (id[i] != id[j])
            g.get(id[j]).add(id[i]);

      int maxCycle = 0;
      int ans = 0;
      for (int i = 0; i < N; i++) {
        if (sz[id[i]] == 2) {
          int curr = i;
          int max = 0;
          for (int j : rev.get(curr)) {
            if (id[j] != id[curr]) {
              max = Math.max(max, compute(id[j]));
            }
          }
          ans += max;
          max = 0;

          for (int j = 0; j < N; j++)
            if (i != j && id[i] == id[j])
              curr = j;

          for (int j : rev.get(curr)) {
            if (id[j] != id[curr]) {
              max = Math.max(max, compute(id[j]));
            }
          }
          ans += max;
          ans += 2;
        }
        maxCycle = Math.max(maxCycle, sz[i]);
      }
      out.printf("Case #%d: %d%n", t, Math.max(maxCycle, ans / 2));
    }

    out.close();
  }

  static int compute(int u) {
    int curr = 0;
    for (int v : g.get(u)) {
      curr = Math.max(curr, compute(v));
    }
    return curr + sz[u];
  }

  static void dfs(int i) {
    disc[i] = lo[i] = ++cnt;
    inStack[i] = true;
    s.push(i);
    for (int j : adj.get(i)) {
      if (disc[j] == 0) {
        dfs(j);
        lo[i] = Math.min(lo[i], lo[j]);
      } else if (inStack[j]) {
        lo[i] = Math.min(lo[i], disc[j]);
      }
    }
    if (disc[i] == lo[i]) {
      while (s.peek() != i) {
        inStack[s.peek()] = false;
        sz[idCnt]++;
        id[s.pop()] = idCnt;
      }
      sz[idCnt]++;
      inStack[s.peek()] = false;
      id[s.pop()] = idCnt++;
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
}
