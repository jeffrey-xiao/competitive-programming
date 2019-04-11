package contest.ccc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CCC_2005_Stage_2_The_Great_Spamway_Strike {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;
  static boolean[] v;
  static int[] lag;
  static int[][] adj;
  static int n;

  public static void main(String[] args) throws IOException {
    n = readInt();
    lag = new int[n + 1];
    adj = new int[n + 1][];
    for (int x = 0; x <= n; x++) {
      lag[x] = readInt();
      int size = readInt();
      adj[x] = new int[size];
      for (int y = 0; y < size; y++)
        adj[x][y] = readInt();
    }
    v = new boolean[n + 1];
    PriorityQueue<State> pq = new PriorityQueue<State>();
    int max = 0;
    pq.offer(new State(0, 0, 0));
    v[0] = true;
    while (!pq.isEmpty()) {
      State curr = pq.poll();
      boolean hasSub = false;
      for (Integer next : adj[curr.index]) {
        if (v[next])
          continue;
        hasSub = true;
        pq.offer(new State(next, curr.depth + 1, curr.lagTime + lag[curr.index]));
        v[next] = true;
      }
      if (!hasSub) {
        int totalTime = curr.depth * 10 * 2 + lag[curr.index] + curr.lagTime * 2;
        max = Math.max(max, totalTime);
      }
    }
    System.out.println(max);
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

  static class State implements Comparable<State> {
    int index, depth, lagTime;

    State(int index, int depth, int lagTime) {
      this.index = index;
      this.depth = depth;
      this.lagTime = lagTime;
    }

    @Override
    public int compareTo(State o) {
      return (depth * 10 * 2 + lagTime * 2 + lag[index]) - (o.depth * 10 * 2 + o.lagTime * 2 + lag[o.index]);
    }

  }
}
