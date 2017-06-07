package contest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class APIO_2009_The_Great_ATM_Robbery {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
  static ArrayList<ArrayList<Integer>> rev = new ArrayList<ArrayList<Integer>>();
  static Stack<Integer> s = new Stack<Integer>();
  static HashSet<Integer> ends = new HashSet<Integer>();
  static int[] id;
  static int n;
  static int m;

  public static void main (String[] args) throws IOException {
    n = readInt();
    m = readInt();
    id = new int[n];
    for (int x = 0; x < n; x++) {
      adj.add(new ArrayList<Integer>());
      rev.add(new ArrayList<Integer>());
    }
    for (int x = 0; x < m; x++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      adj.get(a).add(b);
      rev.get(b).add(a);
    }

    boolean[] v = new boolean[n];
    for (int x = 0; x < n; x++) {
      if (!v[x]) {
        computeS(x, v);
      }
    }
    v = new boolean[n];
    int count = 0;
    for (int x = 0; x < n; x++) {
      if (!v[s.peek()]) {
        computeG(s.peek(), v, count);
        count++;
      }
      s.pop();
    }
    rev.clear();
    ArrayList<HashSet<Integer>> g = new ArrayList<HashSet<Integer>>();
    for (int x = 0; x < count; x++) {
      g.add(new HashSet<Integer>());
    }
    for (int x = 0; x < adj.size(); x++)
      for (Integer i : adj.get(x)) {
        if (id[x] == id[i])
          continue;
        g.get(id[x]).add(id[i]);
      }
    adj.clear();

    int[] cValues = new int[count];
    for (int x = 0; x < n; x++)
      cValues[id[x]] += readInt();
    int start = readInt() - 1;
    int k = readInt();
    for (int x = 0; x < k; x++)
      ends.add(id[readInt() - 1]);

    System.out.println(maxValue(id[start], cValues, g, ends));
  }

  private static int maxValue (int start, int[] values, ArrayList<HashSet<Integer>> g, HashSet<Integer> ends) {
    int[] max = new int[n];
    max[start] = values[start];
    int ans = 0;
    PriorityQueue<Vertex> moves = new PriorityQueue<Vertex>();
    moves.offer(new Vertex(start, values[start]));
    while (!moves.isEmpty()) {
      Vertex curr = moves.poll();
      if (ends.contains(curr.index)) {
        ans = Math.max(ans, curr.value);
      }
      for (Integer i : g.get(curr.index)) {
        if (max[i] >= curr.value + values[i])
          continue;
        max[i] = curr.value + values[i];
        moves.offer(new Vertex(i, max[i]));
      }
    }
    return ans;
  }

  static class Vertex implements Comparable<Vertex> {
    int index;
    int value;

    Vertex (int index, int value) {
      this.index = index;
      this.value = value;
    }

    @Override
    public int compareTo (Vertex o) {
      return o.value - value;
    }
  }

  private static void computeG (int x, boolean[] v, int c) {
    id[x] = c;
    v[x] = true;
    for (Integer i : adj.get(x)) {
      if (v[i])
        continue;
      computeG(i, v, c);
    }
  }

  private static void computeS (int x, boolean[] v) {
    v[x] = true;
    for (Integer i : rev.get(x)) {
      if (v[i])
        continue;
      computeS(i, v);
    }
    s.push(x);
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
