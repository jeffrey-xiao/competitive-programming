package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CCC_1999_P2 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    readInt();
    while (br.ready()) {
      String in = br.readLine();

      Pattern p;
      Matcher m;
      int index;

      p = Pattern.compile("\\b\\d\\d/\\d\\d/\\d\\d\\b");
      m = p.matcher(in);
      index = 0;

      while (m.find(index)) {
        String org = m.group();
        String[] s = org.split("/");
        int day = Integer.parseInt(s[0]);
        int month = Integer.parseInt(s[1]);
        int year = Integer.parseInt(s[2]);
        if (year <= 24)
          year += 2000;
        else
          year += 1900;
        if (isValidDate(day, month, year)) {
          in = in.replace(org, String.format("%02d/%02d/%02d", day, month, year));
          m = p.matcher(in);
          index = 0;
        } else {
          index = m.start() + 1;
        }
      }

      p = Pattern.compile("\\b\\d\\d.\\d\\d.\\d\\d\\b");
      m = p.matcher(in);
      index = 0;

      while (m.find(index)) {
        String org = m.group();
        String[] s = org.split("\\.");
        int year = Integer.parseInt(s[0]);
        int month = Integer.parseInt(s[1]);
        int day = Integer.parseInt(s[2]);
        if (year <= 24)
          year += 2000;
        else
          year += 1900;
        if (isValidDate(day, month, year)) {
          in = in.replace(org, String.format("%02d.%02d.%02d", year, month, day));
          m = p.matcher(in);
          index = 0;
        } else {
          index = m.start() + 1;
        }
      }

      p = Pattern.compile("\\b\\w+\\s\\d\\d,\\s\\d\\d\\b");
      m = p.matcher(in);
      index = 0;

      while (m.find(index)) {
        String org = m.group();
        String[] s = org.split(" ");
        String monthStr = s[0];
        int day = Integer.parseInt(s[1].substring(0, 2));
        int year = Integer.parseInt(s[2]);
        if (getMonth(monthStr) == -1) {
          index = m.start() + 1;
          continue;
        }
        int month = getMonth(monthStr);
        if (year <= 24)
          year += 2000;
        else
          year += 1900;
        if (isValidDate(day, month, year)) {
          in = in.replace(org, String.format("%s %02d, %02d", toMonth(month), day, year));
          m = p.matcher(in);
          index = 0;
        } else {
          index = m.start() + 1;
        }
      }

      System.out.println(in);
    }
  }

  static String toMonth(int monthIndex) {
    String[] validYears = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    return validYears[monthIndex - 1];
  }

  static int getMonth(String year) {
    String[] validYears = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    for (int i = 0; i < 12; i++)
      if (year.equals(validYears[i]))
        return i + 1;
    return -1;
  }

  static boolean isLeapYear(int year) {
    return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
  }

  static boolean isValidDate(int day, int month, int year) {
    int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31, 31};
    if (isLeapYear(year))
      days[1]++;
    if (month < 1 || month > 12)
      return false;
    if (day < 1 || day > days[month - 1])
      return false;
    return true;
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

  static String readLine() throws IOException {
    return br.readLine().trim();
  }
}
