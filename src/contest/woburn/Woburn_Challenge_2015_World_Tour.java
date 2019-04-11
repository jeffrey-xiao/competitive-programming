package contest.woburn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Woburn_Challenge_2015_World_Tour {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N;

  static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
  static int[] sz;
  static boolean[] vis;
  static int[] depth;

  static Stack<Integer> s = new Stack<Integer>();
  static int curr;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();

    sz = new int[N];
    vis = new boolean[N];
    depth = new int[N];

    for (int i = 0; i < N; i++) {
      adj.add(new ArrayList<Integer>());
    }

    for (int i = 0; i < N; i++) {
      int val = readInt() - 1;
      adj.get(i).add(val);
    }

    for (int i = 0; i < N; i++) {
      if (!vis[i]) {
        curr = 0;
        dfs(i, i, 1);
        while (!s.isEmpty()) {
          depth[s.pop()] = 0;
        }
      }
    }

    for (int i = 0; i < N; i++) {
      out.println(sz[i] + 1);
    }
    out.close();
  }

  static int dfs(int i, int start, int d) {
    vis[i] = true;

    depth[i] = d;
    s.push(i);
    curr++;
    for (int j : adj.get(i)) {
      if (vis[j]) {
        if (depth[j] == 0) {
          sz[i] = sz[j] + 1;
          return 1 << 30;
        } else {
          sz[i] = d - depth[j];
          return depth[j];
        }
      }
      int nextDepth = dfs(j, start, d + 1);
      if (d >= nextDepth)
        sz[i] = sz[j];
      else
        sz[i] = sz[j] + 1;
      return nextDepth;
    }
    return 1 << 30;
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
}
