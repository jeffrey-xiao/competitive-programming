package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Postfix_Notation {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //pr = new PrintWriter(new FileWriter("out.txt"));

    StringTokenizer st = new StringTokenizer(readLine().trim());
    Stack<Double> s = new Stack<Double>();
    while (st.hasMoreTokens()) {
      String next = st.nextToken();
      if (next.equals("+"))
        s.push(s.pop() + s.pop());
      else if (next.equals("-"))
        s.push(-s.pop() + s.pop());
      else if (next.equals("*"))
        s.push(s.pop() * s.pop());
      else if (next.equals("/"))
        s.push(1 / s.pop() * s.pop());
      else if (next.equals("^")) {
        double a = s.pop();
        double b = s.pop();
        s.push(Math.pow(b, a));
      } else if (next.equals("%")) {
        double a = s.pop();
        double b = s.pop();
        s.push(b % a);
      } else
        s.push(Double.parseDouble(next));
    }
    System.out.printf("%.1f", s.pop());
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
