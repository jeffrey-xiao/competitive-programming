package contest.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ACM_A_Frightening_Evening {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    for (int t = readInt(); t > 0; t--) {
      int min = readInt();
      int numOfKey = readInt();
      int holdHand = readInt();
      int leave = readInt();
      int[][] moments = new int[numOfKey + 1][2];
      for (int x = 1; x <= numOfKey; x++) {
        moments[x][0] = readInt();
        moments[x][1] = readInt();
      }
      long currFreight = 0;
      long totalTime = 0;
      long maxTime = Long.MAX_VALUE;
      for (int x = 0; x <= numOfKey; x++) {
        currFreight = 0;
        totalTime = 0;
        for (int y = 1; y <= numOfKey; y++) {
          if (currFreight >= leave)
            break;
          if (currFreight >= holdHand)
            totalTime += (moments[y][0] - moments[y - 1][0]);
          if (y != x) {
            currFreight += moments[y][1];
            if (currFreight < 0)
              currFreight = 0;
          }
        }
        if (currFreight >= holdHand && currFreight < leave)
          totalTime += (min - moments[numOfKey][0]);
        maxTime = Math.min(totalTime, maxTime);
      }
      if (numOfKey == 0)
        System.out.println(0);
      else
        System.out.println(maxTime);
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
