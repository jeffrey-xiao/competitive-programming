package contest.codejam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class GCJ_2016_Round_2_A {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T, N, R, P, S;
  static int[] type;
  static boolean[] dp;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();

  main:
    for (int t = 1; t <= T; t++) {
      N = readInt();
      R = readInt();
      P = readInt();
      S = readInt();

      PriorityQueue<State> r = new PriorityQueue<State>();
      PriorityQueue<State> p = new PriorityQueue<State>();
      PriorityQueue<State> s = new PriorityQueue<State>();

      for (int i = 0; i < R + P + S; i++) {
        if (i < R) {
          State newState = new State(1);
          newState.prev.add(1);
          r.offer(newState);
        } else if (i < R + P) {
          State newState = new State(0);
          newState.prev.add(0);
          p.offer(newState);
        } else {
          State newState = new State(2);
          newState.prev.add(2);
          s.offer(newState);
        }
      }
      while (r.size() + p.size() + s.size() != 1) {
        PriorityQueue<State> nr = new PriorityQueue<State>();
        PriorityQueue<State> np = new PriorityQueue<State>();
        PriorityQueue<State> ns = new PriorityQueue<State>();
        while (r.size() != 0 || p.size() != 0 || s.size() != 0) {
          if ((p.size() == 0 && s.size() == 0) || (r.size() == 0 && s.size() == 0) || (r.size() == 0 && p.size() == 0)) {
            out.printf("Case #%d: IMPOSSIBLE%n", t);
            continue main;
          }
          // take R and P
          if ((r.size() >= p.size() && p.size() >= s.size()) || (p.size() >= r.size() && r.size() >= s.size())) {
            State s1 = r.poll();
            State s2 = p.poll();
            State newState = new State(0);
            if (compare(s1.prev, s2.prev) <= 0) {
              newState.prev.addAll(s1.prev);
              newState.prev.addAll(s2.prev);
            } else {
              newState.prev.addAll(s2.prev);
              newState.prev.addAll(s1.prev);
            }
            np.offer(newState);
          }
          // take P and S
          else if ((p.size() >= s.size() && s.size() >= r.size()) || (s.size() >= p.size() && p.size() >= r.size())) {
            State s1 = p.poll();
            State s2 = s.poll();
            State newState = new State(2);
            if (compare(s1.prev, s2.prev) <= 0) {
              newState.prev.addAll(s1.prev);
              newState.prev.addAll(s2.prev);
            } else {
              newState.prev.addAll(s2.prev);
              newState.prev.addAll(s1.prev);
            }
            ns.offer(newState);
          }
          // take S and R
          else if ((s.size() >= r.size() && r.size() >= p.size()) || (r.size() >= s.size() && s.size() >= p.size())) {
            State s1 = s.poll();
            State s2 = r.poll();
            State newState = new State(1);
            if (compare(s1.prev, s2.prev) <= 0) {
              newState.prev.addAll(s1.prev);
              newState.prev.addAll(s2.prev);
            } else {
              newState.prev.addAll(s2.prev);
              newState.prev.addAll(s1.prev);
            }
            nr.offer(newState);
          }
        }
        r = nr;
        p = np;
        s = ns;
      }
      out.printf("Case #%d: ", t);
      State res = null;
      if (s.size() > 0)
        res = s.poll();
      else if (p.size() > 0)
        res = p.poll();
      else
        res = r.poll();

      for (int i : res.prev) {
        if (i == 0)
          out.print("P");
        else if (i == 1)
          out.print("R");
        else
          out.print("S");
      }
      out.println();
    }

    out.close();
  }

  static int compare(ArrayList<Integer> a, ArrayList<Integer> b) {
    for (int i = 0; i < a.size(); i++) {
      if (b.get(i) < b.get(i))
        return -1;
      else if (a.get(i) > b.get(i))
        return 1;
    }
    return 0;
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
    int val;
    ArrayList<Integer> prev;

    State(int val) {
      this.val = val;
      this.prev = new ArrayList<Integer>();
    }

    @Override
    public int compareTo(State s) {
      return compare(prev, s.prev);
    }
  }
}
