package contest.hackerearth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Clash_May_2016_P4 implements Runnable {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, X;
  static ArrayList<ArrayList<Integer>> adj;
  static int[] val;
  static long[] best;
  static boolean[] used;

  static ArrayList<Integer> pathSums = new ArrayList<Integer>();
  static TreeMap<Integer, Integer> occ = new TreeMap<Integer, Integer>();

  static long ans;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    X = readInt();

    adj = new ArrayList<ArrayList<Integer>>(N);
    val = new int[N];
    used = new boolean[N];
    best = new long[N];

    for (int i = 0; i < N; i++)
      adj.add(new ArrayList<Integer>());

    for (int i = 0; i < N - 1; i++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      adj.get(a).add(b);
      adj.get(b).add(a);
    }

    for (int i = 0; i < N; i++) {
      val[i] = readInt();
      if (val[i] >= X)
        val[i] = 1;
      else
        val[i] = -1;
    }

    Thread main = new Thread(null, new Clash_May_2016_P4(), "Main", 1 << 26);
    main.start();
  }

  public static long getPathSums (int u, int prev, int sum) {
    sum += val[u];
    pathSums.add(sum);

    long ret = 0;

    if (occ.get(-sum - 1) != null)
      ret += occ.get(-sum - 1);

    if (occ.get(-sum - 2) != null)
      ret += occ.get(-sum - 2);

    for (int v : adj.get(u)) {
      if (used[v] || v == prev)
        continue;
      ret += getPathSums(v, u, sum);
    }

    best[u] += ret;
    return ret;
  }

  public static int getSize (int u, int par) {
    int sz = 1;
    for (int v : adj.get(u)) {
      if (v == par || used[v])
        continue;
      sz += getSize(v, u);
    }
    return sz;
  }

  public static int getCentroid (int u, int par, int treeSize) {
    int sz = 1;
    boolean valid = true;
    for (int v : adj.get(u)) {
      if (v == par || used[v])
        continue;
      int ret = getCentroid(v, u, treeSize);
      if (ret >= 0)
        return ret;
      valid &= -ret <= treeSize / 2;
      sz += -ret;
    }
    valid &= treeSize - sz <= treeSize / 2;
    return valid ? u : -sz;
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

  @Override
  public void run () {
    Queue<Integer> q = new ArrayDeque<Integer>();
    int initial = getCentroid(0, -1, getSize(0, -1));
    q.offer(initial);
    used[initial] = true;

    while (!q.isEmpty()) {
      int curr = q.poll();

      for (int i = 0; i < 2; i++) {
        occ.clear();

        if (i == 1)
          occ.put(val[curr], 1);

        for (int v : adj.get(curr)) {
          if (!used[v]) {
            pathSums.clear();
            best[curr] += i * getPathSums(v, -1, 0);

            for (int u : pathSums) {
              if (occ.get(u + val[curr]) == null)
                occ.put(u + val[curr], 0);
              occ.put(u + val[curr], occ.get(u + val[curr]) + 1);
            }
          }
        }

        Collections.reverse(adj.get(curr));
      }

      for (int v : adj.get(curr)) {
        if (!used[v]) {
          int next = getCentroid(v, -1, getSize(v, -1));
          q.offer(next);
          used[next] = true;
        }
      }
    }

    for (int i = 0; i < N; i++) {
      if (val[i] == -1)
        ans = Math.max(ans, best[i]);
    }
    out.println(ans);
    out.close();
  }
}