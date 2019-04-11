package contest.ccc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class CCC_2012_J5 {

  static final int RADIX = 8;
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;
  static int n;
  static HashSet<Integer> v;

  public static void main(String[] args) throws IOException {
    while ((n = readInt()) != 0) {
      boolean possible = false;
      v = new HashSet<Integer>();
      State start = new State();
      for (int x = 0; x < n; x++)
        start.s[x].push(readInt());
      int end = 0;
      for (int x = 1; x <= n; x++)
        end += (x) * Math.pow(RADIX, x);
      Queue<Pos> q = new LinkedList<Pos>();
      q.offer(new Pos(toIndex(start), 0));
      while (!q.isEmpty()) {
        Pos curr = q.poll();
        State c = toState(curr.curr);
        if (curr.curr == end) {
          System.out.println(curr.moves);
          possible = true;
          break;
        }
        for (int x = 0; x < n - 1; x++) {
          if (c.s[x].size() == 0 && c.s[x + 1].size() == 0)
            continue;
          if (c.s[x + 1].size() == 0) {
            c.move(x, x + 1);
            int ni = toIndex(c);
            if (!v.contains(ni)) {
              v.add(ni);
              q.offer(new Pos(ni, curr.moves + 1));
            }
            c.move(x + 1, x);
          } else if (c.s[x].size() == 0) {
            c.move(x + 1, x);
            int ni = toIndex(c);
            if (!v.contains(ni)) {
              v.add(ni);
              q.offer(new Pos(ni, curr.moves + 1));
            }
            c.move(x, x + 1);
          } else if (c.s[x].peek() < c.s[x + 1].peek()) {
            c.move(x, x + 1);
            int ni = toIndex(c);
            if (!v.contains(ni)) {
              v.add(ni);
              q.offer(new Pos(ni, curr.moves + 1));
            }
            c.move(x + 1, x);
          } else if (c.s[x].peek() > c.s[x + 1].peek()) {
            c.move(x + 1, x);
            int ni = toIndex(c);
            if (!v.contains(ni)) {
              v.add(ni);
              q.offer(new Pos(ni, curr.moves + 1));
            }
            c.move(x, x + 1);
          }
        }
      }
      if (!possible)
        System.out.println("IMPOSSIBLE");
    }
  }

  static int toIndex(State s) {
    State ns = new State();
    for (int x = 0; x < n; x++)
      ns.s[x].addAll(s.s[x]);
    int res = 0;
    for (int x = 0; x < ns.s.length; x++) {
      while (!ns.s[x].isEmpty())
        res += (x + 1) * (Math.pow(RADIX, ns.s[x].pop()));
    }
    return res;
  }

  static State toState(Integer i) {
    State res = new State();
    for (int x = n; x >= 1; x--) {
      res.s[i / (int) (Math.pow(RADIX, x)) - 1].push(x);
      i %= (int) (Math.pow(RADIX, x));
    }
    return res;
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

  static class Pos {
    int moves, curr;

    Pos(int curr, int moves) {
      this.curr = curr;
      this.moves = moves;
    }
  }

  static class State {
    @SuppressWarnings("unchecked")
    Stack<Integer>[] s = new Stack[n];

    State() {
      for (int x = 0; x < n; x++)
        s[x] = new Stack<Integer>();
    }

    private void move(int i, int j) {
      s[j].push(s[i].pop());
    }

    @Override
    public String toString() {
      String st = "";
      for (int x = 0; x < n; x++) {
        for (Integer i : s[x])
          st += i + " ";
        st += "\\ ";
      }
      return st;
    }
  }
}
