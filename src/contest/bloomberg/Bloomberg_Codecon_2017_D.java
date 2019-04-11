package contest.bloomberg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Bloomberg_Codecon_2017_D {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int C, R;
  static HashMap<String, Integer> toIndex = new HashMap<String, Integer>();
  static String start;
  static ArrayList<String> ans = new ArrayList<String>();
  static HashMap<String, ArrayList<Edge>> adj = new HashMap<String, ArrayList<Edge>>();

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    C = readInt();
    R = readInt();

    start = next();
    int[] initial = new int[C];
    for (int i = 0; i < C; i++) {
      toIndex.put(next(), i);
      int val = readInt();
      initial[i] = val;
    }

    for (int i = 0; i < R; i++) {
      String s1 = next();
      String s2 = next();
      String t = next();
      int type = 0;
      int cost = 1 << 30;
      if (toIndex.containsKey(t)) {
        type = toIndex.get(t);
        cost = readInt();
      } else {
        readInt();
      }
      if (adj.get(s1) == null)
        adj.put(s1, new ArrayList<Edge>());
      if (adj.get(s2) == null)
        adj.put(s2, new ArrayList<Edge>());
      adj.get(s1).add(new Edge(s2, type, cost));
      adj.get(s2).add(new Edge(s1, type, cost));
    }
    State s = new State(start, initial);
    s.vis.add(start);
    bruteforce(s);
    for (String st : ans)
      out.print(st + " ");
    out.println();
    out.close();
  }

  static void bruteforce(State s) {
    if (s.vis.size() > ans.size()) {
      ans = new ArrayList<String>();
      for (String st : s.vis)
        ans.add(st);
    }
    String curr = s.curr;
    for (Edge next : adj.get(s.curr)) {
      if (s.vis.contains(next.dest) || s.tickets[next.type] < next.cost)
        continue;
      s.vis.add(next.dest);
      s.tickets[next.type] -= next.cost;
      s.curr = next.dest;
      bruteforce(s);
      s.curr = curr;
      s.tickets[next.type] += next.cost;
      s.vis.remove(next.dest);
    }
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

  static class Edge {
    String dest;
    int type, cost;

    Edge(String dest, int type, int cost) {
      this.dest = dest;
      this.type = type;
      this.cost = cost;
    }
  }

  static class State {
    ArrayList<String> vis = new ArrayList<String>();
    String curr;
    int[] tickets;

    State(String curr, int[] tickets) {
      this.curr = curr;
      this.tickets = tickets;
    }
  }
}
