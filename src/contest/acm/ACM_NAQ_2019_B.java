import java.io.*;
import java.util.*;

public class B {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    long N = readLong();
    long M = readLong();

    long gcd = gcd(N, M);
    if (N == M) {
      out.println(N);
    } else if (N / gcd % 2 == 1 && M / gcd % 2 == 1) {
      out.println(gcd);
    } else {
      out.println(0);
    }

    out.close();
  }

  static long gcd(long a, long b) {
    return b == 0 ? a : gcd(b, a % b);
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
