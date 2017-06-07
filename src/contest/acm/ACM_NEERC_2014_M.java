package contest.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class ACM_NEERC_2014_M {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int N = readInt();
    ArrayList<HashMap<Character, Variable>> s = new ArrayList<HashMap<Character, Variable>>();
    s.add(new HashMap<Character, Variable>());
    for (int i = 0; i < N; i++) {
      char[] line = br.readLine().toCharArray();
      for (int j = 0; j < line.length; j++) {
        if ('a' <= line[j] && line[j] <= 'z') {
          s.get(s.size() - 1).put(line[j], new Variable(i + 1, j + 1));
          for (int k = s.size() - 2; k >= 0; k--) {
            if (s.get(k).containsKey(line[j])) {
              out.printf("%d:%d: warning: shadowed declaration of %c, the shadowed position is %d:%d\n", i + 1, j + 1, line[j], s.get(k).get(line[j]).line, s.get(k).get(line[j]).col);
              break;
            }
          }
        } else if (line[j] == '{') {
          s.add(new HashMap<Character, Variable>());
        } else if (line[j] == '}') {
          s.remove(s.size() - 1);
        }
      }
    }

    out.close();
  }

  static class Variable {
    int line, col;

    Variable (int line, int col) {
      this.line = line;
      this.col = col;
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
