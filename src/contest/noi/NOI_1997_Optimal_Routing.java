package contest.noi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class NOI_1997_Optimal_Routing {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    int k = readInt();
    ArrayList<HashSet<Integer>> adj = new ArrayList<HashSet<Integer>>();
    for (int x = 0; x < k; x++)
      adj.add(new HashSet<Integer>());
    for (int x = 0; x < n; x++) {
      int[] stops = readInput();
      for (int y = 0; y < stops.length; y++)
        for (int z = y + 1; z < stops.length; z++)
          adj.get(stops[y] - 1).add(stops[z] - 1);
    }
    boolean[] v = new boolean[k];
    Queue<State> moves = new LinkedList<State>();
    moves.offer(new State(0, 0));
    v[0] = true;
    while (!moves.isEmpty()) {
      State curr = moves.poll();
      if (curr.curr == k - 1) {
        System.out.println(Math.max(0, curr.moves - 1));
        return;
      }
      for (Integer next : adj.get(curr.curr)) {
        if (v[next])
          continue;
        v[next] = true;
        moves.offer(new State(next, curr.moves + 1));
      }
    }
    System.out.println("NO");
  }

  private static int[] readInput() throws IOException {
    StringTokenizer s = new StringTokenizer(readLine().trim());
    int[] input = new int[s.countTokens()];
    int c = 0;
    while (s.hasMoreTokens())
      input[c++] = Integer.parseInt(s.nextToken());
    return input;
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

  static class State {
    int curr, moves;

    State(int curr, int moves) {
      this.curr = curr;
      this.moves = moves;
    }
  }
}
