package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CCC_2016_S5 {

  static final int LN = 62;
  static final long CONST = 1l << LN;
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;
  static int N;
  static long T;
  static int[] state;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    T = readLong();

    char[] in = readLine().toCharArray();
    state = new int[N];
    for (int i = 0; i < N; i++)
      state[i] = in[i] - '0';

    for (int i = LN; i >= 1; i--) {
      while (T >= 1l << (i - 1)) {
        state = getState(state, 1l << (i - 1));
        T -= 1l << (i - 1);
      }
    }
    for (int i = 0; i < N; i++)
      out.print(state[i]);
    out.println();
    out.close();
  }

  static int[] getState(int[] state, long k) {
    int[] ret = new int[N];
    for (int i = 0; i < N; i++) {
      ret[(int) (((i - k) % N + N) % N)] ^= state[i];
      ret[(int) ((i + k) % N)] ^= state[i];
    }
    return ret;
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
