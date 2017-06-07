package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class IOI_1995_Street_Race {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static ArrayList<ArrayList<Integer>> adj;
  static int count;
  static int n;
  static boolean isUnavoidable;
  static boolean isSplitting;

  public static void main (String[] args) throws IOException {
    adj = new ArrayList<ArrayList<Integer>>();
    while ((n = readInt()) != -1) {
      adj.add(new ArrayList<Integer>());
      while (n != -2) {
        adj.get(count).add(n);
        n = readInt();
      }
      count++;
    }
    adj.add(new ArrayList<Integer>());
    ArrayList<Integer> up = new ArrayList<Integer>();
    ArrayList<Integer> sp = new ArrayList<Integer>();
    for (int x = 1; x < count; x++) {
      isUnavoidable = false;
      isSplitting = false;
      check(x);
      if (!isUnavoidable)
        up.add(x);
      if (!isSplitting)
        sp.add(x);
    }
    System.out.print(up.size() + " ");
    for (Integer i : up)
      System.out.print(i + " ");
    System.out.println();
    System.out.print(sp.size() + " ");
    for (Integer i : sp)
      System.out.print(i + " ");
  }

  private static void check (int x) {
    boolean[] v = new boolean[count + 1];
    Queue<Integer> moves = new LinkedList<Integer>();
    moves.offer(0);
    v[0] = true;
    while (!moves.isEmpty()) {
      Integer curr = moves.poll();
      if (curr == count)
        isUnavoidable = true;
      for (Integer next : adj.get(curr)) {
        if (v[next] || next == x)
          continue;
        v[next] = true;
        moves.offer(next);
      }
    }
    for (int i = 0; i < adj.size(); i++) {
      for (int j : adj.get(i)) {
        if (!v[i] && v[j])
          isSplitting = true;
      }
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

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
