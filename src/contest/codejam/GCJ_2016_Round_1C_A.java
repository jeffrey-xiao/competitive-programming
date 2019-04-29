import java.io.*;
import java.util.*;

public class GCJ_2016_Round_1C_A {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;
  
  static int T, N;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    for (int t = 1; t <= T; t++) {
      N = readInt();
      PriorityQueue<State> pq = new PriorityQueue<State>();
      for (int i = 0; i < N; i++) {
        pq.offer(new State((char)(i + 'A'), readInt()));
      }

      out.printf("Case #%d: ", t);
      while (pq.size() > 2) {
        State s = pq.poll();
        out.print(s.party + " ");
        if (s.count > 1) {
          pq.offer(new State(s.party, s.count - 1));
        }
      }
      int count = pq.peek().count;
      char a = pq.poll().party;
      char b = pq.poll().party;
      for (int i = 0; i < count; i++) {
        out.print(a + "" + b);
        if (i != count - 1) {
          out.print(" ");
        }
      }
      out.println();
    }

    out.close();
  }

  static class State implements Comparable<State> {
    char party;
    int count;

    State(char party, int count) {
      this.party = party;
      this.count = count;
    } 

    @Override
    public int compareTo(State s) {
      return s.count - count;
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
}
