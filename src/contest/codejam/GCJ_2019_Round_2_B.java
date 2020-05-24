package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2019_Round_2_B {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T;
  static int TOTAL_DAYS = 100;
  static int TOTAL_VASES = 20;
  static int BAD_VASES = 12;
  static int TOKENS_PER_BAD_VASE = 5;

  static int BAD_VASE_ITERS = BAD_VASES * TOKENS_PER_BAD_VASE;
  static int SABOTAGE_ITERS = 4;

  static int[] bins;
  static ArrayList<HashSet<Integer>> knownNumbers;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    for (int t = 0; t < T; t++) {
      knownNumbers = new ArrayList<>();
      for (int i = 0; i < TOTAL_VASES; i++) {
        knownNumbers.add(new HashSet<>());
      }

      for (int i = 0; i < BAD_VASES; i++) {
        for (int j = 0; j < TOKENS_PER_BAD_VASE; j++) {
          int d = readInt();
          addToken(i, d);
        }
      }

      PriorityQueue<State> pq = new PriorityQueue<State>();
      ArrayList<State> candidates = new ArrayList<State>();
      for (int i = 0; i < TOTAL_VASES; i++) {
        readInt();
        out.printf("%d %d%n", i + 1, 0);
        out.flush();

        int N = readInt();
        boolean valid = true;

        for (int j = 0; j < N; j++) {
          int number = readInt();
          if (knownNumbers.contains(number)) {
            valid = false;
          }
          knownNumbers.get(i).add(number);
        }

        if (valid) {
          candidates.add(new State(N, i));
        }
        pq.offer(new State(N, i));
      }
      Collections.sort(candidates);
      int index1 = candidates.get(0).index;
      int index2 = candidates.get(1).index;

      for (int i = 0; i < TOTAL_DAYS - BAD_VASE_ITERS - SABOTAGE_ITERS - TOTAL_VASES - 3; i++) {
        State curr = pq.poll();
        if (curr.index == index1 || curr.index == index2) {
          i--;
          continue;
        }

        readInt();
        addToken(curr.index, 1);
        pq.offer(new State(curr.count + 1, curr.index));
      }

      readInt();
      out.printf("%d %d%n", index1 + 1, 0);
      out.flush();
      int count1 = readInt();
      for (int i = 0; i < count1; i++) {
        readInt();
      }

      readInt();
      out.printf("%d %d%n", index2 + 1, 0);
      out.flush();
      int count2 = readInt();
      for (int i = 0; i < count2; i++) {
        readInt();
      }

      if (count1 <= count2) {
        for (int i = 0; i < SABOTAGE_ITERS; i++) {
          readInt();
          addToken(index2, 1);
        }
        readInt();
        addToken(index1, 100);
      } else {
        for (int i = 0; i < SABOTAGE_ITERS; i++) {
          readInt();
          addToken(index1, 1);
        }
        readInt();
        addToken(index2, 100);
      }
    }
    out.close();
  }

  static void addToken(int index, int number) throws IOException {
    out.printf("%d %d%n", index + 1, number);
    out.flush();
  }

  static int getUnusedToken(int index) {
    for (int i = 1; i <= TOTAL_DAYS; i++) {
      if (!knownNumbers.contains(i)) {
        return i;
      }
    }
    return 1;
  }

  static class State implements Comparable<State> {
    int count, index;
    State(int count, int index) {
      this.count = count;
      this.index = index;
    }

    @Override
    public int compareTo(State s) {
      if (count == s.count) {
        return s.index - index;
      }
      return count - s.count;
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
