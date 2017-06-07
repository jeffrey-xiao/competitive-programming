package contest.hackercup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;
import java.util.StringTokenizer;

public class FHC_2015_Round_3_Gentrification {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static ArrayList<ArrayList<Integer>> adj;
  static ArrayList<HashSet<Integer>> dag;
  static int n, m, t;
  static int[] disc, lo, id;
  static boolean[] inStack;
  static Stack<Integer> s;
  static int cnt, idCnt;

  static int[] prev;
  static boolean[] v;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    t = readInt();
    adj = new ArrayList<ArrayList<Integer>>();
    dag = new ArrayList<HashSet<Integer>>();
    s = new Stack<Integer>();
    for (int tt = 1; tt <= t; tt++) {
      n = readInt();
      m = readInt();

      adj.clear();
      dag.clear();

      cnt = 0;
      idCnt = 0;

      disc = new int[n];
      lo = new int[n];
      id = new int[n];
      inStack = new boolean[n];

      for (int i = 0; i < n; i++) {
        adj.add(new ArrayList<Integer>());
        disc[i] = lo[i] = -1;
      }

      for (int i = 0; i < m; i++)
        adj.get(readInt()).add(readInt());

      for (int i = 0; i < n; i++)
        if (disc[i] == -1)
          dfs(i);

      prev = new int[2 * idCnt + n];
      v = new boolean[2 * idCnt + n];
      for (int i = 0; i < 2 * idCnt + n; i++) {
        prev[i] = -1;
        dag.add(new HashSet<Integer>());
      }

      for (int i = 0; i < n; i++) {
        dag.get(id[i] * 2).add(idCnt * 2 + i);
        dag.get(idCnt * 2 + i).add(id[i] * 2 + 1);
      }

      for (int i = 0; i < adj.size(); i++)
        for (int j : adj.get(i))
          if (id[i] != id[j])
            dag.get(id[i] * 2 + 1).add(id[j] * 2);

      for (int i = 0; i < 2 * idCnt + n; i++)
        if (!v[i])
          compute(i);

      int ans = 0;
      for (int i = 0; i < 2 * idCnt + n; i++) {
        v = new boolean[2 * idCnt + n];
        ans += match(i) ? 1 : 0;
      }
      out.printf("Case #%d: %d\n", tt, 2 * idCnt + n - ans);
    }
    out.close();
  }

  static boolean match (int i) {
    for (int j : dag.get(i)) {
      if (!v[j]) {
        v[j] = true;
        if (prev[j] == -1 || match(prev[j])) {
          prev[j] = i;
          return true;
        }
      }
    }
    return false;
  }

  static void compute (int i) {
    v[i] = true;
    HashSet<Integer> toAdd = new HashSet<Integer>();
    for (int j : dag.get(i)) {
      if (!v[j]) {
        compute(j);
      }

      toAdd.addAll(dag.get(j));
    }
    dag.get(i).addAll(toAdd);
  }

  static void dfs (int i) {
    disc[i] = lo[i] = cnt++;
    inStack[i] = true;
    s.push(i);

    for (int j : adj.get(i)) {
      if (disc[j] == -1) {
        dfs(j);
        lo[i] = Math.min(lo[j], lo[i]);
      } else if (inStack[j]) {
        lo[i] = Math.min(lo[i], disc[j]);
      }
    }
    if (disc[i] == lo[i]) {
      while (s.peek() != i) {
        inStack[s.peek()] = false;
        id[s.pop()] = idCnt;
      }
      inStack[s.peek()] = false;
      id[s.pop()] = idCnt++;
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
