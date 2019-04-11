package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class USACO_2012_Meet_And_Greet {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int[] timesN = new int[1000010];
    int[] timesM = new int[1000010];
    int n = readInt();
    int m = readInt();
    int currTimeN = 1;
    int currTimeM = 1;
    for (int x = 0; x < n; x++) {
      int move = readInt();
      String dir = next();
      if (dir.equals("R")) {
        timesN[currTimeN]++;
        timesN[currTimeN + move]--;
        currTimeN += move;
      } else if (dir.equals("L")) {
        timesN[currTimeN]--;
        timesN[currTimeN + move]++;
        currTimeN += move;
      }
    }
    for (int x = 0; x < m; x++) {
      int move = readInt();
      String dir = next();
      if (dir.equals("R")) {
        timesM[currTimeM]++;
        timesM[currTimeM + move]--;
        currTimeM += move;
      } else if (dir.equals("L")) {
        timesM[currTimeM]--;
        timesM[currTimeM + move]++;
        currTimeM += move;
      }
    }
    int currA = 0;
    int currB = 0;
    int distanceA = 0;
    int prevDistanceA = 0;
    int distanceB = 0;
    int prevDistanceB = 0;
    int count = 0;
    for (int x = 1; x < Math.max(currTimeN, currTimeM) + 5; x++) {
      currA += timesN[x];
      timesN[x] = currA;
      prevDistanceA = distanceA;
      distanceA += timesN[x];
      currB += timesM[x];
      timesN[x] = currB;
      prevDistanceB = distanceB;
      distanceB += timesN[x];
      if (prevDistanceA - prevDistanceB == 0 && distanceA != distanceB)
        count++;
    }
    System.out.println(count - 1);
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

  static short readShort() throws IOException {
    return Short.parseShort(next());
  }

  static double readDouble() throws IOException {
    return Double.parseDouble(next());
  }

  static String re1adLine() throws IOException {
    return br.readLine().trim();
  }
}
