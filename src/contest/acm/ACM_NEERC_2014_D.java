package contest.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class ACM_NEERC_2014_D {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int N = readInt();
    long M = readLong();

    Server[] s = new Server[N];

    for (int i = 0; i < N; i++) {
      s[i] = new Server(readLong(), readInt(), i);
    }

    Arrays.sort(s);
    long total = 0;

    int i = 0;
    ArrayList<Integer> used = new ArrayList<Integer>();
    ArrayList<Server> poss = new ArrayList<Server>();
    int lowVoltage = 0;
    for (; i < N && total < M; i++) {
      total += s[i].capacity;
      if (s[i].type == 1) {
        used.add(s[i].index);
        lowVoltage++;
      } else
        poss.add(s[i]);
    }
    Collections.sort(poss);
    Collections.reverse(poss);
    int j = 0;
    for (; i < N && j < poss.size(); i++) {
      if (s[i].type == 0) {
        continue;
      }
      if (total - (poss.get(j).capacity - s[i].capacity) < M)
        break;
      total -= poss.get(j).capacity - s[i].capacity;
      used.add(s[i].index);
      lowVoltage++;
      j++;
    }
    for (; j < poss.size(); j++)
      used.add(poss.get(j).index);

    out.printf("%d %d%n", used.size(), lowVoltage);
    for (int index : used)
      out.print(index + 1 + " ");
    out.println();
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

  static class Server implements Comparable<Server> {
    Long capacity;
    int type, index;

    Server(long capacity, int type, int index) {
      this.capacity = capacity;
      this.type = type;
      this.index = index;
    }

    @Override
    public int compareTo(Server o) {
      return o.capacity.compareTo(capacity);
    }
  }
}
