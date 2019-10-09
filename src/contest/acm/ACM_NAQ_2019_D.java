import java.io.*;
import java.util.*;

public class D {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    int N = readInt();
    int last = 0;
    HashSet<Integer> hs = new HashSet<Integer>();
    for (int i = 0; i < N; i++) {
      last = readInt();
      hs.add(last);
    }

    boolean goodJob = true;
    for (int i = 1; i <= last; i++) {
      if (!hs.contains(i)) {
        out.println(i);
        goodJob = false;
      }
    }

    if (goodJob) {
      out.println("good job");
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
