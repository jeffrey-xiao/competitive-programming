package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class CCC_2014_Stage_2_Early_Exam_Evacuation {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static long[][] tree;
  static int ny;// column
  static int nx;// row
  static Long[] up;
  static Long[] down;

  public static void main(String[] args) throws IOException {
    // INPUT
    nx = readInt();
    ny = 6;
    int n = readInt();
    int a = readInt();
    int b = readInt();
    tree = new long[nx + 1][ny + 1];
    Integer[] index = new Integer[n];
    up = new Long[n];
    down = new Long[n];
    // POPULATING BIT TREE
    for (int x = 1; x <= nx; x++)
      for (int y = 1; y <= ny; y++)
        update(x, y, 1);

    for (int z = 0; z < n; z++) {
      index[z] = z;
      String s = next();
      int x = 0;
      int y = 0;
      x = Integer.parseInt(s.substring(0, s.length() - 1));
      y = s.charAt(s.length() - 1) - 'A' + 1;
      // UPDATING AND GETTING AREA OF WALKING UP AND DOWN
      update(x, y, -1);
      int area = 0;
      if (y <= 3) {
        area += getArea(x, y, x, 3);
        if (getValue(x, 3) != 0)
          area--;
      } else {
        area += getArea(x, 4, x, y);
        if (getValue(x, 4) != 0)
          area--;
      }
      up[z] = area + getArea(1, 3, x, 4);
      down[z] = area + getArea(x, 3, nx, 4);
    }
    Arrays.sort(index, new StudentCompare());
    long topCount = 0;
    long bottomCount = 0;
    long total = 0;

    for (int x = 0; x < n; x++) {
      total += up[index[x]] * a + topCount * b;
      topCount++;
    }
    topCount--;
    for (int x = 0; x < n; x++) {
      int curr = index[x];
      long d = down[curr] * a + (bottomCount) * b;
      long u = up[curr] * a + (topCount) * b;
      if (d <= u) {
        total -= u;
        total += d;
        bottomCount++;
        topCount--;
      } else {
      }
    }
    System.out.println(total);
  }

  static long getArea(int x1, int y1, int x2, int y2) {
    x1--;
    y1--;
    return freqTo(x2, y2) + freqTo(x1, y1) - freqTo(x2, y1) - freqTo(x1, y2);
  }

  static long getValue(int x, int y) {
    return getArea(x, y, x, y);
  }

  private static void update(int idxx, int idxy, int val) {
    for (int x = idxx; x <= nx; x += (x & -x))
      for (int y = idxy; y <= ny; y += (y & -y))
        tree[x][y] += val;
  }

  private static long freqTo(int idxx, int idxy) {
    long sum = 0;
    for (int x = idxx; x > 0; x -= (x & -x))
      for (int y = idxy; y > 0; y -= (y & -y))
        sum += tree[x][y];
    return sum;
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

  static class StudentCompare implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
      long compare = (down[o1] - up[o1]) - (down[o2] - up[o2]);
      if (compare == 0)
        return down[o1].compareTo(down[o2]);
      return compare < 0 ? -1 : 1;
      // return down[o1]-down[o2];
    }
  }
}