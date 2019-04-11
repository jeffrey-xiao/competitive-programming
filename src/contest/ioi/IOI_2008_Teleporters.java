package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class IOI_2008_Teleporters {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int n, m;
  static int[] to;
  static int[] start, end;
  static int[] sorted;
  static boolean[] v;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    m = readInt();

    sorted = new int[2 * n];
    start = new int[n];
    end = new int[n];
    to = new int[2 * n + 1];
    v = new boolean[2 * n + 1];

    for (int i = 0; i < n; i++) {
      start[i] = readInt();
      end[i] = readInt();
      sorted[i * 2] = start[i];
      sorted[i * 2 + 1] = end[i];
    }
    Arrays.sort(sorted);
    for (int i = 0; i < n; i++) {
      to[toIndex(start[i])] = toIndex(end[i]) + 1;
      to[toIndex(end[i])] = toIndex(start[i]) + 1;
    }
    to[2 * n] = -1;
    PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
    int ans = bfs(0) - 1;
    for (int i = 0; i <= 2 * n; i++)
      if (!v[i])
        pq.offer(bfs(i));
    for (int i = 0; i < m; i++) {
      if (!pq.isEmpty()) {
        ans += 2 + pq.poll();
      } else {
        ans += (m - i) / 2 * 2 * 2 + (m - i) % 2;
        break;
      }
    }
    out.println(ans);
    out.close();
  }

  static int toIndex(int i) {
    int lo = 0;
    int hi = 2 * n - 1;
    while (lo <= hi) {
      int mid = (lo + hi) >> 1;
      if (sorted[mid] < i) {
        lo = mid + 1;
      } else if (sorted[mid] > i) {
        hi = mid - 1;
      } else {
        return mid;
      }
    }
    return -1;
  }

  static int bfs(int i) {
    int cnt = 0;
    v[i] = true;
    while (to[i] != -1 && !v[to[i]]) {
      cnt++;
      i = to[i];
      v[i] = true;
    }
    return cnt + 1;
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
