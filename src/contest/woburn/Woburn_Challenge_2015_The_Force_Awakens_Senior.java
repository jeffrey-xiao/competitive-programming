package contest.woburn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Woburn_Challenge_2015_The_Force_Awakens_Senior {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, M;
  static int x1, y1, x2, y2;
  static long[] lazy, damage, ships;
  static final double EPS = 1e-6;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    M = readInt();
    x1 = readInt();
    y1 = readInt();
    x2 = readInt();
    y2 = readInt();

    lazy = new long[N * 4];
    damage = new long[N * 4];
    ships = new long[N * 4];

    PriorityQueue<Event> pq = new PriorityQueue<Event>();

    for (int i = 1; i <= N; i++) {
      double sx = readInt();
      double sy = readInt();
      double dx = readInt();
      double dy = readInt();

      double t, x, y;

      ArrayList<Double> times = new ArrayList<Double>();

      if (dx != 0) {
        // checking x1
        t = (x1 - sx) / dx;
        y = sy + t * dy;
        if (y1 <= y && y <= y2)
          times.add(t);

        // checking x2
        t = (x2 - sx) / dx;
        y = sy + t * dy;
        if (y1 <= y && y <= y2)
          times.add(t);
      }

      if (dy != 0) {
        // checking y1
        t = (y1 - sy) / dy;
        x = sx + t * dx;
        if (x1 <= x && x <= x2)
          times.add(t);

        // checking y2
        t = (y2 - sy) / dy;
        x = sx + t * dx;
        if (x1 <= x && x <= x2)
          times.add(t);
      }

      Collections.sort(times);

      for (int j = 0; j < times.size() - 1;) {
        if (Math.abs(times.get(j) - times.get(j + 1)) < EPS)
          times.remove(j + 1);
        else
          j++;
      }

      if (times.size() == 2) {
        for (int j = 0; j < times.size(); j++) {
          if (j == 0)
            pq.offer(new Event(times.get(j), i));
          else
            pq.offer(new Event(times.get(j) + EPS, -i));
        }
      }
    }

    int time = 0;
    for (int i = 0; i < M; i++) {
      int a = readInt();
      int b = readInt();
      int c = readInt();

      if (a == 1) {
        time += b;
        while (!pq.isEmpty() && pq.peek().time <= time) {
          Event e = pq.poll();
          update(1, 1, N, e.ship);
        }
        updateDamage(c);
      } else {
        if (b <= c)
          out.println(query(1, 1, N, b, c));
        else
          out.println(0);
      }
    }

    out.close();
  }

  static long query (int n, int l, int r, int ql, int qr) {
    if (l == ql && r == qr)
      return damage[n];

    pushDown(n);

    int mid = (l + r) >> 1;

    if (qr <= mid)
      return query(n << 1, l, mid, ql, qr);
    else if (ql > mid)
      return query(n << 1 | 1, mid + 1, r, ql, qr);
    return query(n << 1, l, mid, ql, mid) + query(n << 1 | 1, mid + 1, r, mid + 1, qr);
  }

  static void updateDamage (int c) {
    lazy[1] += c;
    damage[1] += c * ships[1];
  }

  static void update (int n, int l, int r, int x) {
    if (l == Math.abs(x) && Math.abs(x) == r) {
      if (x < 0)
        ships[n] = 0;
      else
        ships[n] = 1;
      return;
    }

    pushDown(n);

    int mid = (l + r) >> 1;

    if (Math.abs(x) <= mid)
      update(n << 1, l, mid, x);
    else
      update(n << 1 | 1, mid + 1, r, x);

    pushUp(n);
  }

  static void pushUp (int n) {
    // damage[n] = damage[n << 1] + damage[n << 1 | 1];
    ships[n] = ships[n << 1] + ships[n << 1 | 1];
  }

  static void pushDown (int n) {
    if (lazy[n] > 0) {
      lazy[n << 1] += lazy[n];
      lazy[n << 1 | 1] += lazy[n];
      damage[n << 1] += lazy[n] * ships[n << 1];
      damage[n << 1 | 1] += lazy[n] * ships[n << 1 | 1];
      lazy[n] = 0;
    }
  }

  static class Event implements Comparable<Event> {
    Double time;
    int ship;

    Event (double time, int ship) {
      this.time = time;
      this.ship = ship;
    }

    @Override
    public int compareTo (Event e) {
      return time.compareTo(e.time);
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
