import java.io.*;
import java.util.*;

public class C {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    int P = readInt();
    int D = readInt();

    HashMap<Integer, Long> aVotes = new HashMap<>(), bVotes = new HashMap<>();
    for (int i = 0; i < P; i++) {
      int d = readInt();
      long a = readInt();
      long b = readInt();
      aVotes.put(d, aVotes.getOrDefault(d, 0L) + a);
      bVotes.put(d, bVotes.getOrDefault(d, 0L) + b);
    }

    long total = 0;
    long wastedA = 0;
    long wastedB = 0;
    for (int i = 1; i <= D; i++) {
      long totalVotes = aVotes.get(i) + bVotes.get(i);
      long wastedAVotes = 0;
      long wastedBVotes = 0;
      total += totalVotes;
      if (aVotes.get(i) > bVotes.get(i)) {
        out.print("A ");
        wastedAVotes = aVotes.get(i) - (totalVotes / 2 + 1);
        wastedBVotes = bVotes.get(i);;
      } else {
        out.print("B ");
        wastedBVotes = bVotes.get(i) - (totalVotes / 2 + 1);
        wastedAVotes = aVotes.get(i);;
      }
      wastedA += wastedAVotes;
      wastedB += wastedBVotes;
      out.println(wastedAVotes + " " +wastedBVotes);
    }
    out.println(Math.abs(1.0 * (wastedA - wastedB) / total));

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
