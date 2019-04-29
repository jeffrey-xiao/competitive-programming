import java.io.*;
import java.util.*;

public class GCJ_2018_Round_1C_A {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T, N, L;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    for (int t = 1; t <= T; t++) {
      N = readInt();
      L = readInt();

      String[] words = new String[N];
      HashSet<String> s = new HashSet<String>();
      for (int i = 0; i < N; i++) {
        words[i] = next();
        s.add(words[i]);
      }
      boolean valid = false;
      for (int i = 0; i < N; i++) {
        for (int j = i + 1; j < N; j++) {
          if (!words[i].equals(words[j]) && !valid) {
            for (int k = 0; k < L; k++) {
              if (words[i].charAt(k) != words[j].charAt(k)) {
                String word = words[i].substring(0, k) + words[j].charAt(k) + words[i].substring(k + 1, L);
                if (!s.contains(word)) {
                  out.printf("Case #%d: %s%n", t, word);
                  valid = true;
                  break;
                }
              }
            }
          }
        }
      }
      if (!valid) {
        out.printf("Case #%d: -%n", t);
      }
    }

    out.close();
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
