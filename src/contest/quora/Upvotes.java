package quora;

import java.io.*;
import java.util.*;

public class Upvotes {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    int N = readInt();
    int K = readInt();

    int[] upvotes = new int[N];
    ArrayDeque<Segment> inc = new ArrayDeque<Segment>(), dec = new ArrayDeque<Segment>();

    for (int i = 0; i < N; i++) {
      upvotes[i] = readInt();
    }

    // Adding increasing segments
    int i = 0;
    while (i < N) {
      int l = i;
      int r = i;
      while (r + 1 < N && upvotes[r + 1] >= upvotes[r]) {
        r++;
      }
      inc.add(new Segment(l, r));
      i = r + 1;
    }

    // Adding decreasing segments
    i = 0;
    while (i < N) {
      int l = i;
      int r = i;
      while (r + 1 < N && upvotes[r + 1] <= upvotes[r]) {
        r++;
      }
      dec.add(new Segment(l, r));
      i = r + 1;
    }

    ArrayDeque<Segment> currInc = new ArrayDeque<Segment>(), currDec = new ArrayDeque<Segment>();
    long currTotal = 0;
    for (i = 0; i < N - K + 1; i++) {
      long l = i;
      long r = i + K - 1;
      // popping in
      while (!inc.isEmpty() && inc.peekFirst().l <= r) {
        Segment s = inc.pollFirst();
        currTotal += s.segments();
        currInc.addLast(s);
      }
      while (!dec.isEmpty() && dec.peekFirst().l <= r) {
        Segment s = dec.pollFirst();
        currTotal -= s.segments();
        currDec.addLast(s);
      }

      // popping out
      while (!currInc.isEmpty() && currInc.peekFirst().r < l) {
        Segment s = currInc.pollFirst();
        currTotal -= s.segments();
      }
      while (!currDec.isEmpty() && currDec.peekFirst().r < l) {
        Segment s = currDec.pollFirst();
        currTotal += s.segments();
      }

      // Manually calculate ends
      long ans = currTotal;

      if (currInc.size() == 1) {
        Segment s = currInc.peekFirst();
        ans -= s.segments() - (r - l + 1) * (r - l) / 2;
      } else {
        Segment s = currInc.peekFirst();
        ans -= s.segments() - (s.r - l + 1) * (s.r - l) / 2;
        s = currInc.peekLast();
        ans -= s.segments() - (r - s.l + 1) * (r - s.l) / 2;
      }

      if (currDec.size() == 1) {
        Segment s = currDec.peekFirst();
        ans += s.segments() - (r - l + 1) * (r - l) / 2;
      } else {
        Segment s = currDec.peekFirst();
        ans += s.segments() - (s.r - l + 1) * (s.r - l) / 2;
        s = currDec.peekLast();
        ans += s.segments() - (r - s.l + 1) * (r - s.l) / 2;
      }

      out.println(ans);
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

  static class Segment {
    int l, r;

    Segment(int l, int r) {
      this.l = l;
      this.r = r;
    }

    public long dist() {
      return r - l + 1;
    }

    public long segments() {
      return dist() * (dist() - 1) / 2;
    }

    @Override
    public String toString() {
      return String.format("[%d, %d]", l, r);
    }
  }
}
