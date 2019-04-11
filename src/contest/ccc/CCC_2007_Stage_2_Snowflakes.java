package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class CCC_2007_Stage_2_Snowflakes {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    int[][] snowflakes = new int[n][6];
    for (int x = 0; x < n; x++) {
      int min = Integer.MAX_VALUE;
      int[] s = new int[6];
      int[] minS = new int[6];
      for (int y = 0; y < 6; y++) {
        s[y] = readInt();
        min = Math.min(min, s[y]);
        minS[y] = Integer.MAX_VALUE;
      }
      for (int y = 0; y < 6; y++) {
        if (s[y] == min) {
          int[] curr = new int[6];
          for (int z = y; z < y + 6; z++)
            curr[z - y] = s[z % 6];
          if (compare(minS, curr) == -1)
            minS = Arrays.copyOf(curr, curr.length);
          for (int z = y; z > y - 6; z--)
            curr[y - z] = s[(z + 6) % 6];
          if (compare(minS, curr) == -1)
            minS = Arrays.copyOf(curr, curr.length);
        }
      }
      snowflakes[x] = Arrays.copyOf(minS, minS.length);
    }
    Arrays.sort(snowflakes, new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        for (int x = 0; x < 6; x++) {
          if (o1[x] != o2[x])
            return o1[x] - o2[x];
        }
        return 0;
      }
    });
    for (int x = 0; x < n - 1; x++) {
      if (compare(snowflakes[x], snowflakes[x + 1]) == 0) {
        System.out.println("Twin snowflakes found.");
        return;
      }
    }
    System.out.println("No two snowflakes are alike.");
  }

  private static int compare(int[] minS, int[] curr) {
    for (int x = 0; x < 6; x++) {
      if (minS[x] > curr[x])
        return -1;
      if (minS[x] < curr[x])
        return 1;
    }
    return 0;
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
