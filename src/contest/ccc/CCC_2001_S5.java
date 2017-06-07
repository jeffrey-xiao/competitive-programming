package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CCC_2001_S5 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    HashSet<State> visited = new HashSet<State>();
    int m = readInt();
    int k = readInt();
    String[] as = new String[k];
    String[] bs = new String[k];
    for (int x = 0; x < k; x++)
      as[x] = next();
    for (int x = 0; x < k; x++)
      bs[x] = next();
    Queue<State> q = new LinkedList<State>();
    for (int x = 0; x < k; x++) {
      State s = new State(as[x], bs[x], 0, x, new ArrayList<Integer>());
      if (s.trim() != 0)
        q.offer(s);
    }
    while (!q.isEmpty()) {
      State curr = q.poll();
      if (curr.length > m || visited.contains(curr))
        continue;
      if (curr.a.equals(curr.b)) {
        System.out.println(curr.moves.size());
        for (Integer i : curr.moves)
          System.out.println(i + 1);
        return;
      }
      visited.add(curr);
      for (int x = 0; x < k; x++) {
        State s = new State(curr.a + as[x], curr.b + bs[x], curr.length + 1, x, curr.moves);
        if (s.trim() != 0)
          q.offer(s);
      }
    }
    System.out.println("No solution.");
  }

  static class State {
    String a;
    String b;
    int length;
    ArrayList<Integer> moves;

    State (String a, String b, int length, int move, ArrayList<Integer> moves) {
      this.a = a;
      this.b = b;
      this.length = length;
      this.moves = new ArrayList<Integer>(moves);
      this.moves.add(move);
    }

    public int trim () {
      int l = Math.min(a.length(), b.length());
      if (!a.substring(0, l).equals(b.substring(0, l))) {
        return 0;
      }
      a = a.substring(l, a.length());
      b = b.substring(l, b.length());
      return l;
    }

    @Override
    public int hashCode () {
      return a.hashCode() * 31 + b.hashCode();
    }

    @Override
    public boolean equals (Object o) {
      if (o instanceof State) {
        State s = (State)o;
        return a.equals(s.a) && b.equals(s.b);
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
