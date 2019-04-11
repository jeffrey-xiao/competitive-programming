package contest.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class ACM_NEERC_2014_B {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, M, K;
  static int[] C1, C2;
  static HashMap<Integer, Integer> cnt = new HashMap<Integer, Integer>();
  static HashMap<Integer, LinkedList<Integer>> pos = new HashMap<Integer, LinkedList<Integer>>();
  static TreeSet<State> ts = new TreeSet<State>();

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    K = readInt();
    N = readInt();
    M = K / N;

    C1 = new int[K];
    C2 = new int[K];

    for (int i = 0; i < K; i++) {
      C1[i] = readInt();
      if (cnt.get(C1[i]) == null) {
        cnt.put(C1[i], 0);
        pos.put(C1[i], new LinkedList<Integer>());
      }
      cnt.put(C1[i], cnt.get(C1[i]) + 1);
      pos.get(C1[i]).add(i);
    }

    for (Map.Entry<Integer, Integer> e : cnt.entrySet())
      if (e.getKey() != -1)
        ts.add(new State(e.getKey(), e.getValue()));

    while (ts.size() >= 1) {
      State s1 = ts.first();
      State s2 = ts.last();
      ts.remove(s1);
      ts.remove(s2);
      int currCount = 0;

      while (!pos.get(s1.color).isEmpty() && currCount < M) {
        currCount++;
        C2[pos.get(s1.color).removeLast()] = s2.color;
      }

      while (!pos.get(s2.color).isEmpty() && currCount < M) {
        currCount++;
        C2[pos.get(s2.color).removeLast()] = s1.color;
      }

      while (pos.containsKey(-1) && !pos.get(-1).isEmpty() && currCount < M) {
        currCount++;
        C1[pos.get(-1).getLast()] = s1.color;
        C2[pos.get(-1).getLast()] = s2.color;
        pos.get(-1).removeLast();
      }

      if (currCount < M) {
        out.println("No");
        out.close();
        return;
      }

      if (!pos.get(s1.color).isEmpty())
        ts.add(new State(s1.color, pos.get(s1.color).size()));
      if (!pos.get(s2.color).isEmpty())
        ts.add(new State(s2.color, pos.get(s2.color).size()));
    }

    if (pos.containsKey(-1) && pos.get(-1).size() % M != 0) {
      out.println("No");
      out.close();
      return;
    }
    while (pos.containsKey(-1) && !pos.get(-1).isEmpty())
      C1[pos.get(-1).getFirst()] = C2[pos.get(-1).removeFirst()] = 1;

    out.println("Yes");

    for (int i = 0; i < K; i++)
      out.printf("%d %d\n", C1[i], C2[i]);

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

  static class State implements Comparable<State> {
    int occ, color;

    State(int color, int occ) {
      this.color = color;
      this.occ = occ;
    }

    @Override
    public int compareTo(State o) {
      if (occ == o.occ)
        return color - o.color;
      return occ - o.occ;
    }
  }
}
