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
TASK: palsquare
 */
import java.util.ArrayList;
import java.util.StringTokenizer;

public class palsquare {
  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new FileReader("palsquare.in"));
    pr = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));

    char[] c = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    int n = readInt();
    main : for (int x = 1; x <= 300; x++) {
      ArrayList<Integer> currNums = new ArrayList<Integer>();
      int currFactor = (int)Math.pow(n, (int)(Math.log(x * x) / Math.log(n)));
      int curr = x * x;
      while (currFactor != 0) {
        int next = curr / currFactor;
        currNums.add(next);
        curr %= currFactor;
        currFactor /= n;
      }
      for (int y = 0; y < currNums.size() / 2; y++) {
        if (currNums.get(y) != currNums.get(currNums.size() - 1 - y))
          continue main;
      }
      pr.print(Integer.toString(x, n).toUpperCase() + " ");
      for (int y = 0; y < currNums.size(); y++)
        pr.print(c[currNums.get(y)]);
      pr.println();
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
