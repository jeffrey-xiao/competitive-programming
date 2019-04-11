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
TASK: censor
 */
import java.util.StringTokenizer;

public class censor {
  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new FileReader("censor.in"));
    // br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new BufferedWriter(new FileWriter("censor.out")));

    StringBuilder s = new StringBuilder(readLine());
    int n = readInt();
    char[][] str = new char[n][];
    int[][] prefix = new int[n][];
    for (int x = 0; x < n; x++) {
      str[x] = readLine().toCharArray();
      prefix[x] = computePrefix(str[x]);
    }
    boolean complete = false;
    int min = Integer.MAX_VALUE;
    int index = 0;
    while (!complete) {
      complete = true;
      for (int i = 0; i < n; i++) {
        int next = search(prefix[i], str[i], s);
        if (next != -1 && min > next) {
          min = next;
          index = i;
        }
      }
      if (min != Integer.MAX_VALUE) {
        complete = false;
        s.delete(min, min + str[index].length);
        min = Integer.MAX_VALUE;
      }
    }
    pr.println(s);
    pr.close();
    System.exit(0);
  }

  static int search(int[] prefix, char[] s1, StringBuilder s2) {
    int y = 0;
    for (int x = 0; x < s2.length(); x++) {
      while (y > 0 && s2.charAt(x) != s1[y])
        y = prefix[y - 1];
      if (s2.charAt(x) == s1[y]) {
        y++;
        if (y == s1.length)
          return x - (y - 1);
      }
    }
    return -1;
  }

  static int[] computePrefix(char[] s) {
    int[] LSP = new int[s.length];
    LSP[0] = 0;
    for (int x = 1; x < s.length; x++) {
      int y = LSP[x - 1];
      while (y > 0 && s[y] != s[x]) {
        y = LSP[y - 1];
      }
      if (s[y] == s[x])
        y++;
      LSP[x] = y;
    }
    return LSP;
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
}
