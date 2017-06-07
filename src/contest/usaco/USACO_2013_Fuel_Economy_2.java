package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class USACO_2013_Fuel_Economy_2 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    int n = readInt();
    int maxGas = readInt();
    int curr = readInt();
    int end = readInt();
    Station[] stations = new Station[n];
    for (int x = 0; x < n; x++)
      stations[x] = new Station(readInt(), readInt());
    Arrays.sort(stations);
    int[] nextSmall = new int[n];
    Stack<Integer> s = new Stack<Integer>();
    for (int x = n - 1; x >= 0; x--) {
      while (!s.isEmpty() && (stations[s.peek()].cost > stations[x].cost)) {
        s.pop();
      }
      if (s.isEmpty())
        nextSmall[x] = -1;
      else
        nextSmall[x] = s.peek();
      s.push(x);
    }
    curr -= stations[0].pos;
    long cost = 0;
    for (int x = 0; x < n; x++) {
      if (curr < 0) {
        System.out.println("-1");
        return;
      }
      int needed = Math.min(maxGas, (nextSmall[x] == -1 ? end : stations[nextSmall[x]].pos) - stations[x].pos);
      if (needed > curr) {
        cost += (long)(needed - curr) * (long)stations[x].cost;
        curr = needed;
      }
      curr -= (x == n - 1 ? end : stations[x + 1].pos) - stations[x].pos;
    }
    System.out.println(curr < 0 ? "-1" : cost);
  }

  static class Station implements Comparable<Station> {
    int pos;
    int index;
    int cost;

    Station (int pos, int cost) {
      this.pos = pos;
      this.cost = cost;
    }

    @Override
    public int compareTo (Station o) {
      return pos - o.pos;
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