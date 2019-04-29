package contest.hackercup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FHC_2015_Round_1_Autocomplete {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int cases = readInt();
    for (int k = 1; k <= cases; k++) {
      Trie root = new Trie(' ');
      int total = 0;
      for (int t = readInt(); t > 0; t--) {
        String c = next();
        total += find(c, 0, root);
        insert(c, 0, root);
      }
      out.printf("Case #%d: %d%n", k, total);
    }

    out.close();
  }

  static int find(String s, int i, Trie root) {
    Trie curr = root;
    while (true) {
      if (i == s.length())
        return i;
      int next = s.charAt(i) - 'a';
      if (curr.child[next] != null) {
        curr = curr.child[next];
        i++;
        continue;
      }
      return i + 1;
    }
  }

  static void insert(String s, int i, Trie root) {
    Trie curr = root;
    while (true) {
      if (i == s.length())
        return;
      int next = s.charAt(i) - 'a';
      if (curr.child[next] == null)
        curr.child[next] = new Trie(s.charAt(i));
      curr = curr.child[next];
      i++;
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

  static class Trie {
    Trie[] child = new Trie[26];
    int c;

    Trie(char c) {
      this.c = c;
      for (int x = 0; x < 26; x++)
        child[x] = null;
    }

    int find(String s, int i) {
      if (i == s.length())
        return i;
      int next = s.charAt(i) - 'a';
      if (child[next] != null)
        return child[next].find(s, i + 1);
      return i + 1;
    }

    void insert(String s, int i) {
      if (i == s.length())
        return;
      int next = s.charAt(i) - 'a';
      if (child[next] == null) {
        child[next] = new Trie(s.charAt(i));
      }
      child[next].insert(s, i + 1);
    }
  }
}
