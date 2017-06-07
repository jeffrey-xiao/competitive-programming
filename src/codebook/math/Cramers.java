package codebook.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Cramers {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int a1, b1, c1, d1;
    int a2, b2, c2, d2;
    int a3, b3, c3, d3;

    a1 = readInt();
    b1 = readInt();
    c1 = readInt();
    d1 = readInt();
    a2 = readInt();
    b2 = readInt();
    c2 = readInt();
    d2 = readInt();
    a3 = readInt();
    b3 = readInt();
    c3 = readInt();
    d3 = readInt();

    long D = det(a1, b1, c1, a2, b2, c2, a3, b3, c3);
    double DX = det(d1, b1, c1, d2, b2, c2, d3, b3, c3);
    double DY = det(a1, d1, c1, a2, d2, c2, a3, d3, c3);
    double DZ = det(a1, b1, d1, a2, b2, d2, a3, b3, d3);

    if (D == 0)
      out.println("Math Error");
    else
      out.println((DX / D) + " " + (DY / D) + " " + (DZ / D));

    out.close();
  }

  static long det (int a1, int b1, int c1, int a2, int b2, int c2, int a3, int b3, int c3) {
    return a1 * (b2 * c3 - b3 * c2) - b1 * (a2 * c3 - a3 * c2) + c1 * (a2 * b3 - a3 * b2);
  }

  static String next () throws IOException {
    while (st == null || !st.hasMoreTokens())
      st = new StringTokenizer(br.readLine().trim());
    return st.nextToken();
  }

  static long readLong () throws IOException {
    return Long.parseLong(next());
  }

  static int readInt () throws IOException {
    return Integer.parseInt(next());
  }

  static double readDouble () throws IOException {
    return Double.parseDouble(next());
  }

  static char readCharacter () throws IOException {
    return next().charAt(0);
  }

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
