package contest.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Round_189C {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N;
  static long[] A, B, dp;
  static ArrayList<State> s = new ArrayList<State>();

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();

    A = new long[N];
    B = new long[N];
    dp = new long[N];

    for (int i = 0; i < N; i++)
      A[i] = readLong();

    for (int i = 0; i < N; i++)
      B[i] = readLong();

    dp[0] = B[0];

    s.add(new State(0, 0));

    for (int i = 1; i < N; i++) {
      int lo = 0;
      int hi = s.size() - 1;

      while (lo <= hi) {
        int mid = (lo + hi) >> 1;
        if (s.get(mid).x <= A[i])
          lo = mid + 1;
        else
          hi = mid - 1;
      }
      int index = s.get(hi).index;

      dp[i] = dp[index] + B[index] * (A[i] - 1) + B[i];

      State last = s.get(s.size() - 1);
      while (getIntersectX(B[last.index], dp[last.index], B[i], dp[i]) <= last.x) {
        s.remove(s.size() - 1);
        if (s.isEmpty())
          break;
        last = s.get(s.size() - 1);
      }

      if (s.isEmpty())
        s.add(new State(0, i));
      else
        s.add(new State(getIntersectX(B[last.index], dp[last.index], B[i], dp[i]), i));
    }

    out.println(dp[N - 1]);
    out.close();
  }

  static long getIntersectX (long m1, long b1, long m2, long b2) {
    long num = b2 - b1;
    long den = m1 - m2;
    return (num + den - 1) / den;
  }

  static class State {
    long x;
    int index;

    State (long x, int index) {
      this.x = x;
      this.index = index;
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
