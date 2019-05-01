package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2018_Round_1B_A {

  static final int INF = 1 << 30;

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T, N, L;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    for (int t = 1; t <= T; t++) {
      N = readInt();
      L = readInt();
      int[] C = new int[N];
      int remaining = N;
      for (int i = 0; i < L; i++) {
        C[i] = readInt();
        remaining -= C[i];
      }

      int ans = 0;
      HashMap<Integer, Integer> cached = new HashMap<Integer, Integer>();
      PriorityQueue<State> states = new PriorityQueue<State>();
      for (int i = 0; i < N; i++) {
        if (cached.containsKey(C[i])) {
          states.add(new State(C[i], cached.get(C[i])));
          continue;
        }
        boolean hasRoundUp = false;
        for (int j = 0; j <= remaining; j++) {
          if (isRoundUp(C[i] + j)) {
            cached.put(C[i], j);
            states.add(new State(C[i], j));
            hasRoundUp = true;
            break;
          }
        }
        if (!hasRoundUp) {
          cached.put(C[i], INF / 2);
          states.add(new State(C[i], INF / 2));
        }
      }

      while (remaining > 0) {
        State s = states.poll();
        int extra = Math.min(s.extra, remaining);
        remaining -= extra;
        states.add(new State(s.count + extra, INF));
      }

      for (State s : states) {
        ans += 100 * s.count / N;
        if (isRoundUp(s.count)) {
          ans++;
        }
      }

      out.printf("Case #%d: %d%n", t, ans);
    }

    out.close();
  }

  static boolean isRoundUp(int n) {
    return 100 * n % N >= (N + 1) / 2;
  }

  static class State implements Comparable<State> {
    int count, extra;
    State(int count, int extra) {
      this.count = count;
      this.extra = extra;
    }

    @Override
    public int compareTo(State s) {
      return extra - s.extra;
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
