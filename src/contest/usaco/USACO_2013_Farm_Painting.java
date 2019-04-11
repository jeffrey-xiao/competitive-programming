package contest.usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class USACO_2013_Farm_Painting {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    ArrayList<Event> e = new ArrayList<Event>();
    TreeSet<HoriEvent> ts = new TreeSet<HoriEvent>();
    for (int x = 0; x < n; x++) {
      int x1 = readInt();
      int y1 = readInt();
      int x2 = readInt();
      int y2 = readInt();
      e.add(new Event(x1, y1, y2, true));
      e.add(new Event(x2, y1, y2, false));
    }
    int i = 0;
    int total = 0;
    Collections.sort(e);
    while (i < e.size()) {
      if (e.get(i).add) {
        HoriEvent lower = ts.lower(new HoriEvent(e.get(i).y1, false));
        if (lower == null || lower.add) {
          total++;
          ts.add(new HoriEvent(e.get(i).y1, true));
          ts.add(new HoriEvent(e.get(i).y2, false));
        }
      } else {
        ts.remove(new HoriEvent(e.get(i).y1, true));
        ts.remove(new HoriEvent(e.get(i).y2, false));
      }
      i++;
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

  static char readCharacter() throws IOException {
    return next().charAt(0);
  }

  static String readLine() throws IOException {
    return br.readLine().trim();
  }

  static class HoriEvent implements Comparable<HoriEvent> {
    int y;
    boolean add;

    HoriEvent(int y, boolean add) {
      this.y = y;
      this.add = add;
    }

    @Override
    public int compareTo(HoriEvent o) {
      return -y + o.y;
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof HoriEvent) {
        HoriEvent e = (HoriEvent)o;
        return y == e.y && add == e.add;
      }
      return false;
    }
  }

  static class Event implements Comparable<Event> {
    int x, y1, y2, i;
    boolean add;

    Event(int x, int y1, int y2, boolean add) {
      this.x = x;
      this.y1 = y1;
      this.y2 = y2;
      this.add = add;
    }

    @Override
    public int compareTo(Event o) {
      if (x == o.x)
        return y1 - o.y1;
      return x - o.x;
    }
  }
}
