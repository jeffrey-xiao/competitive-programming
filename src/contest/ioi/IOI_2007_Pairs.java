package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class IOI_2007_Pairs {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int[] bit;
  static int[][][] bit2;
  static int b, n, d, m, size;
  static long ans;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    b = readInt();
    n = readInt();
    d = readInt();
    m = readInt();
    if (b == 1) {
      int[] a = new int[n];
      for (int i = 0; i < n; i++)
        a[i] = readInt();
      Arrays.sort(a);
      TreeMap<Integer, Integer> tm = new TreeMap<Integer, Integer>();
      for (int i = n - 1; i >= 0; i--) {
        Integer j = tm.floorKey(a[i] + d);
        if (j != null)
          ans += tm.get(j) - i;
        if (!tm.containsKey(a[i]))
          tm.put(a[i], i);
      }
      out.println(ans);
    } else if (b == 2) {
      size = m * 2;
      bit = new int[size + 1];
      Point[] a = new Point[n];
      for (int i = 0; i < n; i++) {
        int x = readInt();
        int y = readInt();
        a[i] = new Point(x - y, x + y);
      }
      Arrays.sort(a);
      ArrayDeque<Point> dq = new ArrayDeque<Point>();
      for (int i = 0; i < n; i++) {
        dq.addLast(a[i]);
        update(a[i].b, 1);
        while (a[i].a - dq.getFirst().a > d)
          update(dq.pollFirst().b, -1);
        ans += query(a[i].b + d) - query(a[i].b - d - 1) - 1;
      }
      out.println(ans);
    } else if (b == 3) {
      /*
       * |x1 - x2| + |y1 - y2| + |z1 - z2|
       * 
       * (+ x1 - x2) + (+ y1 - y2) + (+ z1 - z2)
       * (- x1 + x2) + (+ y1 - y2) + (+ z1 - z2)
       * (+ x1 - x2) + (- y1 + y2) + (+ z1 - z2)
       * (- x1 + x2) + (- y1 + y2) + (+ z1 - z2)
       * (+ x1 - x2) + (+ y1 - y2) + (- z1 + z2)
       * (- x1 + x2) + (+ y1 - y2) + (- z1 + z2)
       * (+ x1 - x2) + (- y1 + y2) + (- z1 + z2)
       * (- x1 + x2) + (- y1 + y2) + (- z1 + z2)
       * 
       * (+ x1 + y1 + z1) + (- x2 - y2 - z2)
       * (- x1 + y1 + z1) + (+ x2 - y2 - z2)
       * (+ x1 - y1 + z1) + (- x2 + y2 - z2)
       * (- x1 - y1 + z1) + (+ x2 + y2 - z2)
       * (+ x1 + y1 - z1) + (- x2 - y2 + z2)
       * (- x1 + y1 - z1) + (+ x2 - y2 + z2)
       * (+ x1 - y1 - z1) + (- x2 + y2 + z2)
       * (- x1 - y1 - z1) + (+ x2 + y2 + z2)
       * a = x + y + z
       * b = - x + y + z
       * c = x - y + z
       * d = x + y - z
       */
      size = 4 * m;
      bit2 = new int[size + 1][size + 1][size + 1];
      Point[] a = new Point[n];
      for (int i = 0; i < n; i++) {
        int x = readInt();
        int y = readInt();
        int z = readInt();
        a[i] = new Point(x + y + z, -x + y + z + m, x - y + z + m, x + y - z + m);
      }
      Arrays.sort(a);
      ArrayDeque<Point> dq = new ArrayDeque<Point>();
      for (int i = 0; i < n; i++) {
        dq.addLast(a[i]);
        update(a[i].b, a[i].c, a[i].d, 1);
        while (a[i].a - dq.getFirst().a > d) {
          Point p = dq.pollFirst();
          update(p.b, p.c, p.d, -1);
        }
        ans += query(a[i].b - d - 1, a[i].c - d - 1, a[i].d - d - 1, a[i].b + d, a[i].c + d, a[i].d + d) - 1;
      }
      out.println(ans);
    }

    out.close();
  }

  static int query (int x1, int y1, int z1, int x2, int y2, int z2) {
    return query(x2, y2, z2) - query(x1, y2, z2) - query(x2, y1, z2) - query(x2, y2, z1) + query(x1, y1, z2) + query(x1, y2, z1) + query(x2, y1, z1) - query(x1, y1, z1);
  }

  static void update (int x, int y, int z, int val) {
    for (int i = x; i <= size; i += (i & -i))
      for (int j = y; j <= size; j += (j & -j))
        for (int k = z; k <= size; k += (k & -k))
          bit2[i][j][k] += val;
  }

  static int query (int x, int y, int z) {
    int sum = 0;
    for (int i = Math.min(size, x); i > 0; i -= (i & -i))
      for (int j = Math.min(size, y); j > 0; j -= (j & -j))
        for (int k = Math.min(size, z); k > 0; k -= (k & -k))
          sum += bit2[i][j][k];
    return sum;
  }

  static void update (int x, int val) {
    for (int i = x; i <= size; i += (i & -i))
      bit[i] += val;
  }

  static int query (int x) {
    int sum = 0;
    for (int i = Math.min(size, x); i > 0; i -= (i & -i))
      sum += bit[i];
    return sum;
  }

  static class Point implements Comparable<Point> {
    int a, b, c, d;

    Point (int a, int b) {
      this.a = a;
      this.b = b;
    }

    Point (int a, int b, int c, int d) {
      this.a = a;
      this.b = b;
      this.c = c;
      this.d = d;
    }

    @Override
    public int compareTo (Point p) {
      return a - p.a;
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

  static char readCharacter () throws IOException {
    return next().charAt(0);
  }

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
