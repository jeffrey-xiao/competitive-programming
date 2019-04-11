package contest.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Round_142D {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // pr = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    Queue<Integer> q = new ArrayDeque<Integer>();
    for (int i = 0; i < n; i++)
      q.offer(readInt());
    ArrayDeque<Integer> res = new ArrayDeque<Integer>();
    int prev = q.poll();
    int curr = 0;
    int cnt = 0;
    while (!q.isEmpty()) {
      if (curr < prev) {
        if (curr != 0)
          cnt++;
        curr += q.poll();
      } else {
        res.push(prev);
        prev = curr;
        curr = q.poll();
      }
    }
    res.addLast(prev);
    while (!res.isEmpty() && res.peekLast() > curr) {
      curr += res.pollLast();
      cnt++;
    }
    System.out.println(cnt);
    pr.close();
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
