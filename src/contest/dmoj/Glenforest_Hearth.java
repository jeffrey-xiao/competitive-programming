package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Glenforest_Hearth {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  static int n, t;
  static Card[] c;

  public static void main (String[] args) throws IOException {
    n = readInt();
    t = readInt();
    c = new Card[n];
    for (int i = 0; i < n; i++)
      c[i] = new Card(next(), readInt());
    Arrays.sort(c);
    compute(0, t, 0, "");
  }

  private static void compute (int i, int t, int count, String curr) {
    if (count == 3) {
      System.out.println(curr);
      return;
    }
    if (i == n)
      return;
    if (t - c[i].mana >= 0) {
      compute(i + 1, t - c[i].mana, count + 1, curr + c[i].name + " ");
    }
    compute(i + 1, t, count, curr);
  }

  static class Card implements Comparable<Card> {
    String name;
    int mana;

    Card (String name, int mana) {
      this.name = name;
      this.mana = mana;
    }

    @Override
    public int compareTo (Card o) {
      for (int i = 0; i < Math.min(o.name.length(), name.length()); i++) {
        if (o.name.charAt(i) != name.charAt(i))
          return name.charAt(i) - o.name.charAt(i);
      }
      return name.length() - o.name.length();
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
