package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class COCI_2006_TENKICI {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    Pos[] r = new Pos[n];
    Pos[] c = new Pos[n];
    for (int x = 0; x < n; x++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      r[x] = new Pos(x + 1, a);
      c[x] = new Pos(x + 1, b);
    }
    Arrays.sort(r);
    Arrays.sort(c);
    ArrayList<Command> com = new ArrayList<Command>();
    for (int x = 0; x < n; x++) {
      int diff = Math.abs(x - r[x].c);
      boolean first = x > r[x].c;
      for (int y = 0; y < diff; y++) {
        com.add(new Command(r[x].index, first ? 'D' : 'U'));
      }
    }
    for (int x = 0; x < n; x++) {
      int diff = Math.abs(x - c[x].c);
      boolean first = x > c[x].c;
      for (int y = 0; y < diff; y++) {
        com.add(new Command(c[x].index, first ? 'R' : 'L'));
      }
    }
    System.out.println(com.size());
    for (Command ch : com) {
      System.out.println(ch.index + " " + ch.c);
    }
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

  static class Command {
    int index;
    char c;

    Command(int index, char c) {
      this.index = index;
      this.c = c;
    }
  }

  static class Pos implements Comparable<Pos> {
    int index, c;

    Pos(int index, int c) {
      this.index = index;
      this.c = c;
    }

    @Override
    public int compareTo(Pos o) {
      return c - o.c;
    }
  }
}
