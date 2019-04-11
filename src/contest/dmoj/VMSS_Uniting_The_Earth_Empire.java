package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class VMSS_Uniting_The_Earth_Empire {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    Stack<Hill> prev = new Stack<Hill>();
    long total = 0;
    for (int x = 0; x < n; x++) {
      long nextH = readLong();
      Hill next = new Hill(nextH, 1);
      while (!prev.isEmpty()) {
        if (prev.peek().height < nextH) {
          total += prev.peek().num;
          prev.pop();
        } else if (prev.peek().height == nextH) {
          total += prev.peek().num;
          next.num += prev.peek().num;
          prev.pop();
        } else {
          break;
        }
      }
      if (!prev.isEmpty())
        total++;
      prev.push(next);
    }
    System.out.println(total);
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

  static class Hill {
    long height;
    int num;

    Hill(long height, int num) {
      this.height = height;
      this.num = num;
    }
  }
}
