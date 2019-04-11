package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class USACO_2013_Luxury {
  // GET MAP AFTER SEQ -> YIELD GRAPH
  // FIND CYCLE
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
    HashMap<Integer, Integer> cycle = new HashMap<Integer, Integer>();
    int currPort = 0;
    int start = -1;
    int cycleCount = 0;
    boolean done = false;
    boolean totallyDone = false;
    HashSet<Integer> bigCycle = new HashSet<Integer>();
    for (int x = 0; x < repeat; x++) {
      if (done && !totallyDone && repeat > seqLength) {
        cycleCount--;
        x %= cycleCount;
        x--;
        repeat %= cycleCount;

        repeat += cycleCount;
        totallyDone = true;
        continue;
      }
      if (start == currPort)
        break;
      start = currPort;
      if (!done)
        cycleCount++;
      if (bigCycle.contains(start))
        done = true;
      bigCycle.add(start);

      if (cycle.get(currPort) != null) {
        currPort = cycle.get(currPort);
        continue;
      }
      for (int y = 0; y < seqLength; y++) {
        if (goRight[y])
          currPort = ports[currPort][1];
        else
          currPort = ports[currPort][0];
      }
      cycle.put(start, currPort);
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
