package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class COCI_2008_MAJSTOR {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    int n = readInt();
    String s = next();

    int r = readInt();
    int[][] scores = new int[n][3];
    int sum = 0;
    for (int x = r; x > 0; x--) {
      String next = next();
      for (int y = 0; y < n; y++) {
        if (next.charAt(y) == 'S') {// 0 is ROCK, 1 IS SCISSOR, 2 PAPER
          if (s.charAt(y) == 'S')
            sum++;
          else if (s.charAt(y) == 'R')
            sum += 2;
          scores[y][0] += 2;
          scores[y][1]++;
        } else if (next.charAt(y) == 'P') {
          if (s.charAt(y) == 'P')
            sum++;
          else if (s.charAt(y) == 'S')
            sum += 2;
          scores[y][1] += 2;
          scores[y][2]++;
        } else {
          if (s.charAt(y) == 'R')
            sum++;
          else if (s.charAt(y) == 'P')
            sum += 2;
          scores[y][2] += 2;
          scores[y][0]++;
        }
      }
    }
    System.out.println(sum);
    int max = 0;
    for (int x = 0; x < n; x++) {
      max += Math.max(scores[x][0], Math.max(scores[x][1], scores[x][2]));
    }
    System.out.println(max);
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
