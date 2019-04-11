package contest.woburn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Woburn_Challenge_2015_Shootout {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, M;

  static int[] hench, toSorted;
  static State[] door;
  static boolean[] open;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    M = readInt();

    hench = new int[N];
    door = new State[M];
    toSorted = new int[M];
    open = new boolean[M];

    for (int i = 0; i < N; i++)
      hench[i] = readInt();
    Arrays.sort(hench);

    TreeSet<State> ts = new TreeSet<State>();
    for (int i = 0; i < N; i++) {
      ts.add(new State(hench[i], i + 1));
    }

    for (int i = 0; i < M; i++) {
      door[i] = new State(readInt(), i);
    }
    Arrays.sort(door);

    for (int i = 0; i < M; i++)
      toSorted[door[i].cnt] = i;
    int index = 0;
    for (int i = 0; i < M; i++) {
      open[toSorted[i]] = true;
      while (index < M && open[index])
        index++;
      if (index == M)
        out.println(N);
      else {
        State s = ts.floor(door[index]);
        if (s == null)
          out.println(0);
        else
          out.println(s.cnt);
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

  static class State implements Comparable<State> {
    int pos, cnt;

    State(int pos, int cnt) {
      this.pos = pos;
      this.cnt = cnt;
    }

    @Override
    public int compareTo(State o) {
      return pos - o.pos;
    }
  }
}
