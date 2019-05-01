import java.io.*;
import java.util.*;

public class GCJ_2019_Round_1B_A {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T, P, Q;
  static int ans, ansX, ansY;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    for (int t = 1; t <= T; t++) {
      P = readInt();
      Q = readInt();
      ans = 0;
      ansX = Integer.MAX_VALUE;
      ansY = Integer.MAX_VALUE;

      ArrayList<Event> hEvents = new ArrayList<Event>(), vEvents = new ArrayList<Event>();
      for (int i = 0; i < P; i++) {
        Event event = new Event(readInt(), readInt(), readCharacter());
        if (event.dir == 'N') {
          event.y++;
          vEvents.add(event);
        } else if (event.dir == 'S') {
          event.y--;
          vEvents.add(event);
        } else if (event.dir == 'E') {
          event.x++;
          hEvents.add(event);
        } else {
          event.x--;
          hEvents.add(event);
        }
      }
      hEvents.add(new Event(0, 0, 'E'));
      vEvents.add(new Event(0, 0, 'N'));

      Collections.sort(hEvents, (Event e1, Event e2) -> e1.x == e2.x ? e1.dir - e2.dir : e1.x - e2.x);
      Collections.sort(vEvents, (Event e1, Event e2) -> e1.y == e2.y ? e1.dir - e2.dir : e1.y - e2.y);

      int hCount, vCount;
      int maxHCount = 0, maxX = Integer.MAX_VALUE;
      int maxVCount = 0, maxY = Integer.MAX_VALUE;

      hCount = 0;
      for (Event h : hEvents) {
        if (h.dir == 'W') {
          hCount++;
        }
      }
      for (Event h : hEvents) {
        if (h.dir == 'W') {
          hCount--;
        } else {
          hCount++;
        }
        if (hCount > maxHCount || (hCount == maxHCount && h.x < maxX)) {
          maxHCount = hCount;
          maxX = h.x;
        }
      }

      vCount = 0;
      for (Event v : vEvents) {
        if (v.dir == 'S') {
          vCount++;
        }
      }
      for (Event v : vEvents) {
        if (v.dir == 'S') {
          vCount--;
        } else {
          vCount++;
        }
        if (vCount > maxVCount || (vCount == maxVCount && v.y < maxY)) {
          maxVCount = vCount;
          maxY = v.y;
        }
      }

      out.printf("Case #%d: %d %d%n", t, maxX, maxY);
    }

    out.close();
  }

  static void setAns(int curr, int currX, int currY) {
    if (curr > ans
        || (curr == ans && currX < ansX)
        || (curr == ans && currX == ansX && currY < ansY)) {
      ans = curr;
      ansX = currX;
      ansY = currY;
    }
  }

  static class Event implements Comparable<Event> {
    int x, y;
    char dir;

    Event(int x, int y, char dir) {
      this.x = x;
      this.y = y;
      this.dir = dir;
    }

    @Override
    public int compareTo(Event e) {
      return x - e.x;
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
