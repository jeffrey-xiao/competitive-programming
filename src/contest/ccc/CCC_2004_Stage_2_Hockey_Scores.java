package contest.ccc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CCC_2004_Stage_2_Hockey_Scores {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    for (int t = readInt(); t > 0; t--) {
      int n = readInt();
      Score[] s = new Score[n];
      for (int x = 0; x < n; x++) {
        String[] next = readLine().split("-");
        int a = Integer.parseInt(next[0]);
        int b = Integer.parseInt(next[1]);
        if (a < b) {
          int temp = a;
          a = b;
          b = temp;
        }
        s[x] = new Score(a, b);
      }
      Arrays.sort(s);
      ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
      for (int x = 0; x < n; x++) {
        int y = 0;
        for (; y < list.size(); y++) {
          if (s[list.get(y).get(list.get(y).size() - 1)].y <= s[x].y)
            break;
        }
        if (y == list.size())
          list.add(new ArrayList<Integer>());
        list.get(y).add(x);
      }
      System.out.println(list.size());
      for (ArrayList<Integer> i : list) {
        for (Integer a : i)
          System.out.print(s[a].x + "-" + s[a].y + " ");
        System.out.println();
      }
    }
  }

  static class Score implements Comparable<Score> {
    int x, y;

    Score (int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public int compareTo (Score o) {
      if (x - o.x == 0)
        return y - o.y;
      return x - o.x;
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
