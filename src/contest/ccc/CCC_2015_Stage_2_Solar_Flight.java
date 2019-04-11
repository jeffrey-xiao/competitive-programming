package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CCC_2015_Stage_2_Solar_Flight {

  static final double EPS = 0.000001;
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;
  static int x, k, n, q;
  static int[] a, b, c;
  static ArrayList<LinkedList<Collision>> max = new ArrayList<LinkedList<Collision>>();
  static PriorityQueue<Collision> col = new PriorityQueue<Collision>();
  static long[] curr;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    x = readInt();
    k = readInt();
    n = readInt();
    q = readInt();
    a = new int[n];
    b = new int[n];
    c = new int[n];
    curr = new long[n];
    for (int i = 0; i < n; i++) {
      max.add(new LinkedList<Collision>());
      max.get(i).add(new Collision(0, 0));
      a[i] = readInt();
      b[i] = readInt();
      c[i] = readInt();
    }

    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        if (a[i] > a[j]) {
          col.add(new Collision(0.0, j, i));
        } else if (a[j] > a[i]) {
          col.add(new Collision(0.0, i, j));
        }
        double s1 = (double)(b[i] - a[i]) / x;
        double s2 = (double)(b[j] - a[j]) / x;
        double cx = (double)(a[j] - a[i]) / (s1 - s2);
        if (0 <= cx && cx <= x) {
          if (b[i] > b[j])
            col.offer(new Collision(cx, j, i));
          else
            col.offer(new Collision(cx, i, j));
        }
      }
    }
    ArrayList<Query> queries = new ArrayList<Query>();
    for (int i = 0; i < q; i++)
      queries.add(new Query(i, readInt() - 1, readInt()));
    Collections.sort(queries);
    long[] ans = new long[q];
    for (Query query : queries) {
      while (!col.isEmpty() && col.peek().pos <= query.position + k) {
        Collision next = col.poll();
        curr[next.inc] += c[next.dec];
        max.get(next.inc).getLast().end = next.pos;
        add(new Collision(next.pos, curr[next.inc]), next.inc);
        if (next.pos != 0) {
          curr[next.dec] -= c[next.inc];
          max.get(next.dec).getLast().end = next.pos;
          add(new Collision(next.pos, curr[next.dec]), next.dec);
        }
      }
      while (max.get(query.plane).size() > 1 && max.get(query.plane).get(0).end <= query.position)
        max.get(query.plane).pollFirst();
      ans[query.index] = max.get(query.plane).getFirst().val;
    }
    for (int i = 0; i < q; i++)
      out.println(ans[i]);

    out.close();
  }

  static void add(Collision c, int plane) {
    while (!max.get(plane).isEmpty() && (max.get(plane).getLast().val <= c.val || Math.abs(max.get(plane).getLast().pos - c.pos) < EPS))
      max.get(plane).pollLast();
    max.get(plane).addLast(c);
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

  static class Query implements Comparable<Query> {
    int index, plane, position;

    Query(int index, int plane, int position) {
      this.index = index;
      this.plane = plane;
      this.position = position;
    }

    @Override
    public int compareTo(Query q) {
      return position - q.position;
    }
  }

  static class Collision implements Comparable<Collision> {
    Double pos, end;
    int inc, dec;
    long val;

    Collision(double pos, long val) {
      this.pos = pos;
      this.val = val;
    }

    Collision(double pos, int inc, int dec) {
      this.pos = pos;
      this.inc = inc;
      this.dec = dec;
    }

    @Override
    public int compareTo(Collision c) {
      return pos.compareTo(c.pos);
    }
  }
}
