import java.io.*;
import java.util.*;

public class A {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T;
  static String N;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();

    for (int t = 1; t <= T; t++) {
      N = next();
      StringBuilder A = new StringBuilder(), B = new StringBuilder();
      for (int i = 0; i < N.length(); i++) {
        if (N.charAt(i) == '4') {
          A.append((char) (N.charAt(i) - 1));
          B.append('1');
        } else {
          A.append(N.charAt(i));
          if (B.length() != 0) {
            B.append('0');
          }
        }
      }
      if (B.length() == 0) {
        B.append('0');
      }
      out.printf("Case #%d: %s %s%n", t, A, B);
    }

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
