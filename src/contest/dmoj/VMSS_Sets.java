package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class VMSS_Sets {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    ArrayList<TreeSet<Character>> l = new ArrayList<TreeSet<Character>>();
    boolean[] exist = new boolean[26];
    for (int i = 0; i < 26; i++)
      l.add(new TreeSet<Character>());
    ArrayList<Integer> first = new ArrayList<Integer>();
    ArrayList<Integer> second = new ArrayList<Integer>();
    for (int i = 0; i < n; i++) {
      char a = readCharacter();
      next();
      char b = readCharacter();
      exist[a - 'A'] = true;
      if ('A' <= b && b <= 'Z') {
        exist[b - 'A'] = true;
        first.add(a - 'A');
        second.add(b - 'A');
      } else
        l.get(a - 'A').add(b);
    }
    for (int j = 0; j < 10; j++)
      for (int i = 0; i < first.size(); i++)
        l.get(first.get(i)).addAll(l.get(second.get(i)));
    for (int i = 0; i < 26; i++) {
      if (exist[i]) {
        System.out.print((char)(i + 'A') + " = {");
        int k = 0;
        for (Character c : l.get(i)) {
          System.out.print(c);
          if (k != l.get(i).size() - 1)
            System.out.print(",");
          k++;
        }
        System.out.println("}");
      }
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
}
