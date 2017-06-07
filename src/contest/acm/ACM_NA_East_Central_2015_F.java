package contest.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class ACM_NA_East_Central_2015_F {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static ArrayList<Edge> e = new ArrayList<Edge>();
  static int[] last;
  static int n, cnt, src, sink;

  static int[] height;
  static int[] count;
  static int[] excess;
  static boolean[] active;
  static HashMap<String, Integer> toIndex = new HashMap<String, Integer>();
  static int hashCount = 0;
  static HashSet<String> suppliers = new HashSet<String>();
  static HashSet<String> factories = new HashSet<String>();
  static Queue<Integer> q = new ArrayDeque<Integer>();

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int s = readInt();
    int r = readInt();
    int f = readInt();
    int t = readInt();

    n = 2 + s + t + t;

    src = 0;
    sink = n;

    last = new int[n];

    height = new int[n];
    count = new int[2 * n];
    excess = new int[n];
    active = new boolean[n];

    for (int i = 0; i < n; i++)
      last[i] = -1;

    for (int i = 0; i < r; i++) {
      String next = next();
      suppliers.add(next);
      addEdge(0, 1 + getIndex(next), 1, 0);
    }

    for (int i = 0; i < f; i++) {
      String next = next();
      factories.add(next);
      addEdge(1 + getIndex(next), n - 1, 1, 0);
    }

    for (int i = 0; i < t; i++) {
      int cnt = readInt();
      for (int j = 0; j < cnt; j++) {
        String next = next();
        int a = 1 + getIndex(next);
        int b = s + i + 1;
        if (factories.contains(next)) {
          addEdge(b + t, a, 1, 0);
        }
        if (suppliers.contains(next))
          addEdge(a, b, 1, 0);
      }
      addEdge(s + i + 1, s + i + t + 1, 1, 0);
      for (int j = 0; j < t; j++)
        if (i != j)
          addEdge(s + i + 1 + t, s + j + 1, 1, 0);
    }

    out.println(getFlow(src, sink - 1));
    out.close();
  }

  static int getIndex (String s) {
    if (!toIndex.containsKey(s))
      toIndex.put(s, hashCount++);
    return toIndex.get(s);
  }

  static int getFlow (int s, int t) {
    count[0] = n - 1;
    count[n] = 1;

    height[s] = n;
    active[s] = active[t] = true;
    for (int i = last[s]; i != -1; i = e.get(i).next) {
      excess[s] += e.get(i).cap;
      push(i);
    }
    while (!q.isEmpty()) {
      int curr = q.poll();
      active[curr] = false;
      discharge(curr);
    }
    int totalFlow = 0;
    for (int i = last[s]; i != -1; i = e.get(i).next)
      totalFlow += e.get(i).flow;
    return totalFlow;
  }

  static void discharge (int v) {
    for (int i = last[v]; i != -1 && excess[v] > 0; i = e.get(i).next)
      push(i);
    if (excess[v] > 0) {
      if (count[height[v]] == 1)
        gap(height[v]);
      else
        relabel(v);
    }
  }

  static void relabel (int v) {
    count[height[v]]--;
    height[v] = 2 * n;
    for (int i = last[v]; i != -1; i = e.get(i).next)
      if (e.get(i).cap - e.get(i).flow > 0)
        height[v] = Math.min(height[v], height[e.get(i).dest] + 1);
    count[height[v]]++;
    add(v);
  }

  static void gap (int h) {
    for (int i = 0; i < n; i++) {
      if (height[i] < h)
        continue;
      count[height[i]]--;
      height[i] = Math.max(height[i], n + 1);
      count[height[i]]++;
      add(i);
    }
  }

  static void push (int i) {
    int flow = Math.min(excess[e.get(i).src], e.get(i).cap - e.get(i).flow);
    if (height[e.get(i).src] <= height[e.get(i).dest] || flow == 0)
      return;
    e.get(i).flow += flow;
    e.get(i ^ 1).flow -= flow;
    excess[e.get(i).src] -= flow;
    excess[e.get(i).dest] += flow;
    add(e.get(i).dest);
  }

  static void add (int i) {
    if (!active[i] && excess[i] > 0) {
      active[i] = true;
      q.offer(i);
    }
  }

  static void addEdge (int x, int y, int xy, int yx) {
    e.add(new Edge(x, y, xy, 0, last[x]));
    last[x] = cnt++;
    e.add(new Edge(y, x, yx, 0, last[y]));
    last[y] = cnt++;
  }

  static class Edge {
    int src, dest, cap, flow, next;

    Edge (int src, int dest, int cap, int flow, int next) {
      this.src = src;
      this.dest = dest;
      this.cap = cap;
      this.flow = flow;
      this.next = next;
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
