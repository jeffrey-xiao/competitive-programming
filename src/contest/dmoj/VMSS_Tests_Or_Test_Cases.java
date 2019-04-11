package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class VMSS_Tests_Or_Test_Cases {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int n, l;
  static ArrayList<ArrayList<Character>> c = new ArrayList<ArrayList<Character>>();
  static ArrayList<String> res = new ArrayList<String>();

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    l = readInt();

    for (int i = 0; i < n; i++)
      c.add(new ArrayList<Character>());

    for (int i = 0; i < n; i++) {
      int len = readInt();
      for (int j = 0; j < len; j++)
        c.get(i).add(readCharacter());
    }

    solve(0, "");
    Collections.sort(res);

    for (String s : res)
      if (s.length() <= l && s.length() >= 0)
        out.println(s);
    out.close();
  }

  static void solve(int i, String curr) {
    if (i == n) {
      res.add(curr);
      return;
    }
    if (curr.length() > l)
      return;
    for (char ch : c.get(i))
      solve(i + 1, curr + ch);
    if (i != 0)
      solve(i + 1, curr);
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
