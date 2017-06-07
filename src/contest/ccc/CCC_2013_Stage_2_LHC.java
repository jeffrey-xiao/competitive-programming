package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CCC_2013_Stage_2_LHC {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int max;
  static int total;
  static ArrayList<ArrayList<Integer>> adj;
  static boolean[] v;

  public static void main (String[] args) throws IOException {
    int n = readInt();
    adj = new ArrayList<ArrayList<Integer>>();
    for (int x = 0; x < n; x++)
      adj.add(new ArrayList<Integer>());
    for (int x = 0; x < n - 1; x++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      adj.get(a).add(b);
      adj.get(b).add(a);
    }
    // FIRST DFS
    int end = 0;
    Queue<State> moves = new LinkedList<State>();
    v = new boolean[n];
    v[0] = true;
    moves.offer(new State(0, 0));
    while (!moves.isEmpty()) {
      State curr = moves.poll();
      if (curr.moves > max) {
        max = curr.moves;
        end = curr.dest;
      }
      for (Integer i : adj.get(curr.dest))
        if (!v[i]) {
          v[i] = true;
          moves.offer(new State(i, curr.moves + 1));
        }
    }
    moves.clear();
    v = new boolean[n];
    int[] prev = new int[n];
    v[end] = true;
    prev[end] = -1;
    moves.offer(new State(end, 0));
    max = 0;
    int best = 0;
    // GETTING THE DIAMETER
    while (!moves.isEmpty()) {
      State curr = moves.poll();
      if (curr.moves > max) {
        max = curr.moves;
        best = curr.dest;
      }
      for (Integer i : adj.get(curr.dest))
        if (!v[i]) {
          v[i] = true;
          prev[i] = curr.dest;
          moves.offer(new State(i, curr.moves + 1));
        }
    }
    ArrayList<Integer> path = new ArrayList<Integer>();
    int curr = best;
    while (curr != -1) {
      path.add(curr);
      curr = prev[curr];
    }
    // SETTING THE CENTERS AND SEPARATING INTO TWO SUBTREES IF THERE ARE TWO
    // CENTERS
    ArrayList<Integer> centers = new ArrayList<Integer>();
    double split = (path.size() - 1) / 2.0d;
    for (int x = (int)Math.floor(split); x <= (int)Math.ceil(split); x++)
      centers.add(path.get(x));
    for (int x = 0; x < centers.size() - 1; x++) {
      int a = centers.get(x);
      int b = centers.get(x + 1);
      int index1 = adj.get(a).indexOf(b);
      int index2 = adj.get(b).indexOf(a);
      adj.get(a).remove(index1);
      adj.get(b).remove(index2);
    }
    v = new boolean[n];
    int pathLength = max;
    max = max / 2;
    long sum = 1;
    ArrayList<Long> subtrees = new ArrayList<Long>();

    if (centers.size() == 1) {
      v[centers.get(0)] = true;
      subtrees.clear();
      long currTotal = 0;
      long currPaths = 0;
      for (Integer i : adj.get(centers.get(0))) {
        v[i] = true;
        long add = dfs(i, 1);

        currTotal += (add) * currPaths;
        currPaths += add;
      }
      sum = currTotal;
    } else {
      for (int x = 0; x < centers.size(); x++) {
        v[centers.get(x)] = true;
        subtrees.clear();
        for (Integer i : adj.get(centers.get(x))) {
          v[i] = true;
          long add = dfs(i, 1);
          subtrees.add(add);
        }
        long currSum = 0;
        for (int y = 0; y < subtrees.size(); y++)
          currSum += subtrees.get(y);
        if (currSum != 0)
          sum *= currSum;
      }

    }
    System.out.println(pathLength + 1 + " " + sum);
  }

  private static long dfs (Integer i, int l) {
    long total = 0;
    for (Integer next : adj.get(i)) {
      if (!v[next]) {
        v[next] = true;
        long n = dfs(next, l + 1);
        total += n;
      }
    }
    if (l == max) {
      ++total;
    }
    return total;
  }

  static class State {
    int moves;
    int dest;

    State (int dest, int moves) {
      this.dest = dest;
      this.moves = moves;
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
