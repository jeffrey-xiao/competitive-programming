package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class New_Year_Leftover_Eggnog {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
  static StringTokenizer st;
  static int a, b, k;

  public static void main (String[] args) throws IOException {
    a = readInt();
    b = readInt();
    k = readInt();
    Queue<State> q = new ArrayDeque<State>();
    v.clear();
    q.offer(new State(0, 0, null));
    v.add(new State(0, 0, null));
    while (!q.isEmpty()) {
      State curr = q.poll();
      if (curr.x == k || curr.y == k) {
        print(curr);
        out.close();
        return;
      }
      int nx = 0; // a
      int ny = 0; // b
      int fill = 0;
      // x -> y
      fill = Math.min(curr.x, b - curr.y);
      nx = curr.x - fill;
      ny = curr.y + fill;
      if (!v.contains(new State(nx, ny, curr))) {
        v.add(new State(nx, ny, curr));
        q.offer(new State(nx, ny, curr));
      }
      // x <- y
      fill = Math.min(curr.y, a - curr.x);
      nx = curr.x + fill;
      ny = curr.y - fill;
      if (!v.contains(new State(nx, ny, curr))) {
        v.add(new State(nx, ny, curr));
        q.offer(new State(nx, ny, curr));
      }
      // empty y
      if (!v.contains(new State(0, curr.y, curr))) {
        v.add(new State(0, curr.y, curr));
        q.offer(new State(0, curr.y, curr));
      }
      // empty x
      if (!v.contains(new State(curr.x, 0, curr))) {
        v.add(new State(curr.x, 0, curr));
        q.offer(new State(curr.x, 0, curr));
      }

      // fill x
      if (!v.contains(new State(a, curr.y, curr))) {
        v.add(new State(a, curr.y, curr));
        q.offer(new State(a, curr.y, curr));
      }
      // fill y
      if (!v.contains(new State(curr.x, b, curr))) {
        v.add(new State(curr.x, b, curr));
        q.offer(new State(curr.x, b, curr));
      }
    }
    System.out.println("Not possible");
  }

  private static void print (State curr) {
    if (curr.prev != null) {
      print(curr.prev);
      if (curr.x > curr.prev.x && curr.y < curr.prev.y)
        out.println("pour B A");
      else if (curr.y > curr.prev.y && curr.x < curr.prev.x)
        out.println("pour A B");
      else if (curr.y == 0 && curr.prev.y != 0)
        out.println("chug B");
      else if (curr.x == 0 && curr.prev.x != 0)
        out.println("chug A");
      else if (curr.x == a && curr.prev.x != a)
        out.println("fill A");
      else if (curr.y == b && curr.prev.y != b)
        out.println("fill B");
    }
  }

  static HashSet<State> v = new HashSet<State>();

  static class State {
    int x, y;
    State prev;

    State (int x, int y, State prev) {
      this.x = x;
      this.y = y;
      this.prev = prev;
    }

    @Override
    public int hashCode () {
      return new Integer(x).hashCode() * 31 + new Integer(y).hashCode();
    }

    @Override
    public boolean equals (Object o) {
      if (o instanceof State) {
        State p = (State)o;
        return p.x == x && p.y == y;
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

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}