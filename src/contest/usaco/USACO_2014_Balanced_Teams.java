package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class USACO_2014_Balanced_Teams {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int[] skills = new int[12];
  static int[] playerIndex = new int[12];
  static int[] teamCount = new int[4];
  static int lowest = Integer.MAX_VALUE;

  public static void main(String[] args) throws IOException {
    for (int x = 0; x < 12; x++)
      skills[x] = readInt();
    compute(0);
    System.out.println(lowest);
  }

  private static void compute(int i) {
    if (i == 12) {
      int[] team = new int[4];
      for (int x = 0; x < 12; x++)
        team[playerIndex[x]] += skills[x];
      int hi = Integer.MIN_VALUE;
      int lo = Integer.MAX_VALUE;
      for (int x = 0; x < 4; x++) {
        hi = Math.max(hi, team[x]);
        lo = Math.min(lo, team[x]);
      }
      lowest = Math.min(hi - lo, lowest);
    }
    for (int x = 0; x < 4; x++) {
      if (teamCount[x] < 3) {
        playerIndex[i] = x;
        teamCount[x]++;
        compute(i + 1);
        teamCount[x]--;
      }
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

  static String readLine() throws IOException {
    return br.readLine().trim();
  }
}
