package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class USACO_2014_Decorating_The_Pastures {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {

    int finalCount = 0;
    ArrayList<ArrayList<Integer>> adjlist = new ArrayList<ArrayList<Integer>>();
    int n = readInt();
    byte[] vertices = new byte[n];
    for (int x = 0; x < n; x++)
      adjlist.add(new ArrayList<Integer>());
    int m = readInt();
    for (int x = 0; x < m; x++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      adjlist.get(a).add(b);
      adjlist.get(b).add(a);
    }
    Queue<int[]> moves = new LinkedList<int[]>();
    for (int y = 0; y < n; y++) {
      int count1 = 0;
      int count2 = 0;
      moves.add(new int[]{y, -1});
      while (!moves.isEmpty()) {
        int[] curr = moves.poll();
        if (vertices[curr[0]] != 0)
          continue;

        byte opp = (byte) (curr[1] == -1 ? 1 : -1);
        vertices[curr[0]] = opp;
        if (opp == 1)
          count1++;
        else
          count2++;
        for (int x = 0; x < adjlist.get(curr[0]).size(); x++) {
          int conn = adjlist.get(curr[0]).get(x);
          if (vertices[conn] == opp) {
            System.out.println(-1);
            return;
          } else if (vertices[conn] == 0) {
            moves.offer(new int[]{conn, opp});
          }
        }
      }
      finalCount += Math.max(count1, count2);
    }
    System.out.println(finalCount);
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
}
