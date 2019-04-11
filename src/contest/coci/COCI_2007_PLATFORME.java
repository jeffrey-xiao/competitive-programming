package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class COCI_2007_PLATFORME {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    int[][] platforms = new int[n + 1][3];
    platforms[0][0] = 0;
    platforms[0][1] = 0;
    platforms[0][2] = Integer.MAX_VALUE;
    int count = 0;
    for (int x = 1; x < platforms.length; x++) {
      platforms[x][0] = readInt();
      platforms[x][1] = readInt();
      platforms[x][2] = readInt();
    }
    Arrays.sort(platforms, new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        return o1[0] - o2[0];
      }
    });
    for (int x = 1; x < platforms.length; x++) {
      int x1 = Integer.MAX_VALUE;
      int x2 = Integer.MAX_VALUE;

      for (int y = x - 1; y >= 0 && (x1 == Integer.MAX_VALUE || x2 == Integer.MAX_VALUE); y--) {
        if (x1 == Integer.MAX_VALUE && platforms[x][1] >= platforms[y][1] && platforms[x][1] < platforms[y][2]) {
          x1 = platforms[x][0] - platforms[y][0];
        }
        if (x2 == Integer.MAX_VALUE && platforms[x][2] > platforms[y][1] && platforms[x][2] <= platforms[y][2]) {
          x2 = platforms[x][0] - platforms[y][0];
        }
      }
      count += x1 + x2;
    }
    System.out.println(count);
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
