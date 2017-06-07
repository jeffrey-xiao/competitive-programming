package contest.bloomberg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Bloomberg_Qualifier_2017_A {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, M;
  static int[] p;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    M = readInt();

    Deque<State> d = new ArrayDeque<State>();

    for (int i = 0; i < N; i++)
      d.addLast(new State(readInt(), i == M));

    int ret = 0;

    while (!d.isEmpty()) {
      State curr = d.removeFirst();
      boolean isGreater = false;

      for (State s : d)
        if (s.val > curr.val)
          isGreater = true;

      if (!isGreater && curr.curr) {
        ret += 2;
        break;
      }

      if (isGreater)
        d.addLast(curr);
      else
        ret += 2;
    }

    out.println(ret);
    out.close();
  }

  static class State {
    int val;
    boolean curr;

    State (int val, boolean curr) {
      this.val = val;
      this.curr = curr;
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
