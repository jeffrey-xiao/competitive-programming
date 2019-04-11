package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class VMSS_Ms_Theorem {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    double x = 0;
    double y = 0;
    for (int i = 0; i < n; i++) {
      int r = readInt();
      double degrees = readInt();
      y += Math.sin(Math.toRadians(degrees)) * r;
      x += Math.cos(Math.toRadians(degrees)) * r;
    }
    int degrees = (int)Math.round(Math.toDegrees(Math.atan2(y, x)));
    if (degrees < 0)
      degrees += 360;
    System.out.println((int)Math.round(Math.sqrt(x * x + y * y)) + " " + degrees);
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
