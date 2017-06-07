package contest.ecoo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ECOO_2002_Serial_Number_Dates {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;
  static int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

  public static void main (String[] args) throws IOException {
    for (int t = 5; t > 0; t--) {
      Date d = new Date();
      double input = readDouble();
      int n = (int)input;
      double frac = input - n;
      for (int x = 1904;; x++) {
        int num = x % 4 == 0 ? 366 : 365;
        if (n - num < 0)
          break;
        n -= num;
        d.year++;
      }
      d.year += 1904;
      int currMonth = 0;
      while (n >= monthDays[currMonth] + (currMonth == 1 && d.year % 4 == 0 ? 1 : 0)) {
        n -= monthDays[currMonth] + (currMonth == 1 && d.year % 4 == 0 ? 1 : 0);
        currMonth++;
      }
      d.month = currMonth + 1;
      d.day = n + 1;

      int min = (int)(frac * 1440);
      int seconds = (int)Math.ceil(60 * (frac * 1440 - (int)(frac * 1440)));
      d.hour = min / 60;
      min %= 60;
      d.minute = min;
      d.second = seconds;
      System.out.printf("year = %d month = %d day = %d\n", d.year, d.month, d.day);
      System.out.printf("time is: %2d:%02d:%02d\n\n", d.hour, d.minute, d.second);
    }
  }

  static class Date {
    int day, month, year, hour, minute, second;
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
