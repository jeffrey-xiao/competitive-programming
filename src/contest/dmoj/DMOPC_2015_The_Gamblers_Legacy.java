package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DMOPC_2015_The_Gamblers_Legacy {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    for (int i = 1; i <= N; i++) {
      int val = readInt();
      State s = getCycle(val);
      if (s.length == 1)
        out.printf("Equilibrium: Bob's investment becomes $%d after %d second(s)!\n", s.val, s.start);
      else
        out.printf("Instability: Loop of length %d encountered after %d second(s)!\n", s.length, s.start);
    }

    out.close();
  }

  static State getCycle(int x) {
    int tortoise = f(x), hare = f(f(x));
    while (tortoise != hare) {
      tortoise = f(tortoise);
      hare = f(f(hare));
    }
    int start = 0;
    tortoise = x;
    while (tortoise != hare) {
      tortoise = f(tortoise);
      hare = f(hare);
      start++;
    }
    int length = 1;
    hare = f(tortoise);
    while (tortoise != hare) {
      hare = f(hare);
      length++;
    }
    return new State(start, length, tortoise);
  }

  static int f(int x) {
    int p = (int) Math.log10(x) + 1;
    int ret = 0;
    while (x > 0) {
      ret += pow(x % 10, p);
      x /= 10;
    }
    return ret;
  }

  static int pow(int b, int p) {
    if (p == 0)
      return 1;
    if (p == 1)
      return b;
    return p % 2 == 0 ? pow(b * b, p / 2) : b * pow(b * b, p / 2);
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

  static class State {
    int start, length, val;

    State(int start, int length, int val) {
      this.start = start;
      this.length = length;
      this.val = val;
    }

    @Override
    public String toString() {
      return "Start: " + start + "; Length: " + length;
    }
  }
}
