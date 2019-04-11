package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class IOI_2009_Salesman {

  static final int INF = 1 << 27;
  static final int SIZE = 500001;
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;
  static int N, U, D, S;
  static Trade[] t;
  static int[] up, down;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    D = readInt();
    U = readInt();
    S = readInt();

    t = new Trade[N + 1];

    // going up to location
    up = new int[4 * SIZE];
    // going down to location
    down = new int[4 * SIZE];

    for (int i = 1; i <= N; i++) {
      t[i] = new Trade(readInt(), readInt(), readInt());
    }

    buildUp(1, 1, SIZE);
    buildDown(1, 1, SIZE);

    Arrays.sort(t, 1, t.length);

    int ans = 0, j;

    for (int i = 1; i <= N; i = j) {
      j = i;
      ArrayList<Trade> f = new ArrayList<Trade>();
      while (j <= N && t[i].day == t[j].day)
        f.add(t[j++]);

      int[] bestDown = new int[f.size()];
      int[] bestUp = new int[f.size()];

      for (int k = 0; k < f.size(); k++)
        bestDown[k] = bestUp[k] = queryCost(f.get(k).pos) + f.get(k).profit;

      // going down
      for (int k = f.size() - 2; k >= 0; k--) {
        bestDown[k] = Math.max(bestDown[k], f.get(k).profit + bestDown[k + 1] - D * (f.get(k + 1).pos - f.get(k).pos));
      }

      // going up
      for (int k = 1; k < f.size(); k++) {
        bestUp[k] = Math.max(bestUp[k], f.get(k).profit + bestUp[k - 1] - U * (f.get(k).pos - f.get(k - 1).pos));
      }
      for (int k = 0; k < f.size(); k++) {
        int best = Math.max(bestDown[k], bestUp[k]);
        updateProfit(f.get(k).pos, best);
        if (f.get(k).pos >= S)
          ans = Math.max(ans, best - D * (f.get(k).pos - S));
        else
          ans = Math.max(ans, best - U * (S - f.get(k).pos));
      }
    }

    out.println(ans);
    out.close();
  }

  static void updateProfit(int pos, int profit) {
    update(down, 1, 1, SIZE, pos, profit - D * pos);
    update(up, 1, 1, SIZE, pos, profit + U * pos);
  }

  static int queryCost(int pos) {
    int downQuery = query(down, 1, 1, SIZE, pos, SIZE) + D * pos;
    int upQuery = query(up, 1, 1, SIZE, 1, pos) - U * pos;
    return Math.max(downQuery, upQuery);
  }

  static int query(int[] val, int n, int l, int r, int ql, int qr) {
    if (l == ql && r == qr)
      return val[n];

    int mid = (l + r) >> 1;
    if (qr <= mid)
      return query(val, n << 1, l, mid, ql, qr);
    else if (ql > mid)
      return query(val, n << 1 | 1, mid + 1, r, ql, qr);
    return Math.max(query(val, n << 1, l, mid, ql, mid), query(val, n << 1 | 1, mid + 1, r, mid + 1, qr));
  }

  static void update(int[] val, int n, int l, int r, int x, int profit) {
    if (l == x && x == r) {
      val[n] = Math.max(val[n], profit);
      return;
    }

    int mid = (l + r) >> 1;
    if (x <= mid)
      update(val, n << 1, l, mid, x, profit);
    else
      update(val, n << 1 | 1, mid + 1, r, x, profit);

    val[n] = Math.max(val[n << 1], val[n << 1 | 1]);
  }

  static void buildDown(int n, int l, int r) {
    if (l == r) {
      if (l == S)
        down[n] = -D * l;
      else
        down[n] = -INF;
      return;
    }
    int mid = (l + r) >> 1;
    buildDown(n << 1, l, mid);
    buildDown(n << 1 | 1, mid + 1, r);
    down[n] = Math.max(down[n << 1], down[n << 1 | 1]);
  }

  static void buildUp(int n, int l, int r) {
    if (l == r) {
      if (l == S)
        up[n] = +U * l;
      else
        up[n] = -INF;
      return;
    }
    int mid = (l + r) >> 1;
    buildUp(n << 1, l, mid);
    buildUp(n << 1 | 1, mid + 1, r);
    up[n] = Math.max(up[n << 1], up[n << 1 | 1]);
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

  static class Trade implements Comparable<Trade> {
    int day, pos, profit;

    Trade(int day, int pos, int profit) {
      this.day = day;
      this.pos = pos;
      this.profit = profit;
    }

    @Override
    public int compareTo(Trade t) {
      if (day == t.day)
        return pos - t.pos;
      return day - t.day;
    }

    public String toString() {
      return String.format("(%d %d %d)", day, pos, profit);
    }
  }
}
