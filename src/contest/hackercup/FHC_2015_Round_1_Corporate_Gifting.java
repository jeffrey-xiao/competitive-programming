package contest.hackercup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FHC_2015_Round_1_Corporate_Gifting {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static State[] dp1;
  static State[] dp2;
  static ArrayList<ArrayList<Integer>> adj;
  static int n;
  static int ln;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int t = readInt();
    for (int qq = 1; qq <= t; qq++) {
      n = readInt();
      ln = (int) (Math.ceil(Math.log(n) / Math.log(2)));
      adj = new ArrayList<ArrayList<Integer>>();
      dp1 = new State[n];
      dp2 = new State[n];
      for (int x = 0; x < n; x++)
        adj.add(new ArrayList<Integer>());
      for (int x = 0; x < n; x++) {
        int a = readInt() - 1;
        if (a >= 0)
          adj.get(a).add(x);
      }
      compute(0);
      out.printf("Case #%d: %d\n", qq, dp1[0].value);
    }

    out.close();
  }

  private static void compute(int i) {
    if (adj.get(i).size() == 0) {
      dp1[i] = new State(1);
      dp1[i].curr.add(1);
      dp2[i] = new State(2);
      dp2[i].curr.add(2);
      return;
    }
    for (Integer x : adj.get(i))
      compute(x);

    int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
    ArrayList<Integer> firstC = new ArrayList<Integer>(), secondC = new ArrayList<Integer>();
    for (int j = 1; j <= ln; j++) {
      int t = j;
      for (int k : adj.get(i)) {
        if (dp1[k].curr.size() == 1 && dp1[k].curr.get(0) == j)
          t += dp2[k].value;
        else
          t += dp1[k].value;
      }
      if (t < first) {
        second = first;
        first = t;
        secondC = firstC;
        firstC = new ArrayList<Integer>();
        firstC.add(j);
      } else if (t == first) {
        firstC.add(j);
      } else if (t < second) {
        second = t;
        secondC = new ArrayList<Integer>();
        secondC.add(j);
      } else if (t == second) {
        secondC.add(j);
      }
    }

    dp1[i] = new State(first, firstC);
    dp2[i] = new State(second, secondC);
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

  static String readLine() throws IOException {
    return br.readLine().trim();
  }

  static class State {
    int value;
    ArrayList<Integer> curr = new ArrayList<Integer>();

    State(int value, ArrayList<Integer> curr) {
      this.value = value;
      this.curr = curr;
    }

    State(int value) {
      this.value = value;
    }
  }
}
