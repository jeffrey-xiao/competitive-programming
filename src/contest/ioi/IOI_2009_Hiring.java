package contest.ioi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class IOI_2009_Hiring {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int N = readInt();
    long W = readLong();
    Person[] p = new Person[N];
    for (int i = 0; i < N; i++) {
      p[i] = new Person(readInt(), readInt(), i);
    }
    Arrays.sort(p);
    PriorityQueue<Person> pq = new PriorityQueue<Person>(N + 10, new Comparator<Person>() {
      @Override
      public int compare(Person o1, Person o2) {
        return o2.quality - o1.quality;
      }
    });
    boolean[] used = new boolean[N];
    int total = 0, best = 0, bestIndex = 0;
    double bestCost = Long.MAX_VALUE;
    for (int i = 0; i < N; i++) {
      pq.offer(p[i]);
      total += p[i].quality;
      double max = W / p[i].rate;
      while (total > max) {
        total -= pq.poll().quality;
      }
      int sz = pq.size();
      double cost = total * p[i].rate;
      if (sz > best || (sz == best && cost < bestCost)) {
        best = sz;
        bestIndex = i;
        bestCost = cost;
      }
    }
    pq.clear();
    total = 0;
    for (int i = 0; i <= bestIndex; i++) {
      pq.offer(p[i]);
      used[p[i].index] = true;
      total += p[i].quality;
    }
    double max = W / p[bestIndex].rate;
    while (total > max) {
      Person removed = pq.poll();
      total -= removed.quality;
      used[removed.index] = false;
    }
    ps.println(best);
    for (int i = 0; i < N; i++)
      if (used[i])
        ps.println(i + 1);
    ps.close();
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

  static class Person implements Comparable<Person> {
    int pay, quality, index;
    Double rate;

    Person(int pay, int quality, int index) {
      this.pay = pay;
      this.quality = quality;
      this.rate = ((double) (pay)) / ((double) (quality));
      this.index = index;
    }

    @Override
    public int compareTo(Person o) {
      return pay * o.quality - o.pay * quality;
    }
  }
}
