package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CCC_2016_S2 {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int T = readInt();
    int N = readInt();

    int[] A = new int[N];
    int[] B = new int[N];

    for (int i = 0; i < N; i++)
      A[i] = readInt();

    for (int i = 0; i < N; i++)
      B[i] = readInt();

    int ans = 0;

    Arrays.sort(A);
    Arrays.sort(B);

    if (T == 1) {
      for (int i = 0; i < N; i++) {
        ans += Math.max(A[i], B[i]);
      }
    } else {
      for (int i = 0; i < N; i++) {
        ans += Math.max(A[i], B[N - i - 1]);
      }
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
