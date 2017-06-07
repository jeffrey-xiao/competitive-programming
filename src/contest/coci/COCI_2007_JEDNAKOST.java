package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class COCI_2007_JEDNAKOST {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  static char[] a;
  static HashMap<State, Integer> dp = new HashMap<State, Integer>();
  static int n, target;

  public static void main (String[] args) throws IOException {
    String[] input = next().split("=");
    a = input[0].toCharArray();
    target = Integer.parseInt(input[1]);
    n = a.length;
    solve(1, target, a[0] - '0');
    print(1, target, "" + a[0]);
  }

  private static void print (int i, int t, String sum) {
    int j = Integer.parseInt(sum);
    if (i == n) {
      System.out.print(sum + "=" + target);
      return;
    }
    if (j * 10 + a[i] - '0' > t || (t - j >= 0 && dp.get(new State(i + 1, t - j, a[i] - '0')) < dp.get(new State(i + 1, t, j * 10 + a[i] - '0')))) {
      System.out.print(sum + "+");
      print(i + 1, t - j, "" + a[i]);
    } else {
      print(i + 1, t, sum + a[i]);
    }
  }

  private static int solve (int i, int t, int currSum) {
    if (i == n) {
      if (t == currSum) {
        dp.put(new State(i, t, currSum), 0);
        return 0;
      }
      dp.put(new State(i, t, currSum), 1 << 30);
      return 1 << 30;
    }
    if (t < 0) {
      dp.put(new State(i, t, currSum), 1 << 30);
      return 1 << 30;
    }
    if (dp.containsKey(new State(i, t, currSum)))
      return dp.get(new State(i, t, currSum));
    int res = 1 << 30;
    if (currSum * 10 + a[i] - '0' <= t) {
      res = Math.min(res, solve(i + 1, t, currSum * 10 + a[i] - '0'));
    }
    res = Math.min(res, solve(i + 1, t - currSum, a[i] - '0') + 1);
    dp.put(new State(i, t, currSum), res);
    return res;
  }

  static class State {
    Integer i, t, s;

    State (int i, int t, int s) {
      this.i = i;
      this.t = t;
      this.s = s;
    }

    public boolean equals (Object o) {
      if (o instanceof State) {
        State st = (State)o;
        return i.equals(st.i) && t.equals(st.t) && s.equals(st.s);
      }
      return false;
    }

    public int hashCode () {
      return i.hashCode() * 31 * 31 + t.hashCode() * 31 + s.hashCode();
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
