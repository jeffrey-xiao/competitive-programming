import java.io.*;
import java.util.*;

public class J {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    int N = readInt();
    double[] a = new double[N];
    for (int i = 0; i < N; i++) {
      a[i] = readDouble();
    }

    double max = 0;
    double sum = 0;
    for (int i = 0; i < N; i++) {
      sum += a[i];
      max = Math.max(max, sum / (i + 1));
    }

    sum = 0;
    for (int i = N - 1; i >= 0; i--) {
      sum += a[i];
      max = Math.max(max, sum / (N - i));
    }

    out.println(max);
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
