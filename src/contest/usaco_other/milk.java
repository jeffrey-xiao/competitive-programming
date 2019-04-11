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
TASK: milk
 */
import java.util.Arrays;
import java.util.StringTokenizer;

public class milk {
  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new FileReader("milk.in"));
    pr = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));

    int n = readInt();
    int m = readInt();
    Farmer[] f = new Farmer[m];
    for (int x = 0; x < m; x++) {
      f[x] = new Farmer(readInt(), readInt());
    }
    Arrays.sort(f);
    int totalCost = 0;
    for (int x = 0; x < m; x++) {
      int num = Math.min(n, f[x].units);
      totalCost += num * f[x].cost;
      n -= num;
    }
    pr.println(totalCost);
    pr.close();
    System.exit(0);
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

  static class Farmer implements Comparable<Farmer> {
    int units, cost;

    Farmer(int cost, int units) {
      this.units = units;
      this.cost = cost;
    }

    @Override
    public int compareTo(Farmer o) {
      return cost - o.cost;
    }
  }
}
