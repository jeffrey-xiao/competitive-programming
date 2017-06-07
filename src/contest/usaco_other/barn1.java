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
TASK: barn1
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class barn1 {
  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new FileReader("barn1.in"));
    pr = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));

    int m = readInt();
    int s = readInt();
    int c = readInt();

    int[] stalls = new int[s];
    for (int x = 0; x < c; x++)
      stalls[readInt() - 1]++;
    int curr = 0;
    ArrayList<Integer> gap = new ArrayList<Integer>();
    boolean leading = false;
    for (int x = 0; x < s; x++) {
      if (stalls[x] == 0)
        curr++;
      else {
        if (curr != 0 && leading)
          gap.add(curr);
        leading = true;
        curr = 0;
      }
    }
    Collections.sort(gap);
    int totalCost = 0;
    for (int x = 0; x < gap.size() - m + 1; x++)
      totalCost += gap.get(x);
    pr.println(totalCost + c);

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
