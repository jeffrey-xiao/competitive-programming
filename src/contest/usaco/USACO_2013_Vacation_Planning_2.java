package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class USACO_2013_Vacation_Planning_2 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    int[][] dist = new int[n][n];
    int m = readInt();
    int hubNum = readInt() - 1;
    int paths = readInt();
    for (int x = 0; x < n; x++)
      for (int y = 0; y < n; y++)
        if (x != y)
          dist[x][y] = 500000000;
    for (int x = 0; x < m; x++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      int c = readInt();
      dist[a][b] = c;
    }
    for (int x = 0; x < n; x++) {
      for (int y = 0; y < n; y++) {
        for (int z = 0; z < n; z++) {
          dist[y][z] = Math.min(dist[y][z], dist[x][z] + dist[y][x]);
        }
      }
    }
    int count = 0;
    long total = 0;
    for (int x = 0; x < paths; x++) {
      int value = 500000000;
      int a = readInt() - 1;
      int b = readInt() - 1;
      for (int y = 0; y <= hubNum; y++) {
        value = Math.min(value, dist[a][y] + dist[y][b]);
      }
      if (value != 500000000) {
        count++;
        total += value;
      }
    }
    System.out.println(count + "\n" + total);
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
