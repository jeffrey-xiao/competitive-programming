package contest.hackercup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class FHC_2016_Round_1_Laundro_Matt {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static long[] min, wash;
  static int[] index;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int T = readInt();
    for (int t = 1; t <= T; t++) {
      int l = readInt();
      int n = readInt();
      int m = readInt();
      int d = readInt();

      wash = new long[n + 1];
      min = new long[4 * n];
      index = new int[4 * n];

      for (int i = 1; i <= n; i++)
        wash[i] = readInt();
      build(1, 1, n);

      PriorityQueue<Long> pq = new PriorityQueue<Long>();
      long ans = 0;
      if (m <= l) {
        for (int i = 0; i < m; i++)
          pq.offer(0l);

        for (int i = 0; i < l; i++) {
          pq.offer(Math.max(pq.poll(), min[1]) + d);
          update(1, 1, n, index[1]);
        }
        for (int i = 0; i < m - 1; i++)
          pq.poll();
        ans = pq.poll();
      } else {
        ans = 0;
        for (int i = 0; i < l; i++) {
          ans = min[1];
          update(1, 1, n, index[1]);
        }
        ans += d;
      }
      out.printf("Case #%d: %d\n", t, ans);
    }
    out.close();
  }

  static void build(int n, int l, int r) {
    if (l == r) {
      min[n] = wash[l];
      index[n] = l;
      return;
    }
    int mid = (l + r) >> 1;
    build(n << 1, l, mid);
    build(n << 1 | 1, mid + 1, r);
    pushUp(n);
  }

  static void update(int n, int l, int r, int x) {
    if (l == x && x == r) {
      min[n] += wash[l];
      return;
    }
    int mid = (l + r) >> 1;
    if (x <= mid)
      update(n << 1, l, mid, x);
    else
      update(n << 1 | 1, mid + 1, r, x);
    pushUp(n);
  }

  static void pushUp(int n) {
    if (min[n << 1] < min[n << 1 | 1]) {
      min[n] = min[n << 1];
      index[n] = index[n << 1];
    } else {
      min[n] = min[n << 1 | 1];
      index[n] = index[n << 1 | 1];
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

  static char readCharacter() throws IOException {
    return next().charAt(0);
  }

  static String readLine() throws IOException {
    return br.readLine().trim();
  }
}
