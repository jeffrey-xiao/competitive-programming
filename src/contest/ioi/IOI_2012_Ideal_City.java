package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class IOI_2012_Ideal_City {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int n;
  static HashSet<Point> p = new HashSet<Point>();
  static HashSet<Point> v = new HashSet<Point>();
  static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
  static ArrayList<Edge> e = new ArrayList<Edge>();
  static int[] size, subtreeSize;
  static int cnt = 0;

  static int[] movex = {-1, 1, 0, 0};
  static int[] movey = {0, 0, -1, 1};

  static Queue<Point> q = new ArrayDeque<Point>();

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    long n = readInt();
    int x = 0, y = 0;
    for (int i = 0; i < n; i++)
      p.add(new Point(x = readInt(), y = readInt()));
    long ans = 0;
    for (int i = 0; i <= 1; i++) {
      init();
      build(new Point(x, y), i == 0);
      compute(0);
      for (Edge edge : e) {
        long child = Math.min(subtreeSize[edge.from], subtreeSize[edge.to]);
        ans = (ans + (n - child) * child) % 1000000000;
      }
    }
    out.println(ans);
    out.close();
  }

  static void compute(int i) {
    boolean[] vis = new boolean[1000001];
    Queue<Integer> q = new ArrayDeque<Integer>();
    Stack<Integer> rev = new Stack<Integer>();
    q.offer(i);
    rev.push(i);
    vis[i] = true;
    while (!q.isEmpty()) {
      Integer curr = q.poll();
      subtreeSize[curr] = size[curr];
      for (int next : adj.get(curr))
        if (!vis[next]) {
          vis[next] = true;
          q.offer(next);
          rev.push(next);
        }

    }
    while (!rev.isEmpty()) {
      Integer curr = rev.pop();
      vis[curr] = false;
      for (int next : adj.get(curr))
        if (!vis[next])
          subtreeSize[curr] += subtreeSize[next];
    }
  }

  static void init() {
    adj.clear();
    for (int i = 0; i <= 100000; i++)
      adj.add(new ArrayList<Integer>());
    size = new int[100001];
    subtreeSize = new int[100001];
    v.clear();
    e.clear();
    cnt = 0;
  }

  static void createNewComponent(int currComponent) {
    adj.get(currComponent).add(cnt);
    adj.get(cnt).add(currComponent);
    e.add(new Edge(currComponent, cnt));
  }

  static void build(Point point, boolean isHorizontal) {
    q.offer(point);
    while (!q.isEmpty()) {
      Point next = q.poll();
      if (v.contains(next))
        continue;
      if (next.prev != -1)
        createNewComponent(next.prev);
      dfs(next, isHorizontal);
      cnt++;
    }
  }

  static void dfs(Point point, boolean isHorizontal) {
    v.add(point);
    Stack<Point> s = new Stack<Point>();
    s.push(point);
    while (!s.isEmpty()) {
      Point curr = s.pop();
      size[cnt]++;
      for (int i = 0; i < 4; i++) {
        Point next = new Point(curr.x + movex[i], curr.y + movey[i], cnt);
        if (p.contains(next) && !v.contains(next)) {
          if (isHorizontal) {
            if (i >= 2)
              q.offer(next);
            else {
              s.push(next);
              v.add(next);
            }
          } else {
            if (i <= 1)
              q.offer(next);
            else {
              s.push(next);
              v.add(next);
            }
          }
        }
      }
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

  static class Edge {
    int from, to;

    Edge(int from, int to) {
      this.from = from;
      this.to = to;
    }
  }

  static class Point {
    Integer x, y, prev;

    Point(Integer x, Integer y) {
      this(x, y, -1);
    }

    Point(Integer x, Integer y, Integer prev) {
      this.x = x;
      this.y = y;
      this.prev = prev;
    }

    @Override
    public int hashCode() {
      return x.hashCode() * 31 + y.hashCode();
    }

    public boolean equals(Object o) {
      if (o instanceof Point) {
        Point p = (Point) o;
        return p.x.compareTo(x) == 0 && p.y.compareTo(y) == 0;
      }
      return false;
    }
  }
}
