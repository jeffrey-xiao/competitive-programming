package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class DMOPC_2015_Pizza_Bag {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N;
  static int K;
  static long[] prefix;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    K = readInt();

    prefix = new long[2 * N + 1];

    for (int i = 1; i <= N; i++)
      prefix[i] = prefix[i + N] = readLong();

    for (int i = 1; i <= 2 * N; i++)
      prefix[i] += prefix[i - 1];

    Deque<State> mono = new ArrayDeque<State>();
    mono.addFirst(new State(0, 0));
    long ans = 0;

    for (int i = 1; i < 2 * N; i++) {
      State add = new State(i, prefix[i]);
      while (!mono.isEmpty() && mono.getLast().val >= prefix[i])
        mono.removeLast();

      mono.addLast(add);

      while (mono.getFirst().index < i - K)
        mono.removeFirst();

      ans = Math.max(ans, prefix[i] - mono.getFirst().val);
    }

    out.println(ans);
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

  static class State {
    int index;
    long val;

    State(int index, long val) {
      this.index = index;
      this.val = val;
    }
  }
}
