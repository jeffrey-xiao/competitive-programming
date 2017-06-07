package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class IOI_1996_Network_Of_Schools {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
  static ArrayList<ArrayList<Integer>> rev = new ArrayList<ArrayList<Integer>>();
  static HashSet<Integer> include = new HashSet<Integer>();
  static HashSet<Integer> left = new HashSet<Integer>();
  static int[] id;
  static int n;
  static Stack<Integer> s = new Stack<Integer>();

  public static void main (String[] args) throws IOException {
    n = readInt();
    id = new int[n];
    for (int x = 0; x < n; x++) {
      adj.add(new ArrayList<Integer>());
      rev.add(new ArrayList<Integer>());
      left.add(x);
    }
    int a = 0;
    for (int x = 0; x < n; x++) {
      while ((a = readInt()) != 0) {
        adj.get(x).add(a - 1);
        rev.get(a - 1).add(x);
      }
    }
    for (int x = 0; x < n; x++) {
      if (left.contains(x))
        find(x);
    }
    System.out.println(include.size());
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
    ArrayList<HashSet<Integer>> g = new ArrayList<HashSet<Integer>>();
    ArrayList<HashSet<Integer>> r = new ArrayList<HashSet<Integer>>();
    for (int x = 0; x < count; x++) {
      g.add(new HashSet<Integer>());
      r.add(new HashSet<Integer>());
    }
    for (int x = 0; x < adj.size(); x++)
      for (Integer i : adj.get(x)) {
        if (id[x] == id[i])
          continue;
        g.get(id[x]).add(id[i]);
        r.get(id[i]).add(id[x]);
      }
    int l1 = 0, l2 = 0;
    for (int x = 0; x < g.size(); x++) {
      if (g.get(x).size() == 0)
        l1++;
      if (r.get(x).size() == 0)
        l2++;
    }
    System.out.println(count == 1 ? 0 : Math.max(l1, l2));
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

  private static void find (int x) {

    boolean[] v = new boolean[n];
    v[x] = true;
    Queue<Integer> moves = new LinkedList<Integer>();
    moves.offer(x);
    while (!moves.isEmpty()) {
      Integer curr = moves.poll();

      if (include.contains(curr)) {
        include.remove(curr);
        continue;
      }
      left.remove(curr);
      for (Integer next : adj.get(curr)) {
        if (v[next])
          continue;
        v[next] = true;
        moves.offer(next);
      }
    }
    include.add(x);
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