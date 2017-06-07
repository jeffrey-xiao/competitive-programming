package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class CCC_2013_S4 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static Map<Integer, ArrayList<Integer>> p = new HashMap<Integer, ArrayList<Integer>>();
  static int numOfPeople;
  static int numOfCon;

  public static void main (String[] args) throws IOException {
    numOfPeople = readInt();
    for (int x = 0; x < numOfPeople; x++)
      p.put(x, new ArrayList<Integer>());
    numOfCon = readInt();
    for (int x = 0; x < numOfCon; x++)
      p.get(readInt() - 1).add(readInt() - 1);
    int x = readInt() - 1;
    int y = readInt() - 1;
    if (isTaller(x, y))
      System.out.println("yes");
    else if (isTaller(y, x))
      System.out.println("no");
    else
      System.out.println("unknown");
  }

  private static boolean isTaller (int x, int y) {
    Queue<Integer> moves = new LinkedList<Integer>();
    boolean[] visited = new boolean[numOfPeople];
    moves.offer(x);
    visited[x] = true;
    while (!moves.isEmpty()) {
      int curr = moves.poll();
      if (curr == y)
        return true;
      for (int z = 0; z < p.get(curr).size(); z++) {

        int next = p.get(curr).get(z);
        if (visited[next])
          continue;
        visited[next] = true;
        moves.add(next);
      }
    }
    return false;
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
