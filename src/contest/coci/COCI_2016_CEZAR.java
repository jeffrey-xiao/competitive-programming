package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;
import java.util.StringTokenizer;

public class COCI_2016_CEZAR {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N;
  static ArrayList<HashSet<Integer>> adj = new ArrayList<HashSet<Integer>>();
  static Stack<Integer> s = new Stack<Integer>();
  static boolean[] vis = new boolean[26], currVis = new boolean[26];
  static boolean valid = true;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    for (int i = 0; i < 26; i++)
      adj.add(new HashSet<Integer>());

    N = readInt();

    Word[] w = new Word[N];
    for (int i = 0; i < N; i++)
      w[i] = new Word(next());
    for (int i = 0; i < N; i++)
      w[readInt() - 1].index = i;

    Arrays.sort(w);
    for (int i = 0; i < N; i++) {
      for (int j = i + 1; j < N; j++) {
        int index = -1;
        for (int k = 0; k < Math.min(w[i].s.length(), w[j].s.length()); k++) {
          if (w[i].s.charAt(k) != w[j].s.charAt(k)) {
            index = k;
            break;
          }
        }
        if (index == -1 && w[i].s.length() > w[j].s.length()) {
          out.println("NE");
          out.close();
        } else if (index != -1) {
          adj.get(w[i].s.charAt(index) - 'a').add(w[j].s.charAt(index) - 'a');
        }
      }
    }

    for (int i = 25; i >= 0; i--)
      if (!vis[i])
        dfs(i);

    if (!valid)
      out.println("NE");
    else {
      out.println("DA");
      char[] ret = new char[26];
      int index = 0;
      while (!s.isEmpty()) {
        ret[s.pop()] = (char)(index++ + 'a');
      }
      out.println(ret);
    }

    out.close();
  }

  static void dfs (int u) {
    if (currVis[u]) {
      valid = false;
      return;
    }
    currVis[u] = true;
    for (int v : adj.get(u))
      if (!vis[v])
        dfs(v);
    currVis[u] = false;
    vis[u] = true;
    s.push(u);
  }

  static class Word implements Comparable<Word> {
    StringBuilder s;
    int index;

    Word (String s) {
      this.s = new StringBuilder(s);
    }

    @Override
    public int compareTo (Word w) {
      return index - w.index;
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
