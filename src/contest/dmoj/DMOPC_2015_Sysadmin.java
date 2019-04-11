package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class DMOPC_2015_Sysadmin {

  static final double EPS = 0.0000001;
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    int q = readInt();

    Line[] l = new Line[n];
    TreeSet<State> ts = new TreeSet<State>();
    int min = 0;
    for (int i = 0; i < n; i++) {
      l[i] = new Line(readLong(), readLong(), i);
      if (l[i].s > l[min].s)
        min = i;
    }
    Arrays.sort(l);
    LinkedList<State> s = new LinkedList<State>();
    s.addLast(new State(0, l[0].s, l[0].m, l[0].index));
    for (int i = 1; i < n; i++) {
      State last = s.getLast();
      if (l[i].m == last.m || l[i].index == min)
        continue;
      Double x = (last.s - l[i].s) / (double) (l[i].m - last.m);
      while (x > 0 && (x < last.x || (Math.abs(x - last.x) < EPS && l[i].m > last.m && l[i].index < last.index))) {
        s.removeLast();
        last = s.getLast();
        x = (last.s - l[i].s) / (double) (l[i].m - last.m);
      }
      if (x > 0)
        s.addLast(new State(x, l[i].s, l[i].m, l[i].index));
    }
    ArrayList<State> finalS = new ArrayList<State>();
    for (State state : s) {
      finalS.add(state);
    }
    ts.add(new State(0, finalS.get(0).index));
    Collections.sort(finalS);
    for (int i = 1; i < finalS.size(); i++) {
      double x = (finalS.get(i).s - finalS.get(i - 1).s) / (double) (finalS.get(i - 1).m - finalS.get(i).m);
      if (x == Math.ceil(x)) {
        if (finalS.get(i).index < finalS.get(i - 1).index) {
          ts.remove(new State((int) Math.ceil(x), finalS.get(i).index));
          ts.add(new State((int) Math.ceil(x), finalS.get(i).index));
        } else {
          ts.remove(new State((int) Math.ceil(x) + 1, finalS.get(i).index));
          ts.add(new State((int) Math.ceil(x) + 1, finalS.get(i).index));
        }
      } else {
        ts.remove(new State((int) Math.ceil(x), finalS.get(i).index));
        ts.add(new State((int) Math.ceil(x), finalS.get(i).index));
      }
    }
    for (int i = 0; i < q; i++) {
      int query = readInt();
      int a = min;
      if (query != 0)
        a = ts.floor(new State(query, 0)).index;
      System.out.println(a);
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

  static class State implements Comparable<State> {
    Double x;
    Long s, m;
    int index;

    State(double x, int index) {
      this(x, 0, 0, index);
    }

    State(double x, long s, long m, int index) {
      this.x = x;
      this.s = s;
      this.m = m;
      this.index = index;
    }

    @Override
    public int compareTo(State o) {
      if (x.compareTo(o.x) == 0)
        return m.compareTo(o.m);
      return x.compareTo(o.x);
    }
  }

  static class Line implements Comparable<Line> {
    Long s, m;
    int index;

    Line(long s, long m, int index) {
      this.s = s;
      this.m = m;
      this.index = index;
    }

    @Override
    public int compareTo(Line l) {
      int cmp1 = l.s.compareTo(s);
      int cmp2 = l.m.compareTo(m);
      int cmp3 = index - l.index;
      if (cmp1 == 0) {
        if (cmp2 == 0) {
          return cmp3;
        }
        return cmp2;
      }
      return cmp1;
    }

  }
}
