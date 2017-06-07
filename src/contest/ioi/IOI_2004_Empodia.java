package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class IOI_2004_Empodia {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N;
  static int[] hi, lo, val;
  static ArrayList<ArrayList<Integer>> poss;
  static TreeSet<Interval> intervals;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();

    val = new int[N];
    hi = new int[N];
    lo = new int[N];
    poss = new ArrayList<ArrayList<Integer>>();
    intervals = new TreeSet<Interval>();

    for (int i = 0; i < N; i++)
      val[i] = readInt();

    for (int i = 0; i < 2 * N; i++)
      poss.add(new ArrayList<Integer>());

    Stack<State> s = new Stack<State>();

    // processing upper bound (first number less than the current number)
    for (int i = N - 1; i >= 0; i--) {
      while (!s.isEmpty() && val[i] < s.peek().val)
        s.pop();
      if (s.isEmpty())
        hi[val[i]] = N;
      else
        hi[val[i]] = s.peek().index;
      s.push(new State(val[i], i));
    }

    s.clear();
    //processing lower bound (last number greater than the current number)
    for (int i = 0; i < N; i++) {
      while (!s.isEmpty() && val[i] > s.peek().val)
        s.pop();
      if (s.empty())
        lo[val[i]] = -1;
      else
        lo[val[i]] = s.peek().index;
      s.push(new State(val[i], i));
    }

    for (int i = 0; i < N; i++) {
      int diff = val[i] - i + N - 1;
      poss.get(diff).add(i);
    }

    for (int i = 0; i < 2 * N; i++) {
      sweep(poss.get(i));
    }

    out.println(intervals.size());
    for (Interval i : intervals)
      out.printf("%d %d\n", i.l + 1, i.r + 1);

    out.close();
  }

  static void sweep (ArrayList<Integer> indexes) {
    PriorityQueue<Event> pq = new PriorityQueue<Event>();
    for (int i = 0; i < indexes.size(); i++) {
      pq.offer(new Event(lo[val[indexes.get(i)]] + 1, 1, indexes.get(i)));
      pq.offer(new Event(hi[val[indexes.get(i)]], -1, indexes.get(i)));
    }

    TreeSet<Integer> active = new TreeSet<Integer>();
    while (!pq.isEmpty()) {
      Event curr = pq.poll();
      if (curr.type == 1)
        active.add(curr.index);
      else if (curr.type == -1) {
        active.remove(curr.index);
        Integer lower = active.lower(curr.index);
        if (lower != null && lower > lo[val[curr.index]]) {
          Interval add = new Interval(lower, curr.index);
          Interval prev = intervals.floor(add);
          Interval next = intervals.ceiling(add);
          boolean intersectPrev = true;
          boolean intersectNext = true;

          if (prev != null) {
            if (Math.max(add.l, prev.l) < Math.min(add.r, prev.r)) {
              if (add.r - add.l <= prev.r - prev.l) {
                intervals.remove(prev);
                intersectPrev = false;
              }
            } else {
              intersectPrev = false;
            }
          } else {
            intersectPrev = false;
          }

          if (next != null) {
            if (Math.max(add.l, next.l) < Math.min(add.r, next.r)) {
              if (add.r - add.l <= next.r - next.l) {
                intervals.remove(next);
                intersectNext = false;
              }
            } else {
              intersectNext = false;
            }
          } else {
            intersectNext = false;
          }

          if (!intersectPrev && !intersectNext)
            intervals.add(add);
        }
      }
    }
  }

  static class State {
    int val, index;

    State (int val, int index) {
      this.val = val;
      this.index = index;
    }
  }

  static class Event implements Comparable<Event> {
    int x, index, type;

    Event (int x, int type, int index) {
      this.x = x;
      this.type = type;
      this.index = index;
    }

    @Override
    public int compareTo (Event e) {
      if (x == e.x && type == e.type)
        return e.index - index;
      if (x == e.x)
        return type - e.type;
      return x - e.x;
    }
  }

  static class Interval implements Comparable<Interval> {
    int l, r;

    Interval (int l, int r) {
      this.l = l;
      this.r = r;
    }

    @Override
    public int compareTo (Interval i) {
      return l - i.l;
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
