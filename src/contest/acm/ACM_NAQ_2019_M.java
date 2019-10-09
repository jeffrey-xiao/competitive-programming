import java.io.*;
import java.util.*;

public class M {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    for (int i = 0; i < n; i++) {
      double w = readInt();
      double g = readInt();
      double h = readInt();
      double r = readInt();

      double minHeight = Math.max(g, h) - Math.min(g, h);
      double min = Math.sqrt(minHeight * minHeight + w * w);

      double h1 = g - r;
      double h2 = h - r;
      if (h1 + h2 == 0) {
        out.println(min + " " + min);
      } else {
        double pivot = h1 / (h1 + h2) * w;
        double max = Math.sqrt(h1 * h1 + pivot * pivot) + Math.sqrt(h2 * h2 + (w - pivot) * (w - pivot));
        out.println(min + " " + max);
      }
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
