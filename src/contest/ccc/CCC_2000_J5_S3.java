package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class CCC_2000_J5_S3 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static Map<Integer, String> m = new HashMap<Integer, String>();
  static Map<String, Integer> rm = new HashMap<String, Integer>();
  static Set<String> sr = new HashSet<String>();
  static ArrayList<ArrayList<Integer>> adjlist;
  static int n;

  public static void main(String[] args) throws IOException {
    n = readInt();
    adjlist = new ArrayList<ArrayList<Integer>>();
    for (int x = 0; x < 100; x++)
      adjlist.add(new ArrayList<Integer>());
    int index = 0;
    for (int x = 0; x < n; x++) {

      String from = readLine();
      if (!sr.contains(from)) {
        sr.add(from);
        m.put(index, from);
        rm.put(from, index);
        index++;
      }
      String s = readLine();
      while (!s.equals("</HTML>")) {
        int i = s.indexOf("<A HREF=");
        while (i >= 0) {
          int j = s.indexOf("\">", i + 9);
          String to = s.substring(i + 9, j);
          if (!sr.contains(to)) {
            sr.add(to);
            m.put(index, to);
            rm.put(to, index);
            index++;
          }

          System.out.println("Link from " + from + " to " + to);
          adjlist.get(rm.get(from)).add(rm.get(to));
          i = s.indexOf("<A HREF=", j);
        }
        s = readLine();
      }
    }
    System.out.println();
    String s1 = readLine();
    String s2 = readLine();
    while (!s1.equals("The End")) {
      System.out.printf("%s surf from %s to %s.%n", bfs(rm.get(s1), rm.get(s2)) ? "Can" : "Can't", s1, s2);
      s1 = readLine();
      if (br.ready())
        s2 = readLine();
    }
  }

  private static boolean bfs(int s, int e) {
    boolean[] visited = new boolean[100];
    Queue<Integer> moves = new LinkedList<Integer>();
    moves.offer(s);
    visited[s] = true;
    while (!moves.isEmpty()) {
      int curr = moves.poll();
      for (int x = 0; x < adjlist.get(curr).size(); x++) {
        int next = adjlist.get(curr).get(x);
        if (visited[next])
          continue;
        moves.offer(next);
        visited[next] = true;
      }
    }
    return visited[e];
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
