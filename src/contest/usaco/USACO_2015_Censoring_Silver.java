package contest.usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class USACO_2015_Censoring_Silver {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    StringBuilder s = new StringBuilder(readLine());
    char[] str = readLine().toCharArray();
    int[] prefix = computePrefix(str);
    int start = 0;
    while (true) {
      int next = search(prefix, str, s, start);
      if (next != -1) {
        System.out.println(next);
        s.delete(next, next + str.length);
        start = Math.max(0, next - str.length);
      } else {
        break;
      }
    }
    System.out.println(s);
  }

  static int search(int[] prefix, char[] s1, StringBuilder s2, int start) {
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

  static char readCharacter() throws IOException {
    return next().charAt(0);
  }

  static String readLine() throws IOException {
    return br.readLine().trim();
  }
}