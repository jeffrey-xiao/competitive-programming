package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class IOI_2008_Islands {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int n;
  static boolean[] inCycle, used;
  static long[] sz, nextDist;
  static int[] disc, lo;
  static long currDist;

  static Edge[] e;
  static int[] last;
  static int cnt;

  static Stack<Integer> s = new Stack<Integer>();
  static ArrayList<Integer> currCycle;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();

    inCycle = new boolean[n];
    sz = new long[n];
    disc = new int[n];
    lo = new int[n];
    used = new boolean[2 * n];
    e = new Edge[2 * n];
    last = new int[n];
    nextDist = new long[n];

    for (int i = 0; i < n; i++)
      disc[i] = last[i] = -1;
    for (int i = 0; i < n; i++) {
      int j = readInt() - 1;
      int dist = readInt();
      addEdge(i, j, dist);
    }
    long ans = 0;
    for (int i = 0; i < n; i++)
      if (disc[i] == -1) {
        dfs(i);
        currDist = 0;
        long maxSubtree = 0;
        for (int j = 0; j < currCycle.size(); j++) {
          computeSizes(currCycle.get(j), -1);
          State s = getFarthest(currCycle.get(j), currCycle.get(j), -1, 0);
          s = getFarthest(s.index, currCycle.get(j), -1, 0);
          maxSubtree = Math.max(maxSubtree, s.value);
          for (int k = last[currCycle.get(j)]; k != -1; k = e[k].prev)
            if (e[k].dest == currCycle.get((j + 1) % currCycle.size()) && used[k]) {
              currDist += nextDist[currCycle.get(j)] = e[k].cost;
              used[k] = used[k ^ 1] = false;
              break;
            }
        }
        // forward dynamic programming
        long max = 0;
        long maxCurr = sz[currCycle.get(0)] + nextDist[currCycle.get(0)];
        for (int j = 1; j < currCycle.size(); j++) {
          max = Math.max(max, maxCurr + sz[currCycle.get(j)]);
          maxCurr = nextDist[currCycle.get(j)] + Math.max(maxCurr, sz[currCycle.get(j)]);
        }
        // backward dynamic programming
        maxCurr = currDist + sz[currCycle.get(0)] - nextDist[currCycle.get(0)];
        for (int j = 1; j < currCycle.size(); j++) {
          max = Math.max(max, maxCurr + sz[currCycle.get(j)]);
          maxCurr = Math.max(maxCurr, currDist + sz[currCycle.get(j)]) - nextDist[currCycle.get(j)];
        }
        ans += Math.max(max, maxSubtree);
      }
    out.println(ans);
    out.close();
  }

  static State getFarthest(int i, int root, int par, long dist) {
    State res = new State(i, dist);
    for (int j = last[i]; j != -1; j = e[j].prev) {
      if (e[j].dest == par || (inCycle[e[j].dest] && e[j].dest != root))
        continue;
      State ret = getFarthest(e[j].dest, root, i, dist + e[j].cost);
      if (ret.value > res.value)
        res = ret;
    }
    return res;
  }

  static long computeSizes(int i, int prev) {
    for (int j = last[i]; j != -1; j = e[j].prev)
      if (!inCycle[e[j].dest] && e[j].dest != prev)
        sz[i] = Math.max(sz[i], computeSizes(e[j].dest, i) + e[j].cost);
    return sz[i];
  }

  static void dfs(int i) {
    disc[i] = lo[i] = cnt++;
    s.push(i);
    for (int j = last[i]; j != -1; j = e[j].prev) {
      if (used[j])
        continue;
      used[j] = used[j ^ 1] = true;
      if (disc[e[j].dest] == -1) {
        dfs(e[j].dest);
        lo[i] = Math.min(lo[i], lo[e[j].dest]);
      } else {
        lo[i] = Math.min(lo[i], disc[e[j].dest]);
      }
    }
    if (disc[i] == lo[i]) {
      ArrayList<Integer> curr = new ArrayList<Integer>();
      while (s.peek() != i)
        curr.add(s.pop());
      curr.add(s.pop());
      if (curr.size() > 1) {
        currCycle = curr;
        for (int j : curr)
          inCycle[j] = true;
      }
    }
  }

  static void addEdge(int i, int j, int dist) {
    e[cnt] = new Edge(j, dist, last[i]);
    last[i] = cnt++;
    e[cnt] = new Edge(i, dist, last[j]);
    last[j] = cnt++;
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
    int dest, cost, prev;

    Edge(int dest, int cost, int prev) {
      this.dest = dest;
      this.cost = cost;
      this.prev = prev;
    }
  }

  static class State {
    int index;
    long value;

    State(int index, long value) {
      this.index = index;
      this.value = value;
    }
  }
}
