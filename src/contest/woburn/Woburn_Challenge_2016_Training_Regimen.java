package contest.woburn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Woburn_Challenge_2016_Training_Regimen {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, M;
  static int[] times;
  static ArrayList<ArrayList<State>> adj = new ArrayList<ArrayList<State>>();

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    M = readInt();

    times = new int[N];

    for (int i = 0; i < N; i++) {
      times[i] = readInt();
      adj.add(new ArrayList<State>());
    }

    for (int i = 0; i < M; i++) {
      int u = readInt() - 1;
      int v = readInt() - 1;
      int c = readInt();

      adj.get(u).add(new State(v, c));
      adj.get(v).add(new State(u, c));
    }

    int[] dist = new int[N];
    Arrays.fill(dist, 1 << 30);
    dist[0] = 0;

    PriorityQueue<State> pq = new PriorityQueue<State>();
    pq.offer(new State(0, 0));

    while (!pq.isEmpty()) {
      State curr = pq.poll();

      for (State next : adj.get(curr.index)) {
        if (dist[next.index] <= Math.max(curr.cost, next.cost))
          continue;
        dist[next.index] = Math.max(curr.cost, next.cost);
        pq.offer(new State(next.index, dist[next.index]));
      }
    }

    ArrayList<State> states = new ArrayList<State>();

    for (int i = 1; i < N; i++)
      states.add(new State(i, dist[i]));
    Collections.sort(states);

    int currLevel = 1;
    int trainingCost = times[0];
    long ans = 0;

    for (int i = 0; i < states.size(); i++) {
      if (states.get(i).cost == 1 << 30)
        continue;
      ans += 1L * (states.get(i).cost - currLevel) * trainingCost;
      currLevel = states.get(i).cost;
      trainingCost = Math.min(trainingCost, times[states.get(i).index]);
      if (states.get(i).index == N - 1) {
        out.println(ans);
        out.close();
        return;
      }
    }
    out.println(-1);
    out.close();
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
    int index, cost;

    State(int index, int cost) {
      this.index = index;
      this.cost = cost;
    }

    @Override
    public int compareTo(State s) {
      if (cost == s.cost)
        return times[index] - times[s.index];
      return cost - s.cost;
    }
  }
}
