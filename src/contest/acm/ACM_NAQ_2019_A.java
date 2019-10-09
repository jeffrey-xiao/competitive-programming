import java.io.*;
import java.util.*;

public class A {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    int N = readInt();
    HashMap<String, Integer> hm = new HashMap<String, Integer>();

    for (int i = 0; i < N; i++) {
      int c = readCharacter() == 'T' ? 1 : 0;
      hm.put("" + (char)('A' + i), c);
    }

    String[] tokens = readLine().split(" ");
    Stack<Integer> s = new Stack<Integer>();
    for (int i = 0; i < tokens.length; i++) {
      if (tokens[i].equals("*")) {
        s.push(s.pop() & s.pop());
      } else if (tokens[i].equals("+")) {
        s.push(s.pop() | s.pop());
      } else if (tokens[i].equals("-")) {
        s.push(s.pop() == 1 ? 0 : 1);
      } else {
        s.push(hm.get(tokens[i]));
      }
    }
    out.println(s.pop() == 1 ? "T" : "F");

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
