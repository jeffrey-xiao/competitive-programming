package contest.acm;

import java.io.*;
import java.util.*;

public class ACM_Waterloo_Local_2017_Winter_D {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int M, N, S;
  static long[] A, B;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    M = readInt();
    N = readInt();
    S = readInt();

    A = new long[N];
    B = new long[N];

    for (int i = 0; i < N; i++) {
      A[i] = readInt();
      B[i] = readInt();
    }

    int[] moves = new int[M];
    Arrays.fill(moves, 1 << 30);
    Queue<Integer> q = new ArrayDeque<Integer>();
    q.offer(S);
    moves[S] = 0;

    while (!q.isEmpty()) {
      int curr = q.poll();
      if (curr == 0) {
        out.println(moves[curr]);
        out.close();
        return;
      }
      for (int i = 0; i < N; i++) {
        int next = (int) ((curr * A[i] + B[i]) % M);
        if (moves[next] == 1 << 30) {
          q.offer(next);
          moves[next] = moves[curr] + 1;
        }
      }
    }

    out.println(-1);
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
}
