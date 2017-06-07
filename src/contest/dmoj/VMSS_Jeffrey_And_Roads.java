package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class VMSS_Jeffrey_And_Roads {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    Point s = new Point(readDouble(), readDouble());
    Point e = new Point(readDouble(), readDouble());

    int n = readInt();
    int counter = 0;
    for (int z = 0; z < n; z++) {
      double a = readDouble();
      double b = readDouble();
      double c = readDouble();

      if (a == 0) {
        double y = -c / b;
        if ((s.y >= y && e.y <= y) || (s.y <= y && e.y >= y))
          counter++;
        continue;
      } else if (b == 0) {
        double x = -c / a;
        if ((s.x >= x && e.y <= x) || (s.y <= x && e.y >= x))
          counter++;
      }

      double slope = -a / b;
      double cept = -c / b;

      double y1 = s.x * slope + cept;
      double y2 = e.x * slope + cept;
      if (s.y <= y1 && e.y >= y2 || s.y >= y1 && e.y <= y2)
        counter++;
    }
    out.println(counter);
    out.close();
  }

  static class Point {
    double x, y;

    Point (double x, double y) {
      this.x = x;
      this.y = y;
    }
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
