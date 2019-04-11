package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class IOI_2014_Wall {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int[] up;
  static int[] down;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    int height = (int) Math.ceil(Math.log(n) / Math.log(2));
    int size = 2 * (int) Math.pow(2, height) - 1;
    up = new int[size * 2 + 1];
    down = new int[size * 2 + 1];
    int k = readInt();
    for (int x = 0; x < k; x++) {
      boolean build = readInt() == 1;
      int left = readInt();
      int right = readInt();
      int value = readInt();
      update(value, left, right, 0, n - 1, 0, build);
      for (int y = 0; y < size; y++) {
        combine(2 * y, down[y], up[y]);
        combine(2 * y + 1, down[y], up[y]);
      }
      print(0, n - 1, 0);
      System.out.println();
    }
    for (int x = 0; x < size; x++) {
      combine(2 * x, down[x], up[x]);
      combine(2 * x + 1, down[x], up[x]);
    }
    print(0, n - 1, 0);
  }

  private static void print(int x, int y, int j) {
    if (x == y)
      System.out.print(Math.min(up[j], down[j]) + " ");
    else {
      int mid = x + (y - x) / 2;
      print(x, mid, j * 2 + 1);
      print(mid + 1, y, j * 2 + 2);
    }
  }

  static void combine(int n, int d, int u) {
    down[n] = Math.min(down[n], d);// adjusting min for child considering
    // min parent
    down[n] = Math.max(down[n], u);// adjusting min for child considering
    // max parent
    up[n] = Math.max(up[n], u);// adjusting max for child considering max
    // parent
    up[n] = Math.min(up[n], d);// adjusting max for child considering min
    // parent
  }

  static void update(int value, int x, int y, int sindex, int eindex, int curr, boolean build) {
    System.out.println(sindex + " " + eindex + " " + curr);
    if (eindex > x || sindex < y)
      return;
    if (sindex >= x && eindex <= y) {
      if (build) {
        down[curr] = Math.max(value, down[curr]);
        up[curr] = Math.max(value, up[curr]);
      } else {
        down[curr] = Math.min(value, down[curr]);
        up[curr] = Math.min(value, up[curr]);
      }
      return;
    }
    if (sindex != eindex) {
      combine(2 * curr, down[curr], up[curr]);
      combine(2 * curr + 1, down[curr], up[curr]);
      down[curr] = Integer.MAX_VALUE;
      up[curr] = 0;
      int mid = sindex + (eindex - sindex) / 2;
      update(value, x, y, sindex, mid, curr * 2 + 1, build);
      update(value, x, y, mid + 1, eindex, curr * 2 + 2, build);
    }
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
