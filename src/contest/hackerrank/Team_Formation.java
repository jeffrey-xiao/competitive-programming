package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Team_Formation {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int t = readInt();
    for (int qq = 0; qq < t; qq++) {
      int n = readInt();
      int[] a = new int[n];
      for (int i = 0; i < n; i++)
        a[i] = readInt();
      Arrays.sort(a);
      if (n == 0) {
        out.println(0);
        continue;
      }
      ArrayList<State> list = new ArrayList<State>();
      int prev = -1;
      int prevIndex = 0;
      for (int i = 0; i < n; i++) {
        if (a[i] != prev) {
          prev = a[i];
          prevIndex = list.size() - 1;
        }
        if (list.size() == 0 || prevIndex < 0 || list.get(prevIndex).end != a[i] - 1) {
          list.add(new State(a[i], 1));
          prevIndex = list.size() - 1;
        } else {
          list.set(prevIndex, new State(a[i], list.get(prevIndex).sz + 1));
          prevIndex--;
        }
      }
      int max = 1 << 30;
      for (State s : list)
        max = Math.min(s.sz, max);
      out.println(max);
    }
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

  static class State {
    int end, sz;

    State(int end, int sz) {
      this.end = end;
      this.sz = sz;
    }
  }
}
