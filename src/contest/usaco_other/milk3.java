package contest.usaco_other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/*
ID: jeffrey40
LANG: JAVA
TASK: milk3
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class milk3 {
  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  static boolean[][][] v;

  static int a, b, c;

  static ArrayList<Integer> ts = new ArrayList<Integer>();

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new FileReader("milk3.in"));
    pr = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
    // br = new BufferedReader(new InputStreamReader(System.in));

    a = readInt();
    b = readInt();
    c = readInt();

    v = new boolean[a + 1][b + 1][c + 1];

    dfs(0, 0, c);
    Collections.sort(ts);
    for (int x = 0; x < ts.size(); x++) {
      pr.print(ts.get(x));
      if (x < ts.size() - 1)
        pr.print(" ");
    }
    pr.println();
    pr.close();
    System.exit(0);
  }

  private static void dfs (int x, int y, int z) {
    if (v[x][y][z])
      return;
    v[x][y][z] = true;
    if (x > 0) {
      int minusA = Math.min(b - y, x);
      int minusB = Math.min(c - z, x);
      dfs(x - minusA, y + minusA, z);
      dfs(x - minusB, y, z + minusB);
    }
    if (y > 0) {
      int minusA = Math.min(y, a - x);
      int minusB = Math.min(y, c - z);
      dfs(x + minusA, y - minusA, z);
      dfs(x, y - minusB, z + minusB);
    }
    if (z > 0) {
      int minusA = Math.min(z, a - x);
      int minusB = Math.min(z, b - y);
      dfs(x + minusA, y, z - minusA);
      dfs(x, y + minusB, z - minusB);
    }
    if (x == 0) {
      ts.add(z);
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
