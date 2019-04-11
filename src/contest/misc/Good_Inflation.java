package contest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Good_Inflation {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int n;
  static long[] s, m; // start and slope

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();

    s = new long[n];
    m = new long[n];

    for (int i = 0; i < n; i++) {
      s[i] = readInt();
      m[i] = -readInt();
    }

    Stack<Line> stack = new Stack<Line>();
    TreeSet<Line> ts = new TreeSet<Line>();

    stack.push(new Line(s[0], m[0], 1 << 30));
    ts.add(new Line(s[0], m[0], 1 << 30));

    for (int i = 1; i < n; i++) {
      Line build = ts.ceiling(new Line(0, 0, i));
      long y = Math.max(build.s + build.m * i, 0) + s[i] - i * m[i];
      long slope = m[i];

      long x = 0;
      while (!stack.isEmpty()) {
        if (stack.peek().m <= slope) {
          ts.remove(stack.pop());
        } else {
          x = (stack.peek().s - y) / (slope - stack.peek().m);
          if (x >= stack.peek().x) {
            ts.remove(stack.pop());
          } else {
            break;
          }
        }
      }
      if (stack.isEmpty()) {
        stack.push(new Line(y, slope, 1 << 30));
        ts.add(new Line(y, slope, 1 << 30));
      } else {
        stack.push(new Line(y, slope, x));
        ts.add(new Line(y, slope, x));
      }
    }

    Line ans = ts.ceiling(new Line(0, 0, n));
    out.println(Math.max(ans.s + ans.m * (n), 0));
    out.close();
  }

  static long brute() {
    Line[] line = new Line[n];
    line[0] = new Line(s[0], m[0], 0);
    for (int i = 1; i < n; i++) {
      long best = s[i] - m[i] * i;
      for (int j = 0; j < i; j++)
        best = Math.max(best, line[j].s + line[j].m * i + s[i] - m[i] * i);
      line[i] = new Line(best, m[i], 0);
    }
    long ans = 0;
    for (int i = 0; i < n; i++)
      ans = Math.max(ans, line[i].s + line[i].m * n);
    return ans;
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

  static class Line implements Comparable<Line> {
    long s, m;
    Long x; // if x1 <= x, choose this Line

    Line(long s, long m, long x) {
      this.s = s;
      this.m = m;
      this.x = x;
    }

    @Override
    public int compareTo(Line l) {
      return x.compareTo(l.x);
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof Line) {
        Line l = (Line) o;
        return s == l.s && m == l.m && x.compareTo(l.x) == 0;
      }
      return false;
    }
  }
}
