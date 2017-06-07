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
TASK: skidesign
 */
import java.util.StringTokenizer;

public class skidesign {
  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new FileReader("skidesign.in"));
    pr = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));
    int hills[] = new int[101];
    int max = -1;
    int min = 101;
    for (int x = readInt(); x > 0; x--) {
      int a = readInt();
      hills[a]++;
      if (a < min)
        min = a;
      if (a > max)
        max = a;
    }
    if (max - min <= 17)
      pr.println(0);

    else {
      int minCost = Integer.MAX_VALUE;
      for (int lowerBound = max - 17; lowerBound >= min; max--, lowerBound--) {
        int cost = 0;
        for (int x = 0; x < lowerBound; x++)
          if (x < lowerBound)
            cost += (lowerBound - (x)) * (lowerBound - (x)) * hills[x];
        for (int x = 100; x > max; x--)
          if (x > max)
            cost += (x - max) * (x - max) * hills[x];

        if (cost < minCost)
          minCost = cost;
      }
      pr.println(minCost);
    }
    pr.close();
    System.exit(0);
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
