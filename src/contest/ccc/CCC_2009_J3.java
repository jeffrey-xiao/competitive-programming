package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2009_J3 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    String time = next();
    if (time.length() < 4)
      time = "0000" + time;
    int l = time.length();
    int hour = Integer.parseInt(time.substring(l - 4, l - 2));
    int minutes = Integer.parseInt(time.substring(l - 2, l));
    System.out.printf("%s%02d in Ottawa\n", hour == 0 ? "" : hour, minutes);
    hour = (hour + 21) % 24;
    System.out.printf("%s%02d in Victoria\n", hour == 0 ? "" : hour, minutes);
    hour = (hour + 1) % 24;
    System.out.printf("%s%02d in Edmonton\n", hour == 0 ? "" : hour, minutes);
    hour = (hour + 1) % 24;
    System.out.printf("%s%02d in Winnipeg\n", hour == 0 ? "" : hour, minutes);
    hour = (hour + 1) % 24;
    System.out.printf("%s%02d in Toronto\n", hour == 0 ? "" : hour, minutes);
    hour = (hour + 1) % 24;
    System.out.printf("%s%02d in Halifax\n", hour == 0 ? "" : hour, minutes);
    if (minutes + 30 >= 60)
      hour = (hour + 1) % 24;
    minutes = (minutes + 30) % 60;
    System.out.printf("%d%02d in St. John\'s\n", hour == 0 ? "" : hour, minutes);
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

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
