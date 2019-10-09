import java.io.*;
import java.util.*;

public class B {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    long K = readInt();
    ArrayList<Long> ans = new ArrayList<Long>();
    for (long i = 1; i * i <= K; i++) {
      if (K % i == 0) {
        long j = K / i;
        if ((j + i) % 2 == 0) {
          long a = (j + i) / 2;
          long b = (j - i) / 2;
          if (b > 0) {
            ans.add(b * b);
            ans.add(-a * a);
          }
        }
      }
    }

    HashSet<Long> squares = new HashSet<Long>();
    for (long i = 1; i * i <= K; i++) {
      squares.add(i * i);
    }

    for (long i = 1; i * i <= K; i++) {
      long j = K - i * i;
      if (squares.contains(j)) {
        ans.add(-i * i);
      }
    }


    Collections.sort(ans);
    out.println(ans.size());
    for (int i = 0; i < ans.size(); i++) {
      if (i == 0) {
        out.print(ans.get(i));
      } else {
        out.print(" " + ans.get(i));
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
