package contest.acm;

import java.util.*;
import java.io.*;

public class ACM_Waterloo_Local_2017_C {
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    int N = readInt();
    int K = readInt();

    int[] val = new int[N];

    for (int i = 0; i < N; i++)
      val[i] = readInt();

    Arrays.sort(val);

    PriorityQueue<State> pq = new PriorityQueue<State>();
    int margin = 0;
    for (int i = 0; i < N; i++) {
      // considering from [i + K - 1, i]
      if (i + K - 1 < N)
        pq.offer(new State(i, val[i + K - 1] - val[i]));

      // considering ranges that start [i - K + 1, i]
      while (pq.peek().index < i - K + 1)
        pq.poll();

      margin = Math.max(margin, pq.peek().pos);
    }
    out.println(margin);
    out.close();
  }

  static class State implements Comparable<State> {
    int index, pos;
    State (int index, int pos) {
      this.index = index;
      this.pos = pos;
    }

    @Override
    public int compareTo (State s) {
      return pos - s.pos;
    }

    @Override
    public boolean equals (Object o) {
      if (o instanceof State) {
        State s = (State)o;
        return index == s.index && pos == s.pos;
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