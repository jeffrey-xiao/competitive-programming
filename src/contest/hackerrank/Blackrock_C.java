package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Blackrock_C {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int[] val, s;
  static int p, t, threshold;
  static double r;

  static PriorityQueue<State> pq;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    p = readInt();
    r = readDouble() / 100;
    t = readInt();
    threshold = readInt();

    s = new int[t + 1];
    val = new int[t + 1];

    pq = new PriorityQueue<State>(3);

    for (int i = 1; i <= t; i++)
      s[i] = readInt();

    out.printf("%.3f%n", getAnswer(s));

    compute(1, 0);
    String[] ans = new String[3];
    int index = 2;
    while (!pq.isEmpty()) {
      State s = pq.poll();
      ans[index--] = String.format("%.3f - %s", s.d, s.val);
    }

    for (int i = 0; i < 3; i++)
      out.println(ans[i]);

    out.close();
  }

  static void compute(int i, int sum) {
    if (i == t + 1) {
      for (int j = 1; j <= t; j++) {
        val[j] += s[j];
      }
      addAnswer(val);
      for (int j = 1; j <= t; j++) {
        val[j] -= s[j];
      }
      return;
    }

    for (int j = -threshold; j <= threshold; j++) {
      val[i] = j;
      if (s[i] + j < 0 || s[i] + j >= 100)
        continue;
      if (Math.abs(sum + j) > Math.abs(t - i) * threshold)
        continue;
      compute(i + 1, sum + j);
    }
  }

  static double getAnswer(int[] a) {
    double ans = 0;
    double mult = 1;
    for (int i = 1; i <= t; i++) {
      ans += 1.0 * p * a[i] * mult * Math.pow(1 + r, i) / Math.pow(100, i);
      mult *= (100 - a[i]);
    }
    return ans;
  }

  static void addAnswer(int[] a) {
    double ans = getAnswer(a);
    StringBuilder ret = new StringBuilder("");
    for (int i = 1; i <= t; i++)
      ret.append(a[i] + " ");
    pq.offer(new State(ans, ret.toString()));
    if (pq.size() > 3)
      pq.poll();
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
    Double d;
    String val;

    State(double d, String val) {
      this.d = d;
      this.val = val;
    }

    @Override
    public int compareTo(State s) {
      return d.compareTo(s.d);
    }
  }
}
