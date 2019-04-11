package quora;

import java.io.*;
import java.util.*;

public class RelatedQuestions {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static double[] subtreeReadTime, totalReadTime, questionReadTime;
  static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
  static int N;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();

    questionReadTime = new double[N];
    subtreeReadTime = new double[N];
    totalReadTime = new double[N];

    for (int i = 0; i < N; i++) {
      questionReadTime[i] = readInt();
      adj.add(new ArrayList<Integer>());
    }

    for (int i = 0; i < N - 1; i++) {
      int u = readInt() - 1;
      int v = readInt() - 1;
      adj.get(u).add(v);
      adj.get(v).add(u);
    }

    computeSubtree(2, -1);
    computeTotal(2, -1);

    int minIndex = 0;
    for (int i = 1; i < N; i++) {
      if (totalReadTime[i] < totalReadTime[minIndex]) {
        minIndex = i;
      }
    }

    out.println(minIndex + 1);
    out.close();
  }

  // compute subtree read times
  static void computeSubtree(int u, int par) {
    subtreeReadTime[u] = questionReadTime[u];
    for (int v : adj.get(u)) {
      if (v == par) {
        continue;
      }
      computeSubtree(v, u);
      subtreeReadTime[u] += subtreeReadTime[v] / (adj.get(u).size() - (par == -1 ? 0 : 1));
    }
  }

  // compute total read times
  static void computeTotal(int u, int par) {
    if (par == -1) {
      totalReadTime[u] = subtreeReadTime[u];
    } else {
      int parSize = adj.get(par).size();
      int size = adj.get(u).size();
      if (size == 1 && parSize == 1) {
        totalReadTime[u] = questionReadTime[u] + questionReadTime[par];
      } else if (size == 1) {
        totalReadTime[u] = questionReadTime[u];
        totalReadTime[u] += ((totalReadTime[par] - questionReadTime[par]) - questionReadTime[u] / parSize) * parSize / (parSize - 1) + questionReadTime[par];
      } else if (parSize == 1) {
        totalReadTime[u] = questionReadTime[u];
        totalReadTime[u] += (subtreeReadTime[u] - questionReadTime[u]) * (size - 1) / size;
        totalReadTime[u] += questionReadTime[par] / size;
      } else {
        totalReadTime[u] = questionReadTime[u];
        totalReadTime[u] += (subtreeReadTime[u] - questionReadTime[u]) * (size - 1) / size;
        totalReadTime[u] += (((totalReadTime[par] - questionReadTime[par]) - subtreeReadTime[u] / parSize) * parSize / (parSize - 1) + questionReadTime[par]) / size;
      }
    }

    for (int v : adj.get(u)) {
      if (v == par) {
        continue;
      }
      computeTotal(v, u);
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
