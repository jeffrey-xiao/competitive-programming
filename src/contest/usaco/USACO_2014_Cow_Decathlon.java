package contest.usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class USACO_2014_Cow_Decathlon {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;
  static Award[] awards;
  static short[][] skill;
  static int[][] dp;
  static int n, k;

  public static void main(String[] args) throws IOException {
    n = readInt();
    k = readInt();
    awards = new Award[k];
    dp = new int[1 << n][n];
    skill = new short[n][n];
    for (int x = 0; x < k; x++)
      awards[x] = new Award(readInt(), readInt(), readInt());
    for (int x = 0; x < n; x++)
      for (int y = 0; y < n; y++)
        skill[x][y] = readShort();
    for (int x = 0; x < 1 << n; x++)
      for (int y = 0; y < n; y++)
        dp[x][y] = -1;
    dp[0][0] = 0;
    boolean[] state = new boolean[n];
    int max = 0;
    for (int x = 0; x < n; x++) {
      state[x] = true;
      dp[toIndex(state)][0] = skill[x][0];
      max = Math.max(max, compute(state, 1, skill[x][0]));
      state[x] = false;
    }
    System.out.println(max);
  }

  // k is previous cow added
  public static int compute(boolean[] s, int k, int prev) {
    int i = toIndex(s);
    if (i == ((1 << n) - 1))
      return prev;
    if (dp[i][k] != -1)
      return dp[i][k];
    int length = 0;
    for (int x = 0; x < n; x++)
      if (s[x])
        length++;
    int max = 0;
    for (int x = 0; x < n; x++) {
      if (!s[x]) {
        int next = prev + skill[x][length];
        for (int y = 0; y < awards.length; y++)
          if (length == awards[y].events && prev >= awards[y].min)
            next += awards[y].points;
        s[x] = true;
        max = Math.max(compute(Arrays.copyOf(s, s.length), x, next), max);
        s[x] = false;
      }
    }
    dp[i][k] = max;
    return max;
  }

  public static int toIndex(boolean[] s) {
    int b = 1;
    int total = 0;
    for (int x = 0; x < s.length; x++, b *= 2)
      if (s[x])
        total += b;
    return total;
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

  static short readShort() throws IOException {
    return Short.parseShort(next());
  }

  static char readCharacter() throws IOException {
    return next().charAt(0);
  }

  static String readLine() throws IOException {
    return br.readLine().trim();
  }

  static class Award {
    int events, min, points;

    Award(int events, int min, int points) {
      this.events = events;
      this.min = min;
      this.points = points;
    }
  }
}
