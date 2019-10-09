import java.io.*;
import java.util.*;

public class K {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    char[] in = readLine().toCharArray();
    HashMap<Character, TreeSet<Integer>> hm = new HashMap<>();
    for (char j = 'a'; j <= 'z'; j++) {
      hm.put(j, new TreeSet<>());
    }

    for (int i = 0; i < in.length; i++) {
      hm.get(in[i]).add(i);
    }

    int ans = 0;
    for (int i = 0; i < in.length; i++) {
      Integer nextOccurrence = hm.get(in[i]).higher(i);
      for (char j = 'a'; j <= 'z'; j++) {
        if (in[i] == j) {
          continue;
        }
        Integer nextDiff = hm.get(j).higher(i);
        if (nextDiff != null && (nextOccurrence == null || nextDiff < nextOccurrence)) {
          ans++;
        }
      }
    }

    out.println(ans);
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
