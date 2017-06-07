package contest.usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class USACO_2015_Stampede_Good {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    int n = readInt();
    ArrayList<Event> e = new ArrayList<Event>();
    for (int i = 0; i < n; i++) {
      int x = readInt();
      int y = readInt();
      int r = readInt();
      x *= -r;
      e.add(new Event(x - r, y));
      e.add(new Event(x, -y));
    }
    Collections.sort(e);
    TreeSet<Integer> curr = new TreeSet<Integer>();
    HashSet<Integer> res = new HashSet<Integer>();
    for (int x = 0; x < e.size();) {
      int y;
      for (y = x; y < e.size() && e.get(x).x == e.get(y).x; y++) {
        if (e.get(y).y > 0)
          curr.add(e.get(y).y);
        else
          curr.remove(-e.get(y).y);
      }
      if (curr.size() != 0) {
        res.add(curr.first());
      }
      x = y;
    }
    System.out.println(res.size());
  }

  static class Event implements Comparable<Event> {
    int x, y;

    Event (int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public int compareTo (Event o) {
      return x - o.x;
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

  static char readCharacter () throws IOException {
    return next().charAt(0);
  }

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
