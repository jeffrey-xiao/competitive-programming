package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class COCI_2009_POSLOZI {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  static int[] A;
  static int[] B;
  static final int RADIX = 12;
  static int n, m;

  static HashMap<Integer, Byte> vs = new HashMap<Integer, Byte>();
  static HashMap<Integer, Byte> ve = new HashMap<Integer, Byte>();

  public static void main (String[] args) throws IOException {
    n = readInt();
    m = readInt();
    A = new int[m];
    B = new int[m];
    State startState = new State(true);
    State endState = new State(false);
    for (int x = 0; x < n; x++) {
      startState.s[x] = readInt();
      endState.s[x] = x + 1;
    }
    for (int x = 0; x < m; x++) {
      A[x] = readInt() - 1;
      B[x] = readInt() - 1;
    }
    vs.put(toIndex(startState.s), (byte)-1);
    ve.put(toIndex(endState.s), (byte)-1);
    Queue<State> q = new LinkedList<State>();
    q.offer(startState);
    q.offer(endState);
    int[] end = null;
    while (!q.isEmpty()) {
      State curr = q.poll();
      if ((curr.isStart && ve.containsKey(toIndex(curr.s)) || (!curr.isStart && vs.containsKey(toIndex(curr.s))))) {
        end = Arrays.copyOf(curr.s, curr.s.length);
        break;
      }
      for (byte x = 0; x < m; x++) {
        swap(curr.s, A[x], B[x]);
        int i = toIndex(curr.s);
        if (curr.isStart) {
          if (!vs.containsKey(i)) {
            vs.put(i, x);
            q.offer(new State(curr.s, curr.isStart));
          }
        } else {
          if (!ve.containsKey(i)) {
            ve.put(i, x);
            q.offer(new State(curr.s, curr.isStart));
          }
        }

        swap(curr.s, A[x], B[x]);
      }
    }
    ArrayList<Integer> steps = new ArrayList<Integer>();
    int[] c = Arrays.copyOf(end, end.length);
    while (true) {
      int i = toIndex(c);
      int j = vs.get(i);
      if (j == -1)
        break;
      steps.add(j);
      swap(c, A[j], B[j]);
    }
    Collections.reverse(steps);
    c = Arrays.copyOf(end, end.length);
    while (true) {
      int i = toIndex(c);
      int j = ve.get(i);
      if (j == -1)
        break;

      steps.add(j);
      swap(c, A[j], B[j]);
    }
    System.out.println(steps.size());
  }

  static class State {
    int[] s;
    boolean isStart;

    State (boolean isStart) {
      s = new int[n];
      this.isStart = isStart;
    }

    State (int[] s, boolean isStart) {
      this.s = Arrays.copyOf(s, s.length);
      this.isStart = isStart;
    }
  }

  private static void swap (int[] s, int a, int b) {
    int temp = s[a];
    s[a] = s[b];
    s[b] = temp;
  }

  private static int toIndex (int[] s) {
    int res = 0;
    int m = 1;
    for (int x = 0; x < n; x++) {
      res += s[x] * m;
      m *= RADIX;
    }
    return res;
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

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}