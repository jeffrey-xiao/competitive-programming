package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class IOI_2013_Dreaming {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();
  static int max = 0;
  static int end = 0;
  static boolean[] visited;
  static int[] prev;
  static int[] maxD;
  static int n;
  static ArrayList<Integer> pathLengths = new ArrayList<Integer>();

  public static void main (String[] args) throws IOException {
    n = readInt();
    int m = readInt();
    int l = readInt();
    for (int x = 0; x < n; x++)
      adj.add(new ArrayList<Edge>());
    visited = new boolean[n];
    prev = new int[n];
    for (int x = 0; x < m; x++) {
      int a = readInt();
      int b = readInt();
      int c = readInt();
      adj.get(a).add(new Edge(b, c));
      adj.get(b).add(new Edge(a, c));
    }
    ArrayList<Integer> ends = new ArrayList<Integer>();
    for (int x = 0; x < n; x++) {
      if (!visited[x]) {
        visited[x] = true;
        max = end = -1;
        getEnd(x);
        ends.add(end);
      }
    }
    ArrayList<Integer> diameters = new ArrayList<Integer>();
    ArrayList<Integer> starts = new ArrayList<Integer>();
    for (int x = 0; x < n; x++)
      prev[x] = -1;
    visited = new boolean[n];
    for (Integer i : ends) {
      visited[i] = true;
      max = end = -1;
      diameters.add(getPath(i));
      starts.add(end);
    }
    ArrayList<Integer> radii = new ArrayList<Integer>();
    visited = new boolean[n];
    for (int x = 0; x < starts.size(); x++)
      radii.add(getRadius(starts.get(x), diameters.get(x)));
    int size = starts.size();
    Collections.sort(diameters, Collections.reverseOrder());
    Collections.sort(radii, Collections.reverseOrder());
    int minDist = diameters.get(0);
    if (size >= 2) {
      minDist = Math.max(minDist, radii.get(0) + l + radii.get(1));
    }
    if (size >= 3)
      minDist = Math.max(minDist, radii.get(1) + l + l + radii.get(2));
    System.out.println(minDist);
  }

  private static int getRadius (int i, int diameter) {
    int curr = i;
    int total = 0;
    int minR = Integer.MAX_VALUE;
    while (prev[curr] != -1) {
      minR = Math.min(minR, Math.max(total, diameter - total));
      int index = adj.get(prev[curr]).indexOf(new Edge(curr, 0));
      total += adj.get(prev[curr]).get(index).cost;
      curr = prev[curr];
    }
    return minR == Integer.MAX_VALUE ? 0 : minR;
  }

  private static int getPath (int i) {
    Stack<State> s = new Stack<State>();
    s.push(new State(i, 0, 0));
    while (!s.isEmpty()) {
      State curr = s.pop();
      if (curr.cost > max) {
        max = curr.cost;
        end = curr.index;
      }
      for (Edge e : adj.get(curr.index)) {
        if (!visited[e.dest]) {
          visited[e.dest] = true;
          prev[e.dest] = curr.index;
          s.push(new State(e.dest, curr.count + 1, curr.cost + e.cost));
        }
      }
    }
    return max;
  }

  private static void getEnd (int i) {
    Stack<State> s = new Stack<State>();
    s.push(new State(i, 0, 0));
    while (!s.isEmpty()) {
      State curr = s.pop();
      if (curr.cost > max) {
        max = curr.cost;
        end = curr.index;
      }
      for (Edge e : adj.get(curr.index)) {
        if (!visited[e.dest]) {
          visited[e.dest] = true;
          s.push(new State(e.dest, curr.count + 1, curr.cost + e.cost));
        }
      }
    }
  }

  static class Edge {
    int dest;
    int cost;

    Edge (int dest, int cost) {
      this.dest = dest;
      this.cost = cost;
    }

    @Override
    public boolean equals (Object o) {
      if (o instanceof Edge) {
        Edge e = (Edge)o;
        return dest == e.dest;
      }
      return false;
    }
  }

  static class State {
    int count;
    int index;
    int cost;

    State (int index, int count) {
      this.index = index;
      this.count = count;
    }

    State (int index, int count, int cost) {
      this.index = index;
      this.count = count;
      this.cost = cost;
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