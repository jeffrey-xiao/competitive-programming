package contest.ecoo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class ECOO_2016_R3_P4 {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static final int TEST_CASES = 10;

  static char[] use;
  static int target;
  static HashMap<Integer, String> vis = new HashMap<Integer, String>();
  static HashMap<Integer, String> base = new HashMap<Integer, String>();

  static final int MAX = 10000;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt")); // DATA41.txt
    // out = new PrintWriter(new FileWriter("OUTPUT41.txt"));

    for (int t = 1; t <= TEST_CASES; t++) {
      use = readLine().toCharArray();
      target = readInt();
      vis.clear();
      base.clear();

      String binaryOp = "";
      boolean canNegate = false;

      Queue<State> q = new ArrayDeque<State>();
      for (int i = 0; i < use.length; i++) {
        if ('0' <= use[i] && use[i] <= '9') {
          q.offer(new State(use[i] - '0', "" + use[i]));
          base.put(use[i] - '0', "" + use[i]);
          vis.put(use[i] - '0', "" + use[i]);
        } else if (use[i] == '+' || use[i] == '-' || use[i] == '*') {
          binaryOp += use[i];
        } else if (use[i] == 'n') {
          canNegate = true;
        }
      }

      while (!q.isEmpty()) {
        State curr = q.poll();
        for (int i = 0; i < use.length; i++) {
          if ('0' <= use[i] && use[i] <= '9') {
            State newState = new State(curr.num * 10 + use[i] - '0', curr.op + " " + use[i]);
            if (Math.abs(newState.num) <= MAX && !base.containsKey(newState.num)) {
              q.offer(newState);
              base.put(newState.num, newState.op);
              vis.put(newState.num, newState.op);
            }
          }
        }
      }

      for (Map.Entry<Integer, String> s : base.entrySet())
        q.offer(new State(s.getKey(), s.getValue()));

      while (!q.isEmpty()) {
        State curr = q.poll();
        if (vis.containsKey(target))
          break;
        for (int i = 0; i < use.length; i++) {
          if (use[i] == 's' && binaryOp.length() == 0) {
            State newState = new State(curr.num * curr.num, curr.op + " s");
            if (Math.abs(newState.num) <= MAX && !vis.containsKey(newState.num)) {
              q.offer(newState);
              vis.put(newState.num, newState.op);
            }
          } else if (use[i] == '+') {
            for (Map.Entry<Integer, String> s : base.entrySet()) {
              State newState = new State(curr.num + s.getKey(), curr.op + " + " + s.getValue());
              if (Math.abs(newState.num) <= MAX && !vis.containsKey(newState.num)) {
                q.offer(newState);
                vis.put(newState.num, newState.op);
              }
              if (canNegate) {
                newState = new State(curr.num - s.getKey(), curr.op + " + " + s.getValue() + " n");
                if (Math.abs(newState.num) <= MAX && !vis.containsKey(newState.num)) {
                  q.offer(newState);
                  vis.put(newState.num, newState.op);
                }
              }
            }
          } else if (use[i] == '-') {
            for (Map.Entry<Integer, String> s : base.entrySet()) {
              State newState = new State(curr.num - s.getKey(), curr.op + " - " + s.getValue());
              if (Math.abs(newState.num) <= MAX && !vis.containsKey(newState.num)) {
                q.offer(newState);
                vis.put(newState.num, newState.op);
              }

              if (canNegate) {
                newState = new State(curr.num + s.getKey(), curr.op + " - " + s.getValue() + " n");
                if (Math.abs(newState.num) <= MAX && !vis.containsKey(newState.num)) {
                  q.offer(newState);
                  vis.put(newState.num, newState.op);
                }
              }
            }
          } else if (use[i] == '*') {
            for (Map.Entry<Integer, String> s : base.entrySet()) {
              State newState = new State(curr.num * s.getKey(), curr.op + " * " + s.getValue());
              if (Math.abs(newState.num) <= MAX && !vis.containsKey(newState.num)) {
                q.offer(newState);
                vis.put(newState.num, newState.op);
              }

              if (canNegate) {
                newState = new State(curr.num * -s.getKey(), curr.op + " * " + s.getValue() + " n");
                if (Math.abs(newState.num) <= MAX && !vis.containsKey(newState.num)) {
                  q.offer(newState);
                  vis.put(newState.num, newState.op);
                }
              }
            }
          }
        }
      }

      out.print(vis.get(target));
      if (binaryOp.length() > 0)
        out.print(" " + binaryOp.charAt(0));
      out.println();
      out.flush();
    }

    out.close();
  }

  static class State {
    int num;
    String op;

    State (int num, String op) {
      this.num = num;
      this.op = op;
    }

    @Override
    public int hashCode () {
      return new Integer(num).hashCode();
    }

    @Override
    public boolean equals (Object o) {
      if (o instanceof State) {
        State s = (State)o;
        return num == s.num;
      }
      return false;
    }

    @Override
    public String toString () {
      return String.format("(%d %s)", num, op);
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
