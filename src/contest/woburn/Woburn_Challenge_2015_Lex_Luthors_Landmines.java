package contest.woburn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Woburn_Challenge_2015_Lex_Luthors_Landmines {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, M;

  static Mine[] mine;
  static TreeMap<Integer, Mine> tm = new TreeMap<Integer, Mine>();

  // segtree
  static int[] minIndex, maxIndex;

  // adjacency list
  static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();

  // SCC
  static int[] lo, disc, id, sz;
  static int cnt, comCnt;
  static ArrayDeque<Integer> q = new ArrayDeque<Integer>();

  // condensed graph
  static ArrayList<HashSet<Integer>> g = new ArrayList<HashSet<Integer>>();
  static boolean[] vis, inStack;
  static int[] lRange, rRange;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    M = readInt();

    mine = new Mine[N + 1];

    minIndex = new int[4 * N];
    maxIndex = new int[4 * N];

    lo = new int[N + 1];
    disc = new int[N + 1];
    id = new int[N + 1];
    sz = new int[N + 1];
    inStack = new boolean[N + 1];

    adj = new ArrayList<ArrayList<Integer>>();

    for (int i = 0; i <= N; i++)
      adj.add(new ArrayList<Integer>());

    for (int i = 0; i < N; i++) {
      int pos = readInt();
      int l = readInt();
      int r = readInt();

      if (tm.get(pos) == null)
        tm.put(pos, new Mine(pos, 1 << 30, -1 << 30, 0));
      Mine old = tm.get(pos);
      tm.put(pos, new Mine(pos, Math.min(old.l, pos - l), Math.max(old.r, pos + r), old.cnt + 1));
    }

    N = tm.size();

    int cnt = 1;

    for (Map.Entry<Integer, Mine> e : tm.entrySet()) {
      Mine m = e.getValue();
      m.index = cnt;
      mine[cnt++] = m;
    }

    build(1, 1, N);

    for (int i = 1; i <= N; i++) {
      Integer lower = tm.floorKey(mine[i].r);
      Integer higher = tm.ceilingKey(mine[i].l);

      if (lower != null && higher != null) {
        int higherIndex = tm.get(lower).index;
        int lowerIndex = tm.get(higher).index;
        int lo = queryMin(1, 1, N, lowerIndex, higherIndex);
        int hi = queryMax(1, 1, N, lowerIndex, higherIndex);

        if (lo != i)
          adj.get(i).add(lo);

        if (hi != i)
          adj.get(i).add(hi);
      }
    }

    for (int i = 1; i <= N; i++)
      if (disc[i] == 0)
        dfs(i);

    vis = new boolean[comCnt];
    lRange = new int[comCnt];
    rRange = new int[comCnt];

    for (int i = 0; i < comCnt; i++) {
      g.add(new HashSet<Integer>());
      lRange[i] = 1 << 30;
      rRange[i] = -1 << 30;
    }

    for (int i = 1; i <= N; i++) {
      lRange[id[i]] = Math.min(lRange[id[i]], mine[i].l);
      rRange[id[i]] = Math.max(rRange[id[i]], mine[i].r);
    }

    for (int i = 1; i <= N; i++)
      for (int j : adj.get(i))
        if (id[i] != id[j])
          g.get(id[i]).add(id[j]);

    for (int i = 0; i < comCnt; i++)
      if (!vis[i])
        order(i);

    for (int u : q) {
      for (int v : g.get(u)) {
        lRange[u] = Math.min(lRange[u], lRange[v]);
        rRange[u] = Math.max(rRange[u], rRange[v]);
      }
    }

    int[] ans = new int[M];

    PriorityQueue<State> pq = new PriorityQueue<State>();

    for (int i = 0; i < comCnt; i++) {
      // -2 = add seg
      pq.offer(new State(lRange[i], -2, sz[i]));
      // -1 = remove seg
      pq.offer(new State(rRange[i] + 1, -1, sz[i]));
    }

    for (int i = 0; i < M; i++) {
      pq.offer(new State(readInt(), i));
    }

    int curr = 0;
    while (!pq.isEmpty()) {
      State currState = pq.poll();
      if (currState.index == -2)
        curr += currState.cnt;
      else if (currState.index == -1)
        curr -= currState.cnt;
      else
        ans[currState.index] = curr;
    }

    for (int i = 0; i < M; i++)
      out.println(ans[i]);

    out.close();
  }

  static void order(int u) {
    vis[u] = true;
    for (int v : g.get(u))
      if (!vis[v])
        order(v);

    q.addLast(u);
  }

  static void dfs(int u) {
    disc[u] = lo[u] = ++cnt;
    q.addLast(u);
    inStack[u] = true;

    for (int v : adj.get(u)) {
      if (disc[v] == 0) {
        dfs(v);
        lo[u] = Math.min(lo[u], lo[v]);
      } else if (inStack[v]) {
        lo[u] = Math.min(lo[u], disc[v]);
      }
    }

    if (disc[u] == lo[u]) {
      while (q.getLast() != u) {
        inStack[q.getLast()] = false;
        sz[comCnt] += mine[q.getLast()].cnt;
        id[q.removeLast()] = comCnt;
      }
      inStack[q.getLast()] = false;
      sz[comCnt] += mine[q.getLast()].cnt;
      id[q.removeLast()] = comCnt;

      comCnt++;
    }
  }

  static int queryMin(int n, int l, int r, int ql, int qr) {
    if (l == ql && r == qr) {
      return minIndex[n];
    }

    int mid = (l + r) >> 1;

    if (qr <= mid) {
      return queryMin(n << 1, l, mid, ql, qr);
    } else if (ql > mid) {
      return queryMin(n << 1 | 1, mid + 1, r, ql, qr);
    } else {
      int index1 = queryMin(n << 1 | 1, mid + 1, r, mid + 1, qr);
      int index2 = queryMin(n << 1, l, mid, ql, mid);
      if (mine[index1].l <= mine[index2].l)
        return index1;
      else
        return index2;
    }
  }

  static int queryMax(int n, int l, int r, int ql, int qr) {
    if (l == ql && r == qr) {
      return maxIndex[n];
    }

    int mid = (l + r) >> 1;

    if (qr <= mid) {
      return queryMax(n << 1, l, mid, ql, qr);
    } else if (ql > mid) {
      return queryMax(n << 1 | 1, mid + 1, r, ql, qr);
    } else {
      int index1 = queryMax(n << 1 | 1, mid + 1, r, mid + 1, qr);
      int index2 = queryMax(n << 1, l, mid, ql, mid);
      if (mine[index1].r >= mine[index2].r)
        return index1;
      else
        return index2;
    }
  }

  static void build(int n, int l, int r) {
    if (l == r) {
      minIndex[n] = l;
      maxIndex[n] = l;
      return;
    }

    int mid = (l + r) >> 1;

    build(n << 1, l, mid);
    build(n << 1 | 1, mid + 1, r);

    if (mine[minIndex[n << 1]].l < mine[minIndex[n << 1 | 1]].l) {
      minIndex[n] = minIndex[n << 1];
    } else {
      minIndex[n] = minIndex[n << 1 | 1];
    }

    if (mine[maxIndex[n << 1]].r > mine[maxIndex[n << 1 | 1]].r) {
      maxIndex[n] = maxIndex[n << 1];
    } else {
      maxIndex[n] = maxIndex[n << 1 | 1];
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

  static class State implements Comparable<State> {
    int x, index, cnt;

    State(int x, int index, int cnt) {
      this.x = x;
      this.index = index;
      this.cnt = cnt;
    }

    State(int x, int index) {
      this(x, index, 0);
    }

    @Override
    public int compareTo(State o) {
      if (x == o.x)
        return index - o.index;
      return x - o.x;
    }
  }

  static class Mine implements Comparable<Mine> {
    int pos, l, r, index, cnt;

    Mine(int pos, int l, int r, int cnt) {
      this.pos = pos;
      this.l = l;
      this.r = r;
      this.cnt = cnt;
    }

    @Override
    public int compareTo(Mine o) {
      if (pos == o.pos)
        return index - o.index;
      return pos - o.pos;
    }
  }
}
