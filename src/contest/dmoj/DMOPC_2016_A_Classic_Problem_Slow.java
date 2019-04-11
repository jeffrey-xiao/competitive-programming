package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DMOPC_2016_A_Classic_Problem_Slow {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int[] max, min;
  static int[] a;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    int k = readInt();
    a = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      a[i] = readInt();
    }

    max = new int[4 * n];
    min = new int[4 * n];

    buildMax(1, 1, n);
    buildMin(1, 1, n);

    int ans = 0;
    for (int i = 1; i <= n; i++) {
      int index = query(1, 1, n, i, k, 1 << 30, -1 << 30).index;
      ans += (index - i + 1);
    }
    out.println(ans);
    out.close();
  }

  static State query(int n, int l, int r, int x, int k, int minV, int maxV) {
    if (l == r) {
      maxV = Math.max(maxV, max[n]);
      minV = Math.min(minV, min[n]);
      if (maxV - minV <= k)
        return new State(minV, maxV, l);
      else
        return new State(-1, -1, -1);
    }
    if (x <= l && Math.max(maxV, max[n]) - Math.min(minV, min[n]) <= k)
      return new State(Math.min(minV, min[n]), Math.max(maxV, max[n]), r);
    int mid = (l + r) >> 1;
    if (x <= mid) {
      State res = query(n << 1, l, mid, x, k, minV, maxV);
      if (res.index == mid) {
        State ret = query(n << 1 | 1, mid + 1, r, x, k, Math.min(minV, res.min), Math.max(maxV, res.max));
        if (ret.index != -1)
          return ret;
        else
          return res;
      } else {
        return res;
      }
    } else {
      return query(n << 1 | 1, mid + 1, r, x, k, minV, maxV);
    }
  }

  static void buildMax(int n, int l, int r) {
    if (l == r) {
      max[n] = a[l];
      return;
    }
    int mid = (l + r) >> 1;
    buildMax(n << 1, l, mid);
    buildMax(n << 1 | 1, mid + 1, r);
    max[n] = Math.max(max[n << 1], max[n << 1 | 1]);
  }

  static void buildMin(int n, int l, int r) {
    if (l == r) {
      min[n] = a[l];
      return;
    }
    int mid = (l + r) >> 1;
    buildMin(n << 1, l, mid);
    buildMin(n << 1 | 1, mid + 1, r);
    min[n] = Math.min(min[n << 1], min[n << 1 | 1]);
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

  static class State {
    int min, max, index;

    State(int min, int max, int index) {
      this.min = min;
      this.max = max;
      this.index = index;
    }
  }
}