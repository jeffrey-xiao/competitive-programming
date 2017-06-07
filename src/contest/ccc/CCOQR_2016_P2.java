package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.StringTokenizer;

public class CCOQR_2016_P2 {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N;
  static ArrayList<HashMap<Integer, Integer>> adj = new ArrayList<HashMap<Integer, Integer>>();
  static HashSet<State> visited = new HashSet<State>();

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    for (int i = 0; i < N; i++)
      adj.add(new HashMap<Integer, Integer>());

    for (int i = 0; i < N; i++) {
      int m = readInt();
      int[] a = new int[m];
      for (int j = 0; j < m; j++)
        a[j] = readInt() - 1;
      for (int j = 0; j < m; j++) {
        adj.get(i).put(a[j], a[(j - 1 + m) % m]);
      }
    }

    int[] max = new int[N];
    int[] last = new int[N];
    int[] first = new int[N];

    for (int i = 0; i < N; i++)
      first[i] = 1 << 30;

    for (int i = 0; i < N; i++) {
      for (Map.Entry<Integer, Integer> edge : adj.get(i).entrySet()) {
        int prev = i;
        int curr = edge.getKey();
        int cnt = 1;

        if (visited.contains(new State(prev, curr)))
          continue;

        visited.add(new State(prev, curr));
        HashSet<Integer> inloop = new HashSet<Integer>();
        inloop.add(prev);
        inloop.add(curr);

        first[prev] = 0;
        first[curr] = 1;
        last[prev] = 0;
        last[curr] = 1;

        while (true) {
          cnt++;

          int prevprev = prev;
          prev = curr;
          curr = adj.get(curr).get(prevprev);

          if (visited.contains(new State(prev, curr))) {
            cnt--;
            break;
          }

          visited.add(new State(prev, curr));

          first[curr] = Math.min(first[curr], cnt);
          max[curr] = Math.max(max[curr], cnt - last[curr]);
          last[curr] = cnt;

          inloop.add(curr);
        }

        for (int v : inloop) {
          max[v] = Math.max(max[v], cnt - last[v] + first[v]);
          first[v] = 1 << 30;
          last[v] = 0;
        }
      }
    }

    int q = readInt();
    for (int i = 0; i < q; i++)
      out.println(max[readInt() - 1]);

    out.close();

  }

  static class State {
    int from, to;

    State (int from, int to) {
      this.from = from;
      this.to = to;
    }

    public int hashCode () {
      return new Integer(from).hashCode() * 31 + new Integer(to).hashCode();
    }

    public boolean equals (Object o) {
      if (o instanceof State) {
        State s = (State)(o);
        return from == s.from && to == s.to;
      }
      return false;
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