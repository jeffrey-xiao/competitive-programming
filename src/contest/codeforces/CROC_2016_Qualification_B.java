package contest.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class CROC_2016_Qualification_B {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    int b = readInt();

    long currTime = 0;
    Queue<Query> q = new ArrayDeque<Query>();

    long[] ans = new long[n];

    for (int i = 0; i < n; i++) {
      long time = readInt();
      long len = readInt();
      while (!q.isEmpty() && Math.max(currTime, q.peek().start) <= time) {
        currTime = Math.max(currTime, q.peek().start) + q.peek().time;
        ans[q.peek().index] = currTime;
        q.poll();
      }

      if (q.size() < b)
        q.offer(new Query(time, len, i));
      else
        ans[i] = -1;
    }

    while (!q.isEmpty())
      ans[q.peek().index] = (currTime = Math.max(currTime, q.peek().start) + q.poll().time);

    for (int i = 0; i < n; i++)
      out.print(ans[i] + " ");
    out.println();
    out.close();
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

  static class Query {
    long start, time;
    int index;

    Query(long start, long time, int index) {
      this.start = start;
      this.time = time;
      this.index = index;
    }
  }
}
