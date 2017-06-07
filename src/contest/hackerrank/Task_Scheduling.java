package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Task_Scheduling {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static Task[] t;
  static int[] over;
  static int[] p;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    t = new Task[n + 1];
    int[] m = new int[n + 1];
    int[] d = new int[n + 1];
    over = new int[4 * n];
    p = new int[4 * n];
    for (int i = 0; i < 4 * n; i++)
      over[i] = -1 << 29;
    for (int i = 1; i <= n; i++) {
      d[i] = readInt();
      m[i] = readInt();
      t[i] = new Task(d[i], m[i], i);
    }
    int[] rank = new int[n + 1];

    Arrays.sort(t, 1, t.length);
    for (int i = 1; i <= n; i++)
      rank[t[i].index] = i;
    for (int i = 1; i <= n; i++) {
      int index = rank[i];
      set(1, index, 1, n);
      update(1, index + 1, n, 1, n, t[index].min);
      System.out.println(Math.max(0, over[1]));
    }

    out.close();
  }

  static void set (int n, int x, int l, int r) {
    if (l == r && x == l) {
      over[n] += (1 << 29) + t[x].min - t[x].deadline;
      return;
    }
    if (p[n] > 0) {
      p[n << 1] += p[n];
      p[n << 1 | 1] += p[n];
      over[n << 1] += p[n];
      over[n << 1 | 1] += p[n];
      p[n] = 0;
    }
    int mid = (l + r) / 2;
    if (x <= mid)
      set(n << 1, x, l, mid);
    else
      set(n << 1 | 1, x, mid + 1, r);
    over[n] = Math.max(over[n << 1], over[n << 1 | 1]);
  }

  static void update (int n, int ql, int qr, int l, int r, int val) {
    if (ql > qr)
      return;
    if (ql == l && qr == r) {
      over[n] += val;
      p[n] += val;
      return;
    }
    if (p[n] > 0) {
      p[n << 1] += p[n];
      p[n << 1 | 1] += p[n];
      over[n << 1] += p[n];
      over[n << 1 | 1] += p[n];
      p[n] = 0;
    }
    int mid = (l + r) / 2;
    if (qr <= mid)
      update(n << 1, ql, qr, l, mid, val);
    else if (ql > mid)
      update(n << 1 | 1, ql, qr, mid + 1, r, val);
    else {
      update(n << 1, ql, mid, l, mid, val);
      update(n << 1 | 1, mid + 1, qr, mid + 1, r, val);
    }
    over[n] = Math.max(over[n << 1], over[n << 1 | 1]);
  }

  static class Task implements Comparable<Task> {
    int min, deadline, index;

    Task (int deadline, int min, int index) {
      this.min = min;
      this.deadline = deadline;
      this.index = index;
    }

    @Override
    public int compareTo (Task o) {
      return deadline - o.deadline;
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
