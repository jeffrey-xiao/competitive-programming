package contest.yandex_warm_up_2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.StringTokenizer;

public class C {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // pr = new PrintWriter(new FileWriter("out.txt"));

    double x1 = readDouble();
    double y1 = readDouble();
    double x2 = readDouble();
    double y2 = readDouble();
    if ((y1 > 0 && y2 > 0) || (y1 < 0 && y2 < 0))
      y2 = -y2;
    BigDecimal d1 = new BigDecimal(x1 - x2);
    BigDecimal d2 = new BigDecimal(y1 - y2);
    BigDecimal ans = d1.multiply(d1).add(d2.multiply(d2));
    ans = ans.setScale(20, RoundingMode.CEILING);
    pr.println(ans);
    pr.close();
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
