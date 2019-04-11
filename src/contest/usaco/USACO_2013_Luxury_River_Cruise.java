package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class USACO_2013_Luxury_River_Cruise {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int numOfPorts = readInt();
    int[][] ports = new int[numOfPorts][2];
    // 0 left, 1 right
    int seqLength = readInt();
    boolean[] goRight = new boolean[seqLength];
    int repeat = readInt();
    for (int x = 0; x < numOfPorts; x++) {
      ports[x][0] = readInt() - 1;
      ports[x][1] = readInt() - 1;
    }
    for (int x = 0; x < seqLength; x++) {
      char dir = next().charAt(0);
      if (dir == 'R')
        goRight[x] = true;
      else
        goRight[x] = false;
    }
    int tIndex = 0;
    int rIndex = 0;
    int tPort = 0;
    int rPort = 0;
    do {
      tIndex++;

      if (goRight[tIndex % seqLength])
        tPort = ports[tPort][1];
      else
        tPort = ports[tPort][0];
      for (int x = 0; x < 2; x++) {
        rIndex++;
        if (goRight[rIndex % seqLength])
          rPort = ports[rPort][1];
        else
          rPort = ports[rPort][0];
      }
    } while (tPort != rPort);
    Set<Integer> cycle = new HashSet<Integer>();
    do {
      cycle.add(rPort);
      rIndex++;
      if (goRight[rIndex % seqLength])
        rPort = ports[rPort][1];
      else
        rPort = ports[rPort][0];
    } while (tPort != rPort);
    int currPort = 0;
    int amountToCycle = 0;
    for (int x = 0; x < repeat * seqLength; x++) {
      if (cycle.contains(currPort)) {
        amountToCycle = x;
        break;
      }
      currPort = goRight[x % seqLength] ? ports[currPort][1] : ports[currPort][0];
    }
    for (int x = amountToCycle; x < (repeat * seqLength); x++) {
      currPort = goRight[x % seqLength] ? ports[currPort][1] : ports[currPort][0];
    }
    System.out.println(currPort + 1);
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
