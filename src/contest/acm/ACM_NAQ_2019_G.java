import java.io.*;
import java.util.*;

public class G {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N;
  static ArrayList<Double> p;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    p = new ArrayList<>();

    for (int i = 0; i < N; i++) {
      p.add(readInt() / 100.0);
    }

    Collections.sort(p);
    Collections.reverse(p);

    double ans = 0;
    for (int i = 1; i <= N; i++) {
      ans = Math.max(ans, getExpectedValue(i));
    }

    out.println(ans);
    out.close();
  }

  static double getExpectedValue(int n) {
    double[] dp = new double[n + 1];
    dp[0] = 1.0;
    for (int i = 0; i < n; i++) {
      for (int j = n; j >= 0; j--) {
        if (j > 0) {
          dp[j] = dp[j - 1] * p.get(i) + dp[j] * (1 - p.get(i));
        } else {
          dp[j] = dp[j] * (1 - p.get(i));
        }
      }
    }
    double value = 0;
    double p = 0;
    for (int i = 1; i <= n; i++) {
      p += dp[i];
      value += dp[i] * Math.pow(i, 1.0 * i / n);
    }
    p += dp[0];
    return value;
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
