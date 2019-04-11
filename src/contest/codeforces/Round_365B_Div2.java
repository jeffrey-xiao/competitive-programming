package contest.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Round_365B_Div2 {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, K;
  static boolean[] isCapital;
  static int[] beauty;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    K = readInt();

    isCapital = new boolean[N];
    beauty = new int[N];

    long sum = 0;
    for (int i = 0; i < N; i++)
      sum += beauty[i] = readInt();

    for (int i = 0; i < K; i++)
      isCapital[readInt() - 1] = true;

    long ans = 0;
    for (int i = 0; i < N; i++) {
      if (isCapital[i]) {
        long curr = (sum - beauty[i]);
        if (!isCapital[(i - 1 + N) % N])
          curr -= beauty[(i - 1 + N) % N];
        ans += curr * beauty[i];
        sum -= beauty[i];
      } else
        ans += beauty[i] * beauty[(i + 1) % N];
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
