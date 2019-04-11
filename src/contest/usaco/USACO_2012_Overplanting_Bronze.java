package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class USACO_2012_Overplanting_Bronze {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    int[][] lines = new int[n * 2][3];
    for (int x = 0; x < n; x++) {
      int x1 = readInt();
      int y1 = readInt();
      int x2 = readInt();
      int y2 = readInt();
      lines[x * 2] = (new int[]{x1, y2, y1, 1});
      lines[x * 2 + 1] = (new int[]{x2, y2, y1, -1});
    }
    Arrays.sort(lines, new Comparator<int[]>() {
      @Override
      public int compare(int[] arg0, int[] arg1) {
        return arg0[0] - arg1[0];
      }
    });
    int total = 0;
    int sum = 0;
    int curr = Integer.MIN_VALUE;
    int index = 0;
    int[] strip = new int[20001];
    while (index < n * 2) {
      if (lines[index][0] > curr) {
        total += (lines[index][0] - curr - 1) * sum;
        curr = lines[index][0];
        int add = lines[index][3];
        sum = 0;
        for (int y = lines[index][1]; y < lines[index][2]; y++) {
          strip[y + 10000] += add;
        }
        for (int y = 0; y < 20001; y++)
          sum += strip[y] > 0 ? 1 : 0;
        index++;
      }
      while (index < n * 2 && lines[index][0] == curr) {
        int add = lines[index][3];
        sum = 0;
        for (int y = lines[index][1]; y < lines[index][2]; y++) {
          strip[y + 10000] += add;
        }
        for (int y = 0; y < 20001; y++)
          sum += strip[y] > 0 ? 1 : 0;
        index++;
      }
      total += sum;
    }
    System.out.println(total);
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
