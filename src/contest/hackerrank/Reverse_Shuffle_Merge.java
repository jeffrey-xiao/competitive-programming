package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Reverse_Shuffle_Merge {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //pr = new PrintWriter(new FileWriter("out.txt"));

    String s = new StringBuilder(next()).reverse().toString();
    int[] cnt = new int[26];
    int[] need = new int[26];
    for (int i = 0; i < s.length(); i++)
      cnt[s.charAt(i) - 'a']++;
    for (int i = 0; i < 26; i++)
      need[i] = cnt[i] / 2;

    int in = 0;
    int i = 0;
    while (in < s.length() / 2) {
      int index = -1;
      while (true) {
        char c = s.charAt(i);
        if (need[c - 'a'] > 0 && (index < 0 || c < s.charAt(index))) {
          index = i;
        }
        cnt[c - 'a']--;
        if (cnt[c - 'a'] < need[c - 'a'])
          break;
        i++;
      }
      for (int j = index + 1; j < i + 1; j++) {
        cnt[s.charAt(j) - 'a']++;
      }
      need[s.charAt(index) - 'a']--;
      in++;
      System.out.print(s.charAt(index));
      i = index + 1;
    }
    System.out.println();
    pr.close();
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
