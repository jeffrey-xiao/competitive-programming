package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class CCC_2000_Stage_2_Subsets {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static ArrayList<Set<Character>> sets = new ArrayList<Set<Character>>();
  static ArrayList<ArrayList<Integer>> link = new ArrayList<ArrayList<Integer>>();
  static boolean[] visited = new boolean[26];
  static TreeSet<Integer> setsToPrint;

  public static void main (String[] args) throws IOException {
    for (int x = 0; x < 26; x++) {
      sets.add(new TreeSet<Character>());
      link.add(new ArrayList<Integer>());
    }
    setsToPrint = new TreeSet<Integer>();
    int n = readInt();
    Queue<Integer> moves = new LinkedList<Integer>();
    for (int x = 0; x < n; x++) {
      int set = next().charAt(0) - 65;
      next();
      int s = next().charAt(0);
      if (s >= 97)
        sets.get(set).add((char)s);
      else {
        link.get(set).add(s - 65);
        if (!moves.contains(set))
          moves.offer(set);
      }
    }
    while (!moves.isEmpty()) {
      int curr = moves.poll();
      dfs(curr);
    }
    for (int x : setsToPrint) {
      String s = "";
      for (char y : sets.get(x))
        s += "," + y;
      if (s.equals(""))
        System.out.println((char)(x + 65) + " = {}");
      else
        System.out.println((char)(x + 65) + " = {" + s.substring(1, s.length()) + "}");
    }
  }

  private static void dfs (int curr) {
    if (visited[curr])
      return;
    visited[curr] = true;
    setsToPrint.add(curr);
    for (int x = 0; x < link.get(curr).size(); x++) {
      int next = link.get(curr).get(x);
      dfs(next);
      sets.get(curr).addAll(sets.get(next));
    }
    return;
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
