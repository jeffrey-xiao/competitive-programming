package contest.usaco_other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/*
ID: jeffrey40
LANG: JAVA
TASK: friday
 */
import java.util.StringTokenizer;

public class friday {
  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new FileReader("friday.in"));
    pr = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
    int year = 1900;
    int month = 1;
    int day = 0;
    int n = readInt();
    int[] freq = new int[7];
    while (year != 1900 + n - 1 || month != 13) {
      day %= 7;
      freq[day]++;

      if (month > 12) {
        year++;
        month = 1;
      }

      if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
        day += 31;
      else if (month == 4 || month == 6 || month == 9 || month == 11)
        day += 30;
      else if (month == 2 && year % 4 == 0 && (year % 100 != 0 || year % 400 == 0))
        day += 29;
      else
        day += 28;
      month++;
    }
    for (int x = 0; x < 7; x++) {
      pr.print(freq[x] + (x == 6 ? "" : " "));
    }
    pr.println();
    pr.close();
    System.exit(0);
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
