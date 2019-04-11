package contest.woburn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Woburn_Challenge_2016_EHC {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, M, R;
  static int[] pos;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    M = readInt();
    R = readInt();

    pos = new int[N + 1];

    for (int i = 0; i < N; i++)
      pos[i] = readInt();
    pos[N] = M + R;
    Arrays.sort(pos);
    int prev = R, ans = 0;
    for (int i = 0; i <= N; i++) {
      ans += Math.max(0, (pos[i] - R - prev + 2 * R - 1) / (2 * R));
      prev = pos[i] + R;
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
}
